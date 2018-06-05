import React, {Component, Fragment} from "react";
import {
  API_ENDPOINT,
  ERROR_MESSAGE,
  TIME_PERIOD_ENUM,
  TIME_PERIOD_SELECT_ENUM,
  TIME_PERIOD_TYPE,
  TOPIC_OPTIONS_ENUM as TOPIC_OPTIONS,
} from "../utils/Constants";
import DashboardHeader from "./DashboardHeader";
import {Grid, Loader, Message} from "semantic-ui-react";
import StatsListMain from "./StatsListMain";
import ColumnHeader from "./ColumnHeader";

class DashboardData extends Component {

  /**
   * Gets data for specified by calling API endpoint
   */
  getData = (timePeriod) => {

    //TODO: Time Period should come from TIME_PERIOD_ENUM

    let url = `${API_ENDPOINT}/${this.props.topic}/${timePeriod}`;

    return fetch(url)
      .then((response) => response.json())
      .then((json) => {

        let data = {
          recordsReceived: json.recordsReceived,
          fullyCoded: json.fullyCoded,
          outstandingGeographyFull: json.outstandingGeographyFull,
          outstandingGeographyPOE: json.outstandingGeographyPOE,
          outstandingGeographyPOB: json.outstandingGeographyPOB,
          outstandingGeographyUR: json.outstandingGeographyUR,
          outstandingOccupation: json.outstandingOccupation,
          outstandingCause: json.outstandingCause
        };

        switch (timePeriod) {
          case TIME_PERIOD_ENUM.WEEK_CURRENT:
          case TIME_PERIOD_ENUM.MONTH_CURRENT:
          case TIME_PERIOD_ENUM.QUARTER_CURRENT:
          case TIME_PERIOD_ENUM.YEAR_CURRENT:
            this.setState({statDataCurrent: data, statDataCurrentLoading: false});
            break;
          case TIME_PERIOD_ENUM.WEEK_LAST:
          case TIME_PERIOD_ENUM.MONTH_LAST:
          case TIME_PERIOD_ENUM.QUARTER_LAST:
          case TIME_PERIOD_ENUM.YEAR_LAST:
            this.setState({statDataLast: data, statDataLastLoading: false});
            break;
          case TIME_PERIOD_ENUM.WEEK_BEFORE:
          case TIME_PERIOD_ENUM.MONTH_BEFORE:
          case TIME_PERIOD_ENUM.QUARTER_BEFORE:
          case TIME_PERIOD_ENUM.YEAR_BEFORE:
            this.setState({statDataBefore: data, statDataBeforeLoading: false});
            break;
          default:
            return null;
        }

      }).catch((error) => {
        console.log('error', error);

        switch (timePeriod) {
          case TIME_PERIOD_ENUM.WEEK_CURRENT:
          case TIME_PERIOD_ENUM.MONTH_CURRENT:
          case TIME_PERIOD_ENUM.QUARTER_CURRENT:
          case TIME_PERIOD_ENUM.YEAR_CURRENT:
            this.setState({
              statDataCurrentLoading: false,
              statDataCurrentError: true,
              statDataCurrentErrorMessage: ERROR_MESSAGE.DATABASE_CONNECTION_ERROR
            });
            break;
          case TIME_PERIOD_ENUM.WEEK_LAST:
          case TIME_PERIOD_ENUM.MONTH_LAST:
          case TIME_PERIOD_ENUM.QUARTER_LAST:
          case TIME_PERIOD_ENUM.YEAR_LAST:
            this.setState({
              statDataLastLoading: false,
              statDataLastError: true,
              statDataLastErrorMessage: ERROR_MESSAGE.DATABASE_CONNECTION_ERROR
            });
            break;
          case TIME_PERIOD_ENUM.WEEK_BEFORE:
          case TIME_PERIOD_ENUM.MONTH_BEFORE:
          case TIME_PERIOD_ENUM.QUARTER_BEFORE:
          case TIME_PERIOD_ENUM.YEAR_BEFORE:
            this.setState({
              statDataBeforeLoading: false,
              statDataBeforeError: true,
              statDataBeforeErrorMessage: ERROR_MESSAGE.DATABASE_CONNECTION_ERROR
            });
            break;
          default:
            return null;
        }

      });

  };

