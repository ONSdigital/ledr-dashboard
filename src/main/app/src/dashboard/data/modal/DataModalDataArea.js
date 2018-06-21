import React, {Component} from 'react';
import {Loader} from "semantic-ui-react";
import {timePeriodMapper} from "../../../utils/Utils";
import {API_ENDPOINT, DATA_PROPERTY, ERROR_MESSAGE} from "../../../utils/Constants";
import {connect} from "react-redux";
import OutstandingCauseDataLayout from "./datalayout/OutstandingOccupationDataLayout";
import OutstandingGeographyDataLayout from "./datalayout/OutstandingGeographyDataLayout";

const mapStateToProps = state => {
  return {
    topic: state.topic,
    timePeriod: state.timePeriod,
    modalOpen: state.modalOpen,
    modalDataProperty: state.modalDataProperty,
    modalTimePeriodType: state.modalTimePeriodType
  };
};

class DataModalDataAreaRedux extends Component {

  getData = () => {

    let {topic, timePeriod, modalDataProperty, modalTimePeriodType} = this.props;

    let timePeriodMapped = timePeriodMapper(timePeriod, modalTimePeriodType);

    let url = `${API_ENDPOINT.DASHBOARD_MOCK}/${topic}/${timePeriodMapped}`;
    if (modalDataProperty === DATA_PROPERTY.OUTSTANDING_CAUSE) {
      url = url + '/causecoding';
    }

    return fetch(url)
      .then((response) => {
        if (response.status === 500) {
          throw Error(response.statusText);
        }
        return response.json()
      })
      .then((json) => {
        this.setState({statData: json, statDataLoading: false});
      }).catch(() => {
        this.setState({
          statDataLoading: false,
          statDataError: true,
          statDataErrorMessage: ERROR_MESSAGE.DATABASE_CONNECTION_ERROR
        });
      });

  };

  shouldComponentUpdate(nextProps, nextState) {
    if (nextState.statData !== this.state.statData) {
      return true;
    }
  }

  constructor(props) {
    super(props);

    this.state = {
      statData: ''
    };

    this.getData();

  }

  render() {

    let modalDataProperty = this.props.modalDataProperty;
    let statData = this.state.statData;

    switch (modalDataProperty) {
      case DATA_PROPERTY.OUTSTANDING_CAUSE:
        return <OutstandingCauseDataLayout statData={statData}/>;
      case DATA_PROPERTY.OUTSTANDING_GEOGRAPHY:
        return <OutstandingGeographyDataLayout statDataa={statData}/>;
      default:
        return <Loader active/>
    }

  }
}

const DataModalDataArea = connect(mapStateToProps, null)(DataModalDataAreaRedux);

export default DataModalDataArea;
