import React, {Component} from 'react';
import './App.css';
import {Segment} from "semantic-ui-react";
import Main from "./Main";
import LEDRHeader from "./navigation/LEDRHeader";

class App extends Component {
  render() {
    return (
      <div>
        <LEDRHeader/>
        <Segment id='content-main' basic>
          <Main/>
        </Segment>
      </div>
    );
  }
}

export default App;