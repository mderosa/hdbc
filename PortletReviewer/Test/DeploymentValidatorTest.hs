
module Test.DeploymentValidatorTest where

import DeploymentValidator
import Test.HUnit
import Definitions

runAllTests :: IO Counts
runAllTests = runTestTT (
    TestList [TestLabel "testValidateCacheEnabled" testValidateCacheEnabled]
    )

testValidateCacheEnabled :: Test
testValidateCacheEnabled = TestCase (do
    let fileContents = ["<portlet>", "<expiration-cache>300</expiration-cache>", "</portlet"] 
    assertEqual "expected to pass test -- cache is configured" 0 (length (validateCacheEnabled fileContents))
    )
        