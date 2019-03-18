import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { addAnimalRequest } from '../../actions/animal';
import { setAddAnimalError } from '../../actions/errors';
import AddPetForm from './AddPetForm';
import '../../stylesheets/addanimal.scss';
import '../../stylesheets/addanimaldesktop.scss';

const AddPet = ({ addAnimalRequest, setAddAnimalError, animMessage }) => {
  const [currentPhoto, setCurrentPhoto] = useState(null);

  useEffect(
    () => () => {
      setAddAnimalError('');
    },
    [],
  );

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
      const formData = new FormData();
      formData.append('name', animname.value);
      formData.append('birth', animbirth.value);
      formData.append('type', animtype.value);
      formData.append('gender', animgender.value);
      formData.append('spayed', spayed.value);
      formData.append('vaccinated', vaccinated.value);
      formData.append('photo', currentPhoto);
      addAnimalRequest(formData);
      setAddAnimalError('');
    } else {
      setAddAnimalError('Fill out all fields please!');
    }
  };

  const handleFile = event => {
    setCurrentPhoto(event.target.files[0]);
  };

  return <AddPetForm onChange={handleFile} onSubmit={handleSubmit} animMessage={animMessage} currentPhoto={currentPhoto} />;
};

const mapStateToProps = store => ({
  animMessage: store.errors.animalErrorMsg,
});

const mapDispatchToProps = {
  addAnimalRequest,
  setAddAnimalError,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(AddPet);
