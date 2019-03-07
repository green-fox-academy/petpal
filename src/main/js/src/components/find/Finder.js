import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { listAnimalsRequest, listNextAnimalFromRedux } from '../../actions/animal';
// import AnimalCard from '../find/AnimalCard';
import '../../stylesheets/finder.scss';

const Finder = ({ listAnimalsRequest, queuedAnimal, listNextAnimalFromRedux }) => {
  useEffect(() => {
    listAnimalsRequest();
  }, []);

  const listNext = () => {
    listNextAnimalFromRedux();
  };

  return (
    queuedAnimal
      ? (
        <div className="finder">
          {/* {queuedAnimals ? <AnimalCard /> : null} */}
          <div className="animalcard">
            <h2>{queuedAnimal.name}</h2>
            <h2>{queuedAnimal.type}</h2>
            <h2>{queuedAnimal.gender}</h2>
            <h2>{queuedAnimal.vaccinated ? 'vaccinated' : 'not vaccinated'}</h2>
            <h2>{queuedAnimal.spayed ? 'spayed' : 'not spayed'}</h2>
            <h2>{queuedAnimal.birth}</h2>
            <button type="button" onClick={listNext}>swipe further</button>
            <button type="button">like</button>
            <button type="button">adopt</button>
          </div>
        </div>
      )
      : <h2>couldnt load</h2>
  );
};

const mapStateToProps = store => ({
  animals: store.animals.animals,
  queuedAnimal: store.animals.queuedAnimal,
});

const mapDispatchToProps = {
  listAnimalsRequest,
  listNextAnimalFromRedux,
};

export default connect(mapStateToProps, mapDispatchToProps)(Finder);
