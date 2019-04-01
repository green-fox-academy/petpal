import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import qs from 'query-string';
import { loginWithGoogle } from '../../actions/user';

const GoogleAuth = ({ location, loginWithGoogle, history }) => {
  useEffect(() => {
    const token = qs.parse(location.search, { ignoreQueryPrefix: true }).auth_token;
    if (token && token !== '') {
      localStorage.setItem('accesstoken', token);
      loginWithGoogle();
      history.push('/home/find');
    } else {
      history.push('/');
    }
  }, []);
};

// const GoogleAuth = props => {
//   const token = qs.parse(props.location.search, { ignoreQueryPrefix: true }).auth_token;
//   if (token && token != '') {
//     console.log(token);
//     localStorage.setItem('accesstoken', token);
//     props.loginWithGoogle();

//     history.push('/home/find');
//   }

/*  useEffect(() => {
    const queries = qs.parse(location.search);
    console.log(queries.auth_token);
    localStorage.setItem('accesstoken', queries.auth_token);
    if (localStorage.getItem('accesstoken')) {
    } else {
      history.push('/');
    }
  }, []);*/
// };

const mapDispatchToProps = {
  loginWithGoogle,
};

export default connect(
  null,
  mapDispatchToProps,
)(GoogleAuth);
