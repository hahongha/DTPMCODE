import MainCard from "components/MainCard";

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
import { getAllUser } from "action/userAction";

export default function UserTable() {

  const[rows, setRows] = useState([]);

  useEffect(() => {
    getAllUser().then((response) => {
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
            <TableCell>Mã Tài Khoản</TableCell>
            <TableCell align="right">Tên Tài Khoản</TableCell>
            <TableCell align="right">Trạng thái</TableCell>
            <TableCell align="right">Vai trò</TableCell>
            <TableCell align="right">Thời hạn</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow
              key={row.userId}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.userId}
              </TableCell>
              <TableCell align="right">{row.username}</TableCell>
              <TableCell align="right">{row.status === true ? "ACTIVE" : "NOT_ACTIVE" }</TableCell>
              <TableCell align="right">{row.role.roleName}</TableCell>
              <TableCell align="right">{row.expired}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
