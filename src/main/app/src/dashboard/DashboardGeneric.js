import React, {Component, Fragment} from 'react';
import {Grid, Header, Loader, Message, Segment} from "semantic-ui-react";
import moment from "moment";
import {TOPIC_OPTIONS_ENUM as TOPIC_OPTIONS, WEEK_DATE_ENUM, WEEK_DATE_FORMAT} from "../utils/Constants";
import StatsList from "./StatsList";
import {toTitleCase} from "../utils/Utils";

class DashboardGeneric extends Component {

  /**
   * Sets values for start/end weekdays (Week starts on Saturday and ends on Friday)
   */
  setWeekDateStates = () => {
    //TODO: For now date is stored/used as a UNIX Timestamp but this may need to change for API
    let currentWeekDateStart = moment().weekday(-1).valueOf();
    let currentWeekDateEnd = moment().weekday(5).valueOf();
    let lastWeekDateStart = moment().weekday(-8).valueOf();
    let lastWeekDateEnd = moment().weekday(-2).valueOf();
    let weekBeforeDateStart = moment().weekday(-15).valueOf();
    let weekBeforeDateEnd = moment().weekday(-9).valueOf();

    this.setState({
      currentWeekDateStart,
      currentWeekDateEnd,
      lastWeekDateStart,
      lastWeekDateEnd,
      weekBeforeDateStart,
      weekBeforeDateEnd
    })
  };

