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
      id: 'contract',
      title: 'contract',
      type: 'item',
      url: '/contract',
      icon: icons.LoginOutlined,
    },
    {
      id: 'person',
      title: 'person',
      type: 'item',
      url: '/person',
      icon: icons.ProfileOutlined,
    },
    {
      id: 'user',
      title: 'user',
      type:'item',
      url: '/user',
      icon: icons.ProfileOutlined,
    },
    {
      id: 'service',
      title: 'service',
      type:'item',
      url: '/service',
      icon: icons.ProfileOutlined,
    }
  ]
};

export default rent;
