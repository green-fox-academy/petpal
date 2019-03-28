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
      {ownedAnimals.map(animal => (
        <li key={animal.id}>
          <ul className="yellowbgcolor">
            <li>{animal.name}</li>
            <li>{animal.type}</li>
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
                <i data-animalid={animal.id} data-animalaction="remove" className="fas fa-times-circle" />
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
