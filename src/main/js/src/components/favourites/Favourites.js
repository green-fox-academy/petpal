import React from 'react';
import { Route } from 'react-router-dom';
import { connect } from 'react-redux';
import { selectFavTab } from '../../actions/user';
import LikedAnimals from './LikedAnimals';
import AdoptedAnimals from './AdoptedAnimals';
import OwnedAnimals from './OwnedAnimals';
import '../../stylesheets/favourites.scss';

const Favourites = ({ match, history, currentFavTab, selectFavTab }) => {
  const handleClick = event => {
    const { favredir } = event.target.dataset;
    if (favredir) {
      history.push(`${match.url}/${favredir}`);
      selectFavTab(favredir);
    }
  };

  return (
    <div className="favouritescont">
      <nav className="favsbuttons" onClick={handleClick}>
        <button data-favredir="liked" className={currentFavTab === 'liked' ? 'tabselectbtn activetabbtn' : 'tabselectbtn'}>
          liked
        </button>
        <button data-favredir="adopted" className={currentFavTab === 'adopted' ? 'tabselectbtn activetabbtn' : 'tabselectbtn'}>
          adopted
        </button>
        <button data-favredir="owned" className={currentFavTab === 'owned' ? 'tabselectbtn activetabbtn' : 'tabselectbtn'}>
          owned
        </button>
      </nav>
      <div className="selectedfavtab">
        <Route path={`${match.path}/liked`} component={LikedAnimals} />
        <Route path={`${match.path}/adopted`} component={AdoptedAnimals} />
        <Route path={`${match.path}/owned`} component={OwnedAnimals} />
      </div>
    </div>
  );
};

const mapStateToProps = store => ({
  currentFavTab: store.toggles.currentFavTab,
});

const mapDispatchToProps = {
  selectFavTab,
};

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(Favourites);
