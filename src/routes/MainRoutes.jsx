import { lazy } from 'react';

// project import
import Loadable from 'components/Loadable';
import Dashboard from 'layout/Dashboard';
import Contract from 'pages/contract';
import AddContract from 'pages/contract/addContract';
import Person from 'pages/person';
import User from 'pages/user';
import AddUser from 'pages/user/addUser';
import AddPerson from 'pages/person/addPerson';
import AddService from 'pages/service/addService';
import Service from 'pages/service';

const Color = Loadable(lazy(() => import('pages/component-overview/color')));
const Typography = Loadable(lazy(() => import('pages/component-overview/typography')));
const Shadow = Loadable(lazy(() => import('pages/component-overview/shadows')));
const DashboardDefault = Loadable(lazy(() => import('pages/dashboard/index')));

// render - sample page
const SamplePage = Loadable(lazy(() => import('pages/extra-pages/sample-page')));

// ==============================|| MAIN ROUTING ||============================== //

const MainRoutes = {
  path: '/',
  element: <Dashboard />,
  children: [
    {
      path: '/',
      element: <DashboardDefault />
    },
    {
      path: '/contract',
      element: <Contract />
    },
    {
      path: '/addContract',
      element: <AddContract />
    },
    {
      path: '/addUser',
      element: <AddUser />
    },
    {
      path: '/addPerson',
      element: <AddPerson />
    },
    {
      path: '/person',
      element: <Person />
    },
    {
      path: '/addService',
      element: <AddService />
    },
    {
      path: '/service',
      element: <Service />
    },
    {
      path: '/user',
      element: <User />
    },
    {
      path: 'color',
      element: <Color />
    },
    {
      path: 'dashboard',
      children: [
        {
          path: 'default',
          element: <DashboardDefault />
        }
      ]
    },
    {
      path: 'sample-page',
      element: <SamplePage />
    },
    {
      path: 'shadow',
      element: <Shadow />
    },
    {
      path: 'typography',
      element: <Typography />
    }
  ]
};

export default MainRoutes;
