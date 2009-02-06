
module JspValidator where

import Test.HUnit
import Text.Regex
import Definitions
import Common
import Validation

allErrors :: FileLines -> [ErrorMessage]
allErrors lines = validateJavascriptFunctionsEncoded lines ++
                  validateElementIdsEncoded lines ++
                  validateFormNamesEncoded lines ++
                  validateNoHtmlComments lines ++
                  validateUsingPortletClasses lines ++
                  validateContextPathCalls lines ++
                  validateNoIllegalHtmlTags lines ++
                  validateNoIllegalHtmlTags2 lines ++
                  validateNoSessionCreated lines ++
                  validateTextBoxesHaveLabels lines

validateJavascriptFunctionsEncoded :: FileLines -> [ErrorMessage]
validateJavascriptFunctionsEncoded fileLines = 
        let jsFunctionRegex = mkRegex "function[[:space:]]+[[:alnum:]]+[[:space:]]*\\([[:space:]]*\\)"
            nsRegex = mkRegex "<portlet:namespace[[:space:]]*/>function"
            errorMsg = "!!! java function names should be encoded with the <portlet:namespace /> tag.  This\n" ++
                       "will prevent nameing conflicts\n"
            Vldtion c e b = validateLines (jsFunctionRegex, nsRegex, errorMsg) fileLines
        in e

validateElementIdsEncoded :: FileLines -> [ErrorMessage]
validateElementIdsEncoded fileLines =
        let idRegex = mkRegex "[[:space:]]id[[:space:]]*="
            nsRegex = mkRegex "[[:space:]]id[[:space:]]*=[[:space:]]*\"<portlet:namespace"
            errorMsg = "!!! id values in html should be encoded with the <portlet:namespace /> tag\n"
            Vldtion c e b = validateLines (idRegex, nsRegex, errorMsg) fileLines
        in e

validateFormNamesEncoded :: FileLines -> [ErrorMessage]
validateFormNamesEncoded fileLines =
        let idRegex = mkRegex "<[form|FORM].+name="
            nsRegex = mkRegex "name=['\"]*<portlet:namespace"
            errorMsg = "!!! form names should be encoded with the <portlet:namespace /> tag.  This prevents\n" ++
                       "potentially ambiguous references via javascript code\n"
            Vldtion c e b = validateLines (idRegex, nsRegex, errorMsg) fileLines
        in e

validateImagesAreAccessible :: FileLines -> [ErrorMessage]
validateImagesAreAccessible fileLines =
    let idRegex = mkRegex "<[img|IMG]"
        verRegex = mkRegex "[alt|ALT]="
        errorMsg = "!!! images should specify an alt attribute to be fully accessible\n"
        Vldtion c e b = validateLines (idRegex, verRegex, errorMsg) fileLines
    in e

validateNoHtmlComments :: FileLines -> [ErrorMessage]
validateNoHtmlComments fileLines =
        let cmntRegex = mkRegex "<!--.+-->"
            errorMsg = "!!! use jsp comments in preference to html comments\n"
            Vldtion c e b = validateLinesDontMatch (cmntRegex, cmntRegex, errorMsg) fileLines
        in e                            

validateUsingPortletClasses :: FileLines -> [ErrorMessage]
validateUsingPortletClasses fileLines =
        let classRegex = mkRegex "[[:space:]]class[[:space:]]*=[[:space:]]*\""
            portletClassRegex = mkRegex "class[[:space:]]*=[[:space:]]*\"portlet-"
            errorMsg = "!!! the porlet does not use the jsr-168 standard class names.  This portlet will\n" ++
                       "not necessarily support a common look and feel across this particular portal\n" ++
                       ".  It will not be visually compatable with other sites when used remotely and will\n" ++
                       "will not be visually compatable if plugged into other portal projects.  Consider\n" ++
                       "using the standard portlet class names\n"
            Vldtion c e b = validateLines (classRegex, portletClassRegex, errorMsg) fileLines
        in e 

validateContextPathCalls :: FileLines -> [ErrorMessage]
validateContextPathCalls fileLines =
        let ctxPathRegex = mkRegex "getContextPath"
            fullEncRegex = mkRegex ("renderResponse\\.encodeURL[[:space:]]*\\(renderRequest\\.getContextPath\\(" ++
                           "[[:space:]]*\\)[[:space:]]*\\+")
            errorMsg = "!!! urls should be formed using renderResponse.encodeURL((renderRequest.getContextPath() + path).\n" ++
                       " This code will not be binary portable between jsr 168 portlet containers\n"
            Vldtion c e b = validateLines (ctxPathRegex, fullEncRegex, errorMsg) fileLines
        in e

validateNoIllegalHtmlTags :: FileLines -> [ErrorMessage]
validateNoIllegalHtmlTags fileLines = 
       let regex = mkRegex ("<head>|<HEAD>|<body|<BODY|<frameset|<FRAMESET|<iframe|<IFRAME|<base|<BASE" ++
                             "|<html>|<HTML>|<title|<TITLE")
           errorMsg = "!!! the use of head, body, frameset, iframe, base, html, title tags will create both\n" ++
                      "illegal html markup in the portal, it may also disrupt page display\n"
           Vldtion c e b = validateLinesDontMatch (regex, regex, errorMsg) fileLines
       in e

validateNoIllegalHtmlTags2 :: FileLines -> [ErrorMessage]
validateNoIllegalHtmlTags2 fileLines =
    let regex = mkRegex "<link|<LINK|<meta|<META|<style|<STYLE"
        errorMsg = "!!! the html and xhtml standards do not allow the link, meta, style tags to be used outside\n" ++
                   "of the head element.  Different browsers may or may not support this functionality.  Where it is\n" ++
                   "suppoted it is not guaranteed to continue working as browser updates occur\n"
        Vldtion c e b = validateLinesDontMatch (regex, regex, errorMsg) fileLines
    in e

validateNoSessionCreated :: FileLines -> [ErrorMessage]
validateNoSessionCreated fileLines = 
    let regex = mkRegex "<%@[[:space:]]*page[[:space:]]+.*session=\\\"false\\\""
        errorMsg = "!!! you generally want to insure that the jsp page is unable to create sessions by adding\n" ++
                   "the page directive <%@page session=\"false\" contentType=\"text/html\" %>\n"
        Vldtion c e b = validateAtLeastOneLineMatches (regex, regex, errorMsg) fileLines
    in e

validateTextBoxesHaveLabels :: FileLines -> [ErrorMessage]
validateTextBoxesHaveLabels fileLines = 
    let txtRgx = mkRegex "([[:space:]]+type=['|\"]?text['|\"]?[[:space:]]+)"
        selRgx = mkRegex "(<select[[:space:]]+)"
        txaRgx = mkRegex "(<textarea[[:space:]]+)"
        lblRgx = mkRegex "(<label[[:space:]]+)"
        a = matchRegex txtRgx (unlines fileLines)
        b = matchRegex selRgx (unlines fileLines)
        c = matchRegex txaRgx (unlines fileLines)
        d = matchRegex lblRgx (unlines fileLines) in
    if ((count a) + (count b) + (count c)) /= (count d) 
       then ["!!! Proper accessability requires that text input, select, and textarea tags have associated label tags\n"]
       else []

count :: Maybe [a] -> Int
count (Just l) = length l
count Nothing = 0