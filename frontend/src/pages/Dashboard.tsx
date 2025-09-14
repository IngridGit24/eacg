import React, { useEffect } from 'react';
import {
  Grid,
  Paper,
  Typography,
  Box,
  Card,
  CardContent,
} from '@mui/material';
import {
  School,
  People,
  Assignment,
  Payment,
} from '@mui/icons-material';
import { useAppDispatch, useAppSelector } from '../store/hooks';
import { fetchSchools } from '../store/slices/schoolSlice';
import { fetchStudents } from '../store/slices/studentSlice';
import { fetchApplications } from '../store/slices/enrollmentSlice';

const Dashboard: React.FC = () => {
  const dispatch = useAppDispatch();
  const { schools, loading: schoolsLoading } = useAppSelector(state => state.school);
  const { students, loading: studentsLoading } = useAppSelector(state => state.student);
  const { applications, loading: applicationsLoading } = useAppSelector(state => state.enrollment);

  useEffect(() => {
    dispatch(fetchSchools());
    dispatch(fetchStudents());
    dispatch(fetchApplications());
  }, [dispatch]);

  const stats = [
    {
      title: 'Écoles',
      value: schools.length,
      icon: <School sx={{ fontSize: 40 }} />,
      color: '#1976d2',
    },
    {
      title: 'Étudiants',
      value: students.length,
      icon: <People sx={{ fontSize: 40 }} />,
      color: '#388e3c',
    },
    {
      title: 'Inscriptions',
      value: applications.length,
      icon: <Assignment sx={{ fontSize: 40 }} />,
      color: '#f57c00',
    },
    {
      title: 'Paiements',
      value: '0', // Will be implemented with payment service
      icon: <Payment sx={{ fontSize: 40 }} />,
      color: '#d32f2f',
    },
  ];

  return (
    <Box>
      <Typography variant="h4" gutterBottom>
        Tableau de Bord
      </Typography>
      
      <Grid container spacing={3}>
        {stats.map((stat, index) => (
          <Grid item xs={12} sm={6} md={3} key={index}>
            <Card>
              <CardContent>
                <Box sx={{ display: 'flex', alignItems: 'center', mb: 2 }}>
                  <Box sx={{ color: stat.color, mr: 2 }}>
                    {stat.icon}
                  </Box>
                  <Box>
                    <Typography variant="h4" component="div">
                      {stat.value}
                    </Typography>
                    <Typography color="text.secondary">
                      {stat.title}
                    </Typography>
                  </Box>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        ))}
        
        <Grid item xs={12} md={8}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Activité Récente
            </Typography>
            <Typography color="text.secondary">
              Aucune activité récente pour le moment.
            </Typography>
          </Paper>
        </Grid>
        
        <Grid item xs={12} md={4}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Notifications
            </Typography>
            <Typography color="text.secondary">
              Aucune notification pour le moment.
            </Typography>
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Dashboard;
