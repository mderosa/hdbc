
module Main where

import System.Directory
import Text.Regex
import Test.HUnit
import JspValidator
import DeploymentValidator
import DirSystem
import System.IO
import Definitions

main :: IO () 
main = do
    hSetBuffering stdout LineBuffering
    projectDir <- queryProjectDirectory
    runJspValidation projectDir
    runDeploymentDescValidation projectDir
    putStrLn "\npress the 'enter' key to finish:\n"
    aChar <- getChar
    return ()

runJspValidation :: String -> IO ()
runJspValidation projectDir = 
    do let jspRegex = mkRegex "[[:alnum:]]+\\.jsp"
       jspFiles <- accumulateFilesByRegex [projectDir] jspRegex
       sequence_ (map (runSingleFileTest JspValidator.allErrors) jspFiles)

runDeploymentDescValidation :: String -> IO ()
runDeploymentDescValidation projectDir =
    do let portletDesc = mkRegex "portlet.xml"
       deploymentDescriptors <- accumulateFilesByRegex [projectDir] portletDesc
       sequence_ (map (runSingleFileTest DeploymentValidator.allErrors) deploymentDescriptors)
       
runSingleFileTest :: (FileLines -> [ErrorMessage]) -> String -> IO ()
runSingleFileTest errorCollectingFn f = do
    contents <- readFile f
    putStrLn ("testing file, " ++ f ++ "...")
    let fileLines = lines contents
    sequence_ (map putStrLn (errorCollectingFn fileLines))
    
queryProjectDirectory :: IO String
queryProjectDirectory = do
    putStrLn "Enter the path to the portlet project you wish to test (ex: C:\\CVS_HOME\\SP_FS\\Infoboard): "
    path <- getLine
    exists <- doesDirectoryExist path
    if exists then return path else do putStrLn "Path not found"
                                       queryProjectDirectory


