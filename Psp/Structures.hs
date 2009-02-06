
module Structures where

import Graphics.UI.Gtk
import System.Time
import Data.Array

data RecordType = Stage | Pause | Error deriving Show

data Controls = Cntrls {cmbStage :: ComboBoxEntry,
                        btnRecord, btnPause :: Button,
                        txtVariables :: Entry,
                        cmbType :: ComboBoxEntry,
                        btnAdd :: Button, 
                        txtDescription :: Entry,
                        mnuOpen, mnuQuit :: ImageMenuItem}

data Registry = Rgstry {state :: State,
                        projectRoot :: String,
                        startTime :: String}

type StartTime = String
type ProjectRoot = String 

data State = ProjectInactive
           | ProjectActive State
           | Waiting 
           | Paused
           | Recording deriving (Show, Eq)

data Control = OpenProjectItem | QuitProjectItem | StageComboBox | TypeComboBox | VariablesEntryBox
             | DescriptionEntryBox | RecordButton | PauseButton | AddButton deriving (Show, Eq, Ord, Ix, Enum)

class Serializable a where
    serialize :: a -> IO String

instance Serializable RecordType where
    serialize rt = return (show rt)

instance Serializable Controls where
    serialize cntrls = do stage <- comboBoxGetActiveText (cmbStage cntrls)
                          typ <- comboBoxGetActiveText (cmbType cntrls)
                          variables <- entryGetText (txtVariables cntrls)
                          desc <- entryGetText (txtDescription cntrls)
                          let resolve maybeString = case maybeString of
                                                      Just a -> a
                                                      Nothing -> ""
                          return (sep ++ resolve(stage) ++ sep ++ variables ++ sep ++ resolve(typ) ++ sep ++ desc)

instance Serializable Registry where
    serialize r = do now <- getClockTime
                     now <- toCalendarTime now
                     let now' = calendarTimeToString now
                     case (state r) of
                       ProjectActive Recording -> return (sep ++ (startTime r) ++ sep ++ now')
                       ProjectActive Paused -> return (sep ++ now' ++ sep ++ now')
                       otherwise -> error "not able to serialize when program is in this state"

sep :: String
sep = ","

num :: State -> Int
num ProjectInactive = 0
num (ProjectActive Waiting) = 1
num (ProjectActive Paused) = 2
num (ProjectActive Recording) = 3


