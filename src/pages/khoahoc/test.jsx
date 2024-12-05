import React, { useState } from 'react';
import axios from 'axios';
import { createKhoaService } from 'action/khoaHocAction';

function CourseForm() {
  // State để lưu thông tin Khoa
  const [khoa, setKhoa] = useState({
    tenKhoa: '',
    maKhoa: '',
    soBuoi: '',
    mucDo: '',
    moTa: ''
  });

  // State để lưu thông tin các lớp (lopDTO)
  const [lopDTO, setLopDTO] = useState([]);

  // Hàm thêm lớp vào mảng lopDTO
  const addLop = () => {
    setLopDTO([
      ...lopDTO,
      { thuTu: '', ngayBatDau: '', maGV: '' } // Thêm lớp mới với các trường rỗng
    ]);
  };

  // Hàm xử lý thay đổi thông tin Khoa
  const handleKhoaChange = (e) => {
    const { name, value } = e.target;
    setKhoa({
      ...khoa,
      [name]: value
    });
  };

  // Hàm xử lý thay đổi thông tin Lớp
  const handleLopChange = (index, e) => {
    const { name, value } = e.target;
    const newLopDTO = [...lopDTO];
    newLopDTO[index][name] = value;
    setLopDTO(newLopDTO);
  };

  // Hàm tạo JSON và gửi dữ liệu
  const generateJSON = () => {
    const jsonData = {
      khoa: {
        ...khoa
      },
      lopDTO: lopDTO
    };

    // Hiển thị JSON lên giao diện
    // alert(JSON.stringify(jsonData, null, 2));

    // Gửi JSON qua API
    createKhoaService(jsonData);
  };

  return (
    <div>
      <form>
        <h2>Thông Tin Khoa</h2>
        <label>
          Tên Khoa:
          <input
            type="text"
            name="tenKhoa"
            value={khoa.tenKhoa}
            onChange={handleKhoaChange}
            required
          />
        </label><br /><br />
        
        <label>
          Mã Khoa:
          <input
            type="number"
            name="maKhoa"
            value={khoa.maKhoa}
            onChange={handleKhoaChange}
            required
          />
        </label><br /><br />
        
        <label>
          Số Buổi:
          <input
            type="number"
            name="soBuoi"
            value={khoa.soBuoi}
            onChange={handleKhoaChange}
            required
          />
        </label><br /><br />
        
        <label>
          Mức Độ:
          <input
            type="text"
            name="mucDo"
            value={khoa.mucDo}
            onChange={handleKhoaChange}
            required
          />
        </label><br /><br />
        
        <label>
          Mô Tả:
          <input
            type="text"
            name="moTa"
            value={khoa.moTa}
            onChange={handleKhoaChange}
            required
          />
        </label><br /><br />

        <h3>Thông Tin Lớp</h3>
        {lopDTO.map((lop, index) => (
          <div key={index}>
            <h4>Lớp {index + 1}</h4>
            <label>
              Thứ Tự:
              <input
                type="number"
                name="thuTu"
                value={lop.thuTu}
                onChange={(e) => handleLopChange(index, e)}
                required
              />
            </label><br /><br />

            <label>
              Ngày Bắt Đầu:
              <input
                type="datetime-local"
                name="ngayBatDau"
                value={lop.ngayBatDau}
                onChange={(e) => handleLopChange(index, e)}
                required
              />
            </label><br /><br />

            <label>
              Mã Giáo Viên:
              <input
                type="number"
                name="maGV"
                value={lop.maGV}
                onChange={(e) => handleLopChange(index, e)}
                required
              />
            </label><br /><br />
          </div>
        ))}

        <button type="button" onClick={addLop}>Thêm Lớp</button><br /><br />
        <button type="button" onClick={generateJSON}>Tạo JSON</button>
      </form>
    </div>
  );
}

export default CourseForm;
