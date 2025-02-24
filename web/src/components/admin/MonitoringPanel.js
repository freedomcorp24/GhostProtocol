import React, { useState, useEffect } from 'react';
import {
  Grid,
  Card,
  CardContent,
  Typography,
  List,
  ListItem,
  ListItemText,
} from '@material-ui/core';

const MonitoringPanel = () => {
  const [stats, setStats] = useState({
    activeUsers: 0,
    messageCount: 0,
    storageUsage: 0,
  });

  useEffect(() => {
    // Fetch monitoring data
    const fetchStats = async () => {
      // Implementation will fetch data from MonitoringService
    };
    fetchStats();
  }, []);

  return (
    <Grid container spacing={3}>
      <Grid item xs={12} md={4}>
        <Card>
          <CardContent>
            <Typography variant="h6">Active Users</Typography>
            <Typography variant="h3">{stats.activeUsers}</Typography>
          </CardContent>
        </Card>
      </Grid>
      <Grid item xs={12} md={4}>
        <Card>
          <CardContent>
            <Typography variant="h6">Messages Today</Typography>
            <Typography variant="h3">{stats.messageCount}</Typography>
          </CardContent>
        </Card>
      </Grid>
      <Grid item xs={12} md={4}>
        <Card>
          <CardContent>
            <Typography variant="h6">Storage Usage</Typography>
            <Typography variant="h3">{`${(stats.storageUsage / 1024 / 1024).toFixed(2)} MB`}</Typography>
          </CardContent>
        </Card>
      </Grid>
    </Grid>
  );
};

export default MonitoringPanel;
