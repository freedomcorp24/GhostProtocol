import React, { useState } from 'react';
import {
  Grid,
  Card,
  CardContent,
  Typography,
  Button,
  TextField,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
} from '@material-ui/core';

const FileManagementPanel = () => {
  const [settings, setSettings] = useState({
    maxImageDimension: 2048,
    jpegQuality: 85,
    maxVideoBitrate: 2500,
  });

  const handleSave = () => {
    // Implementation will update FileOptimizationService settings
  };

  return (
    <Grid container spacing={3}>
      <Grid item xs={12}>
        <Card>
          <CardContent>
            <Typography variant="h6">File Optimization Settings</Typography>
            <Grid container spacing={2}>
              <Grid item xs={12} md={4}>
                <TextField
                  fullWidth
                  label="Max Image Dimension"
                  type="number"
                  value={settings.maxImageDimension}
                  onChange={(e) => setSettings({
                    ...settings,
                    maxImageDimension: parseInt(e.target.value)
                  })}
                />
              </Grid>
              <Grid item xs={12} md={4}>
                <TextField
                  fullWidth
                  label="JPEG Quality"
                  type="number"
                  value={settings.jpegQuality}
                  onChange={(e) => setSettings({
                    ...settings,
                    jpegQuality: parseInt(e.target.value)
                  })}
                />
              </Grid>
              <Grid item xs={12} md={4}>
                <TextField
                  fullWidth
                  label="Max Video Bitrate (Kbps)"
                  type="number"
                  value={settings.maxVideoBitrate}
                  onChange={(e) => setSettings({
                    ...settings,
                    maxVideoBitrate: parseInt(e.target.value)
                  })}
                />
              </Grid>
            </Grid>
            <Button
              variant="contained"
              color="primary"
              onClick={handleSave}
              style={{ marginTop: 16 }}
            >
              Save Settings
            </Button>
          </CardContent>
        </Card>
      </Grid>
    </Grid>
  );
};

export default FileManagementPanel;
