import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Bar, Pie } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  ArcElement
} from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  ArcElement
);

const Dashboard = () => {
  const [barData, setBarData] = useState(null);
  const [fullCount, setFullCount] = useState(0);
  const [partialCount, setPartialCount] = useState(0);
  const [installmentCount, setInstallmentCount] = useState(0);

  const fetchAllFees = async () => {
    try {
      const response = await axios.get('http://localhost:8080/getAllFees');
      console.log('Response:', response.data); // Log the response data
      if (response.data && Array.isArray(response.data)) {
        const filteredData = response.data.filter(item => {
          const feesType = item.Feetype && item.Feetype.toLowerCase(); // Check if item.Feetype exists
          return feesType === 'full' || feesType === 'partial' || feesType === 'installment'; // Include installment
        });

        console.log('Filtered Data:', filteredData); // Log the filtered data

        const fullData = filteredData.filter(item => item.Feetype.toLowerCase() === 'full');
        const partialData = filteredData.filter(item => item.Feetype.toLowerCase() === 'partial');
        const installmentData = filteredData.filter(item => item.Feetype.toLowerCase() === 'installment');

        const labels = Array.from(new Set(filteredData.map(item => item.label)));
        
        const formattedFullData = labels.map(label => {
          const item = fullData.find(data => data.label === label);
          return item ? item.data.reduce((sum, point) => sum + point.y, 0) : 0; // Sum the y-values
        });

        const formattedPartialData = labels.map(label => {
          const item = partialData.find(data => data.label === label);
          return item ? item.data.reduce((sum, point) => sum + point.y, 0) : 0; // Sum the y-values
        });

        const formattedInstallmentData = labels.map(label => {
          const item = installmentData.find(data => data.label === label);
          return item ? item.data.reduce((sum, point) => sum + point.y, 0) : 0; // Sum the y-values
        });

        setBarData({
          labels,
          datasets: [
            {
              label: 'Full Fees',
              data: formattedFullData,
              backgroundColor: '#ADD8E6',
            },
            {
              label: 'Partial Fees',
              data: formattedPartialData,
              backgroundColor: '#FFA07A',
            },
            {
              label: 'Installment Fees',
              data: formattedInstallmentData,
              backgroundColor: '#FFD700', // Added new color for installment
            }
          ]
        });

        setFullCount(fullData.length);
        setPartialCount(partialData.length);
        setInstallmentCount(installmentData.length);
      } else {
        console.error('Error fetching data: response data is invalid');
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchAllFees();
  }, []);

  const pieData = {
    labels: ['Full', 'Partial', 'Installment'],
    datasets: [{
      data: [fullCount, partialCount, installmentCount],
      backgroundColor: ['#90EE90', '#FFA07A', '#FFD700'],
    }],
  };

  return (
    <div>
      {barData && barData.labels.length > 0 && (
        <div style={{ width: '400px', height: '300px' }}>
          <Bar data={barData} />
        </div>
      )}
      {(fullCount > 0 || partialCount > 0 || installmentCount > 0) && (
        <div style={{ width: '400px', height: '300px' }}>
          <Pie data={pieData} />
        </div>
      )}
    </div>
  );
};

export default Dashboard;
