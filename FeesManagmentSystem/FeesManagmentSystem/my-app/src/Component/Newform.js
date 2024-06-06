import React, { useState } from 'react';
import { Button, Col, Form, Row } from 'react-bootstrap';
import axios from 'axios';

const Newform = () => {
  const [formData, setFormData] = useState({
    studentName: '',
    rollNo: '',
    classStandard: '',
    division: '',
    feesType: '',
    feesAmount: '',
    discount: '',
    registrationDate: '',
    feesCollectionType: '',
    transactionId: '',
    gstNo: '',
    lateFeeCharges: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/SaveFees', formData);
      console.log('Form submitted successfully:', response.data);
    } catch (error) {
      console.error('Error submitting the form:', error);
    }
  };

  return (
    <div className="container">
      <Form className="form" onSubmit={handleSubmit}>
        <Row className="mb-3">
          <Col>
            <h3 className="text-center">Fees Enquiry Form</h3>
          </Col>
        </Row>
        <Row className="mb-3">
          <Form.Group as={Col} controlId="formGridstudentName">
            <Form.Label>Student Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter student name"
              name="studentName"
              value={formData.studentName}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group as={Col} controlId="formGridrollNo">
            <Form.Label>Roll No</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter roll number"
              name="rollNo"
              value={formData.rollNo}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group as={Col} controlId="formGridclassStandard">
            <Form.Label>Class Standard</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter class standard"
              name="classStandard"
              value={formData.classStandard}
              onChange={handleChange}
            />
          </Form.Group>
        </Row>

        <Row className="mb-3">
          <Form.Group as={Col} controlId="formGridfeesType">
            <Form.Label>Fees Type</Form.Label>
            <Form.Select
              name="feesType"
              value={formData.feesType}
              onChange={handleChange}
            >
              <option value="pending">Pending</option>
              <option value="partial">Partial</option>
              <option value="completed">Completed</option>
            </Form.Select>
          </Form.Group>

          <Form.Group as={Col} controlId="formGriddivision">
            <Form.Label>Division</Form.Label>
            <Form.Select
              name="division"
              value={formData.division}
              onChange={handleChange}
            >
              <option value="A">A</option>
              <option value="B">B</option>
              <option value="C">C</option>
              <option value="D">D</option>
              <option value="E">E</option>
            </Form.Select>
          </Form.Group>
        </Row>

        <Row className="mb-3">
          <Form.Group as={Col} controlId="formGridfeesCollectionType">
            <Form.Label>Fees Collection Type</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter fees collection type"
              name="feesCollectionType"
              value={formData.feesCollectionType}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group as={Col} controlId="formGridtransactionId">
            <Form.Label>Transaction ID</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter transaction ID"
              name="transactionId"
              value={formData.transactionId}
              onChange={handleChange}
            />
          </Form.Group>

          <Form.Group as={Col} controlId="formGridgstNo">
            <Form.Label>GST No</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter GST number"
              name="gstNo"
              value={formData.gstNo}
              onChange={handleChange}
            />
          </Form.Group>
        </Row>

        <Row className="mb-3">
          <Form.Group as={Col} controlId="formGriddiscount">
            <Form.Label>Discount</Form.Label>
            <Form.Control
              type="number"
              placeholder="Enter discount"
              name="discount"
              value={formData.discount}
              onChange={handleChange}
            />
          </Form.Group>
        </Row>

        <Row className="mb-3">
          <Form.Group as={Col} controlId="formGridlateFeeCharges">
            <Form.Label>Late Fee Charges</Form.Label>
            <Form.Control
              type="number"
              placeholder="Enter late fee charges"
              name="lateFeeCharges"
              value={formData.lateFeeCharges}
              onChange={handleChange}
            />
          </Form.Group>
        </Row>

        <Row className="mb-3">
          <Form.Group as={Col} controlId="formGridregistrationDate">
            <Form.Label>Registration Date</Form.Label>
            <Form.Control
              type="date"
              name="registrationDate"
              value={formData.registrationDate}
              onChange={handleChange}
            />
          </Form.Group>
        </Row>

        <Row className="mb-3">
          <Col className="text-center">
            <Button className="btn" variant="primary" type="submit">
              Submit
            </Button>
          </Col>
        </Row>
      </Form>
    </div>
  );
};

export default Newform;
