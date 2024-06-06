import axios from 'axios';

const API_URL = 'http://localhost:8080';

class UserService {
   getUsers() {
    return axios.get(`${API_URL}/getAllFees`);
   }

  deleteUser(id) {
    return axios.delete(`${API_URL}/deleteFees/${id}`);
  }

  updateUser(id, userData) {
    return axios.put(`${API_URL}/updateFees/${id}`, userData)
      .then(response => {
        return response.data; // Return the updated data from the response
      })
      .catch(error => {
        throw error.response.data; // Throw the error message from the response
      });
  }
  
  getUserById(id) {
    return axios.get(`${API_URL}/getFeesById/${id}`);
  }
}

export default new UserService();
