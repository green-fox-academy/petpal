import React from 'react';

const ChatPreview = ({ unSeen, partner, lastMessage, lastMessageAt }) => {
  const optionsForDate = { weekday: 'short', day: '2-digit', month: '2-digit', hour: '2-digit', minute: '2-digit' };
  return (
    <li>
      <span>{partner}</span>
      {lastMessage ? <span className={unSeen > 0 ? 'unseenmessageavailable' : null}>{lastMessage}</span> : null}
      {lastMessageAt ? <span>{new Date(lastMessageAt).toLocaleDateString('en-GB', optionsForDate)}</span> : null}
    </li>
  );
};

export default ChatPreview;
