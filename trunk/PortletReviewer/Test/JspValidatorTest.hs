
module Test.JspValidatorTest where

import Test.HUnit
import JspValidator
import Definitions

runAllTests :: IO Counts
runAllTests = runTestTT (
    TestList [TestLabel "testValidateJavascriptFunctionsEncoded" testValidateJavascriptFunctionsEncoded,
              TestLabel "testValidateElementIdsEncoded" testValidateElementIdsEncoded,
              TestLabel "testValidateNoHtmlComments" testValidateNoHtmlComments,
              TestLabel "testValidateImagesAreAccessible" testValidateImagesAreAccessible,
              TestLabel "testValidateFormNamesEncoded" testValidateFormNamesEncoded,
              TestLabel "testValidateNoIllegalHtmlTags" testValidateNoIllegalHtmlTags,
              TestLabel "testValidatUsingPortletClasses" testValidatUsingPortletClasses,
              TestLabel "testValidateContextPathCalls" testValidateContextPathCalls,
              TestLabel "testValidateNoSessionCreated" testValidateNoSessionCreated,
              TestLabel "testValidateTextBoxesHaveLabels" testValidateTextBoxesHaveLabels,
              TestLabel "testValidateTextBoxesHaveLabels2" testValidateTextBoxesHaveLabels2,
              TestLabel "testValidateTextBoxesHaveLabels3" testValidateTextBoxesHaveLabels3]
    )

isErrorFree :: [ErrorMessage] -> Bool
isErrorFree errs = length errs == 0

testValidateJavascriptFunctionsEncoded :: Test
testValidateJavascriptFunctionsEncoded = TestCase (do
    let fileContents1 = ["", "\n", ""]
        fileContents2 = ["", "function myFunction() {", "}"]
        fileContents3 = ["<portlet:namespace  />function myFnc ()"]
    assertEqual "contents1 has no javascript functions" True 
                    (isErrorFree (validateJavascriptFunctionsEncoded fileContents1))
    assertEqual "contents2 is not properly encoded"  False  
                    (isErrorFree (validateJavascriptFunctionsEncoded fileContents2))
    assertEqual "contents3 is properly encoded" True  
                    (isErrorFree (validateJavascriptFunctionsEncoded fileContents3))
    )                                                   

testValidateElementIdsEncoded :: Test
testValidateElementIdsEncoded = TestCase ( do
    let contents1 = ["", "", "\n"]
        contents2 = ["<form id=\"primary\" action=\"post\">", "<input name=\"insert\" />"]
        contents3 = ["<form id=\"<portlet:namespace />primary\" >"]
    assertEqual "contents1: expected 'valid'" True  (isErrorFree (validateElementIdsEncoded contents1))
    assertEqual "contents2: expected 'invalid'" False  (isErrorFree (validateElementIdsEncoded contents2))
    assertEqual "contents3: expected 'valid'" True  (isErrorFree (validateElementIdsEncoded contents3))
    )

testValidateFormNamesEncoded :: Test
testValidateFormNamesEncoded = TestCase (do
    let contents1 = ["<form name=bad_name"]
        contents2 = ["<form id=\"some_id\" name='bad_name'"]
        contents3 = ["<form action=post name=\"bad_name\""]
        contents4 = ["<FORM method=POST name=\"<portlet:namespace />form1\" >"]
    assertEqual "contents1 expected invalid" False (isErrorFree (validateFormNamesEncoded contents1))
    assertEqual "contents2 expected invalid" False (isErrorFree (validateFormNamesEncoded contents2))
    assertEqual "contents3 expected invalid" False (isErrorFree (validateFormNamesEncoded contents3))
    assertEqual "contents4 expected invalid" True (isErrorFree (validateFormNamesEncoded contents4))
    )

testValidateImagesAreAccessible :: Test
testValidateImagesAreAccessible = TestCase (do
    let contents1 = ["<img src=./and/a/path/file.img />"]
        contents2 = ["<IMG src=\"\" alt=\"yo\""]
    assertEqual "contents1 expected invalid" False (isErrorFree (validateImagesAreAccessible contents1))
    assertEqual "contents2 expected valid" True (isErrorFree (validateImagesAreAccessible contents2))
    )

