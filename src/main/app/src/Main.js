import React, {Component} from 'react'
import {Route, Switch} from 'react-router-dom'
import {ROUTER_PATH} from "./utils/Constants";
import Coding from "./coding/Coding";
import Home from "./home/Home";
import Dashboard from "./dashboard/Dashboard";

/**
 * This class deals with 'switching' between different pages in the Application
 */
class Main extends Component {

  render() {

    return (
      <Switch>
        <Route exact path={ROUTER_PATH.HOME} render={(props) => {
          return <Home {...props}/>
        }}/>
        <Route exact path={ROUTER_PATH.CODING} render={(props) => {
          return <Coding {...props}/>
        }}/>
        <Route exact path={ROUTER_PATH.DASHBOARD} render={(props) => {
          return <Dashboard {...props}/>
        }}/>
      </Switch>
    )
  };
}

export default Main