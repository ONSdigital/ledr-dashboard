import React, {Component, Fragment} from 'react';
import DataDialog from "./DataDialog";
import StatsList from "./StatsList";
import {DATA_PROPERTY} from "../utils/Constants";

class StatsListMain extends Component {

  state = {
    modalOpen: false
  };

  show = (dataType) => {

    let dataDialogData;

    if (dataType === DATA_PROPERTY.OUTSTANDING_GEOGRAPHY.PARENT) {
      let {
        outstandingGeographyPOB, outstandingGeographyPOE,
        outstandingGeographyUR
      } = this.props.statData;

      let statData = {};
      statData[DATA_PROPERTY.OUTSTANDING_GEOGRAPHY.USUAL_RESIDENCE] = outstandingGeographyUR;
      statData[DATA_PROPERTY.OUTSTANDING_GEOGRAPHY.PLACE_OF_EVENT] = outstandingGeographyPOE;
      statData[DATA_PROPERTY.OUTSTANDING_GEOGRAPHY.PLACE_OF_BIRTH] = outstandingGeographyPOB;

      dataDialogData = {dataType, statData};

/*      dataDialogData = {
        dataType,
        outstandingGeographyPOB, outstandingGeographyPOE,
        outstandingGeographyUR
      }*/

    }

    this.setState({modalOpen: true, dataDialogData});
  };
  closeModal = () => this.setState({modalOpen: false});

  shouldComponentUpdate(nextProps, nextState) {
    if (nextState.modalOpen !== this.state.modalOpen) {
      return true
    }
    if (nextProps.statData !== this.props.statData) {
      return true
    }
    return false
  }

  render() {

    let {statData, timePeriodType} = this.props;

    let {modalOpen, dataDialogData} = this.state;

    return (
      <Fragment>
        <StatsList statData={statData} show={this.show}/>
        <DataDialog modalOpen={modalOpen} closeModal={this.closeModal} data={dataDialogData} timePeriodType={timePeriodType}/>
      </Fragment>
    );
  }
}

export default StatsListMain;
