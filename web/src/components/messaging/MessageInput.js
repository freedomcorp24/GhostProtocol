import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { TextField, IconButton } from '@material-ui/core';
import { Send } from '@material-ui/icons';

const useStyles = makeStyles((theme) => ({
  root: {
    padding: theme.spacing(2),
    display: 'flex',
    alignItems: 'center',
  },
  input: {
    flexGrow: 1,
    marginRight: theme.spacing(1),
  },
}));

const MessageInput = () => {
  const classes = useStyles();
  const [message, setMessage] = useState('');

  const handleSend = () => {
    if (message.trim()) {
      // Message sending logic will be implemented here
      setMessage('');
    }
  };

  return (
    <div className={classes.root}>
      <TextField
        className={classes.input}
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        placeholder="Type a message"
        variant="outlined"
        size="small"
      />
      <IconButton color="primary" onClick={handleSend}>
        <Send />
      </IconButton>
    </div>
  );
};

export default MessageInput;
