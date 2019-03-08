import React from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { requestLogout, toggleHamburgerIcon } from '../../actions/user';
import '../../stylesheets/sidebar.scss';

const Sidebar = ({
  isToggled, requestLogout, history, match, toggleHamburgerIcon,
}) => {
  const handleClick = (event) => {
    const { dataset } = event.target;
    if (dataset.menuitem) {
      dataset.menuitem === 'logout' ? requestLogout() : history.push(`${match.url}/${dataset.menuitem}`);
      toggleHamburgerIcon(false);
    }
  };

  return (
    <div className="sidebar" style={isToggled ? { left: '0px' } : { left: '-100vw' }}>
      <button type="button" data-menuitem="find" onClick={handleClick}>
        <i className="fas fa-map-pin" />

finder
      </button>
      <button type="button" data-menuitem="add" onClick={handleClick}>
        <i className="fas fa-plus" />

add pet
      </button>
      <button type="button" data-menuitem="settings" onClick={handleClick}>
        <i className="fas fa-cog" />

settings
      </button>
      <button type="button" data-menuitem="logout" onClick={handleClick}>
        <i className="fas fa-sign-out-alt" />

logout
      </button>
    </div>
  );
};

const mapStateToProps = store => ({
  isToggled: store.toggles.isHamburgerToggled,
});

const mapDispatchToProps = {
  requestLogout,
  toggleHamburgerIcon,
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Sidebar));
