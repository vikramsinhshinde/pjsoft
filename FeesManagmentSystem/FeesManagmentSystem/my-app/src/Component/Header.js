import React, { useState } from 'react';
import { AppBar, Toolbar, Typography, Grid, Button, Menu, MenuItem, IconButton } from '@mui/material';
import SchoolIcon from '@mui/icons-material/School'; // Assuming you're using an icon as a logo
import AccountCircle from '@mui/icons-material/AccountCircle';
const Header = () => {
    const [anchorEl, setAnchorEl] = useState(null);
    const handleMenu = (event) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
        setAnchorEl(null);
    };
    const handleLogout = () => {
        // Implement logout logic here
        console.log('Logged out');
        setAnchorEl(null);
    };
    return (
        <AppBar
            position="fixed"
            sx={{
                zIndex: (theme) => theme.zIndex.drawer + 1,
                backgroundImage: 'linear-gradient(to right, #FAD126, #FF564E)', // Use backgroundImage for gradients
            }}
        >
            <Toolbar>
                <Grid container alignItems="center">
                    <Grid item xs={4} display="flex" alignItems="center">
                        <SchoolIcon sx={{ mr: 1 }} /> {/* Replace with your logo */}
                        <Typography variant="h6" noWrap>
                            PjSoftTech
                        </Typography>
                    </Grid>
                    <Grid item xs={4} display="flex" justifyContent="center">
                        <Typography
                            variant="h6"
                            noWrap
                            sx={{
                                background: '#FFFFFF',
                                WebkitBackgroundClip: 'text',
                                WebkitTextFillColor: 'transparent'
                            }}
                        >
                            Fees Management System
                        </Typography>
                    </Grid>
                    <Grid item xs={4} display="flex" justifyContent="flex-end" alignItems="center">
                        <IconButton
                            size="large"
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={handleMenu}
                            color="inherit"
                        >
                            <AccountCircle />
                        </IconButton>
                        <Menu
                            id="menu-appbar"
                            anchorEl={anchorEl}
                            anchorOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            open={Boolean(anchorEl)}
                            onClose={handleClose}
                        >
                            <MenuItem onClick={handleClose}>Profile</MenuItem>
                            <MenuItem onClick={handleClose}>My account</MenuItem>
                            <MenuItem onClick={handleLogout}>Logout</MenuItem>
                        </Menu>
                    </Grid>
                </Grid>
            </Toolbar>
        </AppBar>
    );
};
export default Header;