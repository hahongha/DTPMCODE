import axios from 'axios';
// Định nghĩa các endpoint API
const API_PATH = "http://localhost:8080";
const REST_API_Khoa = API_PATH+'/khoa';
export const getAllKhoa = () => axios.get(REST_API_Khoa+ '/getAll');

export const createKhoaService = (contract) => axios.post(REST_API_Khoa , contract);