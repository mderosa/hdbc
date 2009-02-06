
module Log where

import Structures
import DirSystem
import FileInfo
import Constants
import Data.IORef
import System.IO
import Text.Regex
import Control.Monad

log :: RecordType -> (IORef Registry) -> Controls -> IO ()
log rt reg cntls = do
      r <- readIORef reg
      timeStats <- programLinesString r accumulateFilesByRegex countFileLines
      stageStats <- uiStateString rt r cntls
      let file = projectDataFile r
      appendFile file (stageStats ++ timeStats ++ "\n")

uiStateString :: (Serializable a, Serializable b, Serializable c) => a -> b -> c -> IO String
uiStateString recType registry cntrls = do rtStr <- serialize recType
                                           regStr <- serialize registry
                                           cntStr <- serialize cntrls
                                           return (rtStr ++ regStr ++ cntStr)

programLinesString :: Registry -> FileAccumulator -> FileLineCounter -> IO String
programLinesString r fileAccumulator lineCounter = let baseDirectory = projectRoot r in
  do files <- fileAccumulator [baseDirectory] (mkRegex (codeFileRgx))
     let codeFiles = filter codeFileFilter files
         testFiles = filter (not . codeFileFilter) files
     totalCodeLines <- foldM (\n f -> do 
                            i <- lineCounter f 
                            return (i + n)) 0 codeFiles
     totalTestLines <- foldM (\n f -> do 
                            i <- lineCounter f 
                            return (i + n)) 0 testFiles
     return (sep ++ (show totalCodeLines) ++ sep ++ (show totalTestLines))

codeFileFilter :: String -> Bool
codeFileFilter s = let rgx = mkRegex "Test" in
                      ((matchRegex rgx s) == Nothing)

projectDataFile :: Registry -> String
projectDataFile r = (projectRoot r) ++ "/" ++ dataFile

