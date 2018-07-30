import React, {Component} from 'react';
import {setModalDataProperty, setModalOpen, setModalTimePeriodType} from "../../../redux/actions/index";
import {connect} from "react-redux";
import {Label, List, Segment} from "semantic-ui-react";
import {DATA_PROPERTY, TOPIC_ENUM} from "../../../utils/Constants";
import {nullChecker, round} from "../../../utils/Utils";
import DataColumnItemGeneric from "./DataColumnItemGeneric";

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
        <List>
          <DataColumnItemGeneric
            header='Records received:'
            description={recordsReceivedDisplay}/>
          <List.Item className='data-column-item-generic'>
            <List.Content>
              <List.Header>Fully coded:</List.Header>
              <List.Description>{fullyCodedDisplay}
                {(() => {
                  if (fullyCodedPercent >= 75)
                    return <Label className='list-label' color='green' circular>{fullyCodedPercent}%</Label>;
                  if (fullyCodedPercent < 75 && fullyCodedPercent >= 50)
                    return <Label className='list-label' color='orange' circular>{fullyCodedPercent}%</Label>;
                  else
                    return <Label className='list-label' color='red' circular>{fullyCodedPercent}%</Label>;
                })()}
              </List.Description>
            </List.Content>
          </List.Item>
          <DataColumnItemGeneric
            header='Outstanding geography:'
            description={outstandingGeographyFullDisplay}
            label={outstandingGeographyPercent}
            onclick={() => {
              this.props.setModalTimePeriodType(timePeriodType);
              this.props.setModalDataProperty(DATA_PROPERTY.OUTSTANDING_GEOGRAPHY);
              this.props.setModalOpen(true);
            }
            }/>
          <DataColumnItemGeneric header='Outstanding occupation:' description={outstandingOccupationDisplay}
                                 label={outstandingOccupationPercent}/>
          {topic === TOPIC_ENUM.DEATH ?
            <DataColumnItemGeneric
              header='Outstanding cause:'
              description={outstandingCauseDisplay}
              label={outstandingCausePercent}
              onclick={() => {
                this.props.setModalTimePeriodType(timePeriodType);
                this.props.setModalDataProperty(DATA_PROPERTY.OUTSTANDING_CAUSE);
                this.props.setModalOpen(true);
              }}/>
            :
            <DataColumnItemGeneric
              header='Outstanding cause:'
              description={outstandingCauseDisplay}
              label={outstandingCausePercent}/>
          }
        </List>
      </Segment>
    );

  }
}

const DataColumnList = connect(mapStateToProps, mapDispatchToProps)(DataColumnListRedux);

export default DataColumnList;
