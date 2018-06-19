import React, {Component} from 'react'
import {Route, Switch} from 'react-router-dom'
import {ROUTER_PATH} from "./utils/Constants";
import Dashboard from "./dashboard/Dashboard";

class Main extends Component {

  render() {

    return (
      <Switch>
        <Route exact path={ROUTER_PATH.DASHBOARD} render={(props) => {
          return <Dashboard {...props}/>
        }}/>
      </Switch>
    )
  };
}

export default Main