
module StructuresTest where

import Structures
import Test.HUnit
import Data.Array

allTests :: Test
allTests = TestList [TestLabel "testStateOrdering" testStateOrdering,
                     TestLabel "testRange" testRange]

testStateOrdering :: Test
testStateOrdering = TestCase (do
     assertBool "ProjectInactive is greater than everyone" (ProjectInactive "" > ProjectActive "" Waiting)
     assertEqual "should be false" False (ProjectActive "" Paused > ProjectActive "" Waiting)
     assertBool "should be true" (ProjectActive "" (Recording "") <= ProjectActive "" (Recording ""))
                             )
testRange :: Test
testRange = TestCase (do
    assertEqual "expect no elements" 0 (length(range (ProjectActive "root" (Recording "t0"), ProjectInactive "")))
    assertEqual "just two elements" 2 (length(range (ProjectActive "" Waiting, ProjectActive "" Paused)))
                )