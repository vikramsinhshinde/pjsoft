import React, { useState } from 'react';
import { Form, Button, Container, Row, Col, Card } from 'react-bootstrap';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../Css/Login.css';
import logo from '../img/logo.jpg';
function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await loginUser(email, password);
            console.log('Login successful!', response.data);
            navigate('/dashboard');
        } catch (error) {
            console.error('Error logging in:', error.response.data);
            setError('Invalid email or password');
        }
    };
    const loginUser = async (email, password) => {
        return axios.post('http://192.168.1.58:8080/saveAdmin', {
            email: email,
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
                            <h1 className="text-center mb-4">Welcome to PjsoftTech</h1>
                            <Form onSubmit={handleSubmit} className="custom-form">
                                <Form.Group controlId="formBasicEmail">
                                    <Form.Label>Email address</Form.Label>
                                    <Form.Control type="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder="Enter email" />
                                </Form.Group>
                                <Form.Group controlId="formBasicPassword">
                                    <Form.Label>Password</Form.Label>
                                    <Form.Control type="password" value={password} onChange={(e) => setPassword(e.target.value)} placeholder="Password" />
                                </Form.Group>
                                {error && <p className="text-danger">{error}</p>}
                                <Button variant="primary" type="submit" className="w-100 mb-2">
                                    Login
                                </Button>
                                <Button variant="secondary" onClick={() => navigate('/signup')} className="w-100">
                                    Sign Up
                                </Button>
                                <a href="/forgot-password" className="forgot-password-link mt-2 d-block text-center">Forgot Password?</a>
                            </Form>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}
export default Login;