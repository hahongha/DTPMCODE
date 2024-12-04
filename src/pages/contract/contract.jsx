// // import * as React from 'react';
// import { styled } from '@mui/material/styles';
// import Table from '@mui/material/Table';
// import TableBody from '@mui/material/TableBody';
// import TableCell, { tableCellClasses } from '@mui/material/TableCell';
// import TableContainer from '@mui/material/TableContainer';
// import TableHead from '@mui/material/TableHead';
// import TableRow from '@mui/material/TableRow';
// import Paper from '@mui/material/Paper';
// import { useEffect, useState } from 'react';
// import { getAllContract } from 'action/contractAction';

// const StyledTableCell = styled(TableCell)(({ theme }) => ({
//   [`&.${tableCellClasses.head}`]: {
//     backgroundColor: theme.palette.common.black,
//     color: theme.palette.common.white,
//   },
//   [`&.${tableCellClasses.body}`]: {
//     fontSize: 14,
//   },
// }));

// const StyledTableRow = styled(TableRow)(({ theme }) => ({
//   '&:nth-of-type(odd)': {
//     backgroundColor: theme.palette.action.hover,
//   },
//   // hide last border
//   '&:last-child td, &:last-child th': {
//     border: 0,
//   },
// }));

// function createData(name, calories, fat, carbs, protein) {
//   return { name, calories, fat, carbs, protein };
// }

// const rows = [
//   createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
//   createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
//   createData('Eclair', 262, 16.0, 24, 6.0),
//   createData('Cupcake', 305, 3.7, 67, 4.3),
//   createData('Gingerbread', 356, 16.0, 49, 3.9),
// ];

// export default function ContractTable() {

//   const[rows, setRows] = useState([]);

//   useEffect(() => {
//     getAllContract().then((response) => {
//       setRows(response.data.data);
//     }).catch(error => {
//       console.error(error);
//     });
//   }, []);
//   const formatDate = (date) => {
//     const d = new Date(date);
//     const day = String(d.getDate()).padStart(2, '0');
//     const month = String(d.getMonth() + 1).padStart(2, '0');
//     const year = d.getFullYear();
//     return `${day}-${month}-${year}`;
//   };
//   return (
//     <TableContainer component={Paper}>
//       <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
//         <TableHead>
//           <TableRow>
//             <TableCell>Tên khách</TableCell>
//             <TableCell align="right">Tên phòng</TableCell>
//             <TableCell align="right">Ngày bắt đầu</TableCell>
//             <TableCell align="right">Ngày kết thúc</TableCell>
//             <TableCell align="right">Trạng thái</TableCell>
//             <TableCell align="right">Tiền đặt cọc</TableCell>
//             <TableCell align="right">Tiền thuê/tháng</TableCell>
//           </TableRow>
//         </TableHead>
//         <TableBody>
//           {rows.map((row) => (
//             <TableRow
//               key={row.contractId}
//               sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
//             >
//               <TableCell component="th" scope="row">
//                 {row.person.fullname}
//               </TableCell>
//               <TableCell align="right">{row.room.roomName}</TableCell>
//               <TableCell align="right">{formatDate(row.startDate)}</TableCell>
//               <TableCell align="right">{formatDate(row.endDate)}</TableCell>
//               <TableCell align="right">{row.contractStatus}</TableCell>
//               <TableCell align="right">{row.contractDeposit}</TableCell>
//               <TableCell align="right">{row.contractRent}</TableCell>
//             </TableRow>
//           ))}
//         </TableBody>
//       </Table>
//     </TableContainer>
//   );
// }
