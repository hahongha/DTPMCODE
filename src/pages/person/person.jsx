// import * as React from 'react';
import { styled } from '@mui/material/styles';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import { useEffect, useState } from 'react';
import { getAllPerson } from 'action/personAction';
export default function PersonTable() {

  const[rows, setRows] = useState([]);

  useEffect(() => {
    getAllPerson().then((response) => {
      setRows(response.data.data);
    }).catch(error => {
      console.error(error);
    });
  }, []);
  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell>Tên khách</TableCell>
            <TableCell align="right">Giới tính</TableCell>
            <TableCell align="right">Địa chỉ</TableCell>
            <TableCell align="right">Email</TableCell>
            <TableCell align="right">Số điện thoại</TableCell>
            <TableCell align="right">Trạng thái</TableCell>
            <TableCell align="right">Tài Khoản</TableCell>
            <TableCell align="right">Vai trò</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow
              key={row.personId}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.fullname}
              </TableCell>
              <TableCell align="right">{row.gender === true ? "Nữ" : "Nam"}</TableCell>
              <TableCell align="right">{row.address}</TableCell>
              <TableCell align="right">{row.email}</TableCell>
              <TableCell align="right">{row.phone}</TableCell>
              <TableCell align="right">{row.status === true ? "Còn thuê" : "Không thuê nữa"}</TableCell>
              <TableCell align="right">{row.user?.username|| "Null"}</TableCell>
              <TableCell align="right">{row.user?.role?.roleName ||"NULL"}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
