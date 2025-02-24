import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { List } from '@material-ui/core';
import Message from './Message';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    overflow: 'auto',
    padding: theme.spacing(2),
  },
}));

const MessageList = () => {
  const classes = useStyles();

  return (
    <List className={classes.root}>
      {/* Messages will be rendered here */}
    </List>
  );
};

export default MessageList;
