import React from 'react';
import { Switch, Route } from 'react-router-dom';
import LandingPage from './LandingPage';
import Home from './Home';
import PrivateRoute from './PrivateRoute';
import GoogleAuth from './login/GoogleAuth'
import Helo from './Helo'
import '../stylesheets/main.scss';
import '../stylesheets/buttons.scss';

const App = () => (
  <Switch>
    <Route exact path="/" component={LandingPage} />
    <Route path="/hello" component={Helo} />
    <Route path="/googleauth" component={GoogleAuth} />
    <Route path="/home" component={PrivateRoute(Home)} />
  </Switch>
);

export default App;
