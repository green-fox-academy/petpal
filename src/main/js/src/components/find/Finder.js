import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { listAnimalsRequest, listNextAnimalFromRedux } from '../../actions/animal';
import Spinner from '../Spinner';
import '../../stylesheets/finder.scss';

const Finder = ({ listAnimalsRequest, queuedAnimal, listNextAnimalFromRedux }) => {
  useEffect(() => {
    listAnimalsRequest();
  }, []);

  const listNext = () => {
    listNextAnimalFromRedux();
  };

  return (
    queuedAnimal.name
      ? (
        <div className="finder">
          {console.log(queuedAnimal)}
          <div className="animalcard">
            <h2>{queuedAnimal.name}</h2>
            <h2>{queuedAnimal.type}</h2>
            <h2>{queuedAnimal.gender}</h2>
            <h2>{queuedAnimal.vaccinated ? ' vaccinated' : 'not vaccinated'}</h2>
            <h2>{queuedAnimal.spayed ? ' spayed' : 'not spayed'}</h2>
            <h2>{queuedAnimal.birth}</h2>
            <div>
              <button type="button" onClick={listNext}>swipe further</button>
              <button type="button">like</button>
              <button type="button">adopt</button>
            </div>
          </div>
        </div>
      )
      : <Spinner />
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
