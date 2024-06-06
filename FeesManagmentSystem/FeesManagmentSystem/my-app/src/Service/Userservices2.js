import axios from 'axios';

const API_URL = 'http://localhost:8080';

class StandardService {
  saveStandard(standardData) {
    return axios.post(`${API_URL}/saveStandered`, standardData);
  }

  getAllStandards() {
    return axios.get(`${API_URL}/getallStandered`);
  }

  getStandardById(id) {
    return axios.get(`${API_URL}/getStanderedById/${id}`);
  }

  updateStandard(id, standardData) {
    return axios.put(`${API_URL}/updateStandered/${id}`, standardData)
      .then(response => {
        return response.data; // Return the updated data from the response
      })
      .catch(error => {
        throw error.response.data; // Throw the error message from the response
      });
  }

  deleteStandard(id) {
    return axios.delete(`${API_URL}/deleteStanderedById/${id}`);
  }
  
  getStandardByName(name) {
    return axios.get(`${API_URL}/getstanderedByName/${name}`);
  }
}

export default new StandardService();
