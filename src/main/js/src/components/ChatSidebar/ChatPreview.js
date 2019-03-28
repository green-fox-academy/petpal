import React from 'react';

const ChatPreview = ({ unSeen, partner, lastMessage, lastMessageAt, chatId }) => {
  const optionsForDate = { weekday: 'short', day: '2-digit', month: '2-digit', hour: '2-digit', minute: '2-digit' };
  return (
    <li data-chatid={chatId}>
      <span data-chatid={chatId}>{partner}</span>
      {lastMessage ? (
        <span data-chatid={chatId} className={unSeen > 0 ? 'unseenmessageavailable' : null}>
          {lastMessage}
        </span>
      ) : null}
      {lastMessageAt ? <span data-chatid={chatId}>{new Date(lastMessageAt).toLocaleDateString('en-GB', optionsForDate)}</span> : null}
    </li>
  );
};

export default ChatPreview;
