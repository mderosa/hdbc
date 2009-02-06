
module Test.DirSystemTest where

import Test.HUnit
import System.Cmd
import System.Exit
import DirSystem

runAllTests :: IO Counts
runAllTests = runTestTT (
    TestList [TestLabel "testSubDirectories" testSubDirectories,
             TestLabel "testSubFiles" testSubFiles,
             TestLabel "testHasDirEndingWith" testHasDirEndingWith]
    )

testSubDirectories :: Test
testSubDirectories = TestCase (do
    filePaths <- subDirectories (\x -> return [".", "..", "ADir", "AFile.txt"])
                               (\x -> if x == "C:\\Temp\\ADir" then return True else return False)
                               "C:\\Temp"
    assertEqual "expecting just ADir to come back" 1 (length filePaths)
    assertEqual "return should be in full path form" "C:\\Temp\\ADir" (head filePaths)
    )

testSubFiles :: Test
testSubFiles = TestCase (do
    filePaths <- subFiles (\x -> return [".", "..", "ADir", "AFile.txt"])
                               (\x -> if x == "C:\\Temp\\AFile.txt" then return True else return False)
                               "C:\\Temp"
    assertEqual "expecting just AFile.txt to come back" 1 (length filePaths)
    assertEqual "return should be in full path form" "C:\\Temp\\AFile.txt" (head filePaths)
    )

testHasDirEndingWith :: Test
testHasDirEndingWith = TestCase (do
    assertEqual "no match case" False (endsWithName "yo" "./ONE")
    assertEqual "is matching case" True (endsWithName "yo" "./yo")
    assertEqual "no match case 2" False (endsWithName "yo" "./yo/mama")
    assertEqual "is matching case 2" True (endsWithName ".meta" "./a/b/.meta"))

