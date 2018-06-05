import React, {Component} from 'react';
import {Header} from "semantic-ui-react";
import {toTitleCase} from "../utils/Utils";

class DashboardHeader extends Component {

  shouldComponentUpdate(nextProps) {
    return nextProps.topic !== this.props.topic || nextProps.timePeriod !== this.props.timePeriod
  }

  render() {

    let {topic, timePeriod} = this.props;

    topic = toTitleCase(topic);
    timePeriod = toTitleCase(timePeriod);

    return (
      <Header as='h1'>
        <Header.Content>
          {timePeriod} Stats: {topic}
          <Header.Subheader>
            Initial Regs
          </Header.Subheader>
        </Header.Content>
      </Header>
    );
  }
}

export default DashboardHeader;
