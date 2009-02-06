
module FileInfoTest where

import Test.HUnit
import Text.Regex
import FileInfo

runTests :: IO Counts
runTests = runTestTT allTests

allTests :: Test
allTests = TestList [TestLabel "testLinesOfCode1" testLinesOfCode1,
                     TestLabel "testLinesOfCode2" testLinesOfCode2,
                     TestLabel "testLinesOfCode3" testLinesOfCode3,
                     TestLabel "testMultLnCmntBegin1" testMultLnCmntBegin1,
                     TestLabel "testMultLnCmntBegin2" testMultLnCmntBegin2,
                     TestLabel "testSngLnCmnt" testSngLnCmnt,
                     TestLabel "testEmptyLn1" testEmptyLn1]

-- test that we are not counting empty spaces
testLinesOfCode1 :: Test
testLinesOfCode1 = TestCase (do 
     let input = ["line1","","line2"]
     assertEqual "expect 2 lines of code" 2 (linesOfCode input AtEmpty 0))

-- test that we are not counting haskell comments
testLinesOfCode2 :: Test
testLinesOfCode2 = TestCase (do
     let input = ["-- and some comments","some code","some more code",
                  "{- and a comment","and more comments"," end of comment -}"]
     assertEqual "expect 2 lines of code" 2 (linesOfCode input AtEmpty 0))

-- test that we are not counting java comments
testLinesOfCode3 :: Test
testLinesOfCode3 = TestCase (do
     let input = ["// and some comments","some code","some more code",
                 "/* and a comment","and more comments","end of comment"," */ "]
     assertEqual "expect 2 lines of code" 2 (linesOfCode input AtEmpty 0))

-- test java comments
testMultLnCmntBegin1 :: Test
testMultLnCmntBegin1 = TestCase (do
     assertBool "expect match" ((matchRegex multiLnCmntBegin " /* ddnddndd") /= Nothing))

-- test haskell comments
testMultLnCmntBegin2 :: Test
testMultLnCmntBegin2 = TestCase (do
     assertBool "expect match" ((matchRegex multiLnCmntBegin "{- ddnddndd") /= Nothing))

-- test java single comments
testSngLnCmnt :: Test
testSngLnCmnt = TestCase (do
     assertBool "expect match" ((matchRegex sngLnCmnt "//this is a comnt") /= Nothing))

--test no content, spaces, tab
testEmptyLn1 :: Test
testEmptyLn1 = TestCase (do
     assertBool "empty should match" ((matchRegex emptyLn "") /= Nothing)
     assertBool "spaces should match" ((matchRegex emptyLn "   ") /= Nothing)
     assertBool "tab should match" ((matchRegex emptyLn "\t") /= Nothing))
