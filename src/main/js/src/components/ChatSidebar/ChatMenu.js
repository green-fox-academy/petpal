import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { listChatsOfUser } from '../../actions/chat';
import ChatPreview from './ChatPreview';
import Spinner from '../Spinner';
import '../../stylesheets/chatmenu.scss';

const ChatMenu = ({ isChatToggled, listChatsOfUser, userChats }) => {
  useEffect(() => {
    listChatsOfUser();
  }, []);

  return (
    <div className={isChatToggled ? 'chatmenu activechatmenu' : 'chatmenu'}>
      <h3>Chats</h3>
      {userChats ? (
        <ul>
          {userChats.map(chat => {
            const { chatId, partner, unSeen, messages } = chat;
            return (
              <ChatPreview
                key={chatId}
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
};

export default connect(
  mapStateToProps,
  mapDisPatchToProps,
)(ChatMenu);
