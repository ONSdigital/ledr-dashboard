import React, {Component} from 'react';
import {Header} from "semantic-ui-react";
import {connect} from "react-redux";
import {formDateText, formHeaderText, toTitleCase} from "../../../utils/Utils";

const mapStateToProps = state => {
  return {
    topic: state.topic,
    timePeriod: state.timePeriod,
    modalDataProperty: state.modalDataProperty,
    modalTimePeriodType: state.modalTimePeriodType
  };
};

/**
 * This class displays the Header for the data modal
 * The Header is currently the property selected in the DataColumnList (e.g. Outstanding Geography)
 * The Sub-Header is currently the topic | time period | time period data range
 */
class DataModalHeaderRedux extends Component {

  render() {

    let {topic, timePeriod, modalDataProperty, modalTimePeriodType} = this.props;

    let topicDisplay = toTitleCase(topic);
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
