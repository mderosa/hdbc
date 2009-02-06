
module Validation where

import Definitions
import Test.HUnit
import Text.Regex

data Validation a = Vldtion Integer [ErrorMessage] a

instance Monad Validation where
    (>>=) (Vldtion cnt errs val) fn = let Vldtion c e v = fn val
                                      in Vldtion (cnt + c) (errs ++ e) v
    return a = Vldtion 1 [] a

validateLines :: ErrorDef -> FileLines -> Validation Bool
validateLines _ [] = return True
validateLines (identifyingRegex, validatingRegex, errorMsg) (l:ls) = do
    isValid1 <- validateLine identifyingRegex validatingRegex l
    addValidationError errorMsg l isValid1
    isValid2 <- validateLines (identifyingRegex, validatingRegex, errorMsg) ls
    validateAnd isValid1 isValid2

validateLinesDontMatch :: ErrorDef -> FileLines -> Validation Bool
validateLinesDontMatch _ [] = return True
validateLinesDontMatch (r1, r2, errMsg) (l:ls) = do
    isValid1 <- validateLineDoesNotMatch r1 l
    addValidationError errMsg l isValid1
    isValid2 <- validateLinesDontMatch (r1, r2, errMsg) ls
    validateAnd isValid1 isValid2

validateLine :: Regex -> Regex -> String -> Validation Bool
validateLine idRegex valRegex l = 
    if (matchRegex idRegex l) /= Nothing
       then if (matchRegex valRegex l) /= Nothing
               then return True
               else return False
       else return True

validateLineDoesNotMatch :: Regex -> String -> Validation Bool
validateLineDoesNotMatch r1 l =
    if (matchRegex r1 l) /= Nothing
       then return False 
       else return True

validateAtLeastOneLineMatches :: ErrorDef -> FileLines -> Validation Bool
validateAtLeastOneLineMatches (r1, r2, errMsg) [] = do
    addValidationError errMsg "EOF" False
    return False
validateAtLeastOneLineMatches (r1, r2, errMsg) (l:ls) = do
    noMatch <- validateLineDoesNotMatch r1 l
    if noMatch 
       then validateAtLeastOneLineMatches (r1, r2,errMsg) ls
       else return True
                                                      

validateAnd :: Bool -> Bool -> Validation Bool
validateAnd one two = Vldtion 0 [] (and [one, two])               

addValidationError :: ErrorMessage -> String -> Bool -> Validation ()
addValidationError msg line isValid = 
    if isValid 
       then Vldtion 0 [] () 
       else Vldtion 0 [msg ++ "line is: " ++ line ++ "\n"] ()