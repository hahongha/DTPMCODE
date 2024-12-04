import PropTypes from 'prop-types';

// material-ui
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import InputLabel from '@mui/material/InputLabel';
import OutlinedInput from '@mui/material/OutlinedInput';
import Stack from '@mui/material/Stack';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import dayjs from 'dayjs';
// third party
import * as Yup from 'yup';
import { Formik, useFormik } from 'formik';
import { useNavigate } from 'react-router';
import { useEffect, useState } from 'react';
import { FormControl, MenuItem, Select } from '@mui/material';
import { getAllRoom } from 'action/roomAction';
import { DateField, DatePicker, LocalizationProvider } from '@mui/x-date-pickers';
import { createContractService } from 'action/contractAction';
// ============================|| JWT - LOGIN ||============================ //

export default function AddContract() {
  const [room, setRoom] = useState({});
  const [rooms, setRooms] = useState([]);
  const [contract, setContract] = useState({});
  const today = dayjs();
  useEffect(() => {
    getAllRoom()
      .then((response) => {
        setRooms(response.data.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const formik = useFormik({
    initialValues: {
      contractId : '',
      contract_content: 'Hợp đồng thuê nhà',
      person: {
        fullname: '',
        dateOfBirth: '',
        address: ' ',
        gender: true,
        email: ' ',
        phone: ' ',
        identification: ' ',
        dateOfIssue: today,
        placeOfIssue: ' ',
        status: true
      },
      room: {
        roomId: ' '
      },
      startDate: today,
      contractRent: 0,
      month: 6,
      endDate: today,
      contractStatus: 'WAITING',
      contractDeposit: 0
    },
    onSubmit: (values) => {
      createContractService(values);
      // setContract(values);
      // alert(JSON.stringify(values, null, 2));
      
    }
  });

  return (
    <>
      <form onSubmit={formik.handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel>Họ và tên</InputLabel>
              <OutlinedInput
                id="fullname"
                type="text"
                name="fullname"
                placeholder="Nhập tên của khách thuê"
                fullWidth
                onChange={(e) => formik.setFieldValue('person.fullname', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>
          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel>Địa chỉ thường trú</InputLabel>
              <OutlinedInput
                id="address"
                type="text"
                name="address"
                placeholder="Nhập địa chỉ"
                fullWidth
                onChange={(e) => formik.setFieldValue('person.address', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>

          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel htmlFor="phone">Số điện thoại</InputLabel>
              <OutlinedInput
                id="phone"
                type="phone"
                name="phone"
                placeholder="Nhập số điện thoại"
                fullWidth
                onChange={(e) => formik.setFieldValue('person.phone', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>
          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel htmlFor="email">Email</InputLabel>
              <OutlinedInput
                id="email"
                type="email"
                name="email"
                placeholder="Nhập email của bạn"
                fullWidth
                onChange={(e) => formik.setFieldValue('person.email', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>

          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel> Giới tính </InputLabel>
              <FormControl fullWidth size="small" id="gender">
                <Select
                  id="gender"
                  label="gender"
                  name="gender"
                  onBlur={formik.handleBlur}
                  onChange={(e) => formik.setFieldValue('person.gender', e.target.value)}
                >
                  <MenuItem value={true}> Nữ </MenuItem>
                  <MenuItem value={false}> Nam </MenuItem>
                </Select>
              </FormControl>
            </Stack>
          </Grid>

          
          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel>Ngày sinh</InputLabel>
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DateField
                  onChange={(e) => formik.setFieldValue('person.dateOfBirth', e)}
                  onBlur={formik.handleBlur}
                />
              </LocalizationProvider>
            </Stack>
          </Grid>
      
          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel> Trạng thái </InputLabel>
              <FormControl fullWidth size="small" id="status">
                <Select
                  id="status"
                  label="status"
                  name="status"
                  onBlur={formik.handleBlur}
                  onChange={(e) => formik.setFieldValue('status', e.target.value)}
                >
                  <MenuItem value={true}> Có Thuê </MenuItem>
                  <MenuItem value={false}> Không Thuê </MenuItem>
                </Select>
              </FormControl>
            </Stack>
          </Grid>
          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel> Chọn Phòng </InputLabel>
              <FormControl fullWidth size="small" id="room">
                <Select
                  id="room"
                  label="room"
                  name="room"
                  onBlur={formik.handleBlur}
                  onChange={(e) => {
                    formik.setFieldValue('room.roomId', e.target.value.roomId);
                  }
                }
                >
                  {rooms.map((r) => (
                    <MenuItem key={r.roomId} value={r}>
                      {r.roomName + '- Tầng ' + r.floor.floorNumber + '- Tòa ' + r.floor.building.buildingName}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
            </Stack>
          </Grid>

          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel>Số căn cước công dân</InputLabel>
              <OutlinedInput
                id="identification"
                type="text"
                name="identification"
                placeholder="Nhập số CCCD/CMT"
                fullWidth
                onChange={(e) => formik.setFieldValue('person.identification', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>
          <Grid item xs={3}>
            <Stack spacing={1}>
              <InputLabel>Ngày cấp</InputLabel>
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DateField
                  disableFuture
                  
                  onChange={(e) => formik.setFieldValue('person.dateOfIssue', e)}
                  onBlur={formik.handleBlur}
                />
              </LocalizationProvider>
            </Stack>
          </Grid>
          <Grid item xs={3}>
            <Stack spacing={1}>
              <InputLabel>Nơi cấp</InputLabel>
              <OutlinedInput
                id="placeOfIssue"
                type="text"
                name="placeOfIssue"
                fullWidth
                onChange={(e) => formik.setFieldValue('person.placeOfIssue', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>
          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel>Ngày bắt đầu</InputLabel>
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DateField
                  id="startDate"
                  onBlur={formik.handleBlur}
                  onChange={(e) => formik.setFieldValue('startDate', e)}
                />
              </LocalizationProvider>
            </Stack>
          </Grid>
          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel>Số tháng thuê</InputLabel>
              <OutlinedInput
                id="month"
                type="number"
                name="month"
                fullWidth
                onChange={(e) => formik.setFieldValue('month', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>
        </Grid>
        <Grid item xs={8} margin={5}>
          <Button color="primary" variant="contained" type="submit">
            Submit
          </Button>
        </Grid>
      </form>
    </>
  );
}
