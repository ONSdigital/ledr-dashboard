import React, {Component} from 'react';
import {Header} from "semantic-ui-react";
import {connect} from "react-redux";
import {formDateText, formHeaderText, formTopicDisplay, toTitleCase} from "../../../utils/Utils";

const mapStateToProps = state => {
  return {
    topic: state.topic,
    timePeriod: state.timePeriod,
    modalDataProperty: state.modalDataProperty,
    modalTimePeriodType: state.modalTimePeriodType
  };
};

class DataModalHeaderRedux extends Component {

  render() {

    let {topic, timePeriod, modalDataProperty, modalTimePeriodType} = this.props;

    let topicDisplay = formTopicDisplay(topic);
    let timePeriodDisplay = toTitleCase(timePeriod);
    let timePeriodTypeDisplay = formHeaderText(timePeriod, modalTimePeriodType);
    let dateRangeDisplay = formDateText(timePeriod, modalTimePeriodType);

    return (
      <Header as='h2'>
        <Header.Content>
          {modalDataProperty}
          <Header.Subheader>
            {topicDisplay} | {timePeriodDisplay} | {timePeriodTypeDisplay} ({dateRangeDisplay})
          </Header.Subheader>
        </Header.Content>
      </Header>
    )
  }
}

const DataModalHeader = connect(mapStateToProps, null)(DataModalHeaderRedux);

export default DataModalHeader;
