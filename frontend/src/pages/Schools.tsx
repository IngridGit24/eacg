import React, { useEffect } from 'react';
import {
  Box,
  Typography,
  Button,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Chip,
  IconButton,
} from '@mui/material';
import {
  Add,
  Edit,
  Visibility,
} from '@mui/icons-material';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { fetchSchools } from '../store/slices/schoolSlice';

const Schools: React.FC = () => {
  const dispatch = useAppDispatch();
  const { schools, loading } = useAppSelector(state => state.school);

  useEffect(() => {
    dispatch(fetchSchools());
  }, [dispatch]);

  const getTypeColor = (type: string) => {
    switch (type) {
      case 'Elementary':
        return 'primary';
      case 'Middle_school':
        return 'secondary';
      case 'High_school':
        return 'success';
      case 'College':
        return 'info';
      default:
        return 'default';
    }
  };

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
        <Typography variant="h4">
          Gestion des Écoles
        </Typography>
        <Button
          variant="contained"
          startIcon={<Add />}
        >
          Nouvelle École
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Matricule</TableCell>
              <TableCell>Nom</TableCell>
              <TableCell>Type</TableCell>
              <TableCell>Localisation</TableCell>
              <TableCell>Classes</TableCell>
              <TableCell>Capacité</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {schools.map((school) => (
              <TableRow key={school.id}>
                <TableCell>{school.matricule}</TableCell>
                <TableCell>{school.name}</TableCell>
                <TableCell>
                  <Chip
                    label={school.typeOfSchool}
                    color={getTypeColor(school.typeOfSchool) as any}
                    size="small"
                  />
                </TableCell>
                <TableCell>{school.location}</TableCell>
                <TableCell>{school.numberOfClass}</TableCell>
                <TableCell>{school.capacityPerClass}</TableCell>
                <TableCell>
                  <IconButton size="small">
                    <Visibility />
                  </IconButton>
                  <IconButton size="small">
                    <Edit />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default Schools;
