
module LogTest where

import DirSystem
import FileInfo
import Log
import Test.HUnit
import Structures
import Text.Regex

allTests :: Test
allTests = TestList [TestLabel "testUiStateString" testUiStateString,
                     TestLabel "testUiStateString2 "testUiStateString2,
                     TestLabel "testProgramLinesString" testProgramLinesString]

data Mock = MockCntrls deriving Show

instance Serializable Mock where
    serialize m = return (sep ++ (show m))

fields :: String -> [String]
fields cs = let isSep c = ',' == c 
                (w, r) = break isSep cs
                assemble ls (y:ys) = ls : fields ys
                assemble ls "" = [ls] in
            assemble w r
            
testUiStateString :: Test
testUiStateString = TestCase (do
     actual <- uiStateString Error 
                              (Rgstry (ProjectActive Recording) "/usr/Project/Test" "1:00PM")
                              MockCntrls
     let expected = ["Error", "1:00PM","some time", "MockCntrls"]
         actual' = fields actual
     assertEqual "expecting length 4" 4 (length actual')
     assertEqual "getting record type" "Error" (actual'!!0)
     assertEqual "getting start time" "1:00PM" (actual'!!1)
     assertEqual "and the controls" "MockCntrls" (actual'!!3)
     assertBool "expecting something different from start time" (actual'!!1 /= actual'!!2))

-- same as above just changing the RecordType variable
testUiStateString2 :: Test
testUiStateString2 = TestCase (do
     actual <- uiStateString Stage
                              (Rgstry (ProjectActive Recording) "/usr/Project/Test" "1:00PM")
                              MockCntrls
     let expected = ["Error", "1:00PM","some time", "MockCntrls"]
         actual' = fields actual
     assertEqual "expecting length 4" 4 (length actual')
     assertEqual "getting record type" "Stage" (actual'!!0)
     assertEqual "getting start time" "1:00PM" (actual'!!1)
     assertEqual "and the controls" "MockCntrls" (actual'!!3)
     assertBool "expecting something different from start time" (actual'!!1 /= actual'!!2))
     
testProgramLinesString :: Test
testProgramLinesString = TestCase (do
     actual <- programLinesString (Rgstry (ProjectActive Recording) "/usr/Project/Test" "1:00PM") 
                                   fileAccumulatorForTest 
                                   fileLineCounterForTest
     let expected = ",3,2"
     assertEqual "should find two files w 1 line per file" expected actual)

fileAccumulatorForTest :: FileAccumulator
fileAccumulatorForTest fps rgx = return ["./Projects/Log.hs", "./Projects/LogTest.hs", "./Projects/MoreCode.hs",
                                        "./Projects/Main.hs", "./Projects/MainTest.hs"]

fileLineCounterForTest :: FileLineCounter
fileLineCounterForTest fp = return 1