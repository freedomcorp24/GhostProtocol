import React from 'react';
import { I18nextProvider } from 'react-i18next';
import i18n from './i18n';

/**
 * Language Provider component that wraps the application with I18nextProvider
 * to provide internationalization support.
 */
const LanguageProvider = ({ children }) => {
  return (
    <I18nextProvider i18n={i18n}>
      {children}
    </I18nextProvider>
  );
};

export default LanguageProvider;
