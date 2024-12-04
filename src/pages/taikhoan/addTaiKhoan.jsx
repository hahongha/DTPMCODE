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
import { createTaiKhoanService } from 'action/taiKhoanAction';
// ============================|| JWT - LOGIN ||============================ //

export default function AddTaiKhoan() {

  const formik = useFormik({
    initialValues: {
      vaiTro:'',
      matKhau:'',
      tenNguoiDung:''
    },
    onSubmit: (values) => {
    const jsonString = JSON.stringify(values);
    alert(jsonString);
      createTaiKhoanService(values);
    }
  });

  return (
    <>
      <form onSubmit={formik.handleSubmit}>
        <Grid container spacing={2}>
          <Grid item xs={12}>
            <Stack spacing={1}>
              <InputLabel>Họ và tên</InputLabel>
              <OutlinedInput
                id="tenNguoiDung"
                type="text"
                name="tenNguoiDung"
                placeholder="Nhập tên người dùng"
                fullWidth
                onChange={(e) => formik.setFieldValue('tenNguoiDung', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>
          <Grid item xs={12}>
            <Stack spacing={1}>
              <InputLabel>Nhập mật khẩu</InputLabel>
              <OutlinedInput htmlFor='password'
                id="matKhau"
                type="text"
                name="matKhau"
                placeholder="Nhập mật khẩu"
                fullWidth
                onChange={(e) => formik.setFieldValue('matKhau', e.target.value)}
                onBlur={formik.handleBlur}
              />
            </Stack>
          </Grid>

          <Grid item xs={6}>
            <Stack spacing={1}>
              <InputLabel>Vai trò</InputLabel>
              <OutlinedInput
                id="vaiTro"
                type="text"
                name="vaiTro"
                placeholder="Nhập vai trò"
                fullWidth
                onChange={(e) => formik.setFieldValue('vaiTro', e.target.value)}
                onBlur={formik.handleBlur}
              />
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
