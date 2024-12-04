import axios from 'axios';
// Định nghĩa các endpoint API
const API_PATH = "http://localhost:8080";
const REST_API_ROOM = API_PATH+'/room';
export const getAllRoom = () => axios.get(REST_API_ROOM+ '/getAll');