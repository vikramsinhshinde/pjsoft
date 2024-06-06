  import React, { useEffect, useState } from 'react';
  import { Table, Button, Modal, Form, Row, Col, InputGroup, FormControl, Dropdown } from 'react-bootstrap'; // Import Dropdown component
  import UserService from '../Service/UserService';
  import '../Css/Datatable.css';

  const Datatable = () => {
    const [users, setUsers] = useState([]);
    const [filteredUsers, setFilteredUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);
    const [showUpdateModal, setShowUpdateModal] = useState(false);
    const [showInfoModal, setShowInfoModal] = useState(false);
    const [error, setError] = useState('');
    const [searchTerm, setSearchTerm] = useState('');

    useEffect(() => {
      fetchUsers();
    }, []);

    const fetchUsers = async () => {
      try {
        const response = await UserService.getUsers();
        setUsers(response.data);
        setFilteredUsers(response.data);
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    };

    const handleSearch = (event) => {
      const term = event.target.value;
      setSearchTerm(term);
      if (term === '') {
        setFilteredUsers(users);
      } else {
        const filtered = users.filter(user =>
          user.studentName.toLowerCase().includes(term.toLowerCase()) ||
          user. standard.toLowerCase().includes(term.toLowerCase()) ||
          user.division.toLowerCase().includes(term.toLowerCase()) ||
          user.rollNo.toString().includes(term.toLowerCase()) ||
          user.feesAmount.toString().includes(term.toLowerCase()) ||
          user.pendingFeesAmount.toString().includes(term.toLowerCase()) ||
          user.feesStatus.toLowerCase().includes(term.toLowerCase()) ||
          user.transactionId.toString().includes(term.toLowerCase())
        );
        setFilteredUsers(filtered);
      }
    };

    const fetchUserById = async (id) => {
      try {
        const response = await UserService.getUserById(id);
        setSelectedUser(response.data);
        setShowUpdateModal(true);
      } catch (error) {
        console.error('Error fetching user by ID:', error);
      }
    };

    const handleDelete = async (id) => {
      const confirmation = window.confirm("Are you sure you want to delete this row?");
      if (confirmation) {
        try {
          await UserService.deleteUser(id);
          fetchUsers();
        } catch (error) {
          console.error('Error deleting user:', error);
        }
      }
    };

    const handleUpdate = async () => {
      if (selectedUser) {
        try {
          await UserService.updateUser(selectedUser.id, selectedUser);
          await fetchUsers();
          setShowUpdateModal(false);
          setShowInfoModal(false);
          setSelectedUser(null);
        } catch (error) {
          setError('Error updating user: ' + error.message);
        }
      }
    };

    const handleInputChange = (e) => {
      const { name, value } = e.target;
      setSelectedUser((prevState) => ({
        ...prevState,
        [name]: value,
      }));
    };

    const handleShowInfo = async (id) => {
      try {
        const response = await UserService.getUserById(id);
        setSelectedUser(response.data);
        setShowInfoModal(true);
      } catch (error) {
        console.error('Error fetching user by ID:', error);
      }
    };// Inside your Datatable component

    // State variables for sorting
    const [divisionSort, setDivisionSort] = useState('');
    const [feesStatusSort, setFeesStatusSort] = useState('');
    
    // Sort by division
    const sortByDivision = (division) => {
      setDivisionSort(division);
      const sortedUsers = [...filteredUsers].sort((a, b) => a.division.localeCompare(b.division));
      setFilteredUsers(sortedUsers);
    };
    
    // Sort by fees status
    const sortByFeesStatus = (feesStatus) => {
      setFeesStatusSort(feesStatus);
      const sortedUsers = [...filteredUsers].sort((a, b) => a.feesStatus.localeCompare(b.feesStatus));
      setFilteredUsers(sortedUsers);
    };
    
    return (
      <div className="container1">
      <div className="headertable1">
        <h3>Student List</h3>
        <InputGroup className="search-input">
          <FormControl
            placeholder="Search by any field"
            value={searchTerm}
            onChange={handleSearch}
          /><Dropdown>
          <Dropdown.Toggle variant="secondary" id="division-dropdown">
            Sort by Division
          </Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item onClick={() => sortByDivision('A')}>A</Dropdown.Item>
            <Dropdown.Item onClick={() => sortByDivision('B')}>B</Dropdown.Item>
            <Dropdown.Item onClick={() => sortByDivision('C')}>C</Dropdown.Item>
            <Dropdown.Item onClick={() => sortByDivision('D')}>D</Dropdown.Item>
            <Dropdown.Item onClick={() => sortByDivision('E')}>E</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
        {/* Dropdown for sorting by fees status */}
        <Dropdown>
          <Dropdown.Toggle variant="secondary" id="fees-status-dropdown">
            Sort by Fees Status
          </Dropdown.Toggle>
          <Dropdown.Menu>
            <Dropdown.Item onClick={() => sortByFeesStatus('Full')}>Full</Dropdown.Item>
            <Dropdown.Item onClick={() => sortByFeesStatus('Completed')}>Completed</Dropdown.Item>
            <Dropdown.Item onClick={() => sortByFeesStatus('Partial')}>Partial</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
          </InputGroup>
        </div>
        <br/>
        <Table striped bordered hover className="table">
          <thead>
            <tr>
              <th>Id</th>
              <th>Student Name</th>
              <th> standard</th>
              <th>Division</th>
              <th>Roll No</th>
              <th>Fees Amount</th>
              <th>Pending Fees Amount</th>
              <th>Fees Status</th>
              <th>Transaction ID</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {filteredUsers.map((user) => (
              <tr key={user.id}>
                <td>{user.id}</td>
                <td>{user.studentName}</td>
                <td>{user. standard}</td>
                <td>{user.division}</td>
                <td>{user.rollNo}</td>
                <td>{user.feesAmount}</td>
                <td>{user.pendingFeesAmount}</td>
                <td>{user.feesStatus}</td>
                <td>{user.transactionId}</td>
                <td>
                  <Button variant="info" className="button button-info" onClick={() => handleShowInfo(user.id)}>Info</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </Table>

        {/* Update Modal */}
        {selectedUser && (
          <Modal show={showUpdateModal} onHide={() => setShowUpdateModal(false)}>
            <Modal.Header closeButton>
              <Modal.Title>Update User</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <Form>
                <Row>
                  <Col>
                    <Form.Group>
                      <Form.Label>Student Name</Form.Label>
                      <Form.Control
                        type="text"
                        name="studentName"
                        value={selectedUser.studentName}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label> standard</Form.Label>
                      <Form.Control
                        type="text"
                        name=" standard"
                        value={selectedUser. standard}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Division</Form.Label>
                      <Form.Control
                        type="text"
                        name="division"
                        value={selectedUser.division}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <Form.Group>
                      <Form.Label>Roll No</Form.Label>
                      <Form.Control
                        type="text"
                        name="rollNo"
                        value={selectedUser.rollNo}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Tuition Fee</Form.Label>
                      <Form.Control
                        type="number"
                        name="tuitionFee"
                        value={selectedUser.tuitionFee}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Admission Fee</Form.Label>
                      <Form.Control
                        type="number"
                        name="admissionFee"
                        value={selectedUser.admissionFee}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <Form.Group>
                      <Form.Label>Exam Fees</Form.Label>
                      <Form.Control
                        type="number"
                        name="examFees"
                        value={selectedUser.examFees}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Uniform Fee</Form.Label>
                      <Form.Control
                        type="number"
                        name="uniformFee"
                        value={selectedUser.uniformFee}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Transport Bus Fee</Form.Label>
                      <Form.Control
                        type="number"
                        name="transportBusFee"
                        value={selectedUser.transportBusFee}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <Form.Group>
                      <Form.Label>Hostel Fee</Form.Label>
                      <Form.Control
                        type="number"
                        name="hostelFee"
                        value={selectedUser.hostelFee}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Building Fund Fee</Form.Label>
                      <Form.Control
                        type="number"
                        name="buildingFundFee"
                        value={selectedUser.buildingFundFee}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
      <Form.Group>
        <Form.Label>Fees Paid Type</Form.Label>
        <Form.Control
          as="select"
          name="feesType"
          value={selectedUser.feesType}
          onChange={handleInputChange}
        >
          <option value="full">Full</option>
          <optgroup label="Partial">
            <option value="firstInstallment">1st Installment</option>
            <option value="secondInstallment">2nd Installment</option>
            <option value="thirdInstallment">3rd Installment</option>
          </optgroup>
          <option value="incomplete">Incomplete</option>
        </Form.Control>
      </Form.Group>
    </Col>
                </Row>
                <Row>
                  <Col>
                    <Form.Group>
                      <Form.Label>Fees Amount</Form.Label>
                      <Form.Control
                        type="number"
                        name="feesAmount"
                        value={selectedUser.feesAmount}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Pending Fees Amount</Form.Label>
                      <Form.Control
                        type="number"
                        name="pendingFeesAmount"
                        value={selectedUser.pendingFeesAmount}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  {/* <Col>
                    <Form.Group>
                      <Form.Label>Fees Status</Form.Label>
                      <Form.Control
                        type="text"
                        name="feesStatus"
                        value={selectedUser.feesStatus}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col> */}
                </Row>
                <Row>
                  <Col>
                    <Form.Group>
                      <Form.Label>Fees Collection Type</Form.Label>
                      <Form.Control
                        type="text"
                        name="feesCollectionType"
                        value={selectedUser.feesCollectionType}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Transaction ID</Form.Label>
                      <Form.Control
                        type="text"
                        name="transactionId"
                        value={selectedUser.transactionId}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Discount</Form.Label>
                      <Form.Control
                        type="number"
                        name="discount"
                        value={selectedUser.discount}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                </Row>
                <Row>
                  <Col>
                    <Form.Group>
                      <Form.Label>Practical Fee</Form.Label>
                      <Form.Control
                        type="number"
                        name="practicalFee"
                        value={selectedUser.practicalFee}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>Late Fee Charges</Form.Label>
                      <Form.Control
                        type="number"
                        name="lateFeeCharges"
                        value={selectedUser.lateFeeCharges}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                  <Col>
                    <Form.Group>
                      <Form.Label>GST</Form.Label>
                      <Form.Control
                        type="text"
                        name="gstNo"
                        value={selectedUser.GST}
                        onChange={handleInputChange}
                      />
                    </Form.Group>
                  </Col>
                </Row>
              </Form>
              {error && <p className="text-danger">{error}</p>}
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={() => setShowUpdateModal(false)}>Close</Button>
              <Button variant="primary" onClick={handleUpdate}>Save changes</Button>
            </Modal.Footer>
          </Modal>
        )}

        {/* Info Modal */}
        {selectedUser && (
          <Modal show={showInfoModal} onHide={() => setShowInfoModal(false)}>
            <Modal.Header closeButton>
              <Modal.Title>Student Information</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              <p><strong>Student Name:</strong> {selectedUser.studentName}</p>
              <p><strong> standard:</strong> {selectedUser. standard}</p>
              <p><strong>Division:</strong> {selectedUser.division}</p>
              <p><strong>Roll No:</strong> {selectedUser.rollNo}</p>
              <p><strong>Tuition Fee:</strong> {selectedUser.tuitionFee}</p>
              <p><strong>Admission Fee:</strong> {selectedUser.admissionFee}</p>
              <p><strong>Exam Fees:</strong> {selectedUser.examFees}</p>
              <p><strong>Uniform Fee:</strong> {selectedUser.uniformFee}</p>
              <p><strong>Transport Bus Fee:</strong> {selectedUser.transportBusFee}</p>
              <p><strong>Hostel Fee:</strong> {selectedUser.hostelFee}</p>
              <p><strong>Building Fund Fee:</strong> {selectedUser.buildingFundFee}</p>
              <p><strong>Fees Type:</strong> {selectedUser.feesType}</p>
              <p><strong>Fees Amount:</strong> {selectedUser.feesAmount}</p>
              <p><strong>Pending Fees Amount:</strong> {selectedUser.pendingFeesAmount}</p>
              <p><strong>Fees Status:</strong> {selectedUser.feesStatus}</p>
              <p><strong>Fees Collection Type:</strong> {selectedUser.feesCollectionType}</p>
              <p><strong>Transaction ID:</strong> {selectedUser.transactionId}</p>
              <p><strong>GST No:</strong> {selectedUser.gstNo}</p>
              <p><strong>Practical Fee:</strong> {selectedUser.practicalFee}</p>
              <p><strong>Discount:</strong> {selectedUser.discount}</p>
              <p><strong>Late Fee Charges:</strong> {selectedUser.lateFeeCharges}</p>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={() => setShowInfoModal(false)}>Close</Button>
              <Button variant="primary" onClick={() => fetchUserById(selectedUser.id)}>Update</Button>
              <Button variant="danger" onClick={() => handleDelete(selectedUser.id)}>Delete</Button>
            </Modal.Footer>
          </Modal>
        )}
      </div>
    );
  };

  export default Datatable;
