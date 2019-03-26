import {
  LIST_USER_CHATS_SUCCEEDED,
  LOAD_LAST_MESSAGE_DATE,
  LOAD_USER_MESSAGES,
  LOAD_USER_CHAT_PARTNER,
  SEND_CHAT_MESSAGE_REQUEST,
  SEND_CHAT_MESSAGE_SUCCEEDED,
} from '../actions/types';

const initState = {
  userChats: [],
  lastMessageAt: null,
  chatPartner: null,
  conversation: [],
};

const selectLastMessageDate = (chats, id) => {
  const chatPartner = chats.find(chat => chat.userId === +id);
  return chatPartner.messages[chatPartner.messages.length - 1].sentAt;
};

const selectUserChatPartner = (chats, id) => {
  const chatPartner = chats.find(chat => chat.userId === +id);
  return chatPartner.partner;
};

const selectConversation = (chats, id) => {
  const chatPartner = chats.find(chat => chat.userId === +id);
  return chatPartner.messages;
};

export default (state = initState, action) => {
  switch (action.type) {
    case LIST_USER_CHATS_SUCCEEDED:
      return { ...state, userChats: action.payload };
    case LOAD_LAST_MESSAGE_DATE:
      return { ...state, lastMessageAt: selectLastMessageDate(state.userChats, action.payload) };
    case LOAD_USER_MESSAGES:
      return { ...state, conversation: selectConversation(state.userChats, action.payload) };
    case LOAD_USER_CHAT_PARTNER:
      return { ...state, chatPartner: selectUserChatPartner(state.userChats, action.payload) };
    case SEND_CHAT_MESSAGE_REQUEST:
      return { ...state, conversation: [...state.conversation, action.payload], lastMessageAt: action.payload.sentAt };
    default:
      return state;
  }
};
