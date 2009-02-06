
module Test.ValidationTest where

import Validation
import Test.HUnit
import Text.Regex

allTests :: Test
allTests = TestList [TestLabel "testValidateLine" testValidateLine,
                     TestLabel "testValidateAnd" testValidateAnd,
                     TestLabel "testAddValidationError" testAddValidationError,
                     TestLabel "testAddValidationError2" testAddValidationError2,
                    TestLabel "testValidateAtLeastOneLineMatches" testValidateAtLeastOneLineMatches,
                    TestLabel "testValidateAtLeastOneLineMatches2" testValidateAtLeastOneLineMatches2]

testValidateLine :: Test
testValidateLine = let Vldtion c e v = validateLine (mkRegex "aa") (mkRegex "bb") "aaaaaa"
         in TestCase (do assertBool "expecting no match for bb" (not v)
                         assertEqual "expecting c = 1" 1 c)

testValidateAnd :: Test
testValidateAnd = let Vldtion c e v = do aa <- validateLine (mkRegex "aa") (mkRegex "bb") "aaaaaa"
                                         bb <- validateLine (mkRegex "aa") (mkRegex "bb") "aaaaaa"
                                         cc <- validateAnd aa bb
                                         dd <- validateLine (mkRegex "aa") (mkRegex "bb") "aaabba"
                                         validateAnd cc dd
         in TestCase (do assertBool "expecting overall false result" (not v)
                         assertEqual "expecting c = 3" 3 c
                         assertEqual "expecting no errors" 0 (length e)
                         )

testAddValidationError :: Test
testAddValidationError = let Vldtion c e v = do aa <- validateLine (mkRegex "aa") (mkRegex "bb") "aaaaaa"
                                                addValidationError "this is an error\n" "aaaaa" False
                                                bb <- validateLine (mkRegex "aa") (mkRegex "bb") "aaaaaa"
                                                cc <- validateAnd aa bb
                                                dd <- validateLine (mkRegex "aa") (mkRegex "bb") "aaabba"
                                                validateAnd cc dd
         in TestCase (do assertBool "expecting overall false result" (not v)
                         assertEqual "expecting c = 3" 3 c
                         assertEqual "expecting one error" 1 (length e)
                         assertEqual "expecting error listing"
                                     "this is an error\nline is: aaaaa\n" (head e)
                         )

testAddValidationError2 :: Test
testAddValidationError2 = let Vldtion c e v = do aa <- validateLine (mkRegex "aa") (mkRegex "bb") "aaaaaa"
                                                 addValidationError "this is error 1\n" "aaaaaa" aa
                                                 bb <- validateLine (mkRegex "aa") (mkRegex "bb") "aaaaaa"
                                                 addValidationError "this is error 2\n" "aaaaaa" bb
                                                 cc <- validateAnd aa bb
                                                 dd <- validateLine (mkRegex "aa") (mkRegex "bb") "aaaaaa"
                                                 addValidationError "this is error 3\n" "aaaaaa" aa
                                                 validateAnd cc dd
         in TestCase (do assertBool "expecting overall false result" (not v)
                         assertEqual "expecting c = 3" 3 c
                         assertEqual "expecting three errors" 3 (length e)
                         assertEqual "expecting line number indicator in error msg"
                                     "this is error 1\nline is: aaaaaa\n" (e!!0)
                         assertEqual "expecting line number indicator in error msg"
                                     "this is error 2\nline is: aaaaaa\n" (e!!1)
                         assertEqual "expecting line number indicator in error msg"
                                     "this is error 3\nline is: aaaaaa\n" (e!!2)
                         )

testValidateAtLeastOneLineMatches :: Test
testValidateAtLeastOneLineMatches = 
        let Vldtion c e v = validateAtLeastOneLineMatches (mkRegex "a", mkRegex "a", "yo") []
        in TestCase (do 
           assertEqual "expecting false result" False v
           assertEqual "expecting one error" 1 (length e))

testValidateAtLeastOneLineMatches2 :: Test
testValidateAtLeastOneLineMatches2 =
        let lines = ["line1", "line2", "bonus", "line3"]
            Vldtion c e v = validateAtLeastOneLineMatches (mkRegex "bonus", mkRegex "bonus", "yo") lines
        in TestCase (do assertEqual "expecting to find a match" True v
                        assertEqual "expecting empty error list" [] e)
                      