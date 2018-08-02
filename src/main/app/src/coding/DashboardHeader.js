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
 * Header for displaying Coding title and current topic
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
      <Header as='h1'>{timePeriodDisplay} Stats: {topicDisplay}</Header>
    );
  }
}

const DashboardHeader = connect(mapStateToProps, null)(DashboardHeaderRedux);

export default DashboardHeader;
