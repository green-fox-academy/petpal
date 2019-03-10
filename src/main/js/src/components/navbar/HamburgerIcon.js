import React from 'react';
import '../../stylesheets/hamburgericon.scss';

const HamburgerIcon = ({ toggle }) => (
  <div data-togglename="hambicon" className={toggle ? 'hamburgericon activeicon' : 'hamburgericon'}>
    <div data-togglename="hambicon" className="iconpart topicon" />
    <div data-togglename="hambicon" className="iconpart middleicon" />
    <div data-togglename="hambicon" className="iconpart bottomicon" />
  </div>
);

export default HamburgerIcon;
