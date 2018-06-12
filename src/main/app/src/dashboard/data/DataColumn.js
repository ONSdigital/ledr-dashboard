import React, {Component, Fragment} from 'react';
import DataDialog from "./DataDialog";
import DataList from "./DataList";
import {DATA_PROPERTY_DASHBOARD, DATA_PROPERTY_POPUP} from "../../utils/Constants";

class DataColumn extends Component {

  state = {
    modalOpen: false
  };


  formDialogData = (dataType) => {

    let statData = {};

    if (dataType === DATA_PROPERTY_DASHBOARD.OUTSTANDING_GEOGRAPHY) {
      let {
        outstandingGeographyPOB, outstandingGeographyPOE,
        outstandingGeographyUR
      } = this.props.statData;

      statData[DATA_PROPERTY_POPUP.OUTSTANDING_GEOGRAPHY.USUAL_RESIDENCE] = outstandingGeographyUR;
      statData[DATA_PROPERTY_POPUP.OUTSTANDING_GEOGRAPHY.PLACE_OF_EVENT] = outstandingGeographyPOE;
      statData[DATA_PROPERTY_POPUP.OUTSTANDING_GEOGRAPHY.PLACE_OF_BIRTH] = outstandingGeographyPOB;
    }

    return {dataType, statData}
  };

  show = (dataType) => {

    let dataDialogData = this.formDialogData(dataType);

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
        <DataList statData={statData} show={this.show}/>
        <DataDialog modalOpen={modalOpen} closeModal={this.closeModal} data={dataDialogData} timePeriodType={timePeriodType}/>
      </Fragment>
    );
  }
}

export default DataColumn;
