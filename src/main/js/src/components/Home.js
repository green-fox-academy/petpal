import React, { Fragment } from 'react';
import { Route } from 'react-router-dom';
import NavbarCont from '../containers/navbar/NavbarCont';
import MainContent from './MainContent';
import Sidebar from './sidebar/Sidebar';
import Settings from './settings/Settings';
import AddPet from './add/AddPet';
import Finder from './find/Finder';
import '../stylesheets/home.scss';

const Home = ({ match }) => (
  <Fragment>
    <NavbarCont />
    <MainContent>
      <Sidebar />
      <div className="home">
        <Route exact path={`${match.url}/settings`} component={Settings} />
        <Route exact path={`${match.url}/add`} component={AddPet} />
        <Route exact path={`${match.url}/find`} component={Finder} />
      </div>
    </MainContent>
  </Fragment>
);

export default Home;
