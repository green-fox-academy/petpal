import {
  LIST_USER_CHATS_REQUEST,
  LOAD_LAST_MESSAGE_DATE,
  LOAD_USER_MESSAGES,
  LOAD_USER_CHAT_PARTNER,
  SEND_CHAT_MESSAGE_REQUEST,
} from './types';

export const listChatsOfUser = () => ({
  type: LIST_USER_CHATS_REQUEST,
});

export const loadChatMessages = userId => ({
  type: LOAD_USER_MESSAGES,
  payload: userId,
});

export const loadLastMessageDate = userId => ({
  type: LOAD_LAST_MESSAGE_DATE,
  payload: userId,
});

export const loadChatPartner = userId => ({
  type: LOAD_USER_CHAT_PARTNER,
  payload: userId,
});

export const sendChatMessage = data => ({
  type: SEND_CHAT_MESSAGE_REQUEST,
  payload: data,
});
