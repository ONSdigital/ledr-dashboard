import React, {Component} from 'react';
import {Header} from "semantic-ui-react";
import {formTopicDisplay, toTitleCase} from "../utils/Utils";

class DashboardHeader extends Component {

  shouldComponentUpdate(nextProps) {
    return nextProps.topic !== this.props.topic || nextProps.timePeriod !== this.props.timePeriod
  }

  render() {

    let {topic, timePeriod} = this.props;

    let topicDisplay = formTopicDisplay(topic);
    let timePeriodDisplay = toTitleCase(timePeriod);

    return (
      <Header as='h1'>
        <Header.Content>
          {timePeriodDisplay} Stats: {topicDisplay}
          <Header.Subheader>
            Initial Regs
          </Header.Subheader>
        </Header.Content>
      </Header>
    );
  }
}

export default DashboardHeader;
