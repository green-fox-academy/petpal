import { SELECT_CURRENT_FORM, TOGGLE_HAMB_ICON } from '../actions/types';

const initState = {
  selectedForm: 'login',
  isHamburgerToggled: false,
};

export default (state = initState, action) => {
  switch (action.type) {
    case SELECT_CURRENT_FORM:
      return { ...state, selectedForm: action.payload };
    case TOGGLE_HAMB_ICON:
      return { ...state, isHamburgerToggled: action.payload };
    default:
      return state;
  }
};
