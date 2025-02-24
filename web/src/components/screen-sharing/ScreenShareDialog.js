import React, { useState, useEffect } from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  Typography,
} from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import webrtcService from '../../services/webrtc';

const useStyles = makeStyles((theme) => ({
  video: {
    width: '100%',
    maxHeight: '70vh',
    backgroundColor: theme.palette.grey[900],
  },
  controls: {
    marginTop: theme.spacing(2),
    display: 'flex',
    justifyContent: 'space-between',
  },
}));

const ScreenShareDialog = ({ open, onClose }) => {
  const classes = useStyles();
  const [stream, setStream] = useState(null);

  const startScreenShare = async () => {
    try {
      const mediaStream = await webrtcService.initializeScreenShare();
      setStream(mediaStream);
    } catch (error) {
      console.error('Error accessing screen share:', error);
    }
  };

  const stopScreenShare = () => {
    if (stream) {
      webrtcService.stopScreenShare();
      setStream(null);
    }
  };

  useEffect(() => {
    return () => {
      stopScreenShare();
    };
  }, []);

  return (
    <Dialog
      open={open}
      onClose={onClose}
      maxWidth="lg"
      fullWidth
    >
      <DialogTitle>Screen Sharing</DialogTitle>
      <DialogContent>
        {stream ? (
          <video
            className={classes.video}
            autoPlay
            ref={(video) => {
              if (video) video.srcObject = stream;
            }}
          />
        ) : (
          <Typography variant="body1" align="center">
            Click Start to begin sharing your screen
          </Typography>
        )}
        <div className={classes.controls}>
          {!stream ? (
            <Button
              variant="contained"
              color="primary"
              onClick={startScreenShare}
            >
              Start Sharing
            </Button>
          ) : (
            <Button
              variant="contained"
              color="secondary"
              onClick={stopScreenShare}
            >
              Stop Sharing
            </Button>
          )}
        </div>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Close</Button>
      </DialogActions>
    </Dialog>
  );
};

export default ScreenShareDialog;
