import axios from 'axios';
// Định nghĩa các endpoint API
const API_PATH = "http://localhost:8080";
const REST_API_PERSON = API_PATH+'/person';
export const getAllPerson = () => axios.get(REST_API_PERSON+ '/getAll');