testValidateNoHtmlComments :: Test
testValidateNoHtmlComments = TestCase (do
    let contents1 = ["<!-- and a comment -->"]
    assertEqual "contents1: expected 'invalid'" False  (isErrorFree (validateNoHtmlComments contents1))
    )

testValidateNoIllegalHtmlTags :: Test
testValidateNoIllegalHtmlTags = TestCase (do
    let contents = ["thed <iframe name=\"edkd"]
    assertEqual "expected failure - iframe element exists" False (isErrorFree (validateNoIllegalHtmlTags contents))
    )

testValidatUsingPortletClasses :: Test
testValidatUsingPortletClasses = TestCase (do
    let contents1 = ["<div class=\"bogus-class\" >"]
        contents2 = ["<p class=\"portlet-msg-error\">"]
    assertEqual "contents1: expected 'invalid'" False (isErrorFree (validateUsingPortletClasses contents1))
    assertEqual "contents2: expected 'valid'" True (isErrorFree (validateUsingPortletClasses contents2))
    )

testValidateContextPathCalls :: Test
testValidateContextPathCalls = TestCase (do
    let contents1 = ["String pathToImages = (String) renderRequest.getContextPath();"]
        contents2 = ["String pathToImages = (String) renderResponse.encodeURL(renderRequest.getContextPath() + /img/stuff.img);"]
    assertEqual "contents1: expected 'invalid'" False  (isErrorFree (validateContextPathCalls contents1))
    assertEqual "contents2: expected 'valid'" True  (isErrorFree (validateContextPathCalls contents2))
    )

testValidateNoSessionCreated :: Test
testValidateNoSessionCreated = TestCase (do
    let contents1 = ["<%@page session=\"false\" contentType=\"text/html\" %>"]
        contents2 = ["<%@ page  contentType=\"text/html\"  session=\"false\"%>"]
    assertEqual "contents1:expected 'valid'" True (isErrorFree (validateNoSessionCreated contents1))
    assertEqual "contents2:expected 'valid'" True (isErrorFree (validateNoSessionCreated contents2))
    )

testValidateTextBoxesHaveLabels :: Test
testValidateTextBoxesHaveLabels = TestCase (do
     let contents = ["<label for=\"<portlet:namespace/><%=Consts.SOURCE_URL%>\">",
                     "<spring:message code=\"config.source.url\" />",
		     "</label>",
		     "<br/>",
		     "<input type=\"text\" id=\"<portlet:namespace/><%=Consts.SOURCE_URL%>\" name=\"<%=Consts.SOURCE_URL%>\" size=\"40\"",
		     "value='<c:out value=\"${attributes.sourceUrl}\"/>' />"]
     assertEqual "should pass as control has label" True (isErrorFree (validateTextBoxesHaveLabels contents)))

testValidateTextBoxesHaveLabels2 :: Test
testValidateTextBoxesHaveLabels2 = TestCase (do
     let contents = ["<textarea >",
                     "<spring:message code=\"config.source.url\" />",
		     "</textarea>",
		     "<input type=\"text\" id=\"<portlet:namespace/><%=Consts.SOURCE_URL%>\" name=\"<%=Consts.SOURCE_URL%>\" size=\"40\"",
		     "value='<c:out value=\"${attributes.sourceUrl}\"/>' />"]
     assertEqual "should not pass as control has label" False (isErrorFree (validateTextBoxesHaveLabels contents)))

-- sometimes we have stuff like type=text without any quotes. Not valid html, but why would that stop anyone
testValidateTextBoxesHaveLabels3 :: Test
testValidateTextBoxesHaveLabels3 = TestCase (do
     let contents = ["<input type=text name=whatever / >",
		     "<input type=text id=\"<portlet:namespace/><%=Consts.SOURCE_URL%>\" name=\"<%=Consts.SOURCE_URL%>\" size=\"40\"",
		     "value='<c:out value=\"${attributes.sourceUrl}\"/>' />"]
     assertEqual "should not pass as we have two text boxes without labels" False (isErrorFree (validateTextBoxesHaveLabels contents)))