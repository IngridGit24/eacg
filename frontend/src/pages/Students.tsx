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
  Avatar,
  IconButton,
} from '@mui/material';
import {
  Add,
  Edit,
  Visibility,
} from '@mui/icons-material';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { fetchStudents } from '../store/slices/studentSlice';

const Students: React.FC = () => {
  const dispatch = useAppDispatch();
  const { students, loading } = useAppSelector(state => state.student);

  useEffect(() => {
    dispatch(fetchStudents());
  }, [dispatch]);

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
        <Typography variant="h4">
          Gestion des Étudiants
        </Typography>
        <Button
          variant="contained"
          startIcon={<Add />}
        >
          Nouvel Étudiant
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Photo</TableCell>
              <TableCell>Matricule</TableCell>
              <TableCell>Nom</TableCell>
              <TableCell>Prénom</TableCell>
              <TableCell>Date de Naissance</TableCell>
              <TableCell>Niveau</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {students.map((student) => (
              <TableRow key={student.id}>
                <TableCell>
                  <Avatar src={student.pictureUrl}>
                    {student.name?.charAt(0)}
                  </Avatar>
                </TableCell>
                <TableCell>{student.matricule}</TableCell>
                <TableCell>{student.name}</TableCell>
                <TableCell>{student.lastName}</TableCell>
                <TableCell>
                  {new Date(student.birthDate).toLocaleDateString('fr-FR')}
                </TableCell>
                <TableCell>{student.level}</TableCell>
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

export default Students;
