import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import LanguageProvider from './i18n/LanguageProvider';
import './i18n/i18n'; // Import i18n configuration

// Initialize i18next
import './i18n/i18n';

ReactDOM.render(
  <React.StrictMode>
    <LanguageProvider>
      <App />
    </LanguageProvider>
  </React.StrictMode>,
  document.getElementById('root')
);
