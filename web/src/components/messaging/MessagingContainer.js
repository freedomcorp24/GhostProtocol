import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { Paper, Grid, IconButton } from '@material-ui/core';
import { AttachMoney, ScreenShare } from '@material-ui/icons';
import MessageList from './MessageList';
import MessageInput from './MessageInput';
import CryptoPaymentDialog from '../payments/CryptoPaymentDialog';
import ScreenShareDialog from '../screen-sharing/ScreenShareDialog';
import cryptoService from '../../services/crypto';
import webrtcService from '../../services/webrtc';

const useStyles = makeStyles((theme) => ({
  root: {
    height: '100vh',
    padding: theme.spacing(2),
  },
  paper: {
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
  },
  actions: {
    padding: theme.spacing(1),
    display: 'flex',
    justifyContent: 'flex-end',
  },
}));

const MessagingContainer = () => {
  const classes = useStyles();
  const [paymentDialogOpen, setPaymentDialogOpen] = useState(false);
  const [screenShareDialogOpen, setScreenShareDialogOpen] = useState(false);

  const handlePayment = async (paymentDetails) => {
    try {
      await cryptoService.sendPayment('recipient_id', paymentDetails.amount, paymentDetails.currency);
    } catch (error) {
      console.error('Payment error:', error);
    }
  };

  return (
    <Grid container className={classes.root}>
      <Grid item xs={12}>
        <Paper className={classes.paper}>
          <div className={classes.actions}>
            <IconButton onClick={() => setPaymentDialogOpen(true)}>
              <AttachMoney />
            </IconButton>
            <IconButton onClick={() => setScreenShareDialogOpen(true)}>
              <ScreenShare />
            </IconButton>
          </div>
          <MessageList />
          <MessageInput />
        </Paper>
      </Grid>

      <CryptoPaymentDialog
        open={paymentDialogOpen}
        onClose={() => setPaymentDialogOpen(false)}
        onSend={handlePayment}
      />

      <ScreenShareDialog
        open={screenShareDialogOpen}
        onClose={() => setScreenShareDialogOpen(false)}
      />
    </Grid>
  );
};

export default MessagingContainer;
