import React from 'react';
import { connect } from 'react-redux';
import { setDistance, setDistanceREDUX } from '../../actions/settings';
import '../../stylesheets/settings.scss';

const Settings = ({
  setDistance, currentDistance, inputDistance, setDistanceREDUX,
}) => {
  const handleSubmit = (event) => {
    event.preventDefault();
    setDistance(event.target.geolocation.value);
  };

  const handleChange = (event) => {
    setDistanceREDUX(event.target.value);
  };

  return (
    <div className="settings">
      <div>
        <span>0km</span>
        <form onSubmit={handleSubmit}>
          <input type="range" name="geolocation" min="1" max="80" value={inputDistance} onChange={handleChange} />
          <p style={{ left: `calc(${inputDistance}% - 40%)` }}>
            {inputDistance}
            {'km'}
          </p>
          <button className="button" type="submit">set distance</button>
        </form>
        <span>80km</span>
      </div>
      <h3>
        {'You are searcing between 0 and '}
        {currentDistance}
        {' km!'}
      </h3>
    </div>
  );
};

const mapStateToProps = store => ({
  currentDistance: store.settings.currentDistance,
  inputDistance: store.settings.inputDistance,
});

const mapDispatchToProps = {
  setDistance,
  setDistanceREDUX,
};

export default connect(mapStateToProps, mapDispatchToProps)(Settings);
