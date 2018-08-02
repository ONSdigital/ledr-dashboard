import React, {Component, Fragment} from "react";
import DashboardHeader from "./DashboardHeader";
import {Divider} from "semantic-ui-react";
import DataModal from "./data/modal/DataModal";
import DashboardMenu from "./DashboardMenu";
import DashboardDataArea from "./DashboardDataArea";

/**
 * This is the coding parent class
 */
class Coding extends Component {

  render() {

    return (
      <Fragment>
        <DashboardMenu/>
        <Divider/>
        <DashboardHeader/>
        <DashboardDataArea/>
        <DataModal/>
      </Fragment>
    );
  }

}

export default Coding