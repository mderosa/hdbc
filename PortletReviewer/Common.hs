
module Common where

import Definitions
import Text.Regex
import Test.HUnit

mkTestList :: Int -> [ValidationDef] -> FileLines -> Test
mkTestList index validationDefs lns = 
        let mkTestCase (validationFn, errorMsg) lns' = TestCase (assertBool errorMsg (validationFn lns'))
            mkTestLabel index' validationDef lns' = TestLabel (show index') (mkTestCase validationDef lns')
            mkLabelList index' [] lns' = []
            mkLabelList index' (def:defs) lns' = mkTestLabel index' def lns' : mkLabelList (index' + 1) defs lns'
    in TestList (mkLabelList index validationDefs lns)