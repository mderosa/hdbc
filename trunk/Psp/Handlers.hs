
module Handlers where

import Constants
import WidgetState
import Structures
import Log
import Data.IORef
import Graphics.UI.Gtk
import System.Directory
import System.IO

defineQuitProjectItmClick :: Controls -> IO (ConnectId ImageMenuItem)
defineQuitProjectItmClick ctls = onActivateLeaf (mnuQuit ctls) mainQuit

defineOpenProjectItmClick :: (IORef Registry) -> Controls -> Window -> IO (ConnectId ImageMenuItem)
defineOpenProjectItmClick reg ctls wnd = do
     onActivateLeaf (mnuOpen ctls) $ do 
          rgstry <- readIORef reg
          case (state rgstry) of
            ProjectInactive -> do dlg <- fileChooserDialogNew (Just "select project") (Just wnd) FileChooserActionSelectFolder [("Ok", ResponseOk), ("Cancel", ResponseCancel)]
                                  rsp <- dialogRun dlg
                                  dir <- fileChooserGetFilename dlg
                                  widgetDestroy dlg
                                  if rsp == ResponseCancel || rsp == ResponseNone
                                     then return ()
                                     else if dir == Nothing
                                             then return ()
                                             else do makeDataFile (resolveDir dir)
                                                     writeIORef reg (Rgstry ProjectInactive (resolveDir dir) "")
                                                     enterStateWaiting reg ctls
            otherwise -> return ()

resolveDir :: Maybe String -> String
resolveDir v = maybe "" id v

makeDataFile :: FilePath -> IO ()
makeDataFile dir = do exists <- doesFileExist (dir ++ "/" ++ dataFile)
                      if exists 
                         then return ()
                         else do hdl <- openFile (dir ++ "/" ++ dataFile) WriteMode
                                 hPutStr hdl "\n"
                                 hClose hdl

defineRecordBtnClick :: (IORef Registry) -> Controls -> IO (ConnectId Button)
defineRecordBtnClick reg ctls = do 
     onClicked (btnRecord ctls) $ do rgstry <- readIORef reg
                                     case (state rgstry) of 
                                       ProjectActive Waiting -> enterStateRecording reg ctls
                                       ProjectActive Recording -> do Log.log Stage reg ctls
                                                                     enterStateWaiting reg ctls
                                       otherwise -> return ()

definePauseBtnClick :: (IORef Registry) -> Controls -> IO (ConnectId Button)
definePauseBtnClick reg ctls = do
     onClicked (btnPause ctls) $ do rgstry <- readIORef reg
                                    case (state rgstry) of
                                      ProjectActive Recording -> enterStatePaused reg ctls
                                      ProjectActive Paused -> do Log.log Pause reg ctls
                                                                 enterStateRecording reg ctls
                                      otherwise -> return ()

defineAddErrorBtnClick :: (IORef Registry) -> Controls -> IO (ConnectId Button)
defineAddErrorBtnClick reg ctls = do
     onClicked (btnAdd ctls) $ do rgstry <- readIORef reg
                                  case (state rgstry) of 
                                    ProjectActive Recording -> do Log.log Error reg ctls
                                                                  enterStateRecording reg ctls
                                    otherwise -> return ()






