import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { listChatsOfUser } from '../../actions/chat';
import { toggleChatMenu } from '../../actions/user';
import ChatPreview from './ChatPreview';
import Spinner from '../Spinner';
import '../../stylesheets/chatmenu.scss';

const ChatMenu = ({ isChatToggled, listChatsOfUser, userChats, history, match, toggleChatMenu }) => {
  useEffect(() => {
    listChatsOfUser();
  }, []);

  const handleClick = event => {
    const { chatid } = event.target.dataset;
    if (chatid) {
      history.push(`${match.url}/chat/${chatid}`);
      setTimeout(() => {
        toggleChatMenu(false);
      }, 100);
    }
  };

  return (
    <div className={isChatToggled ? 'chatmenu activechatmenu' : 'chatmenu'}>
      <h3>Chats</h3>
      {userChats ? (
        <ul onClick={handleClick}>
          {userChats.map(chat => {
            const { userId, partner, unSeen, messages } = chat;
            return (
              <ChatPreview
                key={userId}
                chatId={userId}
                partner={partner}
                unSeen={unSeen}
                lastMessage={messages.length > 0 ? messages[messages.length - 1].message : null}
                lastMessageAt={messages.length > 0 ? messages[messages.length - 1].sentAt : null}
              />
            );
          })}
        </ul>
      ) : (
        <Spinner />
      )}
    </div>
  );
};

const mapStateToProps = store => ({
  isChatToggled: store.toggles.isChatToggled,
  userChats: store.chat.userChats,
});

const mapDisPatchToProps = {
  listChatsOfUser,
  toggleChatMenu,
};

export default withRouter(
  connect(
    mapStateToProps,
    mapDisPatchToProps,
  )(ChatMenu),
);
