import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { listAdoptedAnimalsRequest } from '../../actions/animal';
import Spinner from '../Spinner';

const AdoptedAnimals = ({ listAdoptedAnimalsRequest, adoptableAnimals }) => {
  useEffect(() => {
    listAdoptedAnimalsRequest();
  }, []);

  return adoptableAnimals ? (
    <ul className="favanims">
      <li>
        <ul>
          <li>name</li>
          <li>type</li>
          <li>gender</li>
          <li>photo</li>
          <li>remove</li>
        </ul>
      </li>
      {adoptableAnimals.map(animal => (
        <li key={animal.id}>
          <ul>
            <li>{animal.name}</li>
            <li>{animal.type}</li>
            <li>{animal.gender}</li>
            <li>
              <img src={`/assets/${animal.photoPath}`} alt="animal" />
            </li>
            <li>
              <button data-animalid={animal.id} className="removebtn" type="button">
                <i className="fas fa-trash-alt" data-animalid={animal.id} />
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
  adoptableAnimals: store.animals.adoptableAnimals,
});

const mapDispatchToProps = {
  listAdoptedAnimalsRequest,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(AdoptedAnimals);
