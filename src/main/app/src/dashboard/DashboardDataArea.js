import React, {Component} from "react";
import {
  API_ENDPOINT,
  ERROR_MESSAGE,
  TIME_PERIOD_ENUM,
  TIME_PERIOD_SELECT_ENUM,
  TIME_PERIOD_TYPE,
  TOPIC_ENUM as TOPIC_OPTIONS,
} from "../utils/Constants";
import {Grid, Loader, Message} from "semantic-ui-react";
import DataColumnList from "./data/column/DataColumnList";
import DataColumnHeader from "./data/column/DataColumnHeader";
import {connect} from "react-redux";

const mapStateToProps = state => {
  return {
    topic: state.topic,
    timePeriod: state.timePeriod
  };
};

/**
 *
 */
class DataDashboardDataAreaRedux extends Component {

  /**
   * Gets data for specified by calling API endpoint
   */
  getDataForTimePeriod = (timePeriod) => {

    //TODO: Time Period should come from TIME_PERIOD_ENUM
    let url = `${API_ENDPOINT.DASHBOARD_MOCK}/${this.props.topic}/${timePeriod}`;

    return fetch(url)
      .then((response) => {

        if (response.status === 500) {
          throw Error(response.statusText);
        }

        return response.json()
      })
      .then((json) => {

        switch (timePeriod) {
          case TIME_PERIOD_ENUM.WEEK_CURRENT:
          case TIME_PERIOD_ENUM.MONTH_CURRENT:
          case TIME_PERIOD_ENUM.QUARTER_CURRENT:
          case TIME_PERIOD_ENUM.YEAR_CURRENT:
            this.setState({statDataCurrent: json, statDataCurrentLoading: false});
            break;
          case TIME_PERIOD_ENUM.WEEK_LAST:
          case TIME_PERIOD_ENUM.MONTH_LAST:
          case TIME_PERIOD_ENUM.QUARTER_LAST:
          case TIME_PERIOD_ENUM.YEAR_LAST:
            this.setState({statDataLast: json, statDataLastLoading: false});
            break;
          case TIME_PERIOD_ENUM.WEEK_BEFORE:
          case TIME_PERIOD_ENUM.MONTH_BEFORE:
          case TIME_PERIOD_ENUM.QUARTER_BEFORE:
          case TIME_PERIOD_ENUM.YEAR_BEFORE:
            this.setState({statDataBefore: json, statDataBeforeLoading: false});
            break;
          default:
            return null;
        }

      }).catch(() => {
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

  getData = () => {
    switch (this.state.timePeriod) {
      case TIME_PERIOD_SELECT_ENUM.WEEKLY:
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.WEEK_CURRENT);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.WEEK_LAST);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.WEEK_BEFORE);
        break;
      case TIME_PERIOD_SELECT_ENUM.MONTHLY:
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.MONTH_CURRENT);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.MONTH_LAST);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.MONTH_BEFORE);
        break;
      case TIME_PERIOD_SELECT_ENUM.QUARTERLY:
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.QUARTER_CURRENT);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.QUARTER_LAST);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.QUARTER_BEFORE);
        break;
      case TIME_PERIOD_SELECT_ENUM.ANNUAL:
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.YEAR_CURRENT);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.YEAR_LAST);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.YEAR_BEFORE);
        break;
      default:
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.WEEK_CURRENT);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.WEEK_LAST);
        this.getDataForTimePeriod(TIME_PERIOD_ENUM.WEEK_BEFORE);
    }
  };

  constructor() {
    super();

    this.state = {

      topic: TOPIC_OPTIONS.BIRTH,
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
        this.getData();
      });
    }

    if (this.props.timePeriod !== nextProps.timePeriod) {
      let timePeriod = nextProps.timePeriod;
      this.setState({timePeriod}, () => {
        this.getData();
      });
    }

  }

  componentDidMount() {
    this.getData();
  };

  render() {

    let {
      timePeriod,
      statDataCurrent, statDataCurrentLoading, statDataCurrentError, statDataCurrentErrorMessage,
      statDataLast, statDataLastLoading, statDataLastError, statDataLastErrorMessage,
      statDataBefore, statDataBeforeLoading, statDataBeforeError, statDataBeforeErrorMessage
    } = this.state;

    return (
      <Grid columns={3}>
        <Grid.Row>
          <Grid.Column>
            <DataColumnHeader timePeriod={timePeriod} timePeriodType={TIME_PERIOD_TYPE.CURRENT}/>
            {statDataCurrentLoading && <Loader active/>}
            {statDataCurrent &&
            <DataColumnList statData={statDataCurrent} timePeriodType={TIME_PERIOD_TYPE.CURRENT}/>}
            {statDataCurrentError && <Message error>{statDataCurrentErrorMessage}</Message>}
          </Grid.Column>
          <Grid.Column>
            <DataColumnHeader timePeriod={timePeriod} timePeriodType={TIME_PERIOD_TYPE.LAST}/>
            {statDataLastLoading && <Loader active/>}
            {statDataLast && <DataColumnList statData={statDataLast} timePeriodType={TIME_PERIOD_TYPE.LAST}/>}
            {statDataLastError && <Message error>{statDataLastErrorMessage}</Message>}
          </Grid.Column>
          <Grid.Column>
            <DataColumnHeader timePeriod={timePeriod} timePeriodType={TIME_PERIOD_TYPE.BEFORE}/>
            {statDataBeforeLoading && <Loader active/>}
            {statDataBefore && <DataColumnList statData={statDataBefore} timePeriodType={TIME_PERIOD_TYPE.BEFORE}/>}
            {statDataBeforeError && <Message error>{statDataBeforeErrorMessage}</Message>}
          </Grid.Column>
        </Grid.Row>
      </Grid>
    );
  }

}

const DashboardDataArea = connect(mapStateToProps, null)(DataDashboardDataAreaRedux);

export default DashboardDataArea