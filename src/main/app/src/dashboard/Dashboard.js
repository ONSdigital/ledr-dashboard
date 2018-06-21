import React, {Component, Fragment} from 'react';
import {Divider, Message} from "semantic-ui-react";
import DataDashboard from "./data/DataDashboard";
import DashboardMenu from "./DashboardMenu";

class Dashboard extends Component {

  render() {
    return (
      <Fragment>
        <DashboardMenu/>
        <Divider/>
        <DataDashboard/>
        <Message>
          The information on this page is refreshed every hour.
        </Message>
      </Fragment>
    );
  }

}

export default Dashboard;