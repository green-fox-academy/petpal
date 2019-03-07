import React from 'react';
import { connect } from 'react-redux';
import { addAnimalRequest } from '../../actions/animal';
import { setAddAnimalError } from '../../actions/errors';
import '../../stylesheets/addanimal.scss';

const AddPet = ({ addAnimalRequest, setAddAnimalError, animMessage }) => {
  const handleSubmit = event => {
    event.preventDefault();
    const { animname, animbirth, animtype, animgender, spayed, vaccinated, animfile } = event.target;
    if (
      animname.value.trim().length > 0 &&
      animbirth.value.trim().length > 0 &&
      animtype.value.length > 0 &&
      animgender.value.length > 0 &&
      spayed.value.length > 0 &&
      vaccinated.value.length > 0 &&
      animfile.files.length > 0
    ) {
      console.log(animname.value);
      console.log(animbirth.value);
      console.log(animtype.value);
      console.log(animgender.value);
      console.log(spayed.value);
      console.log(vaccinated.value);
      console.log(animfile.files);
      addAnimalRequest({
        name: animname.value,
        birth: animbirth.value,
        type: animtype.value,
        gender: animgender.value,
        spayed: spayed.value,
        vaccinated: vaccinated.value,
        photo: animfile.files[0],
      });
      setAddAnimalError('');
    } else {
      setAddAnimalError('Fill out all fields please!');
    }
  };

  return (
    <div className="addme">
      {animMessage !== '' ? <h3>{animMessage}</h3> : null}
      <form onSubmit={handleSubmit}>
        <input type="text" name="animname" placeholder="your animal's name..." />
        <input type="text" name="animbirth" placeholder="birth date... e.g.:2018.10.24" />
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
            yes
          </label>
          <label htmlFor="spayedno">
            <input type="radio" name="spayed" value="false" id="spayedno" />
            no
          </label>
        </div>
        <div>
          <p>vaccinated?</p>
          <label htmlFor="vaccinatedyes">
            <input type="radio" name="vaccinated" value="true" id="vaccinatedyes" />
            yes
          </label>
          <label htmlFor="vaccinatedno">
            <input type="radio" name="vaccinated" value="false" id="vaccinatedno" />
            no
          </label>
        </div>
        <label htmlFor="animfile">
          select a photo...
          <input type="file" name="animfile" id="animfile" />
        </label>
        <button className="button" type="submit">submit</button>
      </form>
    </div>
  );
};

const mapStateToProps = store => ({
  animMessage: store.errors.animalErrorMsg,
});

const mapDispatchToProps = {
  addAnimalRequest,
  setAddAnimalError,
};

export default connect(mapStateToProps, mapDispatchToProps)(AddPet);
