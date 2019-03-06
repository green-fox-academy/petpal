import React, { Fragment, useState } from 'react';
import NavbarCont from '../containers/navbar/NavbarCont';
import MainContent from './MainContent';
import LoginCont from '../containers/login/LoginCont';
import RegisterCont from '../containers/register/RegisterCont';
import '../stylesheets/landingpage.scss';
import '../stylesheets/forms.scss';

const Landingpage = () => {
  const [currentForm, setCurrentForm] = useState('login');

  const handleClick = (event) => {
    const { dataset } = event.target;
    if (dataset.action) {
      setCurrentForm(dataset.action);
    }
  };

  return (
    <Fragment>
      <NavbarCont />
      <MainContent>
        <div className="landingpage">
          <div className="formscont" style={currentForm === 'login' ? { left: '0px' } : { left: '-100%' }}>
            <LoginCont />
            <RegisterCont />
          </div>
          <nav className="chooseform" onClick={handleClick} role="presentation">
            <button
              data-action="register"
              className="button"
              type="button"
              style={currentForm === 'register' ? { backgroundColor: '#3a0577c9', color: 'white' } : null}
            >
              <span data-action="register">register</span>
            </button>
            <button
              data-action="login"
              className="button"
              type="button"
              style={currentForm === 'login' ? { backgroundColor: '#3a0577c9', color: 'white' } : null}
            >
              <span data-action="login">sign in</span>
            </button>
          </nav>
        </div>
      </MainContent>
    </Fragment>
  );
};

export default Landingpage;
