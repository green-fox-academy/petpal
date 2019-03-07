import { connect } from 'react-redux';
import { requestLogin } from '../../actions/user';
import { setLoginError } from '../../actions/errors';
import Login from '../../components/login/Login';

const mapStateToProps = store => ({
  loginErrorMsg: store.errors.loginErrorMsg,
});

const mapDispatchToProps = {
  requestLogin,
  setLoginError,
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);