  /**
   * Gets data for specified week by calling API endpoint
   */
  getWeekData = (week) => {

    let url = 'http://localhost:7051/ledr-dashboard-poc/topic/' + this.state.topic + '/' + week;

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

        switch (week) {
          case WEEK_DATE_ENUM.CURRENT_WEEK:
            this.setState({statDataCurrentWeek: data, statDataCurrentWeekLoading: false});
            break;
          case WEEK_DATE_ENUM.LAST_WEEK:
            this.setState({statDataLastWeek: data, statDataLastWeekLoading: false});
            break;
          case WEEK_DATE_ENUM.WEEK_BEFORE:
            this.setState({statDataWeekBefore: data, statDataWeekBeforeLoading: false});
            break;
          default:
            return null;
        }

      }).catch((error) => {
        console.log('error', error);

        switch (week) {
          case WEEK_DATE_ENUM.CURRENT_WEEK:
            this.setState({
              statDataCurrentWeekLoading: false,
              statDataCurrentWeekError: true,
              statDataCurrentWeekErrorMessage: error.toString()
            });
            break;
          case WEEK_DATE_ENUM.LAST_WEEK:
            this.setState({
              statDataLastWeekLoading: false,
              statDataLastWeekError: true,
              statDataLastWeekErrorMessage: error.toString()
            });
            break;
          case WEEK_DATE_ENUM.WEEK_BEFORE:
            this.setState({
              statDataWeekBeforeLoading: false,
              statDataWeekBeforeError: true,
              statDataWeekBeforeErrorMessage: error.toString()
            });
            break;
          default:
            return null;
        }

      });

  };

  componentWillReceiveProps(nextProps) {

    if (nextProps.topic !== this.props.topic) {
      let topic = nextProps.topic;
      this.setState({topic}, () => {
        this.setWeekDateStates();
        this.getWeekData(WEEK_DATE_ENUM.CURRENT_WEEK);
        this.getWeekData(WEEK_DATE_ENUM.LAST_WEEK);
        this.getWeekData(WEEK_DATE_ENUM.WEEK_BEFORE);
      });

    }

  }

  componentDidMount() {
    this.setWeekDateStates();
    this.getWeekData(WEEK_DATE_ENUM.CURRENT_WEEK);
    this.getWeekData(WEEK_DATE_ENUM.LAST_WEEK);
    this.getWeekData(WEEK_DATE_ENUM.WEEK_BEFORE);
  };

  constructor() {
    super();

    this.state = {

      topic: TOPIC_OPTIONS.BIRTHS,

      statDataCurrentWeek: '',
      statDataCurrentWeekLoading: true,
      statDataCurrentWeekError: false,
      statDataCurrentWeekErrorMessage: '',

      statDataLastWeek: '',
      statDataLastWeekLoading: true,
      statDataLastWeekError: false,
      statDataLastWeekErrorMessage: '',

      statDataWeekBefore: '',
      statDataWeekBeforeLoading: true,
      statDataWeekBeforeError: false,
      statDataWeekBeforeErrorMessage: '',
    };

  }

  render() {

    let {

      topic,

      currentWeekDateStart,
      currentWeekDateEnd,
      lastWeekDateStart,
      lastWeekDateEnd,
      weekBeforeDateStart,
      weekBeforeDateEnd,

      statDataCurrentWeek,
      statDataCurrentWeekLoading,
      statDataCurrentWeekError,
      statDataCurrentWeekErrorMessage,

      statDataLastWeek,
      statDataLastWeekLoading,
      statDataLastWeekError,
      statDataLastWeekErrorMessage,

      statDataWeekBefore,
      statDataWeekBeforeLoading,
      statDataWeekBeforeError,
      statDataWeekBeforeErrorMessage
    } = this.state;

    topic = toTitleCase(topic);

    return (
      <Fragment>
        <Header as='h1'>
          <Header.Content>
            Weekly Stats: {topic}
            <Header.Subheader>
              Initial Regs
            </Header.Subheader>
          </Header.Content>
        </Header>
        <Grid columns={3}>
          <Grid.Row>
            <Grid.Column>
              <Header as='h3' attached='top'>
                <Header.Content>
                  Current week:
                  <Header.Subheader>
                    {moment(currentWeekDateStart).format(WEEK_DATE_FORMAT)} to {moment(currentWeekDateEnd).format(WEEK_DATE_FORMAT)}
                  </Header.Subheader>
                </Header.Content>
              </Header>
              <Segment attached>
                {statDataCurrentWeekLoading && <Loader active/>}
                {statDataCurrentWeek && <StatsList statData={statDataCurrentWeek}/>}
                {statDataCurrentWeekError && <Message error>{statDataCurrentWeekErrorMessage}</Message>}
              </Segment>
            </Grid.Column>
            <Grid.Column>
              <Header as='h3' attached='top'>
                <Header.Content>
                  Last week:
                  <Header.Subheader>
                    {moment(lastWeekDateStart).format(WEEK_DATE_FORMAT)} to {moment(lastWeekDateEnd).format(WEEK_DATE_FORMAT)}
                  </Header.Subheader>
                </Header.Content>
              </Header>
              <Segment attached>
                {statDataLastWeekLoading && <Loader active/>}
                {statDataLastWeek && <StatsList statData={statDataLastWeek}/>}
                {statDataLastWeekError && <Message error>{statDataLastWeekErrorMessage}</Message>}
              </Segment>
            </Grid.Column>
            <Grid.Column>
              <Header as='h3' attached='top'>
                <Header.Content>
                  Week before:
                  <Header.Subheader>
                    {moment(weekBeforeDateStart).format(WEEK_DATE_FORMAT)} to {moment(weekBeforeDateEnd).format(WEEK_DATE_FORMAT)}
                  </Header.Subheader>
                </Header.Content>
              </Header>
              <Segment attached>
                {statDataWeekBeforeLoading && <Loader active/>}
                {statDataWeekBefore && <StatsList statData={statDataWeekBefore}/>}
                {statDataWeekBeforeError && <Message error>{statDataWeekBeforeErrorMessage}</Message>}
              </Segment>
            </Grid.Column>
          </Grid.Row>
        </Grid>
        <Message>
          The information on this page is refreshed every hour.
        </Message>
      </Fragment>
    );
  }
}

export default DashboardGeneric;
