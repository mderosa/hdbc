
module Definitions where

import Text.Regex

type FileLines = [String]

type LineNumber = Int
type ErrorMessage = String

type ValidationFn = FileLines -> Bool
type ValidationDef = (ValidationFn, ErrorMessage)
type ErrorDef = (Regex, Regex, ErrorMessage)