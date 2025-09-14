import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import { Box } from '@mui/material';
import Navbar from './components/layout/Navbar';
import Sidebar from './components/layout/Sidebar';
import Dashboard from './pages/Dashboard';
import Schools from './pages/Schools';
import Students from './pages/Students';
import Enrollments from './pages/Enrollments';
import Payments from './pages/Payments';
import Academics from './pages/Academics';
import Assessments from './pages/Assessments';
import Reports from './pages/Reports';
import { useAppSelector } from './store/hooks';

function App() {
  const isAuthenticated = useAppSelector(state => state.auth.isAuthenticated);

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  return (
    <Box sx={{ display: 'flex' }}>
      <Navbar />
      <Sidebar />
      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          width: { sm: `calc(100% - 240px)` },
          ml: { sm: '240px' },
          mt: '64px',
        }}
      >
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/schools" element={<Schools />} />
          <Route path="/students" element={<Students />} />
          <Route path="/enrollments" element={<Enrollments />} />
          <Route path="/payments" element={<Payments />} />
          <Route path="/academics" element={<Academics />} />
          <Route path="/assessments" element={<Assessments />} />
          <Route path="/reports" element={<Reports />} />
        </Routes>
      </Box>
    </Box>
  );
}

export default App;
