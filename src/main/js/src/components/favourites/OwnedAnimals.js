import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { listOwnedAnimalsRequest } from '../../actions/animal';
import Spinner from '../Spinner';

const OwnedAnimals = ({ listOwnedAnimalsRequest, ownedAnimals }) => {
  useEffect(() => {
    listOwnedAnimalsRequest();
  }, []);

  return ownedAnimals ? (
    <ul className="favanims">
      {ownedAnimals.map(animal => (
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
  ownedAnimals: store.animals.ownedAnimals,
});

const mapDispatchToProps = {
  listOwnedAnimalsRequest,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(OwnedAnimals);
