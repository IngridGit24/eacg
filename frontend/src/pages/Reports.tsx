import React from 'react';
import { Box, Typography, Paper } from '@mui/material';

const Reports: React.FC = () => {
  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Rapports et Statistiques
      </Typography>
      <Paper sx={{ p: 3 }}>
        <Typography>
          Module de rapports et statistiques en cours de développement.
        </Typography>
      </Paper>
    </Box>
  );
};

export default Reports;
