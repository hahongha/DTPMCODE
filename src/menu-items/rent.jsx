// assets
import { DashboardOutlined } from '@ant-design/icons';

// icons
const icons = {
  DashboardOutlined
};

// ==============================|| MENU ITEMS - DASHBOARD ||============================== //

const rent = {
  id: 'group-dashboard',
  title: 'rent',
  type: 'group',
  children: [
    {
      id: 'addTaiKhoan',
      title: 'Tài Khoản',
      type: 'item',
      url: '/addTaiKhoan',
      icon: icons.DashboardOutlined,
    },
    {
      id: 'addKhoa',
      title: 'Khóa',
      type: 'item',
      url: '/test',
      icon: icons.DashboardOutlined,
    }
  ]
};

export default rent;
