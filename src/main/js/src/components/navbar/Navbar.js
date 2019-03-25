import React from 'react';
import HamburgerIcon from './HamburgerIcon';
import ChatMenuIcon from '../ChatSidebar/ChatMenuIcon';
import '../../stylesheets/navbar.scss';
import { toggleChatMenu } from '../../actions/user';

const Navbar = ({ isAuthenticated, isHamburgerToggled, toggleHamburgerIcon, isChatToggled, toggleChatMenu }) => {
  const handleClick = event => {
    const { togglename } = event.target.dataset;
    if (togglename === 'hambicon') {
      if (isHamburgerToggled) {
        toggleHamburgerIcon(false);
      } else {
        toggleHamburgerIcon(true);
        toggleChatMenu(false);
      }
    }

    if (togglename === 'chatmenuicon') {
      if (isChatToggled) {
        toggleChatMenu(false);
      } else {
        toggleChatMenu(true);
        toggleHamburgerIcon(false);
      }
    }
  };

  return (
    <header className="navheader" onClick={handleClick} role="presentation">
      {isAuthenticated ? <HamburgerIcon toggle={isHamburgerToggled} /> : null}
      <figure>
        <i className="fas fa-paw" />
      </figure>
      <h1>PetPal</h1>
      {isAuthenticated ? <ChatMenuIcon /> : null}
    </header>
  );
};

export default Navbar;
