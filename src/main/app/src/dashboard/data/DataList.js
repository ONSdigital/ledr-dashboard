import React, {Component} from 'react';
import {Label, List, Segment} from "semantic-ui-react";
import {nullChecker, round} from "../../utils/Utils";
import {setModalDataProperty, setModalOpen, setModalTimePeriodType} from "../../redux/actions";
import {connect} from "react-redux";
import {DATA_PROPERTY_DASHBOARD} from "../../utils/Constants";

const mapDispatchToProps = dispatch => {
  return {
    setModalOpen: modalOpen => dispatch(setModalOpen(modalOpen)),
    setModalDataProperty: modalDataProperty => dispatch(setModalDataProperty(modalDataProperty)),
    setModalTimePeriodType: modalTimePeriodType => dispatch(setModalTimePeriodType(modalTimePeriodType))
  };
};

class DataListRedux extends Component {

  shouldComponentUpdate(nextProps) {
    return nextProps.statData !== this.props.statData;
  }

  render() {

    let {statData, timePeriodType} = this.props;

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
        <List link selection>
          <List.Item>
            <List.Content>
              <List.Header>Records received:</List.Header>
              <List.Description>{recordsReceivedDisplay}</List.Description>
            </List.Content>
          </List.Item>
          <List.Item>
            <List.Content>
              <List.Header>Fully coded:</List.Header>
              <List.Description>{fullyCodedDisplay + ' '}
                {(() => {
                  if (fullyCodedPercent >= 75)
                    return <Label color='green' circular>{fullyCodedPercent}%</Label>;
                  if (fullyCodedPercent < 75 && fullyCodedPercent >= 50)
                    return <Label color='orange' circular>{fullyCodedPercent}%</Label>;
                  else
                    return <Label color='red' circular>{fullyCodedPercent}%</Label>;
                })()}
              </List.Description>
            </List.Content>
          </List.Item>
          <List.Item onClick={() => {
            this.props.setModalTimePeriodType(timePeriodType);
            this.props.setModalDataProperty(DATA_PROPERTY_DASHBOARD.OUTSTANDING_GEOGRAPHY);
            this.props.setModalOpen(true);
          }}>
            <List.Content>
              <List.Header>Outstanding geography:</List.Header>
              <List.Description>
                {outstandingGeographyFullDisplay + ' '}
                <Label color='grey' circular>{outstandingGeographyPercent}%</Label>
              </List.Description>
            </List.Content>
          </List.Item>
          <List.Item>
            <List.Content>
              <List.Header>Outstanding occupation: </List.Header>
              <List.Description>
                {outstandingOccupationDisplay + ' '}
                <Label color='grey' circular>{outstandingOccupationPercent}%</Label>
              </List.Description>
            </List.Content>
          </List.Item>
          <List.Item onClick={() => {
            this.props.setModalTimePeriodType(timePeriodType);
            this.props.setModalDataProperty(DATA_PROPERTY_DASHBOARD.OUTSTANDING_CAUSE);
            this.props.setModalOpen(true);
          }}>
            <List.Content>
              <List.Header>Outstanding cause: </List.Header>
              <List.Description>
                {outstandingCauseDisplay + ' '}
                <Label color='grey' circular>{outstandingCausePercent}%</Label>
              </List.Description>
            </List.Content>
          </List.Item>
        </List>
      </Segment>
    );
  }
}

const DataList = connect(null, mapDispatchToProps)(DataListRedux);

export default DataList;
