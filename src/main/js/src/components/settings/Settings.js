import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { setDistance, setDistanceMessage } from '../../actions/settings';
import '../../stylesheets/settings.scss';

const Settings = ({ setDistanceMessage, setDistance, currentDistance }) => {
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
    <div className="settings">
      <div>
        <span>0km</span>
        <input type="range" name="" min="1" max="80" value={currentDistance} onChange={handleInput} />
        <span>80km</span>
      </div>
      {currentDistance ? <h3>You are searcing between 0 and {currentDistance}km!</h3> : null}
    </div>
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
