import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { setDistance, setDistanceREDUX } from '../../actions/settings';
import { setSettingsError } from '../../actions/errors';
import '../../stylesheets/settings.scss';

const Settings = ({ setDistance, currentDistance, inputDistance, setDistanceREDUX, settingsError, setSettingsError }) => {
  useEffect(
    () => () => {
      setSettingsError('');
    },
    [],
  );

  const handleSubmit = event => {
    event.preventDefault();
    setDistance(event.target.geolocation.value);
  };

  const handleChange = event => {
    setDistanceREDUX(event.target.value);
  };

  const handlePasswordChange = event => {
    event.preventDefault();
    const { currpassword, newpassword, newpasswordre } = event.target;
    if (currpassword.value.trim().length > 0 && newpassword.value.trim().length > 0 && newpasswordre.value.trim().length > 0) {
      if (newpassword.value.trim() === newpasswordre.value.trim()) {
        console.log({ currentPassword: currpassword.value, newPassword: newpassword.value });
        event.target.reset();
      } else {
        setSettingsError('New passwords should match!');
      }
    } else {
      setSettingsError('Fill out all fields please!');
    }
  };

  return (
    <div className="settings">
      <div>
        <div>
          <span>0km</span>
          <form onSubmit={handleSubmit}>
            <input type="range" name="geolocation" min="1" max="80" value={inputDistance} onChange={handleChange} />
            <p style={{ left: `calc(${inputDistance}% - 40%)` }}>
              {inputDistance}
              {'km'}
            </p>
            <button className="button" type="submit">
              set distance
            </button>
          </form>
          <span>80km</span>
        </div>
        <h3>
          {'You are searcing between 0 and '}
          {currentDistance}
          {' km!'}
        </h3>
      </div>
      <form onSubmit={handlePasswordChange}>
        {settingsError ? <h2>{settingsError}</h2> : null}
        <input type="password" name="currpassword" placeholder="Current password" />
        <input type="password" name="newpassword" placeholder="New password" />
        <input type="password" name="newpasswordre" placeholder="New password again" />
        <button className="button" type="submit">
          change password
        </button>
      </form>
    </div>
  );
};

const mapStateToProps = store => ({
  currentDistance: store.settings.currentDistance,
  inputDistance: store.settings.inputDistance,
  settingsError: store.errors.settingsErrorMsg,
});

const mapDispatchToProps = {
  setDistance,
  setSettingsError,
  setDistanceREDUX,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Settings);
