import { connect } from 'react-redux';
import { requestRegister } from '../../actions/user';
import { setRegisterError } from '../../actions/errors';
import Register from '../../components/register/Register';

const mapStateToProps = store => ({
  registerErrorMsg: store.errors.registerErrorMsg,
});

const mapDispatchToProps = {
  requestRegister,
  setRegisterError,
};

export default connect(mapStateToProps, mapDispatchToProps)(Register);
