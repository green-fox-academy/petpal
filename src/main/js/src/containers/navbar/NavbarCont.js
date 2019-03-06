import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { requestLogout, toggleHamburgerIcon } from '../../actions/user';
import Navbar from '../../components/navbar/Navbar';

const mapStateToProps = store => ({
  isAuthenticated: store.users.isAuthenticated,
  isHamburgerToggled: store.toggles.isHamburgerToggled,
});

const mapDispatchToProps = {
  requestLogout,
  toggleHamburgerIcon,
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Navbar));
