import React, {Component} from 'react'
import {Route, Switch} from 'react-router-dom'
import {ROUTER_PATH} from "./utils/Constants";
import Dashboard from "./dashboard/Dashboard";
import Login from "./Login";

// The Main component renders one of the provided
// Routes (provided that one matches). Routes will match any
// pathname that starts the name e.g. /jobApplicationNew.
// The / route will only match when the pathname is exactly the string "/"
class Main extends Component {

  render() {

    return (
      <Switch>
        <Route exact path={ROUTER_PATH.DASHBOARD} render={(props) => {
          return <Dashboard {...props}/>
        }}/>
        <Route exact path={ROUTER_PATH.LOGIN} render={(props) => {
          return <Login {...props}/>
        }}/>
      </Switch>
    )
  };
}

export default Main