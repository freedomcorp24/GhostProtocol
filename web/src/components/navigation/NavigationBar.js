import React from 'react';
import { Link } from 'react-router-dom';
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  IconButton,
  Menu,
  MenuItem,
  Avatar,
  Badge,
} from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import {
  Menu as MenuIcon,
  Notifications,
  AccountCircle,
  ExitToApp,
  Videocam,
  Message,
  Lock,
  Settings,
} from '@material-ui/icons';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
  navLink: {
    color: 'white',
    textDecoration: 'none',
    marginRight: theme.spacing(2),
  },
  navButton: {
    marginLeft: theme.spacing(1),
  },
  avatar: {
    width: theme.spacing(4),
    height: theme.spacing(4),
    cursor: 'pointer',
  },
}));

const NavigationBar = () => {
  const classes = useStyles();
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [notificationAnchorEl, setNotificationAnchorEl] = React.useState(null);
  const [mobileMenuAnchorEl, setMobileMenuAnchorEl] = React.useState(null);
  
  const isLoggedIn = localStorage.getItem('token') !== null;
  const user = JSON.parse(localStorage.getItem('user') || '{}');
  
  const handleProfileMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };
  
  const handleNotificationMenuOpen = (event) => {
    setNotificationAnchorEl(event.currentTarget);
  };
  
  const handleMobileMenuOpen = (event) => {
    setMobileMenuAnchorEl(event.currentTarget);
  };
  
  const handleMenuClose = () => {
    setAnchorEl(null);
    setNotificationAnchorEl(null);
    setMobileMenuAnchorEl(null);
  };
  
  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    window.location.href = '/login.html';
  };
  
  const isAdmin = user.role === 'admin' || user.role === 'super_admin';
  
  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            edge="start"
            className={classes.menuButton}
            color="inherit"
            aria-label="menu"
            onClick={handleMobileMenuOpen}
          >
            <MenuIcon />
          </IconButton>
          
          <Typography variant="h6" className={classes.title}>
            <Link to="/" className={classes.navLink}>
              GhostProtocol
            </Link>
          </Typography>
          
          {isLoggedIn ? (
            <>
              <Button
                color="inherit"
                className={classes.navButton}
                startIcon={<Message />}
                component={Link}
                to="/messages"
              >
                Messages
              </Button>
              
              <Button
                color="inherit"
                className={classes.navButton}
                startIcon={<Videocam />}
                component={Link}
                to="/video-call"
              >
                Video Call
              </Button>
              
              <Button
                color="inherit"
                className={classes.navButton}
                startIcon={<Lock />}
                component={Link}
                to="/vault"
              >
                Vault
              </Button>
              
              {isAdmin && (
                <Button
                  color="inherit"
                  className={classes.navButton}
                  component={Link}
                  to="/admin"
                >
                  Admin
                </Button>
              )}
              
              <IconButton
                color="inherit"
                onClick={handleNotificationMenuOpen}
              >
                <Badge badgeContent={3} color="secondary">
                  <Notifications />
                </Badge>
              </IconButton>
              
              <IconButton
                edge="end"
                color="inherit"
                onClick={handleProfileMenuOpen}
              >
                {user.avatar ? (
                  <Avatar
                    className={classes.avatar}
                    src={user.avatar}
                    alt={user.username}
                  />
                ) : (
                  <AccountCircle />
                )}
              </IconButton>
            </>
          ) : (
            <>
              <Button
                color="inherit"
                component={Link}
                to="/login"
              >
                Login
              </Button>
              <Button
                color="inherit"
                component={Link}
                to="/signup"
              >
                Sign Up
              </Button>
            </>
          )}
        </Toolbar>
      </AppBar>
      
      <Menu
        anchorEl={anchorEl}
        keepMounted
        open={Boolean(anchorEl)}
        onClose={handleMenuClose}
      >
        <MenuItem component={Link} to="/profile" onClick={handleMenuClose}>
          Profile
        </MenuItem>
        <MenuItem component={Link} to="/settings" onClick={handleMenuClose}>
          <Settings fontSize="small" style={{ marginRight: 8 }} />
          Settings
        </MenuItem>
        <MenuItem onClick={handleLogout}>
          <ExitToApp fontSize="small" style={{ marginRight: 8 }} />
          Logout
        </MenuItem>
      </Menu>
      
      <Menu
        anchorEl={notificationAnchorEl}
        keepMounted
        open={Boolean(notificationAnchorEl)}
        onClose={handleMenuClose}
      >
        <MenuItem onClick={handleMenuClose}>
          New message from Alice
        </MenuItem>
        <MenuItem onClick={handleMenuClose}>
          Bob started a video call
        </MenuItem>
        <MenuItem onClick={handleMenuClose}>
          Your subscription will expire soon
        </MenuItem>
      </Menu>
      
      <Menu
        anchorEl={mobileMenuAnchorEl}
        keepMounted
        open={Boolean(mobileMenuAnchorEl)}
        onClose={handleMenuClose}
      >
        <MenuItem component={Link} to="/messages" onClick={handleMenuClose}>
          <Message fontSize="small" style={{ marginRight: 8 }} />
          Messages
        </MenuItem>
        <MenuItem component={Link} to="/video-call" onClick={handleMenuClose}>
          <Videocam fontSize="small" style={{ marginRight: 8 }} />
          Video Call
        </MenuItem>
        <MenuItem component={Link} to="/vault" onClick={handleMenuClose}>
          <Lock fontSize="small" style={{ marginRight: 8 }} />
          Vault
        </MenuItem>
        {isAdmin && (
          <MenuItem component={Link} to="/admin" onClick={handleMenuClose}>
            Admin
          </MenuItem>
        )}
      </Menu>
    </div>
  );
};

export default NavigationBar;
