import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { ThemeProvider } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import theme from './styles/theme';
import MessagingContainer from './components/messaging/MessagingContainer';
import NavigationBar from './components/navigation/NavigationBar';
import VideoCallPage from './components/video-call/VideoCallPage';
import PrivateRoute from './components/auth/PrivateRoute';

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <NavigationBar />
        <Switch>
          <Route exact path="/" component={MessagingContainer} />
          <PrivateRoute path="/video-call" component={VideoCallPage} />
          <PrivateRoute path="/messages" component={MessagingContainer} />
          <PrivateRoute path="/vault" component={() => <div>Vault Page</div>} />
          <PrivateRoute path="/profile" component={() => <div>Profile Page</div>} />
          <PrivateRoute path="/settings" component={() => <div>Settings Page</div>} />
          <PrivateRoute path="/admin" component={() => <div>Admin Dashboard</div>} />
          <Route component={() => <div>404 - Page Not Found</div>} />
        </Switch>
      </Router>
    </ThemeProvider>
  );
};

export default App;
