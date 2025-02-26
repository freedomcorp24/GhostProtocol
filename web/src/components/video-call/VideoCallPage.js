import React, { useState, useEffect, useRef } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {
  Button,
  TextField,
  Typography,
  Paper,
  Grid,
  IconButton,
  Snackbar,
} from '@material-ui/core';
import {
  Videocam,
  VideocamOff,
  Mic,
  MicOff,
  ScreenShare,
  StopScreenShare,
  CallEnd,
} from '@material-ui/icons';
import Alert from '@material-ui/lab/Alert';
import webrtcService from '../../services/webrtc';
import ScreenShareDialog from '../screen-sharing/ScreenShareDialog';

const useStyles = makeStyles((theme) => ({
  root: {
    padding: theme.spacing(3),
    height: 'calc(100vh - 64px)',
    display: 'flex',
    flexDirection: 'column',
  },
  videoContainer: {
    flex: 1,
    display: 'flex',
    flexDirection: 'column',
    marginBottom: theme.spacing(2),
    position: 'relative',
    backgroundColor: theme.palette.grey[900],
    borderRadius: theme.shape.borderRadius,
    overflow: 'hidden',
  },
  mainVideo: {
    width: '100%',
    height: '100%',
    objectFit: 'cover',
  },
  selfVideo: {
    position: 'absolute',
    width: '20%',
    maxWidth: '200px',
    height: 'auto',
    bottom: theme.spacing(2),
    right: theme.spacing(2),
    borderRadius: theme.shape.borderRadius,
    border: `2px solid ${theme.palette.primary.main}`,
    zIndex: 10,
  },
  controls: {
    display: 'flex',
    justifyContent: 'center',
    padding: theme.spacing(2),
  },
  controlButton: {
    margin: theme.spacing(0, 1),
  },
  callButton: {
    margin: theme.spacing(0, 1),
    backgroundColor: theme.palette.success.main,
    '&:hover': {
      backgroundColor: theme.palette.success.dark,
    },
  },
  endCallButton: {
    margin: theme.spacing(0, 1),
    backgroundColor: theme.palette.error.main,
    '&:hover': {
      backgroundColor: theme.palette.error.dark,
    },
  },
  callForm: {
    display: 'flex',
    alignItems: 'center',
    marginBottom: theme.spacing(2),
  },
  callInput: {
    marginRight: theme.spacing(2),
    flex: 1,
  },
}));

