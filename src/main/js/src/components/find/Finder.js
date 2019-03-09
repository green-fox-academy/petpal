import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { listAnimalsRequest, listNextAnimalFromRedux } from '../../actions/animal';
import Spinner from '../Spinner';
import '../../stylesheets/finder.scss';

const Finder = ({ listAnimalsRequest, queuedAnimal, listNextAnimalFromRedux }) => {
  const [counter, setCounter] = useState(0);

  useEffect(() => {
    listAnimalsRequest();
  }, []);

  const listNext = () => {
    if (counter > 2) {
      listNextAnimalFromRedux(0);
      setCounter(0);
    } else {
      listNextAnimalFromRedux(counter + 1);
      setCounter(counter + 1);
    }
  };

  return (
    queuedAnimal.name
      ? (
        <div className="finder">
          <div className="animalcard">
            <figure>
              {/* <img src={`images/${queuedAnimal.photo}`} alt="animal picture" /> */}
            </figure>
            <h2>
              {queuedAnimal.name}
              {' - '}
              {queuedAnimal.type}
            </h2>
            <h2>{queuedAnimal.gender}</h2>
            <h2>
              {queuedAnimal.vaccinated ? 'vaccinated' : 'not vaccinated'}
              {' - '}
              {queuedAnimal.spayed ? 'spayed' : 'not spayed'}
            </h2>
            <h2>
              {'birthday: '}
              {queuedAnimal.birth}
            </h2>
            <div>
              <button type="button" onClick={listNext}><i className="fas fa-angle-double-right" /></button>
              <button type="button"><i className="fas fa-thumbs-up" /></button>
              <button type="button"><i className="fas fa-heart" /></button>
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
