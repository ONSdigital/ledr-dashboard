import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

// noinspection ES6ModulesDependencies
// noinspection JSUnresolvedFunction
it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App/>, div);
  ReactDOM.unmountComponentAtNode(div);
});
