import React, {Component} from 'react';
import {setModalDataProperty, setModalOpen, setModalTimePeriodType} from "../../../redux/actions/index";
import {connect} from "react-redux";
import {Header, Segment} from "semantic-ui-react";
import {DATA_PROPERTY} from "../../../utils/Constants";
import {nullChecker, round} from "../../../utils/Utils";
import DataColumnItem from "./DataColumnItem";

const mapStateToProps = state => {
  return {
    topic: state.topic,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    setModalOpen: modalOpen => dispatch(setModalOpen(modalOpen)),
    setModalDataProperty: modalDataProperty => dispatch(setModalDataProperty(modalDataProperty)),
    setModalTimePeriodType: modalTimePeriodType => dispatch(setModalTimePeriodType(modalTimePeriodType))
  };
};

/**
 * This class displays the list of data for each data column within the DashboardDataArea
 */
class DataColumnListRedux extends Component {

  shouldComponentUpdate(nextProps) {
    return nextProps.statData !== this.props.statData;
  }

  render() {

    let {statData, timePeriodType, topic} = this.props;

    let {
      recordsReceived, fullyCoded, outstandingGeographyFull, outstandingOccupation, outstandingCause
    } = statData;

    let recordsReceivedDisplay = nullChecker(recordsReceived);
    let fullyCodedDisplay = nullChecker(fullyCoded);
    let outstandingGeographyFullDisplay = nullChecker(outstandingGeographyFull);
    let outstandingOccupationDisplay = nullChecker(outstandingOccupation);
    let outstandingCauseDisplay = nullChecker(outstandingCause);

    let fullyCodedPercent = round((fullyCoded / recordsReceived) * 100, 0);
    let outstandingGeographyPercent = round((outstandingGeographyFull / recordsReceived) * 100, 0);
    let outstandingOccupationPercent = round((outstandingOccupation / recordsReceived) * 100, 0);
    let outstandingCausePercent = round((outstandingCause / recordsReceived) * 100, 0);

    return (
      <Segment attached>
        <Header as='h4'>Records received: {recordsReceivedDisplay}</Header>
        <DataColumnItem labelText='Fully Coded:' count={fullyCodedDisplay} percent={fullyCodedPercent}/>
        <div style={{height: '12px'}}/>
        <DataColumnItem labelText='Outstanding Geography:' count={outstandingGeographyFullDisplay}
                        percent={outstandingGeographyPercent}
                        onClick={() => {
                          this.props.setModalTimePeriodType(timePeriodType);
                          this.props.setModalDataProperty(DATA_PROPERTY.OUTSTANDING_GEOGRAPHY);
                          this.props.setModalOpen(true);
                        }}/>
        <DataColumnItem labelText='Outstanding occupation:' count={outstandingOccupationDisplay}
                        percent={outstandingOccupationPercent}/>

        <DataColumnItem labelText='Outstanding cause:' count={outstandingCauseDisplay}
                        percent={outstandingCausePercent}
                        onClick={() => {
                          this.props.setModalTimePeriodType(timePeriodType);
                          this.props.setModalDataProperty(DATA_PROPERTY.OUTSTANDING_CAUSE);
                          this.props.setModalOpen(true);
                        }}/>
      </Segment>
    );

  }
}

const DataColumnList = connect(mapStateToProps, mapDispatchToProps)(DataColumnListRedux);

export default DataColumnList;
