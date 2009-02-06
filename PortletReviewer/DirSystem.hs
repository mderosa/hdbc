
module DirSystem where

import Control.Monad
import Text.Regex
import System.Directory
import System.IO
import System.Info
import System.Exit
import System.Cmd

type FileName = String
type DirectoryPath = String

directorySeperator :: String
directorySeperator = if os == "mingw32" then "\\" else "/"

fullDirPaths :: FilePath -> [FilePath] -> [FilePath]
fullDirPaths base [] = []
fullDirPaths base exts = map (\x -> base ++ directorySeperator ++  x) exts

isDirectory :: FilePath -> IO Bool
isDirectory dir = if (foldl (\x y -> y) 'a' dir) == '.' then return False else doesDirectoryExist dir

subDirectories :: (FilePath -> IO [FilePath]) 
               -> (FilePath -> IO Bool) 
               -> FilePath 
               -> IO [FilePath]
subDirectories dirContentsCmd dirTestFn baseDir = do
  	contents <- catch (dirContentsCmd baseDir) (\e -> return [])
        filterM dirTestFn (fullDirPaths baseDir contents)

subFiles :: (FilePath -> IO [FilePath])
            -> (FilePath -> IO Bool)
            -> FilePath 
            -> IO [FilePath]
subFiles dirContentsCmd fileTestFn baseDir = do
  content <- catch (dirContentsCmd baseDir) (\e -> return [])
  filterM fileTestFn (fullDirPaths baseDir content)

{-- 
returns all the directories given in the input list plus any of
their subdirectoies
--}
accumulateDirectories :: [FilePath] -> IO [FilePath]
accumulateDirectories [] = return []
accumulateDirectories (x:xs) = do
	subDirs <- subDirectories getDirectoryContents isDirectory x
	others <- accumulateDirectories (subDirs ++ xs)
	return (x : (others))

{--
returns all of the files that match the given regex given a set
of starting directories
--}
accumulateFilesByRegex :: [FilePath] -> Regex -> IO [FilePath]
accumulateFilesByRegex [] regex = return []
accumulateFilesByRegex (p:ps) regex = do 
       subDirs <- subDirectories getDirectoryContents isDirectory p
       matchingFiles <- subFiles getDirectoryContents fileFilter p
       otherMatchingFiles <- accumulateFilesByRegex (subDirs ++ ps) regex
       return (matchingFiles ++ otherMatchingFiles)
    where fileFilter fileName = do if (matchRegex regex fileName) == Nothing 
                                                                      then return False 
                                                                      else doesFileExist fileName

accumulateDirsNamed :: [FilePath] -> String -> IO [FilePath]
accumulateDirsNamed baseDirList name = do
    allDirs <- accumulateDirectories baseDirList
    return (filter (endsWithName name) allDirs)
  
endsWithName :: String -> String -> Bool
endsWithName name input = let regex = mkRegex (name ++ "$")
    in (matchRegex regex input) /= Nothing

queryDirLocation :: String -> IO String
queryDirLocation locQuestion = do
        hSetBuffering stdout LineBuffering
        putStrLn ""
	putStr locQuestion
	projFilesLoc <- getLine
	exists <- doesDirectoryExist projFilesLoc
	if exists then return projFilesLoc else do putStrLn "Unable to find given directory"
                                                   queryDirLocation locQuestion