import React from 'react';
import { Box, Typography, Paper } from '@mui/material';

const Payments: React.FC = () => {
  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Gestion des Paiements
      </Typography>
      <Paper sx={{ p: 3 }}>
        <Typography>
          Module de gestion des paiements en cours de développement.
        </Typography>
      </Paper>
    </Box>
  );
};

export default Payments;
