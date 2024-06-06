import React, { useState } from 'react';
import { Form, Button, Container, Row, Col, Card } from 'react-bootstrap';
import axios from 'axios';
import '../Css/Signup.css';
import logo from '../img/logo.jpg';
function Signup() {
    const [email, setEmail] = useState('');
    const [phonenumber, setPhoneNumber] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await signupUser(email, phonenumber, password);
            console.log('Signup successful!', response.data);
        } catch (error) {
            console.error('Error signing up:', error.response.data);
            setError('Error signing up. Please try again.');
        }
    };
    const signupUser = async (email, phonenumber, password) => {
        return axios.post('http://192.168.1.58/saveAdmin', {
            email: email,
            phonenumber: phonenumber,
            password: password
        });
    };
    return (
        <Container fluid className="d-flex justify-content-center align-items-center vh-100">
            <Row className="w-100">
                <Col className="d-flex justify-content-center">
                    <Card className="p-4">
                        <Card.Body>
                            <img src={logo} alt="Logo" className="logo mb-4" />
                            <h1 className="text-center mb-4">Sign Up for PjsoftTech</h1>
                            <Form onSubmit={handleSubmit} className="custom-form">
                                <Form.Group controlId="formBasicEmail">
                                    <Form.Label>Email address</Form.Label>
                                    <Form.Control type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter email" />
                                </Form.Group>
                                <Form.Group controlId="formBasicphonenumber">
                                    <Form.Label>PhoneNumber</Form.Label>
                                    <Form.Control type="phonenumber" value={phonenumber} onChange={(e) => setPhoneNumber(e.target.value)} placeholder="Phone Number" />
                                </Form.Group>
                                <Form.Group controlId="formBasicPassword">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" />
                                </Form.Group>
                                {error && <p className="text-danger">{error}</p>}
                                <Button variant="primary" type="submit" className="w-100 mb-2">
                                    Sign Up
                                </Button>
                                <a href="/" className="forgot-password-link mt-2 d-block text-center">Already have an account? Log In</a>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}
export default Signup;