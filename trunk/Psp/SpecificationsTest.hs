
module SpecificationsTest where

import Specifications
import Structures
import Test.HUnit

allTests :: Test
allTests = TestList [TestLabel "testStateControlTableAccess" testStateControlTableAccess,
                     TestLabel "testOperatorPlusGtGt" testOperatorPlusGtGt,
                     TestLabel "testOperatorPlusAndAnd" testOperatorPlusAndAnd]

testStateControlTableAccess :: Test
testStateControlTableAccess = TestCase (do
     assertBool "" True
     )

testOperatorPlusGtGt :: Test
testOperatorPlusGtGt = TestCase (do
    let (ls1, ls2) = OpenProjectItem +>> [(S, Any), (S, Any)]
    assertEqual "expecting list length of one" 1 (length ls1)
    assertEqual "expecting list lenght of two" 2 (length ls2))

testOperatorPlusAndAnd :: Test
testOperatorPlusAndAnd = TestCase (do
    let (a, b) = ([1,2,3], [1,2,3]) +&& ([1], [1])
    assertEqual "expecting length 4" 4 (length a)
    assertEqual "expecting length 4" 4 (length b))