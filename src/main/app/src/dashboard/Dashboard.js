import React, {Component, Fragment} from "react";
import DashboardHeader from "./DashboardHeader";
import {Divider, Message} from "semantic-ui-react";
import DataModal from "./data/modal/DataModal";
import DashboardMenu from "./DashboardMenu";
import DashboardDataArea from "./DashboardDataArea";

/**
 *
 */
class Dashboard extends Component {

  render() {

    return (
      <Fragment>
        <DashboardMenu/>
        <Divider/>
        <DashboardHeader/>
        <DashboardDataArea/>
        <Message>
          The information on this page is refreshed every hour.
        </Message>
        <DataModal/>
      </Fragment>
    );
  }

}

export default Dashboard