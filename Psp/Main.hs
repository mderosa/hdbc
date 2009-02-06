
module Main where

import Handlers
import Structures
import Graphics.UI.Gtk
import Graphics.UI.Gtk.Glade
import Data.IORef

main :: IO ()
main = do 
     initGUI
     registry <- newIORef (Rgstry ProjectInactive "" "")
     Just xml <- xmlNew "psp.glade"
     window <- xmlGetWidget xml castToWindow "psp"
     onDestroy window mainQuit
     controls <- getControls xml
     defineOpenProjectItmClick registry controls window
     defineQuitProjectItmClick controls
     defineRecordBtnClick registry controls
     definePauseBtnClick registry controls
     defineAddErrorBtnClick registry controls
     mainGUI

getControls :: GladeXML -> IO Controls
getControls xml = do
     cmbStage <- xmlGetWidget xml castToComboBoxEntry "cmbStage"
     btnRecord <- xmlGetWidget xml castToButton "btnRecord"
     btnPause <- xmlGetWidget xml castToButton "btnPause"
     txtVariables <- xmlGetWidget xml castToEntry "txtVariables"
     cmbType <- xmlGetWidget xml castToComboBoxEntry "cmbType"
     btnAdd <- xmlGetWidget xml castToButton "btnAdd"
     txtDescription <- xmlGetWidget xml castToEntry "txtDescription"
     mnuOpen <- xmlGetWidget xml castToImageMenuItem "mnuOpen"
     mnuQuit <- xmlGetWidget xml castToImageMenuItem "mnuQuit"
     return (Cntrls cmbStage btnRecord btnPause txtVariables cmbType btnAdd txtDescription mnuOpen mnuQuit)




