import React, { useState, useEffect } from 'react';
import {
  Grid,
  Card,
  CardContent,
  Typography,
  List,
  ListItem,
  ListItemText,
  ListItemSecondaryAction,
  IconButton,
} from '@material-ui/core';
import { Trending } from '@material-ui/icons';

const HashtagPanel = () => {
  const [trendingHashtags, setTrendingHashtags] = useState([]);

  useEffect(() => {
    // Fetch trending hashtags
    const fetchTrending = async () => {
      // Implementation will fetch data from HashtagService
    };
    fetchTrending();
  }, []);

  return (
    <Grid container spacing={3}>
      <Grid item xs={12}>
        <Card>
          <CardContent>
            <Typography variant="h6">Trending Hashtags</Typography>
            <List>
              {trendingHashtags.map((tag, index) => (
                <ListItem key={index}>
                  <ListItemText
                    primary={`#${tag.name}`}
                    secondary={`${tag.count} messages`}
                  />
                  <ListItemSecondaryAction>
                    <IconButton edge="end">
                      <Trending />
                    </IconButton>
                  </ListItemSecondaryAction>
                </ListItem>
              ))}
            </List>
          </CardContent>
        </Card>
      </Grid>
    </Grid>
  );
};

export default HashtagPanel;
