
module WidgetStateTest where

import WidgetState
import Specifications
import Structures
import Test.HUnit

allTests :: Test
allTests = TestList [TestLabel "testSensitivity" testSensitivity,
                     TestLabel "testLabel" testLabel]

testSensitivity :: Test
testSensitivity = TestCase (do
     assertEqual "should pass" True (sensitivity OpenProjectItem ProjectInactive controlStateTable)
     assertEqual "should pass" False (sensitivity StageComboBox ProjectInactive controlStateTable))

testLabel :: Test
testLabel = TestCase (do
     assertEqual "expecting Any" Any (label DescriptionEntryBox ProjectInactive controlStateTable)
     assertEqual "expecting a String" (D "Record") (label RecordButton (ProjectActive Waiting) controlStateTable))