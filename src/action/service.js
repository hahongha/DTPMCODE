import axios from 'axios';
// Định nghĩa các endpoint API
const API_PATH = "http://localhost:8080";
const REST_API_SERVICE = API_PATH+'/service';
export const getAllservice = () => axios.get(REST_API_SERVICE+ '/getAll');

export const createServiceService = (service) => axios.post(REST_API_SERVICE, service);