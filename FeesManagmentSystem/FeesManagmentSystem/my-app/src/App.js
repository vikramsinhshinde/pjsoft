import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'; 
import 'bootstrap/dist/css/bootstrap.min.css';
import FormExample from './Component/FormExample';
import Login from './Component/Login';
import Dashboard from './Component/Dashboard';
import DataTable from './Component/Datatable';
import Header from './Component/Header';
import Sidebar from './Component/Sidebar';
import Standardform from './Component/Standardform';
import Standardtable from './Component/Standardtable'; // Correct import

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <div className="main-content">
          <Sidebar />
          <div className="content">
            <Routes>
              <Route path="/FormExample" element={<FormExample />} />
              <Route path="/DataTable" element={<DataTable />} />
              <Route path="/Login" element={<Login />} />
              <Route path="/Dashboard/*" element={<Dashboard />} />
              <Route path="/Standardform" element={<Standardform />} />
              <Route path="/Standardtable" element={<Standardtable />} /> {/* Added route for Standardtable */}
            </Routes>
          </div>
        </div>
      </Router>
    </div>

    
  );
}

export default App;
