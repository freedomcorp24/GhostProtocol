import React, { useState, useEffect, useRef } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { TextField, IconButton, Tooltip, Badge } from '@material-ui/core';
import { Send, AttachFile, EmojiEmotions, Mic } from '@material-ui/icons';
import 'emoji-mart/css/emoji-mart.css';
import { Picker } from 'emoji-mart';

const useStyles = makeStyles((theme) => ({
  root: {
    padding: theme.spacing(2),
    display: 'flex',
    alignItems: 'center',
    borderTop: `1px solid ${theme.palette.divider}`,
  },
  input: {
    flexGrow: 1,
    marginRight: theme.spacing(1),
  },
  actions: {
    display: 'flex',
    alignItems: 'center',
  },
  emojiPicker: {
    position: 'absolute',
    bottom: '80px',
    right: '20px',
    zIndex: 100,
    boxShadow: theme.shadows[3],
  },
  fileInput: {
    display: 'none',
  },
  draftIndicator: {
    fontSize: '0.75rem',
    color: theme.palette.text.secondary,
    marginLeft: theme.spacing(1),
  },
}));

const MessageInput = ({ onSendMessage, conversationId }) => {
  const classes = useStyles();
  const [message, setMessage] = useState('');
  const [showEmojiPicker, setShowEmojiPicker] = useState(false);
  const [attachments, setAttachments] = useState([]);
  const [isDraftSaved, setIsDraftSaved] = useState(false);
  const fileInputRef = useRef(null);
  const emojiPickerRef = useRef(null);
  const inputRef = useRef(null);

  // Load draft message from local storage when conversation changes
  useEffect(() => {
    const savedDraft = localStorage.getItem(`draft_${conversationId}`);
    if (savedDraft) {
      setMessage(savedDraft);
      setIsDraftSaved(true);
    } else {
      setMessage('');
      setIsDraftSaved(false);
    }
  }, [conversationId]);

  // Save draft message to local storage
  useEffect(() => {
    const draftTimer = setTimeout(() => {
      if (message.trim()) {
        localStorage.setItem(`draft_${conversationId}`, message);
        setIsDraftSaved(true);
      } else {
        localStorage.removeItem(`draft_${conversationId}`);
        setIsDraftSaved(false);
      }
    }, 1000);

    return () => clearTimeout(draftTimer);
  }, [message, conversationId]);

  // Close emoji picker when clicking outside
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (emojiPickerRef.current && !emojiPickerRef.current.contains(event.target)) {
        setShowEmojiPicker(false);
      }
    };

    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  const handleSend = () => {
    if (message.trim() || attachments.length > 0) {
      onSendMessage({
        text: message.trim(),
        attachments: attachments,
        timestamp: new Date().toISOString(),
      });
      setMessage('');
      setAttachments([]);
      localStorage.removeItem(`draft_${conversationId}`);
      setIsDraftSaved(false);
      inputRef.current.focus();
    }
  };

  const handleKeyPress = (e) => {
    // Send message on Enter key (without Shift key for new line)
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      handleSend();
    }
  };

  const handleEmojiSelect = (emoji) => {
    setMessage((prev) => prev + emoji.native);
    inputRef.current.focus();
  };

  const handleAttachmentClick = () => {
    fileInputRef.current.click();
  };

  const handleFileChange = (e) => {
    const newFiles = Array.from(e.target.files);
    setAttachments((prev) => [...prev, ...newFiles]);
    // Reset file input
    e.target.value = null;
  };

  const handleRemoveAttachment = (index) => {
    setAttachments((prev) => prev.filter((_, i) => i !== index));
  };

  return (
    <div className={classes.root}>
      {/* File input (hidden) */}
      <input
        type="file"
        multiple
        ref={fileInputRef}
        onChange={handleFileChange}
        className={classes.fileInput}
        aria-label="Attach files"
      />
      
      {/* Emoji picker */}
      {showEmojiPicker && (
        <div ref={emojiPickerRef} className={classes.emojiPicker}>
          <Picker
            set="apple"
            title="Pick your emoji"
            emoji="point_up"
            onSelect={handleEmojiSelect}
            showPreview={false}
            showSkinTones={false}
            emojiTooltip={true}
            autoFocus={true}
          />
        </div>
      )}
      
      {/* Attachment previews would go here */}
      {attachments.length > 0 && (
        <div className={classes.attachmentPreviews}>
          {attachments.map((file, index) => (
            <div key={index} className={classes.attachmentPreview}>
              {file.name}
              <IconButton size="small" onClick={() => handleRemoveAttachment(index)}>
                &times;
              </IconButton>
            </div>
          ))}
        </div>
      )}
      
      {/* Message input */}
      <TextField
        className={classes.input}
        value={message}
        onChange={(e) => setMessage(e.target.value)}
        onKeyPress={handleKeyPress}
        placeholder="Type a message"
        variant="outlined"
        size="small"
        multiline
        maxRows={4}
        inputRef={inputRef}
        aria-label="Message input"
        InputProps={{
          endAdornment: isDraftSaved && (
            <span className={classes.draftIndicator}>Draft saved</span>
          ),
        }}
      />
      
      {/* Action buttons */}
      <div className={classes.actions}>
        <Tooltip title="Attach files">
          <IconButton onClick={handleAttachmentClick} aria-label="Attach files">
            <Badge badgeContent={attachments.length} color="primary">
              <AttachFile />
            </Badge>
          </IconButton>
        </Tooltip>
        
        <Tooltip title="Add emoji">
          <IconButton 
            onClick={() => setShowEmojiPicker(!showEmojiPicker)} 
            aria-label="Add emoji"
            aria-expanded={showEmojiPicker}
          >
            <EmojiEmotions />
          </IconButton>
        </Tooltip>
        
        <Tooltip title="Voice message">
          <IconButton aria-label="Record voice message">
            <Mic />
          </IconButton>
        </Tooltip>
        
        <Tooltip title="Send message (Enter)">
          <IconButton 
            color="primary" 
            onClick={handleSend}
            disabled={!message.trim() && attachments.length === 0}
            aria-label="Send message"
          >
            <Send />
          </IconButton>
        </Tooltip>
      </div>
    </div>
  );
};

export default MessageInput;
