import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { loadChatPartner, loadLastMessageDate } from '../../actions/chat';
import ChatInput from './ChatInput';
import ChatScreen from './ChatScreen';
import '../../stylesheets/chatbox.scss';

const ChatBox = ({ match, loadChatPartner, loadLastMessageDate, lastMessageAt, chatPartner }) => {
  const optionsForDate = { weekday: 'short', day: '2-digit', month: '2-digit', hour: '2-digit', minute: '2-digit' };

  useEffect(() => {
    const { userId } = match.params;
    loadChatPartner(userId);
    loadLastMessageDate(userId);
  }, []);

  return (
    <div className="chatbox">
      <h2>{`Chatting with ${chatPartner}`}</h2>
      <p>
        <span>last message:</span>
        <span>&nbsp;</span>
        <span>{new Date(lastMessageAt).toLocaleDateString('en-GB', optionsForDate)}</span>
      </p>
      <ChatScreen />
      <ChatInput />
    </div>
  );
};

const mapStateToProps = store => ({
  lastMessageAt: store.chat.lastMessageAt,
  chatPartner: store.chat.chatPartner,
});

const mapDispatchToProps = {
  loadChatPartner,
  loadLastMessageDate,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(ChatBox);
