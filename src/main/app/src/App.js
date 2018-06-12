import React, {Component} from 'react';
import './App.css';
import {Segment} from "semantic-ui-react";
import Main from "./Main";
import NavigationBar from "./navigation/NavigationBar";

class App extends Component {
  render() {
    return (
      <div>
        <NavigationBar/>
        <Segment id='content-main' basic>
          <Main/>
        </Segment>
      </div>
    );
  }
}

export default App;