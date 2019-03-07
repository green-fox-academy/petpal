import React from 'react';

const NavElem = ({ routes }) => <nav>{routes.map(route => <button type="button" key={route} data-route={route}>{route}</button>)}</nav>;

export default NavElem;
