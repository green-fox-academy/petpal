import { SELECT_CURRENT_FORM, TOGGLE_HAMB_ICON, SELECT_FAV_TAB, TOGGLE_CHAT_MENU } from '../actions/types';

const initState = {
  selectedForm: 'login',
  isHamburgerToggled: false,
  isChatToggled: false,
  currentFavTab: 'liked',
};

export default (state = initState, action) => {
  switch (action.type) {
    case SELECT_CURRENT_FORM:
      return { ...state, selectedForm: action.payload };
    case TOGGLE_HAMB_ICON:
      return { ...state, isHamburgerToggled: action.payload };
    case TOGGLE_CHAT_MENU:
      return { ...state, isChatToggled: action.payload };
    case SELECT_FAV_TAB:
      return { ...state, currentFavTab: action.payload };
    default:
      return state;
  }
};
