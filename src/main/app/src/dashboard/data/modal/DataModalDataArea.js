import React, {Component} from 'react';
import {Loader} from "semantic-ui-react";
import {timePeriodMapper} from "../../../utils/Utils";
import {API_ENDPOINT, DATA_PROPERTY, ERROR_MESSAGE} from "../../../utils/Constants";
import {connect} from "react-redux";
import OutstandingCauseDataView from "./view/OutstandingCauseDataView";
import OutstandingGeographyDataView from "./view/OutstandingGeographyDataView";

const mapStateToProps = state => {
  return {
    topic: state.topic,
    timePeriod: state.timePeriod,
    modalOpen: state.modalOpen,
    modalDataProperty: state.modalDataProperty,
    modalTimePeriodType: state.modalTimePeriodType
  };
};

/**
 * This is the parent class for the data modal area and renders differently dependant on the
 * specified modalDataProperty passed from the onClick method of a DataColumnList item
 */
class DataModalDataAreaRedux extends Component {

  getData = () => {

    let {topic, timePeriod, modalDataProperty, modalTimePeriodType} = this.props;

    let timePeriodMapped = timePeriodMapper(timePeriod, modalTimePeriodType);

    let url = `${API_ENDPOINT.DASHBOARD}/${topic}/${timePeriodMapped}`;
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

  constructor(props) {
    super(props);

    this.state = {
      statData: ''
    };

    this.getData();

  }

  shouldComponentUpdate(nextProps, nextState) {
    if (nextState.statData !== this.state.statData) {
      return true;
    }
  }

  render() {

    let modalDataProperty = this.props.modalDataProperty;
    let statData = this.state.statData;

    switch (modalDataProperty) {
      case DATA_PROPERTY.OUTSTANDING_CAUSE:
        return <OutstandingCauseDataView statData={statData}/>;
      case DATA_PROPERTY.OUTSTANDING_GEOGRAPHY:
        return <OutstandingGeographyDataView statDataa={statData}/>;
      default:
        return <Loader active/>
    }

  }
}

const DataModalDataArea = connect(mapStateToProps, null)(DataModalDataAreaRedux);

export default DataModalDataArea;