const VideoCallPage = () => {
  const classes = useStyles();
  const [targetUserId, setTargetUserId] = useState('');
  const [inCall, setInCall] = useState(false);
  const [localStream, setLocalStream] = useState(null);
  const [remoteStream, setRemoteStream] = useState(null);
  const [isMuted, setIsMuted] = useState(false);
  const [isVideoOff, setIsVideoOff] = useState(false);
  const [isScreenSharing, setIsScreenSharing] = useState(false);
  const [screenShareDialogOpen, setScreenShareDialogOpen] = useState(false);
  const [alert, setAlert] = useState({ open: false, message: '', severity: 'info' });
  
  const localVideoRef = useRef(null);
  const remoteVideoRef = useRef(null);

  useEffect(() => {
    // Initialize WebRTC service
    const initializeMedia = async () => {
      try {
        const stream = await navigator.mediaDevices.getUserMedia({
          video: true,
          audio: true,
        });
        setLocalStream(stream);
        
        if (localVideoRef.current) {
          localVideoRef.current.srcObject = stream;
        }
        
        // Set up track handler for incoming streams
        webrtcService.addTrackHandler((stream, userId) => {
          setRemoteStream(stream);
          if (remoteVideoRef.current) {
            remoteVideoRef.current.srcObject = stream;
          }
          setAlert({
            open: true,
            message: `Connected with ${userId}`,
            severity: 'success',
          });
        });
      } catch (error) {
        console.error('Error accessing media devices:', error);
        setAlert({
          open: true,
          message: 'Error accessing camera and microphone',
          severity: 'error',
        });
      }
    };
    
    initializeMedia();
    
    return () => {
      // Clean up
      if (localStream) {
        localStream.getTracks().forEach(track => track.stop());
      }
      webrtcService.cleanup();
    };
  }, []);

  const startCall = async () => {
    if (!targetUserId) {
      setAlert({
        open: true,
        message: 'Please enter a user ID to call',
        severity: 'warning',
      });
      return;
    }
    
    try {
      // Create peer connection
      const pc = await webrtcService.createPeerConnection(targetUserId);
      
      // Add local stream to peer connection
      if (localStream) {
        localStream.getTracks().forEach(track => {
          pc.addTrack(track, localStream);
        });
      }
      
      // Create offer
      const offer = await pc.createOffer();
      await pc.setLocalDescription(offer);
      
      // Send offer to signaling server (this would be implemented in a real app)
      console.log('Sending offer to signaling server for user:', targetUserId);
      
      setInCall(true);
      setAlert({
        open: true,
        message: `Calling ${targetUserId}...`,
        severity: 'info',
      });
    } catch (error) {
      console.error('Error starting call:', error);
      setAlert({
        open: true,
        message: 'Error starting call',
        severity: 'error',
      });
    }
  };

  const endCall = () => {
    webrtcService.cleanup();
    setInCall(false);
    setRemoteStream(null);
    
    // Reinitialize local stream
    navigator.mediaDevices.getUserMedia({
      video: true,
      audio: true,
    }).then(stream => {
      setLocalStream(stream);
      if (localVideoRef.current) {
        localVideoRef.current.srcObject = stream;
      }
    });
    
    setAlert({
      open: true,
      message: 'Call ended',
      severity: 'info',
    });
  };

  const toggleMute = () => {
    if (localStream) {
      localStream.getAudioTracks().forEach(track => {
        track.enabled = !track.enabled;
      });
      setIsMuted(!isMuted);
    }
  };

  const toggleVideo = () => {
    if (localStream) {
      localStream.getVideoTracks().forEach(track => {
        track.enabled = !track.enabled;
      });
      setIsVideoOff(!isVideoOff);
    }
  };

  const toggleScreenShare = () => {
    setScreenShareDialogOpen(true);
  };

  const handleScreenShareClose = () => {
    setScreenShareDialogOpen(false);
  };

  const handleAlertClose = () => {
    setAlert({ ...alert, open: false });
  };

  return (
    <Paper className={classes.root}>
      <Typography variant="h4" gutterBottom>
        Video Call
      </Typography>
      
      {!inCall ? (
        <div className={classes.callForm}>
          <TextField
            className={classes.callInput}
            label="User ID to call"
            variant="outlined"
            value={targetUserId}
            onChange={(e) => setTargetUserId(e.target.value)}
          />
          <Button
            variant="contained"
            color="primary"
            className={classes.callButton}
            onClick={startCall}
            startIcon={<Videocam />}
          >
            Start Call
          </Button>
        </div>
      ) : null}
      
      <div className={classes.videoContainer}>
        {remoteStream && (
          <video
            ref={remoteVideoRef}
            className={classes.mainVideo}
            autoPlay
            playsInline
          />
        )}
        
        <video
          ref={localVideoRef}
          className={inCall ? classes.selfVideo : classes.mainVideo}
          autoPlay
          playsInline
          muted
        />
      </div>
      
      <div className={classes.controls}>
        <IconButton
          className={classes.controlButton}
          onClick={toggleMute}
          color={isMuted ? 'secondary' : 'primary'}
        >
          {isMuted ? <MicOff /> : <Mic />}
        </IconButton>
        
        <IconButton
          className={classes.controlButton}
          onClick={toggleVideo}
          color={isVideoOff ? 'secondary' : 'primary'}
        >
          {isVideoOff ? <VideocamOff /> : <Videocam />}
        </IconButton>
        
        <IconButton
          className={classes.controlButton}
          onClick={toggleScreenShare}
          color={isScreenSharing ? 'secondary' : 'primary'}
        >
          {isScreenSharing ? <StopScreenShare /> : <ScreenShare />}
        </IconButton>
        
        {inCall && (
          <Button
            variant="contained"
            className={classes.endCallButton}
            onClick={endCall}
            startIcon={<CallEnd />}
          >
            End Call
          </Button>
        )}
      </div>
      
      <ScreenShareDialog
        open={screenShareDialogOpen}
        onClose={handleScreenShareClose}
      />
      
      <Snackbar
        open={alert.open}
        autoHideDuration={6000}
        onClose={handleAlertClose}
      >
        <Alert onClose={handleAlertClose} severity={alert.severity}>
          {alert.message}
        </Alert>
      </Snackbar>
    </Paper>
  );
};

export default VideoCallPage;
