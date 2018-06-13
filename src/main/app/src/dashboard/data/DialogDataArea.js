import React, {Component} from 'react';
import {List} from "semantic-ui-react";
import {nullChecker, timePeriodMapper} from "../../utils/Utils";
import {API_ENDPOINT, DATA_PROPERTY_DASHBOARD, ERROR_MESSAGE} from "../../utils/Constants";

class DialogDataArea extends Component {

  getData = () => {

    let {topic, timePeriod, modalDataProperty, modalTimePeriodType} = this.props;

    console.log(topic, timePeriod, modalDataProperty, modalTimePeriodType);

    let timePeriodMapped = timePeriodMapper(timePeriod, modalTimePeriodType);

    let url = `${API_ENDPOINT.DASHBOARD_MOCK}/${topic}/${timePeriodMapped}`;

    if (modalDataProperty === DATA_PROPERTY_DASHBOARD.OUTSTANDING_OCCUPATION) {
      url = url + '/occupation';
    }

    console.log('url: ', url);

    return fetch(url)
      .then((response) => {

        console.log(response);

        if (response.status === 500) {
          throw Error(response.statusText);
        }

        return response.json()
      })
      .then((json) => {
        console.log('json', json);
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
      statData: {}
    };

    this.getData();

  }

  render() {

    let statData = this.state.statData;

    return (
      <List link selection>
        {statData &&
        Object.keys(statData).map((key) => {
          return (
            <List.Item key={key}>
              <List.Content>
                <List.Header>{key}</List.Header>
                <List.Description>{nullChecker(statData[key])}</List.Description>
              </List.Content>
            </List.Item>
          )
        })
        }
      </List>
    )
  }
}

export default DialogDataArea;
