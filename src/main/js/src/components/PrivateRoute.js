import React from 'react';
import { connect } from 'react-redux';

const PrivateRoute = (WrappedComponent) => {
  const Authenticator = (props) => {
    const { isAuthenticated, history } = props;
    if (!isAuthenticated) history.push('/');
    return isAuthenticated ? <WrappedComponent {...props} /> : null;
  };

  const mapStateToProps = store => ({
    isAuthenticated: store.users.isAuthenticated,
  });

  return connect(mapStateToProps)(Authenticator);
};
export default PrivateRoute;
