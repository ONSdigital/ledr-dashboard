import React, {Component} from 'react';
import {Header} from "semantic-ui-react";
import {toTitleCase} from "../utils/Utils";
import {connect} from "react-redux";

const mapStateToProps = state => {
  return {
    topic: state.topic,
    timePeriod: state.timePeriod
  };
};

/**
 * Header for displaying Dashboard title and current topic
 */
class DashboardHeaderRedux extends Component {

  shouldComponentUpdate(nextProps) {
    return nextProps.topic !== this.props.topic || nextProps.timePeriod !== this.props.timePeriod
  }

  render() {

    let {topic, timePeriod} = this.props;

    let topicDisplay = toTitleCase(topic);
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

const DashboardHeader = connect(mapStateToProps, null)(DashboardHeaderRedux);

export default DashboardHeader;
