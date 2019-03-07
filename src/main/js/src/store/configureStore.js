import '@babel/polyfill';
import { createStore, compose, applyMiddleware } from 'redux';
import createHistory from 'history/createBrowserHistory';
import { routerMiddleware } from 'react-router-redux';
import createSagaMiddleware from 'redux-saga';
import rootReducer from '../reducers';
import sayHelloSaga from '../sagas/sagas';

export const history = createHistory();

const configureStore = (initialState) => {
  const reactRouterMiddleware = routerMiddleware(history);
  const sagaMiddleware = createSagaMiddleware();

  const middlewares = [
    sagaMiddleware,
    reactRouterMiddleware,
  ];

  const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
  const store = createStore(rootReducer, initialState, composeEnhancers(
    applyMiddleware(...middlewares),
  ));

  if (module.hot) {
    module.hot.accept('../reducers', () => {
      const nextReducer = require('../reducers').default;
      store.replaceReducer(nextReducer);
    });
  }

  sagaMiddleware.run(sayHelloSaga);

  return store;
};

export default configureStore;
