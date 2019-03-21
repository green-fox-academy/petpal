import React, { Fragment } from 'react';
import { connect } from 'react-redux';
import { setSelectedForm, loginWithGoogle } from '../actions/user';
import NavbarCont from '../containers/navbar/NavbarCont';
import MainContent from './MainContent';
import LoginCont from '../containers/login/LoginCont';
import RegisterCont from '../containers/register/RegisterCont';
import '../stylesheets/landingpage.scss';
import '../stylesheets/forms.scss';

const Landingpage = ({ selectedForm, setSelectedForm, loginWithGoogle }) => {
  const handleClick = event => {
    const { dataset } = event.target;
    if (dataset.action) {
      setSelectedForm(dataset.action);
    }
  };

  const handleGoogleLogin = () => {
    console.log('hello from google');
    loginWithGoogle();
  };

  return (
    <Fragment>
      <NavbarCont />
      <MainContent>
        <div className="landingpage">
          <div className="formscont" style={selectedForm === 'login' ? { left: '0px' } : { left: '-100%' }}>
            <LoginCont />
            <RegisterCont />
          </div>
          <div className="googleconnect">
            <p>or connect with</p>
            <button type="button" className="googlesigninbtn" onClick={handleGoogleLogin}>
              <span>
                <i className="fab fa-google-plus-g" />
              </span>
              <span>Google</span>
            </button>
          </div>
          <nav className="chooseform" onClick={handleClick} role="presentation">
            <button data-action="login" type="button" style={selectedForm === 'login' ? { borderBottomColor: '#3a0577c9' } : null}>
              <span data-action="login">sign in</span>
            </button>
            <button data-action="register" type="button" style={selectedForm === 'register' ? { borderBottomColor: '#3a0577c9' } : null}>
              <span data-action="register">register</span>
            </button>
          </nav>
        </div>
      </MainContent>
    </Fragment>
  );
};

const mapStateToProps = store => ({
  selectedForm: store.toggles.selectedForm,
});

const mapDispatchToProps = {
  setSelectedForm,
  loginWithGoogle,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Landingpage);
