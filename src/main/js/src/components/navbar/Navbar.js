import React from 'react';
import HamburgerIcon from './HamburgerIcon';
import '../../stylesheets/navbar.scss';

const Navbar = ({ isAuthenticated, isHamburgerToggled, toggleHamburgerIcon }) => {
  const handleClick = (event) => {
    if (event.target.dataset.togglename === 'hambicon') {
      isHamburgerToggled ? toggleHamburgerIcon(false) : toggleHamburgerIcon(true);
    }
  };

  return (
    <header className="navheader" onClick={handleClick} role="presentation">
      {isAuthenticated ? <HamburgerIcon toggle={isHamburgerToggled} /> : null}
      <figure><i className="fas fa-paw" /></figure>
      <h1>PetPal</h1>
    </header>
  );
};

export default Navbar;