  getDataNew = () => {
    switch (this.state.timePeriod) {
      case TIME_PERIOD_SELECT_ENUM.WEEKLY:
        this.getData(TIME_PERIOD_ENUM.WEEK_CURRENT);
        this.getData(TIME_PERIOD_ENUM.WEEK_LAST);
        this.getData(TIME_PERIOD_ENUM.WEEK_BEFORE);
        break;
      case TIME_PERIOD_SELECT_ENUM.MONTHLY:
        this.getData(TIME_PERIOD_ENUM.MONTH_CURRENT);
        this.getData(TIME_PERIOD_ENUM.MONTH_LAST);
        this.getData(TIME_PERIOD_ENUM.MONTH_BEFORE);
        break;
      case TIME_PERIOD_SELECT_ENUM.QUARTERLY:
        this.getData(TIME_PERIOD_ENUM.QUARTER_CURRENT);
        this.getData(TIME_PERIOD_ENUM.QUARTER_LAST);
        this.getData(TIME_PERIOD_ENUM.QUARTER_BEFORE);
        break;
      case TIME_PERIOD_SELECT_ENUM.ANNUAL:
        this.getData(TIME_PERIOD_ENUM.YEAR_CURRENT);
        this.getData(TIME_PERIOD_ENUM.YEAR_LAST);
        this.getData(TIME_PERIOD_ENUM.YEAR_BEFORE);
        break;
      default:
        this.getData(TIME_PERIOD_ENUM.WEEK_CURRENT);
        this.getData(TIME_PERIOD_ENUM.WEEK_LAST);
        this.getData(TIME_PERIOD_ENUM.WEEK_BEFORE);
    }
  };

  constructor() {
    super();

    this.state = {

      topic: TOPIC_OPTIONS.BIRTHS,
      timePeriod: TIME_PERIOD_SELECT_ENUM.WEEKLY,

      statDataCurrent: '',
      statDataCurrentLoading: true,
      statDataCurrentError: false,
      statDataCurrentErrorMessage: '',

      statDataLast: '',
      statDataLastLoading: true,
      statDataLastError: false,
      statDataLastErrorMessage: '',

      statDataBefore: '',
      statDataBeforeLoading: true,
      statDataBeforeError: false,
      statDataBeforeErrorMessage: '',
    };

  }

  componentWillReceiveProps(nextProps) {

    if (this.props.topic !== nextProps.topic) {
      let topic = nextProps.topic;
      this.setState({topic}, () => {
        this.getDataNew();
      });
    }

    if (this.props.timePeriod !== nextProps.timePeriod) {
      let timePeriod = nextProps.timePeriod;
      this.setState({timePeriod}, () => {
        this.getDataNew();
      });
    }

  }

  componentDidMount() {
    this.getDataNew();
  };

  render() {

    let {
      statDataCurrent, statDataCurrentLoading, statDataCurrentError, statDataCurrentErrorMessage,
      statDataLast, statDataLastLoading, statDataLastError, statDataLastErrorMessage,
      statDataBefore, statDataBeforeLoading, statDataBeforeError, statDataBeforeErrorMessage
    } = this.state;

    let {topic, timePeriod} = this.props;

    return (
      <Fragment>
        <DashboardHeader topic={topic} timePeriod={timePeriod}/>
        <Grid columns={3}>
          <Grid.Row>
            <Grid.Column>
              <ColumnHeader timePeriod={timePeriod} timePeriodType={TIME_PERIOD_TYPE.CURRENT}/>
              {statDataCurrentLoading && <Loader active/>}
              {statDataCurrent && <StatsListMain statData={statDataCurrent} timePeriodType={TIME_PERIOD_TYPE.CURRENT}/>}
              {statDataCurrentError && <Message error>{statDataCurrentErrorMessage}</Message>}
            </Grid.Column>
            <Grid.Column>
              <ColumnHeader timePeriod={timePeriod} timePeriodType={TIME_PERIOD_TYPE.LAST}/>
              {statDataLastLoading && <Loader active/>}
              {statDataLast && <StatsListMain statData={statDataLast} timePeriodType={TIME_PERIOD_TYPE.LAST}/>}
              {statDataLastError && <Message error>{statDataLastErrorMessage}</Message>}
            </Grid.Column>
            <Grid.Column>
              <ColumnHeader timePeriod={timePeriod} timePeriodType={TIME_PERIOD_TYPE.BEFORE}/>
              {statDataBeforeLoading && <Loader active/>}
              {statDataBefore && <StatsListMain statData={statDataBefore} timePeriodType={TIME_PERIOD_TYPE.BEFORE}/>}
              {statDataBeforeError && <Message error>{statDataBeforeErrorMessage}</Message>}
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Fragment>
    );
  }

}

export default DashboardData