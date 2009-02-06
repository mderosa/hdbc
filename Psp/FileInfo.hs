
module FileInfo where

import Text.Regex
import Control.Monad

multiLnCmntBegin = mkRegex "(/\\*|\\{-)"
multiLnCmntEnd = mkRegex "(\\*/|-\\})"
sngLnCmnt = mkRegex "(--|//)"
emptyLn = mkRegex "^[[:space:]]*$"

data ReadState = AtCode | AtSingleCmnt | InMultiCmnt | AtEndMultiCmnt | AtEmpty deriving Eq

linesOfCode :: [String] -> ReadState -> Integer -> Integer
linesOfCode [] state cnt = cnt
linesOfCode (l:ls) state cnt = let isCommentBegin line = matchRegex multiLnCmntBegin line /= Nothing
                                   isCommentEnd line = matchRegex multiLnCmntEnd line /= Nothing
                                   isSingleComment line = matchRegex sngLnCmnt line /= Nothing
                                   isEmpty line = matchRegex emptyLn line /= Nothing
                                   nextState state line = if state == InMultiCmnt
                                                          then if isCommentEnd line
                                                               then AtEndMultiCmnt
                                                               else InMultiCmnt
                                                          else if isCommentBegin line
                                                               then InMultiCmnt
                                                               else if isSingleComment line
                                                                    then AtSingleCmnt
                                                                    else if isEmpty line
                                                                         then AtEmpty
                                                                         else AtCode
                               in
    case (nextState state l) of
      AtCode -> linesOfCode ls AtCode (cnt + 1)
      st -> linesOfCode ls st cnt

type FileLineCounter = FilePath -> IO (Integer)
countFileLines :: FileLineCounter
countFileLines file = do 
     content <- readFile file
     return (linesOfCode (lines content) AtEmpty 0)