import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { listOwnedAnimalsRequest } from '../../actions/animal';
import Spinner from '../Spinner';

const OwnedAnimals = ({ listOwnedAnimalsRequest, ownedAnimals }) => {
  useEffect(() => {
    listOwnedAnimalsRequest();
  }, []);

  const handleOwnedAnimalClick = event => {
    const { dataset } = event.target;
    if (dataset.animalid) {
      dataset.animalaction === 'edit' ? console.log('editme', dataset.animalid) : console.log('removeme', dataset.animalid);
    }
  };

  return ownedAnimals ? (
    <ul className="favanims" onClick={handleOwnedAnimalClick}>
      <li>
        <ul>
          <li>name</li>
          <li>type</li>
          <li>gender</li>
          <li>photo</li>
          <li>edit</li>
          <li>remove</li>
        </ul>
      </li>
      {ownedAnimals.map(animal => (
        <li key={animal.id}>
          <ul>
            <li>{animal.name}</li>
            <li>{animal.type}</li>
            <li>{animal.gender}</li>
            <li>
              <img src={`/assets/${animal.photoPath}`} alt="animal" />
            </li>
            <li>
              <button data-animalid={animal.id} data-animalaction="edit" type="button">
                <i data-animalid={animal.id} data-animalaction="edit" className="fas fa-edit" />
              </button>
            </li>
            <li>
              <button data-animalid={animal.id} data-animalaction="remove" className="removebtn" type="button">
                <i data-animalid={animal.id} data-animalaction="remove" className="fas fa-trash-alt" />
              </button>
            </li>
          </ul>
        </li>
      ))}
    </ul>
  ) : (
    <Spinner />
  );
};

const mapStateToProps = store => ({
  ownedAnimals: store.animals.ownedAnimals,
});

const mapDispatchToProps = {
  listOwnedAnimalsRequest,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(OwnedAnimals);
