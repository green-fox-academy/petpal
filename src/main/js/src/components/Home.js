import React, { Fragment } from 'react';
import { Route } from 'react-router-dom';
import NavbarCont from '../containers/navbar/NavbarCont';
import MainContent from './MainContent';
import Sidebar from './sidebar/Sidebar';
import Settings from './settings/Settings';
import AddPet from './add/AddPet';
import Favourites from './favourites/Favourites';
import Finder from './find/Finder';
import '../stylesheets/home.scss';

const Home = ({ match }) => (
  <Fragment>
    <NavbarCont />
    <MainContent>
      <Sidebar />
      <div className="home">
        <Route path={`${match.path}/settings`} component={Settings} />
        <Route path={`${match.path}/add`} component={AddPet} />
        <Route path={`${match.path}/favourites`} component={Favourites} />
        <Route path={`${match.path}/find`} component={Finder} />
      </div>
    </MainContent>
  </Fragment>
);

export default Home;
