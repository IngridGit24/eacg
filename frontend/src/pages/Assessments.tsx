import React from 'react';
import { Box, Typography, Paper } from '@mui/material';

const Assessments: React.FC = () => {
  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Gestion des Évaluations
      </Typography>
      <Paper sx={{ p: 3 }}>
        <Typography>
          Module de gestion des évaluations en cours de développement.
        </Typography>
      </Paper>
    </Box>
  );
};

export default Assessments;
