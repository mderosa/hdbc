
module WidgetState where

import Structures
import Specifications
import Graphics.UI.Gtk
import Data.IORef
import System.Time
import Data.Array

enterStateProjectInactive :: (IORef Registry) -> Controls -> IO ()
enterStateProjectInactive reg ctrls = do
     rgstry <- readIORef reg
     let rgstry' = Rgstry ProjectInactive "" ""
     setControlStates rgstry' ctrls
     writeIORef reg rgstry'

enterStateWaiting :: (IORef Registry) -> Controls -> IO ()
enterStateWaiting reg ctrls = do
     rgstry <- readIORef reg
     let rgstry' = Rgstry (ProjectActive Waiting) (projectRoot rgstry) ""
     setControlStates rgstry' ctrls
     writeIORef reg rgstry'

enterStatePaused :: (IORef Registry) -> Controls -> IO ()
enterStatePaused reg ctrls = do
     rgstry <- readIORef reg
     let rgstry' = Rgstry (ProjectActive Paused) (projectRoot rgstry) (startTime rgstry)
     setControlStates rgstry' ctrls
     writeIORef reg rgstry'

enterStateRecording :: (IORef Registry) -> Controls -> IO ()
enterStateRecording reg ctrls = do 
     rgstry <- readIORef reg
     clockTime <- getClockTime
     calTime <- toCalendarTime clockTime
     let rgstry' = case (state rgstry) of
                     ProjectActive Waiting -> Rgstry (ProjectActive Recording) (projectRoot rgstry) (calendarTimeToString calTime)
                     ProjectActive Paused -> Rgstry (ProjectActive Recording) (projectRoot rgstry) (startTime rgstry)
                     otherwise -> rgstry
     setControlStates rgstry' ctrls
     writeIORef reg rgstry'

sensitivity :: Control -> State -> Array (Control, Int) (Sensitive, Display) -> Bool
sensitivity c s spec = let (sensitivity, label) = spec ! (c, num s) in
                       (sensitivity == S)

label :: Control -> State -> Array (Control, Int) (Sensitive, Display) -> Display
label c s spec = let (sensitivity, lbl) = spec ! (c, num s) in
                 lbl

extractLabel :: Display -> String
extractLabel (D s) = s
extractLabel Any = error "not able to extract string"

setControlStates :: Registry -> Controls -> IO ()
setControlStates rgstry' ctrls = do
     let spec = controlStateTable
     widgetSetSensitivity (mnuOpen ctrls) (sensitivity OpenProjectItem (state rgstry') spec)
     widgetSetSensitivity (mnuQuit ctrls) (sensitivity QuitProjectItem (state rgstry') spec)
     widgetSetSensitivity (cmbStage ctrls) (sensitivity StageComboBox (state rgstry') spec)
     widgetSetSensitivity (btnRecord ctrls) (sensitivity RecordButton (state rgstry') spec)
     widgetSetSensitivity (btnPause ctrls) (sensitivity PauseButton (state rgstry') spec)
     widgetSetSensitivity (cmbType ctrls) (sensitivity TypeComboBox (state rgstry') spec)
     widgetSetSensitivity (btnAdd ctrls) (sensitivity AddButton (state rgstry') spec)
     widgetSetSensitivity (txtVariables ctrls) (sensitivity VariablesEntryBox (state rgstry') spec)
     widgetSetSensitivity (txtDescription ctrls) (sensitivity DescriptionEntryBox (state rgstry') spec)
     let (recDis, pauseDis, descDis) = (label RecordButton (state rgstry') spec, 
                                        label PauseButton (state rgstry') spec,
                                        label DescriptionEntryBox (state rgstry') spec)
     if recDis /= Any then buttonSetLabel (btnRecord ctrls) (extractLabel recDis) else return ()
     if pauseDis /= Any then buttonSetLabel (btnPause ctrls) (extractLabel pauseDis) else return ()
     if descDis /= Any then entrySetText (txtDescription ctrls) (extractLabel descDis) else return ()

{-
([ProjectInactive,ProjectActive Waiting,ProjectActive Paused,ProjectActive Recording],
[OpenProjectItem,QuitProjectItem,StageComboBox,VariablesEntryBox,DescriptionEntryBox,RecordButton,PauseButton,AddButton],
[(S,Any),(S,Any),(NS,Any),(NS,Any),(S,Any),(S,Any),(NS,Any),(NS,Any),(NS,Any),(S,Any),(NS,Any),(NS,Any),(NS,D ""),(S,D ""),(NS,Any),(NS,Any),(NS,Any),(NS,Any),(NS,Any),(S,D ""),(NS,Any),(S,D "Record"),(NS,D "Stop"),(S,D "Stop"),(NS,Any),(NS,D "Pause"),(NS,D "Continue"),(S,D "Pause"),(NS,Any),(NS,Any),(NS,Any),(S,D "")])
-}