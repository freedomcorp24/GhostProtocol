import React from 'react';
import { ThemeProvider } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import theme from './styles/theme';
import MessagingContainer from './components/messaging/MessagingContainer';

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <MessagingContainer />
    </ThemeProvider>
  );
};

export default App;
