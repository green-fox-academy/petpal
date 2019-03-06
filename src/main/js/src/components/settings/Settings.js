import React, { Fragment, useEffect } from 'react';
import { connect } from 'react-redux';
import { setDistance, setDistanceMessage } from '../../actions/settings';

const Settings = ({ setDistanceMessage, setDistance, distanceMessage }) => {
  useEffect(() => {
    return () => {
      setDistanceMessage('');
    };
  }, []);

  const handleInput = event => {
    console.log(event.target.value);
    setDistance(event.target.value);
  };

  return (
    <Fragment>
      <input type="range" name="" min="1" max="80" onInput={handleInput} />
      {distanceMessage !== '' ? <h3>{distanceMessage}</h3> : null}
    </Fragment>
  );
};

const mapStateToProps = store => ({
  currentDistance: store.settings.currentDistance,
  distanceMessage: store.settings.distanceMessage,
});

const mapDispatchToProps = {
  setDistance,
  setDistanceMessage,
};

export default connect(mapStateToProps, mapDispatchToProps)(Settings);
