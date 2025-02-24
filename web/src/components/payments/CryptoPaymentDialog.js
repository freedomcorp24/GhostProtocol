import React, { useState } from 'react';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  TextField,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
} from '@material-ui/core';

const CryptoPaymentDialog = ({ open, onClose, onSend }) => {
  const [amount, setAmount] = useState('');
  const [currency, setCurrency] = useState('BTC');

  const handleSend = () => {
    onSend({ amount, currency });
    onClose();
  };

  return (
    <Dialog open={open} onClose={onClose}>
      <DialogTitle>Send Cryptocurrency</DialogTitle>
      <DialogContent>
        <FormControl fullWidth margin="normal">
          <InputLabel>Currency</InputLabel>
          <Select value={currency} onChange={(e) => setCurrency(e.target.value)}>
            <MenuItem value="BTC">Bitcoin (BTC)</MenuItem>
            <MenuItem value="XMR">Monero (XMR)</MenuItem>
            <MenuItem value="USDT">Tether (USDT)</MenuItem>
          </Select>
        </FormControl>
        <TextField
          fullWidth
          margin="normal"
          label="Amount"
          type="number"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Cancel</Button>
        <Button onClick={handleSend} color="primary" disabled={!amount}>
          Send
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default CryptoPaymentDialog;
