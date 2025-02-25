import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { 
  FormControl, 
  InputLabel, 
  Select, 
  MenuItem, 
  Typography, 
  Paper, 
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  List,
  ListItem,
  ListItemText,
  ListItemIcon
} from '@material-ui/core';
import { useTranslation } from 'react-i18next';
import { Language, Check } from '@material-ui/icons';

const useStyles = makeStyles((theme) => ({
  root: {
    padding: theme.spacing(2),
    marginBottom: theme.spacing(2),
  },
  formControl: {
    margin: theme.spacing(1),
    minWidth: 200,
  },
  title: {
    marginBottom: theme.spacing(2),
  },
  languageItem: {
    cursor: 'pointer',
    '&:hover': {
      backgroundColor: theme.palette.action.hover,
    },
  },
  selectedLanguage: {
    backgroundColor: theme.palette.action.selected,
  },
  languageIcon: {
    marginRight: theme.spacing(1),
  },
  languageNative: {
    opacity: 0.7,
    fontSize: '0.9rem',
  },
}));

// Language options with native names
const languages = [
  { code: 'en', name: 'English', nativeName: 'English' },
  { code: 'es', name: 'Spanish', nativeName: 'Español' },
  { code: 'fr', name: 'French', nativeName: 'Français' },
  { code: 'de', name: 'German', nativeName: 'Deutsch' },
  { code: 'zh', name: 'Chinese', nativeName: '中文' },
  { code: 'ru', name: 'Russian', nativeName: 'Русский' },
  { code: 'ar', name: 'Arabic', nativeName: 'العربية' },
];

const LanguageSwitcher = () => {
  const classes = useStyles();
  const { t, i18n } = useTranslation();
  const [currentLanguage, setCurrentLanguage] = useState(i18n.language || 'en');
  const [dialogOpen, setDialogOpen] = useState(false);

  // Update state when i18n language changes
  useEffect(() => {
    setCurrentLanguage(i18n.language);
  }, [i18n.language]);

  // Handle language change
  const handleLanguageChange = (event) => {
    const langCode = event.target.value;
    changeLanguage(langCode);
  };

  // Change language
  const changeLanguage = (langCode) => {
    i18n.changeLanguage(langCode);
    setCurrentLanguage(langCode);
    // Save language preference to localStorage
    localStorage.setItem('i18nextLng', langCode);
  };

  // Open language selection dialog
  const handleOpenDialog = () => {
    setDialogOpen(true);
  };

  // Close language selection dialog
  const handleCloseDialog = () => {
    setDialogOpen(false);
  };

  // Select language from dialog
  const handleSelectLanguage = (langCode) => {
    changeLanguage(langCode);
    handleCloseDialog();
  };

  // Get current language display name
  const getCurrentLanguageDisplay = () => {
    const lang = languages.find(lang => lang.code === currentLanguage) || languages[0];
    return `${lang.name} (${lang.nativeName})`;
  };

  return (
    <Paper className={classes.root}>
      <Typography variant="h6" className={classes.title}>
        {t('settings.languageSettings')}
      </Typography>
      
      <Typography variant="body2" gutterBottom>
        {t('common.language')}: <strong>{getCurrentLanguageDisplay()}</strong>
      </Typography>
      
      <Button 
        variant="outlined" 
        color="primary" 
        startIcon={<Language />}
        onClick={handleOpenDialog}
      >
        {t('settings.languageSettings')}
      </Button>
      
      {/* Language selection dialog */}
      <Dialog 
        open={dialogOpen} 
        onClose={handleCloseDialog}
        aria-labelledby="language-dialog-title"
      >
        <DialogTitle id="language-dialog-title">{t('settings.languageSettings')}</DialogTitle>
        <DialogContent>
          <List>
            {languages.map((lang) => (
              <ListItem 
                key={lang.code}
                button
                onClick={() => handleSelectLanguage(lang.code)}
                className={`${classes.languageItem} ${currentLanguage === lang.code ? classes.selectedLanguage : ''}`}
              >
                <ListItemIcon>
                  {currentLanguage === lang.code ? <Check /> : <Language />}
                </ListItemIcon>
                <ListItemText 
                  primary={lang.name} 
                  secondary={lang.nativeName !== lang.name ? lang.nativeName : null}
                />
              </ListItem>
            ))}
          </List>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseDialog} color="primary">
            {t('common.close')}
          </Button>
        </DialogActions>
      </Dialog>
      
      {/* Alternative dropdown selector */}
      <FormControl className={classes.formControl}>
        <InputLabel id="language-select-label">{t('common.language')}</InputLabel>
        <Select
          labelId="language-select-label"
          id="language-select"
          value={currentLanguage}
          onChange={handleLanguageChange}
        >
          {languages.map((lang) => (
            <MenuItem key={lang.code} value={lang.code}>
              {lang.name} ({lang.nativeName})
            </MenuItem>
          ))}
        </Select>
      </FormControl>
    </Paper>
  );
};

export default LanguageSwitcher;
