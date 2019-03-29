import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import qs from 'query-string';
import { call, put } from 'redux-saga/effects';
import * as actions from '../../actions/types';
import { loginWithGoogle } from '../../actions/user';
import { history } from '../../store/configureStore';

const GoogleAuth = (props) => {

  const token = qs.parse(props.location.search, { ignoreQueryPrefix: true }).auth_token;
  if (token && token != "") {
    console.log(token)
    localStorage.setItem('accesstoken', token);
    props.loginWithGoogle();


    history.push('/home/find');

  }

  /*  useEffect(() => {
    const queries = qs.parse(location.search);
    console.log(queries.auth_token);
    localStorage.setItem('accesstoken', queries.auth_token);
    if (localStorage.getItem('accesstoken')) {
    } else {
      history.push('/');
    }
  }, []);*/
};

const mapDispatchToProps = {
  loginWithGoogle,
}

export default connect(null, mapDispatchToProps)(GoogleAuth);
