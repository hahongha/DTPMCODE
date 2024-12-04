import axios from 'axios';
// Định nghĩa các endpoint API
const API_PATH = "http://localhost:8080";
const REST_API_PERSON = API_PATH+'/room';
export const getAllRoom = () => axios.get(REST_API_PERSON+ '/getAll');
export const getAllRoomValid = () => axios.get(REST_API_PERSON+ '/getAllRoomValid');
export const roomRoomService = (room) => axios.post(REST_API_ROOM, room);