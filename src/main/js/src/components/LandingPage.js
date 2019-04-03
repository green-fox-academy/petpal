import React, { Fragment } from 'react';
import { connect } from 'react-redux';
import { setSelectedForm, loginWithGoogle } from '../actions/user';
import NavbarCont from '../containers/navbar/NavbarCont';
import MainContent from './MainContent';
import LoginCont from '../containers/login/LoginCont';
import RegisterCont from '../containers/register/RegisterCont';
import '../stylesheets/landingpage.scss';
import '../stylesheets/forms.scss';

const Landingpage = ({ selectedForm, setSelectedForm }) => {
  const handleClick = event => {
    const { dataset } = event.target;
    if (dataset.action) {
      setSelectedForm(dataset.action);
    }
  };

  return (
    <Fragment>
      <NavbarCont />
      <MainContent>
        <div className="landingpage">
          <nav className="chooseform" onClick={handleClick} role="presentation">
            <button data-action="login" type="button" className={selectedForm === 'login' ? 'activeformbtn' : null}>
              <span data-action="login">sign in</span>
            </button>
            <button data-action="register" type="button" className={selectedForm === 'register' ? 'activeformbtn' : null}>
              <span data-action="register">register</span>
            </button>
          </nav>
          <div className={selectedForm === 'login' ? 'formscont' : 'formscont activeregisterform'}>
            <LoginCont />
            <RegisterCont />
          </div>
          <div className="googleconnect">
            <p>or</p>
            {/* <a href="http://localhost:8080/oauth2/authorize/google" className="googlesigninbtn"> */}
            <a href="https://petpalgf.herokuapp.com/oauth2/authorize/google" className="googlesigninbtn">
              <i className="fab fa-google-plus-g" />
              <span>connect with Google</span>
            </a>
          </div>
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
