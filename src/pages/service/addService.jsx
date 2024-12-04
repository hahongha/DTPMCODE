import PropTypes from 'prop-types';

// material-ui
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import InputLabel from '@mui/material/InputLabel';
import OutlinedInput from '@mui/material/OutlinedInput';
import Stack from '@mui/material/Stack';
import dayjs from 'dayjs';
// third party
import * as Yup from 'yup';
import { Formik, useFormik } from 'formik';
import { useEffect, useState } from 'react';
import { FormControl, MenuItem, Select } from '@mui/material';
import { getAllRoom } from 'action/roomAction';
import { createServiceService } from 'action/service';
// ============================|| JWT - LOGIN ||============================ //

export default function AddService() {
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
      serviceName :'',
      servicePrice:0,
      serviceDefault: false
    },
    onSubmit: (values) => {
      createServiceService(values);
    }
  });

  return (
    <>
      <form onSubmit={formik.handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel>Tên dịch vụ</InputLabel>
              <OutlinedInput
                id="serviceName"
                type="text"
                name="serviceName"
                placeholder="Nhập tên của khách thuê"
                fullWidth
                onChange={(e) => formik.setFieldValue('serviceName', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>

          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel>Đơn giá</InputLabel>
              <OutlinedInput
                id="servicePrice"
                type="number"
                name="servicePrice"
                placeholder="Nhập địa chỉ"
                fullWidth
                onChange={(e) => formik.setFieldValue('person.servicePrice', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>

        <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel> Có bắt buộc không? </InputLabel>
              <FormControl fullWidth size="small" id="serviceDefault">
                <Select
                  id="serviceDefault"
                  label="serviceDefault"
                  name="serviceDefault"
                  onBlur={formik.handleBlur}
                  onChange={(e) => formik.setFieldValue('serviceDefault', e.target.value)}
                >
                  <MenuItem value={true}> Có</MenuItem>
                  <MenuItem value={false}> Không</MenuItem>
                </Select>
              </FormControl>
            </Stack>
        </Grid>

          
          
        <Grid item xs={8} margin={5}>
          <Button color="primary" variant="contained" type="submit">
            Submit
          </Button>
        </Grid>
        </Grid>
      </form>
    </>
  );
}
