import axios from 'axios';
// Định nghĩa các endpoint API
const API_PATH = "http://localhost:8080";
const REST_API_USER = API_PATH+'/user';
export const getAllUser = () => axios.get(REST_API_USER+ '/getAll');