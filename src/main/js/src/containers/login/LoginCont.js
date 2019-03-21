import { connect } from 'react-redux';
import { requestLogin, loginWithGoogle } from '../../actions/user';
import { setLoginError } from '../../actions/errors';
import Login from '../../components/login/Login';

const mapStateToProps = store => ({
  loginErrorMsg: store.errors.loginErrorMsg,
});

const mapDispatchToProps = {
  requestLogin,
  setLoginError,
  loginWithGoogle,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Login);
