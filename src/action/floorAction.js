import axios from 'axios';
// Định nghĩa các endpoint API
const API_PATH = "http://localhost:8080";
const REST_API_FLOOR = API_PATH+'/floor';
export const getAllFloor = () => axios.get(REST_API_FLOOR+ '/getAll');