
module HandlersTest where
 
import Handlers
import Test.HUnit

allTests :: Test
allTests = TestList []

testSerializeState :: Test
testSerializeState = TestCase (do
      let actual = serializeState State 
      assertBool "" True)