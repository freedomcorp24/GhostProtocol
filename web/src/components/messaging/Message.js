import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { 
  Paper, 
  Typography, 
  IconButton, 
  Menu, 
  MenuItem, 
  Tooltip, 
  Fade,
  Chip,
  Avatar,
  Box
} from '@material-ui/core';
import { 
  MoreVert, 
  Reply, 
  ContentCopy, 
  Delete, 
  Done, 
  DoneAll,
  Attachment,
  InsertEmoticon
} from '@material-ui/icons';
import ReactMarkdown from 'react-markdown';
import DOMPurify from 'dompurify';

const useStyles = makeStyles((theme) => ({
  messageContainer: {
    display: 'flex',
    flexDirection: 'column',
    marginBottom: theme.spacing(1),
    maxWidth: '80%',
    position: 'relative',
  },
  sentContainer: {
    alignSelf: 'flex-end',
  },
  receivedContainer: {
    alignSelf: 'flex-start',
  },
  message: {
    padding: theme.spacing(1.5, 2),
    borderRadius: theme.spacing(2),
    wordBreak: 'break-word',
    boxShadow: theme.shadows[1],
    position: 'relative',
    transition: 'all 0.2s ease',
    '&:hover': {
      boxShadow: theme.shadows[3],
    },
  },
  sent: {
    backgroundColor: theme.palette.primary.main,
    color: theme.palette.primary.contrastText,
    borderTopRightRadius: theme.spacing(0.5),
  },
  received: {
    backgroundColor: theme.palette.grey[100],
    borderTopLeftRadius: theme.spacing(0.5),
  },
  timestamp: {
    fontSize: '0.7rem',
    marginTop: theme.spacing(0.5),
    opacity: 0.7,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'flex-end',
  },
  statusIcon: {
    fontSize: '0.8rem',
    marginLeft: theme.spacing(0.5),
  },
  menuButton: {
    padding: theme.spacing(0.5),
    position: 'absolute',
    top: 0,
    right: 0,
    opacity: 0,
    transition: 'opacity 0.2s ease',
    color: theme.palette.text.secondary,
  },
  messageHover: {
    '&:hover $menuButton': {
      opacity: 1,
    },
  },
  replyButton: {
    padding: theme.spacing(0.5),
    marginRight: theme.spacing(1),
    opacity: 0,
    transition: 'opacity 0.2s ease',
  },
  actionButtons: {
    display: 'flex',
    justifyContent: 'flex-end',
    marginTop: theme.spacing(0.5),
    opacity: 0,
    transition: 'opacity 0.2s ease',
  },
  messageContainerHover: {
    '&:hover $actionButtons': {
      opacity: 1,
    },
  },
  senderName: {
    fontSize: '0.8rem',
    fontWeight: 'bold',
    marginBottom: theme.spacing(0.5),
  },
  attachmentPreview: {
    marginTop: theme.spacing(1),
    maxWidth: '100%',
    borderRadius: theme.spacing(1),
    overflow: 'hidden',
  },
  attachmentIcon: {
    marginRight: theme.spacing(0.5),
    fontSize: '1rem',
  },
  attachmentChip: {
    margin: theme.spacing(0.5, 0),
    maxWidth: '100%',
  },
  emojiOnly: {
    fontSize: '2rem',
    padding: theme.spacing(1),
  },
  replyReference: {
    padding: theme.spacing(0.5, 1),
    borderLeft: `3px solid ${theme.palette.primary.main}`,
    backgroundColor: theme.palette.background.default,
    borderRadius: theme.spacing(0.5),
    marginBottom: theme.spacing(1),
    fontSize: '0.8rem',
  },
  markdownContent: {
    '& p': {
      margin: 0,
    },
    '& a': {
      color: theme.palette.primary.main,
      textDecoration: 'none',
      '&:hover': {
        textDecoration: 'underline',
      },
    },
    '& code': {
      backgroundColor: 'rgba(0, 0, 0, 0.05)',
      padding: theme.spacing(0.25, 0.5),
      borderRadius: theme.spacing(0.5),
      fontFamily: 'monospace',
    },
  },
}));

