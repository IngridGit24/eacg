import React from 'react';
import {
  Drawer,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Divider,
  Box,
} from '@mui/material';
import {
  Dashboard,
  School,
  People,
  Assignment,
  Payment,
  Book,
  Assessment,
  BarChart,
} from '@mui/icons-material';
import { useNavigate, useLocation } from 'react-router-dom';

const drawerWidth = 240;

const menuItems = [
  { text: 'Tableau de Bord', icon: <Dashboard />, path: '/dashboard' },
  { text: 'Écoles', icon: <School />, path: '/schools' },
  { text: 'Étudiants', icon: <People />, path: '/students' },
  { text: 'Inscriptions', icon: <Assignment />, path: '/enrollments' },
  { text: 'Paiements', icon: <Payment />, path: '/payments' },
  { text: 'Académique', icon: <Book />, path: '/academics' },
  { text: 'Évaluations', icon: <Assessment />, path: '/assessments' },
  { text: 'Rapports', icon: <BarChart />, path: '/reports' },
];

const Sidebar: React.FC = () => {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <Drawer
      variant="permanent"
      sx={{
        width: drawerWidth,
        flexShrink: 0,
        [`& .MuiDrawer-paper`]: {
          width: drawerWidth,
          boxSizing: 'border-box',
          top: '64px', // Height of the AppBar
        },
      }}
    >
      <Box sx={{ overflow: 'auto' }}>
        <List>
          {menuItems.map((item) => (
            <ListItem key={item.text} disablePadding>
              <ListItemButton
                selected={location.pathname === item.path}
                onClick={() => navigate(item.path)}
              >
                <ListItemIcon>{item.icon}</ListItemIcon>
                <ListItemText primary={item.text} />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
        <Divider />
      </Box>
    </Drawer>
  );
};

export default Sidebar;
