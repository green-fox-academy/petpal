import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { listLikedAnimalsRequest } from '../../actions/animal';
import Spinner from '../Spinner';

const LikedAnimals = ({ listLikedAnimalsRequest, likedAnimals }) => {
  useEffect(() => {
    listLikedAnimalsRequest();
  }, []);

  return likedAnimals ? (
    <ul className="favanims">
      {likedAnimals.map(animal => (
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
  likedAnimals: store.animals.likedAnimals,
});

const mapDispatchToProps = {
  listLikedAnimalsRequest,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(LikedAnimals);
