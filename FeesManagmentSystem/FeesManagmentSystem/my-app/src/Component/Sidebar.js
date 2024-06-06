import React from 'react';
import { Drawer, List, ListItem, ListItemIcon, ListItemText } from '@mui/material';
import DashboardIcon from '@mui/icons-material/Dashboard';
import PersonIcon from '@mui/icons-material/Person';
import ListIcon from '@mui/icons-material/List';
import { Link } from 'react-router-dom'; // Import Link for navigation
import '../Css/Sidebar.css';

const drawerWidth = 240;

const Sidebar = () => {
  return (
    <Drawer
      sx={{
        width: drawerWidth,
        flexShrink: 0,
        '& .MuiDrawer-paper': {
          width: drawerWidth,
          marginTop: '64px', // Assuming your Navbar height is 64px, adjust this value as needed
        },
      }}
      variant="permanent"
    >
      <div style={{ height: '64px' }} /> {/* Placeholder for the Navbar height */}
      <List>
        <ListItem button component={Link} to="/Dashboard"> {/* Navigate to /dashboard route */}
          <ListItemIcon><DashboardIcon /></ListItemIcon>
          <ListItemText primary="Home" />
        </ListItem>
        <ListItem button component={Link} to="/FormExample"> {/* Navigate to /add-employee route */}
          <ListItemIcon><PersonIcon /></ListItemIcon>
          <ListItemText primary="Add Fees" />
        </ListItem>
        <ListItem button component={Link} to="/Datatable"> {/* Navigate to /employee-list route */}
          <ListItemIcon><ListIcon /></ListItemIcon>
          <ListItemText primary="Fees List" />
        </ListItem>

        <ListItem button component={Link} to="/Standardform"> {/* Navigate to /employee-list route */}
          <ListItemIcon><ListIcon /></ListItemIcon>
          <ListItemText primary="Add standard" />
        </ListItem>

        <ListItem button component={Link} to="/Standardtable"> {/* Navigate to /employee-list route */}
          <ListItemIcon><ListIcon /></ListItemIcon>
          <ListItemText primary="standard table" />
        </ListItem>
      </List>
    </Drawer>
  );
};

export default Sidebar;
