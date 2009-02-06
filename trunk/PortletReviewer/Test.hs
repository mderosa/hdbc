
module Test where

import Test.HUnit
import Test.DirSystemTest
import Test.JspValidatorTest
import Test.ValidationTest

runTests :: IO Counts
runTests = do 
    Test.DirSystemTest.runAllTests
    Test.JspValidatorTest.runAllTests
    runTestTT Test.ValidationTest.allTests
