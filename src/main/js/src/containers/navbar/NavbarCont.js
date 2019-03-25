import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { requestLogout, toggleHamburgerIcon, toggleChatMenu } from '../../actions/user';
import Navbar from '../../components/navbar/Navbar';

const mapStateToProps = store => ({
  isAuthenticated: store.users.isAuthenticated,
  isHamburgerToggled: store.toggles.isHamburgerToggled,
  isChatToggled: store.toggles.isChatToggled,
});

const mapDispatchToProps = {
  requestLogout,
  toggleHamburgerIcon,
  toggleChatMenu,
};

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps,
  )(Navbar),
);
