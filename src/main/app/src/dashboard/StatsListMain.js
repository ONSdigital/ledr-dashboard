import React, {Component, Fragment} from 'react';
import DataDialog from "./DataDialog";
import StatsList from "./StatsList";
import {DATA_PROPERTY as DATATYPE} from "../utils/Constants";

class StatsListMain extends Component {

  state = {
    modalOpen: false
  };

  show = (dataType) => {

    let dataDialogData;

    if (dataType === DATATYPE.OUTSTANDING_GEOGRAPHY) {
      let {
        outstandingGeographyPOB, outstandingGeographyPOE,
        outstandingGeographyUR
      } = this.props.statData;

      dataDialogData = {
        dataType,
        outstandingGeographyPOB, outstandingGeographyPOE,
        outstandingGeographyUR
      }

    }

    this.setState({modalOpen: true, dataDialogData});
  };
  closeModal = () => this.setState({modalOpen: false});

  render() {

    let {statData} = this.props;

    let {modalOpen, dataDialogData} = this.state;

    return (
      <Fragment>
        <StatsList statData={statData} show={this.show}/>
        <DataDialog modalOpen={modalOpen} closeModal={this.closeModal} data={dataDialogData}/>
      </Fragment>
    );
  }
}

export default StatsListMain;
