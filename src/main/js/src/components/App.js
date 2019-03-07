import React from 'react';
import { Switch, Route } from 'react-router-dom';
import LandingPage from './LandingPage';
import Home from './Home';
import PrivateRoute from './PrivateRoute';
import '../stylesheets/main.scss';
import '../stylesheets/buttons.scss';

const App = () => (
  <Switch>
    <Route exact path="/" component={LandingPage} />
    <Route path="/home" component={PrivateRoute(Home)} />
  </Switch>
);

export default App;
