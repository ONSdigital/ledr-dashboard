import React, {Component, Fragment} from 'react';
import TimePeriodSelect from "./menu/TimePeriodSelect";
import TopicSelect from "./menu/TopicSelect";

/**
 * Menu for selecting active Coding topic and time period
 */
class DashboardMenu extends Component {

  render() {

    return (
      <Fragment>
        <TopicSelect/>
        <TimePeriodSelect/>
      </Fragment>
    );
  }

}

export default DashboardMenu;