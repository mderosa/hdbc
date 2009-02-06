
module Specifications where

import Structures
import Data.Array

controlStateTable :: Array (Control, Int) (Sensitive, Display)
controlStateTable = let (states, cntrls, stDetails) = controlStateSpec 
                        sts = map num states
                        controlStateTuples = [(c,s) | c <- cntrls, s <- sts]
                    in
     array ((head cntrls, head sts), (last cntrls, last sts)) (zip controlStateTuples stDetails)

data Sensitive = S | NS deriving (Show, Eq)
data Display = D String | Any deriving (Show, Eq)

controlStateSpec =              [ProjectInactive, ProjectActive Waiting, ProjectActive Paused, ProjectActive Recording] `hh` (
       (OpenProjectItem     +>> [(S, Any)       , (S, Any)             , (NS, Any)            , (NS, Any)])      +&&
       (QuitProjectItem     +>> [(S, Any)       , (S, Any)             , (NS, Any)            , (NS, Any)])      +&&
       (StageComboBox       +>> [(NS, Any)      , (S, Any)             , (NS, Any)            , (NS, Any)])      +&&
       (VariablesEntryBox   +>> [(NS, D "")     , (S, D "")            , (NS, Any)            , (NS, Any)])      +&&
       (TypeComboBox        +>> [(NS, Any)      , (NS, Any)            , (NS, Any)            , (S, Any)])       +&&
       (DescriptionEntryBox +>> [(NS, Any)      , (NS, Any)            , (NS, Any)            , (S, D "")])      +&&
       (RecordButton        +>> [(NS, Any)      , (S, D "Record")      , (NS, D "Stop")       , (S, D "Stop")])  +&&
       (PauseButton         +>> [(NS, Any)      , (NS, D "Pause")      , (S, D "Continue")    , (S, D "Pause")]) +&&
       (AddButton           +>> [(NS, Any)      , (NS, Any)            , (NS, Any)            , (S, D "")]))

(+>>) :: Control -> [(Sensitive, Display)] -> ([Control], [(Sensitive, Display)])
c +>> ls = ([c], ls)

(+&&) :: ([a], [b]) -> ([a], [b]) -> ([a], [b])
(a, b) +&& (c, d) = (a ++ c, b ++ d)

hh :: a -> (b, c) -> (a, b, c)
hh a (b, c) = (a, b, c)