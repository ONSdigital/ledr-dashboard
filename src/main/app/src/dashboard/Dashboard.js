import React, {Component, Fragment} from 'react';
import {Divider, Message} from "semantic-ui-react";
import DashboardData from "./DashboardData";
import DashboardMenu from "./DashboardMenu";

class Dashboard extends Component {

  render() {
    return (
      <Fragment>
        <DashboardMenu/>
        <Divider/>
        <DashboardData/>
        <Message>
          The information on this page is refreshed every hour.
        </Message>
      </Fragment>
    );
  }

}

export default Dashboard;