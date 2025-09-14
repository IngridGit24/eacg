import React, { useEffect, useState } from 'react';
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
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
} from '@mui/material';
import {
  Add,
  Edit,
  Visibility,
  CheckCircle,
  Cancel,
} from '@mui/icons-material';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { fetchApplications, updateApplicationStatus } from '../store/slices/enrollmentSlice';

const Enrollments: React.FC = () => {
  const dispatch = useAppDispatch();
  const { applications, loading } = useAppSelector(state => state.enrollment);
  const [openDialog, setOpenDialog] = useState(false);
  const [selectedApplication, setSelectedApplication] = useState<any>(null);

  useEffect(() => {
    dispatch(fetchApplications());
  }, [dispatch]);

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'PENDING':
        return 'warning';
      case 'APPROVED':
        return 'success';
      case 'REJECTED':
        return 'error';
      case 'UNDER_REVIEW':
        return 'info';
      default:
        return 'default';
    }
  };

  const handleStatusUpdate = (id: number, action: string) => {
    dispatch(updateApplicationStatus({ id, action }));
  };

  const handleViewDetails = (application: any) => {
    setSelectedApplication(application);
    setOpenDialog(true);
  };

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
        <Typography variant="h4">
          Gestion des Inscriptions
        </Typography>
        <Button
          variant="contained"
          startIcon={<Add />}
          onClick={() => setOpenDialog(true)}
        >
          Nouvelle Inscription
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Numéro</TableCell>
              <TableCell>Étudiant</TableCell>
              <TableCell>École</TableCell>
              <TableCell>Niveau</TableCell>
              <TableCell>Date</TableCell>
              <TableCell>Statut</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {applications.map((application) => (
              <TableRow key={application.id}>
                <TableCell>{application.applicationNumber}</TableCell>
                <TableCell>{application.studentId}</TableCell>
                <TableCell>{application.schoolId}</TableCell>
                <TableCell>{application.requestedLevel}</TableCell>
                <TableCell>
                  {new Date(application.applicationDate).toLocaleDateString('fr-FR')}
                </TableCell>
                <TableCell>
                  <Chip
                    label={application.status}
                    color={getStatusColor(application.status) as any}
                    size="small"
                  />
                </TableCell>
                <TableCell>
                  <IconButton
                    size="small"
                    onClick={() => handleViewDetails(application)}
                  >
                    <Visibility />
                  </IconButton>
                  {application.status === 'PENDING' && (
                    <>
                      <IconButton
                        size="small"
                        color="success"
                        onClick={() => handleStatusUpdate(application.id, 'approve')}
                      >
                        <CheckCircle />
                      </IconButton>
                      <IconButton
                        size="small"
                        color="error"
                        onClick={() => handleStatusUpdate(application.id, 'reject')}
                      >
                        <Cancel />
                      </IconButton>
                    </>
                  )}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>

      <Dialog open={openDialog} onClose={() => setOpenDialog(false)} maxWidth="md" fullWidth>
        <DialogTitle>
          {selectedApplication ? 'Détails de l\'inscription' : 'Nouvelle Inscription'}
        </DialogTitle>
        <DialogContent>
          {selectedApplication ? (
            <Box sx={{ mt: 2 }}>
              <Typography><strong>Numéro:</strong> {selectedApplication.applicationNumber}</Typography>
              <Typography><strong>Statut:</strong> {selectedApplication.status}</Typography>
              <Typography><strong>Niveau demandé:</strong> {selectedApplication.requestedLevel}</Typography>
              <Typography><strong>Année académique:</strong> {selectedApplication.academicYear}</Typography>
              {selectedApplication.notes && (
                <Typography><strong>Notes:</strong> {selectedApplication.notes}</Typography>
              )}
            </Box>
          ) : (
            <Box sx={{ mt: 2 }}>
              <TextField
                fullWidth
                label="ID Étudiant"
                margin="normal"
                type="number"
              />
              <TextField
                fullWidth
                label="ID École"
                margin="normal"
                type="number"
              />
              <FormControl fullWidth margin="normal">
                <InputLabel>Niveau</InputLabel>
                <Select>
                  <MenuItem value="PRE_PRIMAIRE">Pré-primaire</MenuItem>
                  <MenuItem value="PRIMAIRE">Primaire</MenuItem>
                  <MenuItem value="COLLEGE">Collège</MenuItem>
                  <MenuItem value="LYCEE">Lycée</MenuItem>
                </Select>
              </FormControl>
              <TextField
                fullWidth
                label="Année académique"
                margin="normal"
                defaultValue="2024-2025"
              />
            </Box>
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setOpenDialog(false)}>
            {selectedApplication ? 'Fermer' : 'Annuler'}
          </Button>
          {!selectedApplication && (
            <Button variant="contained">
              Créer
            </Button>
          )}
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default Enrollments;
