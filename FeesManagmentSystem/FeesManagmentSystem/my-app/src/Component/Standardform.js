import React, { useState } from 'react';
import { Button, TextField, Grid, Typography, Container, Card, CardContent, MenuItem } from '@mui/material';
import axios from 'axios';
import '../Css/Standardform.css';

const StandardForm = () => {
  const [formData, setFormData] = useState({
    tuitionFee: '',
    admissionFee: '',
    practicalFee: '',
    standard: '',
    medium: '', // Added medium field
    division: '', // Added division field
    computerClassFee: '',
    examFees: '',
    uniformFee: '',
    transportBusFee: '',
    hostelFee: '',
    buildingFundFee: '',
    libraryFee: '', // Added library fee field
    sportsFee: '' // Added sports fee field
  });

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: type === 'checkbox' ? checked : value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/saveStandered', formData);
      console.log('Form submitted successfully:', response.data);
    } catch (error) {
      console.error('Error submitting the form:', error);
    }
  };

  return (
    <Container maxWidth="md">
      <Card>
        <CardContent>
          <form onSubmit={handleSubmit}>
            <Typography variant="h4" align="center" gutterBottom>
              Standard Form
            </Typography>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Tuition Fee"
                  name="tuitionFee"
                  type="number"
                  value={formData.tuitionFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Admission Fee"
                  name="admissionFee"
                  type="number"
                  value={formData.admissionFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Practical Fee"
                  name="practicalFee"
                  type="number"
                  value={formData.practicalFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  select
                  label="Standard"
                  name="standard"
                  value={formData.standard}
                  onChange={handleChange}
                  variant="outlined"
                  required
                >
                  {Array.from({ length: 15 }, (_, index) => (
                    <MenuItem key={index + 1} value={index + 1}>
                      {index + 1}
                    </MenuItem>
                  ))}
                </TextField>
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  select
                  label="Medium"
                  name="medium"
                  value={formData.medium}
                  onChange={handleChange}
                  variant="outlined"
                  required
                >
                  {['English', 'Hindi', 'Other'].map((option) => (
                    <MenuItem key={option} value={option}>
                      {option}
                    </MenuItem>
                  ))}
                </TextField>
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  select
                  label="Division"
                  name="division"
                  value={formData.division}
                  onChange={handleChange}
                  variant="outlined"
                >
                  {['A', 'B', 'C', 'D', 'E'].map((option) => (
                    <MenuItem key={option} value={option}>
                      {option}
                    </MenuItem>
                  ))}
                </TextField>
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Computer Class Fee"
                  name="computerClassFee"
                  type="number"
                  value={formData.computerClassFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Exam Fees"
                  name="examFees"
                  type="number"
                  value={formData.examFees}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Uniform Fee"
                  name="uniformFee"
                  type="number"
                  value={formData.uniformFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Transport Bus Fee"
                  name="transportBusFee"
                  type="number"
                  value={formData.transportBusFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Hostel Fee"
                  name="hostelFee"
                  type="number"
                  value={formData.hostelFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Building Fund Fee"
                  name="buildingFundFee"
                  type="number"
                  value={formData.buildingFundFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Library Fee"
                  name="libraryFee"
                  type="number"
                  value={formData.libraryFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Sports Fee"
                  name="sportsFee"
                  type="number"
                  value={formData.sportsFee}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12}>
                <Button variant="contained" color="primary" type="submit">
                  Submit
                </Button>
              </Grid>
            </Grid>
          </form>
        </CardContent>
      </Card>
    </Container>
  );
};

export default StandardForm;
