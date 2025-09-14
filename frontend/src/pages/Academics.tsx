import React from 'react';
import { Box, Typography, Paper } from '@mui/material';

const Academics: React.FC = () => {
  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Gestion Académique
      </Typography>
      <Paper sx={{ p: 3 }}>
        <Typography>
          Module de gestion académique en cours de développement.
        </Typography>
      </Paper>
    </Box>
  );
};

export default Academics;
