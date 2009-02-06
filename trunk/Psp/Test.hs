
module Test where

import FileInfoTest
import LogTest
import SpecificationsTest
import WidgetStateTest
import Test.HUnit
import Text.Regex

main :: IO Counts
main = do
     FileInfoTest.runTests
     runTestTT LogTest.allTests
     runTestTT Test.allTests
     runTestTT SpecificationsTest.allTests
     runTestTT WidgetStateTest.allTests

allTests :: Test
allTests = TestList[TestLabel "testFileExtensionRegex" testFileExtensionRegex]

testFileExtensionRegex :: Test
testFileExtensionRegex = TestCase (do
     let regex = mkRegex "\\.jsp$"
     assertBool "shouldnt match 1" (matchRegex regex "thdkkdjsp" == Nothing)
     assertBool "shouldnt match 2" (matchRegex regex "th.jspttt" == Nothing)
     assertBool "should match 1" (matchRegex regex "file.jsp" /= Nothing))

