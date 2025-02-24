import React, { useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {
  Container,
  Paper,
  Tabs,
  Tab,
  Typography,
  Box
} from '@material-ui/core';
import MonitoringPanel from './MonitoringPanel';
import FileManagementPanel from './FileManagementPanel';
import HashtagPanel from './HashtagPanel';

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.paper,
    marginTop: theme.spacing(3),
  },
  paper: {
    padding: theme.spacing(3),
  },
}));

function TabPanel(props) {
  const { children, value, index, ...other } = props;
  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      {...other}
    >
      {value === index && (
        <Box p={3}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

const AdminPanel = () => {
  const classes = useStyles();
  const [value, setValue] = useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Container>
      <Paper className={classes.root}>
        <Tabs
          value={value}
          onChange={handleChange}
          indicatorColor="primary"
          textColor="primary"
          centered
        >
          <Tab label="Monitoring" />
          <Tab label="File Management" />
          <Tab label="Hashtags" />
        </Tabs>
        <TabPanel value={value} index={0}>
          <MonitoringPanel />
        </TabPanel>
        <TabPanel value={value} index={1}>
          <FileManagementPanel />
        </TabPanel>
        <TabPanel value={value} index={2}>
          <HashtagPanel />
        </TabPanel>
      </Paper>
    </Container>
  );
};

export default AdminPanel;
