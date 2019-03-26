import React, { useEffect, useLayoutEffect, useRef } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import useStayScrolled from 'react-stay-scrolled';
import { loadChatMessages } from '../../actions/chat';
import '../../stylesheets/chatscreen.scss';

const ChatScreen = ({ match, loadChatMessages, conversation }) => {
  const listRef = useRef();
  const { stayScrolled } = useStayScrolled(listRef);

  useEffect(() => {
    const { userId } = match.params;
    loadChatMessages(userId);
  }, []);

  useLayoutEffect(() => {
    stayScrolled();
  }, [conversation.length]);

  return (
    <div className="chatscreen" ref={listRef}>
      {conversation.map(message => (
        <p
          key={message.sentAt}
          className={message.author === +match.params.userId ? 'chatmessage partnermessage' : 'chatmessage ownmessage'}
        >
          {message.message}
        </p>
      ))}
    </div>
  );
};

const mapStateToProps = store => ({
  conversation: store.chat.conversation,
});
const mapDispatchToProps = { loadChatMessages };

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps,
  )(ChatScreen),
);
