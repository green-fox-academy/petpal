import React from 'react';

const AddPetForm = ({ onSubmit, onChange, currentPhoto, animMessage, onBlur }) => (
  <div className="addme">
    <form onSubmit={onSubmit}>
      {animMessage ? <h3>{animMessage}</h3> : null}
      <input type="text" name="animname" placeholder="your animal's name..." onBlur={onBlur} />
      <input type="text" name="animbirth" placeholder="birth date... e.g.:2018.10.24." />
      <select name="animtype">
        <option value="dog">dog</option>
        <option value="cat">cat</option>
        <option value="hamster">hamster</option>
        <option value="pingvin">pinguin</option>
      </select>
      <select name="animgender">
        <option value="male">male</option>
        <option value="female">female</option>
      </select>
      <div>
        <p>spayed?</p>
        <label htmlFor="spayedyes">
          <input type="radio" name="spayed" value="true" id="spayedyes" />
          <span>yes</span>
        </label>
        <label htmlFor="spayedno">
          <input type="radio" name="spayed" value="false" id="spayedno" />
          <span>no</span>
        </label>
      </div>
      <div>
        <p>vaccinated?</p>
        <label htmlFor="vaccinatedyes">
          <input type="radio" name="vaccinated" value="true" id="vaccinatedyes" />
          <span>yes</span>
        </label>
        <label htmlFor="vaccinatedno">
          <input type="radio" name="vaccinated" value="false" id="vaccinatedno" />
          <span>no</span>
        </label>
      </div>
      <label style={currentPhoto ? { backgroundColor: 'rgb(35, 170, 35)', color: 'white' } : null} htmlFor="animfile">
        {currentPhoto ? (
          <span>{currentPhoto.name}</span>
        ) : (
          <span>
            select a photo... <i className="fas fa-paperclip" />
          </span>
        )}
      </label>
      <input type="file" name="animfile" id="animfile" onChange={onChange} />
      <button className="button" type="submit">
        submit
      </button>
    </form>
  </div>
);

export default AddPetForm;
