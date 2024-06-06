import React, { useState, useEffect, useRef } from 'react';
import { Button, TextField, Checkbox, FormControlLabel, MenuItem, Grid, Typography, Container, Snackbar, Box, Paper, FormControl, InputLabel, Select } from '@mui/material';
import axios from 'axios';
import { useReactToPrint } from 'react-to-print';
import ReactToPrint from 'react-to-print';

const Newform = () => {
  const [formData, setFormData] = useState({
    studentName: '',
    rollNo: '',
    standard: '',
    medium: '',
    division: '',
    tuitionFee: '',
    admissionFee: '',
    practicalFee: '',
    computerClassFee: '',
    examFees: '',
    uniformFee: '',
    transportBusFee: '',
    hostelFee: '',
    buildingFundFee: '',
    libraryFee: '',
    sportsFee: '',
    feesAmount: '',
    discount: 0,
    gstCharges: '',
    lateFeeCharges: 0,
    feesType: '',
    installments: [],
    feesCollectionType: '',
    transactionId: '',
    gstNo: '',
    showGstNo: false,
    registrationDate: '',
  });

  const [standards, setStandards] = useState([]);
  const [standardData, setStandardData] = useState({});
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');

  useEffect(() => {
    const fetchStandards = async () => {
      try {
        const response = await axios.get('http://localhost:8080/getallStandered');
        setStandards(response.data);
      } catch (error) {
        console.error('Error fetching standards:', error);
      }
    };

    fetchStandards();
  }, []);

  const fetchStandardData = async (standard) => {
    try {
      const response = await axios.get(`http://localhost:8080/getstanderedByName/${standard}`);
      setStandardData(response.data);
    } catch (error) {
      console.error('Error fetching standard data:', error);
    }
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: type === 'checkbox' ? checked : value
    }));

    if (name === 'standard') {
      fetchStandardData(value);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/SaveFees', formData);
      setSnackbarMessage('Form submitted successfully');
      setSnackbarOpen(true);
      handlePrint();
    } catch (error) {
      console.error('Error submitting the form:', error);
    }
  };

  useEffect(() => {
    if (Object.keys(standardData).length > 0) {
      setFormData((prevData) => ({
        ...prevData,
        ...standardData
      }));
    }
  }, [standardData]);

  const componentRef = useRef();

  const handlePrint = useReactToPrint({
    content: () => componentRef.current
  });

  const handleCloseSnackbar = () => {
    setSnackbarOpen(false);
  };

  return (
    <Container maxWidth="md">
      <Paper>
        <Box p={2}>
          <form onSubmit={handleSubmit}>
            <Typography variant="h4" align="center" gutterBottom>
              Fees Receipt
            </Typography>
            <Grid container spacing={2}>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Student Name"
                  name="studentName"
                  value={formData.studentName}
                  onChange={handleChange}
                  variant="outlined"
                  required
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Roll No"
                  name="rollNo"
                  value={formData.rollNo}
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
                  value={formData.standard || ''}
                  onChange={handleChange}
                  variant="outlined"
                  required
                >
                  {standards.map((standardItem) => (
                    <MenuItem key={standardItem.id} value={standardItem.standard}>
                      {standardItem.standard}
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
                  label="Tuition Fee"
                  type="number"
                  name="tuitionFee"
                  value={formData.tuitionFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Admission Fee"
                  type="number"
                  name="admissionFee"
                  value={formData.admissionFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Practical Fee"
                  type="number"
                  name="practicalFee"
                  value={formData.practicalFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Computer Class Fee"
                  type="number"
                  name="computerClassFee"
                  value={formData.computerClassFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Exam Fees"
                  type="number"
                  name="examFees"
                  value={formData.examFees}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Uniform Fee"
                  type="number"
                  name="uniformFee"
                  value={formData.uniformFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Transport Bus Fee"
                  type="number"
                  name="transportBusFee"
                  value={formData.transportBusFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Hostel Fee"
                  type="number"
                  name="hostelFee"
                  value={formData.hostelFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Building Fund Fee"
                  type="number"
                  name="buildingFundFee"
                  value={formData.buildingFundFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Library Fee"
                  type="number"
                  name="libraryFee"
                  value={formData.libraryFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Sports Fee"
                  type="number"
                  name="sportsFee"
                  value={formData.sportsFee}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Total Fees Amount"
                  type="number"
                  name="feesAmount"
                  value={formData.feesAmount}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Discount"
                  type="number"
                  name="discount"
                  value={formData.discount}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="GST Charges"
                  type="number"
                  name="gstCharges"
                  value={formData.gstCharges}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Late Fee Charges"
                  type="number"
                  name="lateFeeCharges"
                  value={formData.lateFeeCharges}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <FormControl fullWidth>
                  <InputLabel>Fees Paid Type</InputLabel>
                  <Select
                    name="feesType"
                    value={formData.feesType}
                    onChange={handleChange}
                  >
                    <MenuItem value="full">Full</MenuItem>
                    <MenuItem value="partial">Partial</MenuItem>
                  </Select>
                </FormControl>
              </Grid>
              {formData.feesType === 'partial' && (
                <Grid item xs={12} sm={4}>
                  <FormControl fullWidth>
                    <InputLabel>Installment</InputLabel>
                    <Select
                      name="installment"
                      value={formData.installment}
                      onChange={handleChange}
                    >
                      <MenuItem value="">Select Installment</MenuItem>
                      <MenuItem value="1st">1st Installment</MenuItem>
                      <MenuItem value="2nd">2nd Installment</MenuItem>
                      <MenuItem value="3rd">3rd Installment</MenuItem>
                    </Select>
                  </FormControl>
                </Grid>
              )}
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  select
                  label="Fees Collection Type"
                  name="feesCollectionType"
                  value={formData.feesCollectionType}
                  onChange={handleChange}
                  variant="outlined"
                >
                  {['Check', 'Online', 'Cash'].map((option) => (
                    <MenuItem key={option} value={option}>
                      {option}
                    </MenuItem>
                  ))}
                </TextField>
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Transaction ID/CheckNo"
                  name="transactionId"
                  value={formData.transactionId}
                  onChange={handleChange}
                  variant="outlined"
                />
              </Grid>
              <Grid item xs={12} sm={4}>
                <TextField
                  fullWidth
                  label="Registration Date"
                  name="registrationDate"
                  value={formData.registrationDate}
                  onChange={handleChange}
                  variant="outlined"
                  type="date"
                  InputLabelProps={{
                    shrink: true,
                  }}
                />
              </Grid>
              <Grid item xs={12}>
                <FormControlLabel
                  control={
                    <Checkbox
                      name="showGstNo"
                      checked={formData.showGstNo}
                      onChange={handleChange}
                      color="primary"
                    />
                  }
                  label="Show GST No"
                />
              </Grid>
              {formData.showGstNo && (
                <Grid item xs={12} sm={4}>
                  <TextField
                    fullWidth
                    label="GST No"
                    name="gstNo"
                    value={formData.gstNo}
                    onChange={handleChange}
                    variant="outlined"
                  />
                </Grid>
              )}
            </Grid>
            <Box display="flex" justifyContent="space-between" mt={2}>
              <Button variant="contained" color="primary" type="submit" fullWidth size="medium" style={{ width: '45%' }}>
                Submit
              </Button>
              <ReactToPrint
                trigger={() => <Button variant="contained" color="primary" fullWidth size="medium" style={{ width: '45%' }}>Print Receipt</Button>}
                content={() => componentRef.current}
              />
            </Box>
          </form>
        </Box>
      </Paper>
      <div style={{ display: 'none' }}>
        <div ref={componentRef}>
          <Paper style={{ padding: '20px', maxWidth: '500px', margin: '0 auto' }}>
            <Typography variant="h4" align="center" gutterBottom>
              Fees Receipt
            </Typography>
            <Typography variant="subtitle1">Student Name: {formData.studentName}</Typography>
            <Typography variant="subtitle1">Roll No: {formData.rollNo}</Typography>
            <Typography variant="subtitle1">Standard: {formData.standard}</Typography>
            <Typography variant="subtitle1">Medium: {formData.medium}</Typography>
            <Typography variant="subtitle1">Division: {formData.division}</Typography>
            <Typography variant="subtitle1">Fees Type: {formData.feesType}</Typography>
            <Typography variant="subtitle1">Fees Collection Type: {formData.feesCollectionType}</Typography>
            <Typography variant="subtitle1">Transaction ID: {formData.transactionId}</Typography>
            <Typography variant="subtitle1">GST No: {formData.gstNo}</Typography>
            <Typography variant="subtitle1">Late Fee Charges: {formData.lateFeeCharges}</Typography>
            <Typography variant="subtitle1">Discount: {formData.discount}</Typography>
            <Typography variant="subtitle1">Registration Date: {formData.registrationDate}</Typography>
            <Typography variant="subtitle1">Tuition Fee: {formData.tuitionFee}</Typography>
            <Typography variant="subtitle1">Admission Fee: {formData.admissionFee}</Typography>
            <Typography variant="subtitle1">Practical Fee: {formData.practicalFee}</Typography>
            <Typography variant="subtitle1">Computer Class Fee: {formData.computerClassFee}</Typography>
            <Typography variant="subtitle1">Exam Fees: {formData.examFees}</Typography>
            <Typography variant="subtitle1">Uniform Fee: {formData.uniformFee}</Typography>
            <Typography variant="subtitle1">Transport Bus Fee: {formData.transportBusFee}</Typography>
            <Typography variant="subtitle1">Hostel Fee: {formData.hostelFee}</Typography>
            <Typography variant="subtitle1">Building Fund Fee: {formData.buildingFundFee}</Typography>
            <Typography variant="subtitle1">Library Fee: {formData.libraryFee}</Typography>
            <Typography variant="subtitle1">Sports Fee: {formData.sportsFee}</Typography>
            <Typography variant="subtitle1">Total Fees Amount: {formData.feesAmount}</Typography>
            <Typography variant="subtitle1">GST Charges: {formData.gstCharges}</Typography>
          </Paper>
        </div>
      </div>
      <Snackbar
        open={snackbarOpen}
        autoHideDuration={6000}
        onClose={handleCloseSnackbar}
        message={snackbarMessage}
      />
    </Container>
  );
};

export default Newform;
