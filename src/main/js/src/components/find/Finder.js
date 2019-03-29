import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import Draggable from 'react-draggable';
import { listAnimalsRequest, listNextAnimalFromRedux } from '../../actions/animal';
import { likeAnimal, adoptAnimal} from '../../services/api';
import Spinner from '../Spinner';
import '../../stylesheets/finder.scss';

const Finder = ({ listAnimalsRequest, queuedAnimal, listNextAnimalFromRedux, animals }) => {
  const [counter, setCounter] = useState(0);

  useEffect(() => {
    listAnimalsRequest();
  }, []);

  const handleLikeAnimal = (event) => {
    const token = localStorage.getItem("accesstoken");
    likeAnimal(token, queuedAnimal.id);
  }

  const handleAdoptAnimal = (event) =>{
    const token = localStorage.getItem("accesstoken");
    adoptAnimal(token, queuedAnimal.id);
  }

  const listNext = () => {
    if (counter > animals.length - 2) {
      listNextAnimalFromRedux(0);
      setCounter(0);
    } else {
      listNextAnimalFromRedux(counter + 1);
      setCounter(counter + 1);
    }
  };

  return queuedAnimal.name ? (
    <div className="finder">
      {/* <Draggable onStop={listNext} position={{ x: 0, y: 0 }}> */}
      <div className="animalcard">
        <figure>
          <img src={`/assets/${queuedAnimal.photoPath}`} alt="animalpic" />
        </figure>
        <h2>{queuedAnimal.name}</h2>
        <h2>
          {queuedAnimal.type}
          {' - '}
          {queuedAnimal.gender}
        </h2>
        <h2>
          {queuedAnimal.vaccinated ? 'vaccinated' : 'not vaccinated'}
          {' - '}
          {queuedAnimal.spayed ? 'spayed' : 'not spayed'}
        </h2>
        <h2>
          {'birthday: '}
          {new Date(queuedAnimal.birthDate).toLocaleDateString('de-DE', { month: 'short', day: '2-digit', year: 'numeric' })}
        </h2>
        <div>
          <button type="button"  onClick={listNext}>
            <i className="fas fa-angle-double-right" />
          </button>
          <button type="button" onClick={handleLikeAnimal}>
            <i className="fas fa-thumbs-up" />
          </button>
          <button type="button" onClick={handleAdoptAnimal}>
            <i className="fas fa-heart" />
          </button>
        </div>
      </div>
      {/* </Draggable> */}
    </div>
  ) : (
      <Spinner />
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

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Finder);
