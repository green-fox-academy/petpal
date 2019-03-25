import { LIST_USER_CHATS_SUCCEEDED } from '../actions/types';

const initState = {
  userChats: [],
};

export default (state = initState, action) => {
  switch (action.type) {
    case LIST_USER_CHATS_SUCCEEDED:
      return { ...state, userChats: action.payload };
    default:
      return state;
  }
};
