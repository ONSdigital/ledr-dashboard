import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'semantic-ui-css/semantic.min.css';
import App from './App';
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";
import store from './redux/store/index';

//Dev Tool to detect needless component renders
// noinspection JSUnresolvedVariable
/*if (process.env.NODE_ENV !== 'production') {
  // noinspection JSUnresolvedFunction
  const {whyDidYouUpdate} = require('why-did-you-update');
  whyDidYouUpdate(React)
}*/
/**
 * This is the 'base' of the application
 * Redux and React Router are initialised here.
 */
ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <App/>
    </BrowserRouter>
  </Provider>,
  document.getElementById('root'));