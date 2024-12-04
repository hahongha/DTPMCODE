import axios from 'axios';
// Định nghĩa các endpoint API
const API_PATH = "http://localhost:8080";
const REST_API_TaiKhoan = API_PATH+'/taikhoan';
export const getAllTaiKhoan = () => axios.get(REST_API_TaiKhoan+ '/getAll');

export const createTaiKhoanService = (contract) => axios.post(REST_API_TaiKhoan, contract);