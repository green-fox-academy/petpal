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
      {adoptableAnimals.map(animal => (
        <li key={animal.id}>
          <ul>
            <li>{animal.name}</li>
            <li>{animal.type}</li>
            <li>{animal.gender}</li>
            <li>{animal.photoPath}</li>
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
