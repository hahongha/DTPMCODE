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
import { getAllservice } from "action/service";

export default function ServiceTable() {

  const[rows, setRows] = useState([]);

  useEffect(() => {
    getAllservice().then((response) => {
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
            <TableCell>Mã Dịch Vụ</TableCell>
            <TableCell align="right">Tên dịch vụ</TableCell>
            <TableCell align="right">Có dùng được không?</TableCell>
            <TableCell align="right">Đơn giá</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map((row) => (
            <TableRow
              key={row.serviceId}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.serviceId}
              </TableCell>
              <TableCell align="right">{row.serviceName}</TableCell>
              <TableCell align="right">{row.serviceDefault === true ? "Có" : "Không" }</TableCell>
              <TableCell align="right">{row.servicePrice}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
