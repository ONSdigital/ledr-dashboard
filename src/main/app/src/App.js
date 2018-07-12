import React, {Component, Fragment} from 'react';
import './App.css';
import {Segment} from "semantic-ui-react";
import Main from "./Main";
import NavigationBar from "./navigation/NavigationBar";

/**
 * This is the base class of the Application
 * It wraps the NavigationBar and Main Content area
 */
class App extends Component {
  render() {
    return (
      <Fragment>
        <NavigationBar/>
        <Segment id='content-main' basic>
          <Main/>
        </Segment>
      </Fragment>
    );
  }
}

export default App;