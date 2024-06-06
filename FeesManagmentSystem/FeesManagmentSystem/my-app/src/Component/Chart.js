// import React, { useState, useEffect, useMemo } from 'react';
// import { Chart } from 'react-charts';
// import axios from 'axios';

// function Dashboard() {
//   const [data, setData] = useState([]);

//   useEffect(() => {
//     // Replace 'API_URL' with your actual API endpoint
//     axios.get('http://localhost:8080/getAllFees')
//       .then(response => {
//         // Assuming the response data is in the correct format for the chart
//         // Modify this part based on the actual structure of the API response
//         const formattedData = response.data.map(series => ({
//           label: series.label,
//           data: series.data.map(point => [point.x, point.y])
//         }));
//         setData(formattedData);
//       })
//       .catch(error => {
//         console.error('Error fetching data:', error);
//       });
//   }, []);

//   const chartData = useMemo(() => data, [data]);

//   const axes = useMemo(
//     () => [
//       { primary: true, type: 'linear', position: 'bottom' },
//       { type: 'linear', position: 'left' }
//     ],
//     []
//   );

//   return (
//     <div
//       style={{
//         width: '400px',
//         height: '300px'
//       }}
//     >
//       <Chart data={chartData} axes={axes} />
//     </div>
//   );
// }

// export default Dashboard;