const Message = ({ 
  content, 
  timestamp, 
  sent, 
  status = 'delivered', // 'sent', 'delivered', 'read'
  sender = null,
  attachments = [],
  replyTo = null,
  onReply,
  onDelete,
  onCopy
}) => {
  const classes = useStyles();
  const [menuAnchorEl, setMenuAnchorEl] = useState(null);
  const [showFullTimestamp, setShowFullTimestamp] = useState(false);

  const handleMenuOpen = (event) => {
    event.stopPropagation();
    setMenuAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setMenuAnchorEl(null);
  };

  const handleCopy = () => {
    if (onCopy) onCopy(content);
    else navigator.clipboard.writeText(content);
    handleMenuClose();
  };

  const handleDelete = () => {
    if (onDelete) onDelete();
    handleMenuClose();
  };

  const handleReply = () => {
    if (onReply) onReply();
    handleMenuClose();
  };

  const toggleTimestamp = () => {
    setShowFullTimestamp(!showFullTimestamp);
  };

  // Check if message contains only emojis
  const isEmojiOnly = () => {
    const emojiRegex = /^(\p{Emoji}|\s)+$/u;
    return emojiRegex.test(content) && content.length <= 5;
  };

  // Format timestamp
  const formatTimestamp = () => {
    const date = new Date(timestamp);
    if (showFullTimestamp) {
      return date.toLocaleString();
    }
    return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  };

  // Render status icon
  const renderStatusIcon = () => {
    if (!sent) return null;
    
    switch (status) {
      case 'sent':
        return <Done className={classes.statusIcon} />;
      case 'delivered':
        return <DoneAll className={classes.statusIcon} />;
      case 'read':
        return <DoneAll className={classes.statusIcon} style={{ color: '#4FC3F7' }} />;
      default:
        return null;
    }
  };

  // Sanitize and render markdown content
  const renderContent = () => {
    if (isEmojiOnly()) {
      return <Typography className={classes.emojiOnly}>{content}</Typography>;
    }
    
    const sanitizedContent = DOMPurify.sanitize(content);
    return (
      <ReactMarkdown 
        className={classes.markdownContent}
        components={{
          a: ({ node, ...props }) => <a target="_blank" rel="noopener noreferrer" {...props} />
        }}
      >
        {sanitizedContent}
      </ReactMarkdown>
    );
  };

  return (
    <Box 
      className={`${classes.messageContainer} ${sent ? classes.sentContainer : classes.receivedContainer} ${classes.messageContainerHover}`}
      onClick={toggleTimestamp}
    >
      {/* Reply reference */}
      {replyTo && (
        <Paper className={classes.replyReference}>
          <Typography variant="body2" noWrap>
            {replyTo.sender}: {replyTo.content.substring(0, 50)}
            {replyTo.content.length > 50 ? '...' : ''}
          </Typography>
        </Paper>
      )}
      
      {/* Sender name (for received messages in group chats) */}
      {!sent && sender && (
        <Typography className={classes.senderName} color="textSecondary">
          {sender}
        </Typography>
      )}
      
      {/* Message bubble */}
      <Paper 
        className={`${classes.message} ${sent ? classes.sent : classes.received} ${classes.messageHover}`}
        elevation={1}
      >
        {/* Message content */}
        {renderContent()}
        
        {/* Attachments */}
        {attachments.length > 0 && (
          <Box className={classes.attachmentPreview}>
            {attachments.map((attachment, index) => (
              <Chip
                key={index}
                icon={<Attachment className={classes.attachmentIcon} />}
                label={attachment.name || 'Attachment'}
                clickable
                className={classes.attachmentChip}
                size="small"
              />
            ))}
          </Box>
        )}
        
        {/* Menu button */}
        <IconButton 
          className={classes.menuButton}
          size="small"
          onClick={handleMenuOpen}
          aria-label="Message options"
        >
          <MoreVert fontSize="small" />
        </IconButton>
      </Paper>
      
      {/* Timestamp and status */}
      <Typography className={classes.timestamp} color="textSecondary">
        {formatTimestamp()}
        {renderStatusIcon()}
      </Typography>
      
      {/* Action buttons */}
      <Fade in={true}>
        <div className={classes.actionButtons}>
          <Tooltip title="Reply">
            <IconButton 
              className={classes.replyButton} 
              size="small"
              onClick={handleReply}
              aria-label="Reply to message"
            >
              <Reply fontSize="small" />
            </IconButton>
          </Tooltip>
        </div>
      </Fade>
      
      {/* Context menu */}
      <Menu
        anchorEl={menuAnchorEl}
        open={Boolean(menuAnchorEl)}
        onClose={handleMenuClose}
        TransitionComponent={Fade}
      >
        <MenuItem onClick={handleReply}>
          <Reply fontSize="small" style={{ marginRight: 8 }} />
          Reply
        </MenuItem>
        <MenuItem onClick={handleCopy}>
          <ContentCopy fontSize="small" style={{ marginRight: 8 }} />
          Copy
        </MenuItem>
        {sent && (
          <MenuItem onClick={handleDelete}>
            <Delete fontSize="small" style={{ marginRight: 8 }} />
            Delete
          </MenuItem>
        )}
      </Menu>
    </Box>
  );
};

export default Message;
