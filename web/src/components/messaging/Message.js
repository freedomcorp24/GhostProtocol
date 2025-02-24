import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { Paper, Typography } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  message: {
    padding: theme.spacing(1, 2),
    marginBottom: theme.spacing(1),
    maxWidth: '70%',
    wordBreak: 'break-word',
  },
  sent: {
    marginLeft: 'auto',
    backgroundColor: theme.palette.primary.main,
    color: theme.palette.primary.contrastText,
  },
  received: {
    marginRight: 'auto',
    backgroundColor: theme.palette.grey[300],
  },
  timestamp: {
    fontSize: '0.75rem',
    marginTop: theme.spacing(0.5),
    opacity: 0.7,
  },
}));

const Message = ({ content, timestamp, sent }) => {
  const classes = useStyles();

  return (
    <Paper className={`${classes.message} ${sent ? classes.sent : classes.received}`}>
      <Typography>{content}</Typography>
      <Typography className={classes.timestamp}>
        {new Date(timestamp).toLocaleTimeString()}
      </Typography>
    </Paper>
  );
};

export default Message;
