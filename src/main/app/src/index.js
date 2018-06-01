import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import 'semantic-ui-css/semantic.min.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';
import {BrowserRouter} from "react-router-dom";
import {Provider} from "react-redux";
import store from './redux/store/index';
import {ROUTER_PATH} from "./utils/Constants";

//Dev Tool to detect needless component renders
if (process.env.NODE_ENV !== 'production') {
  const {whyDidYouUpdate} = require('why-did-you-update')
  whyDidYouUpdate(React)
}

ReactDOM.render(<Provider store={store}><BrowserRouter basename={ROUTER_PATH.BASE}>
  <App/>
</BrowserRouter></Provider>, document.getElementById('root'));
registerServiceWorker();
