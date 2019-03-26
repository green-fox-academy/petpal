import React from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { sendChatMessage } from '../../actions/chat';
import '../../stylesheets/chatinput.scss';

const ChatInput = ({ sendChatMessage, match }) => {
  const handleSubmit = event => {
    event.preventDefault();
    const { chattextmessage } = event.target;
    if (chattextmessage.value.trim().length > 0) {
      sendChatMessage({ author: 66, sentAt: Date.now(), message: chattextmessage.value });
      event.target.reset();
    }
  };

  return (
    <div className="chatinput">
      <form onSubmit={handleSubmit}>
        <textarea name="chattextmessage" placeholder="tell a nice story..." />
        <button type="submit">
          <span>send</span>
          <i className="far fa-paper-plane" />
        </button>
      </form>
    </div>
  );
};

const mapStateToProps = store => ({});

const mapDispatchToProps = {
  sendChatMessage,
};

export default withRouter(
  connect(
    mapStateToProps,
    mapDispatchToProps,
  )(ChatInput),
);
