import React, {Component, Fragment} from 'react';
import {Divider, Form, Message} from "semantic-ui-react";
import TimePeriodSelect from "./menu/TimePeriodSelect";
import TopicSelect from "./menu/TopicSelect";
import {TIME_PERIOD_SELECT_ENUM, TOPIC_OPTIONS_ENUM} from "../utils/Constants";
import {connect} from "react-redux";
import DashboardData from "./DashboardData";

const mapStateToProps = state => {
  return {
    topic: state.topic,
    timePeriod: state.timePeriod
  };
};

class DashboardRedux extends Component {

  constructor() {
    super();

    this.state = {
      topic: TOPIC_OPTIONS_ENUM.BIRTH,
      timePeriod: TIME_PERIOD_SELECT_ENUM.WEEKLY
    };

  }

  componentWillReceiveProps(nextProps) {

    if (nextProps.topic !== this.props.topic) {
      let topic = nextProps.topic;
      this.setState({topic})
    }

    if (nextProps.timePeriod !== this.props.timePeriod) {
      let timePeriod = nextProps.timePeriod;
      this.setState({timePeriod})
    }

  }

  render() {

    let {topic, timePeriod} = this.state;

    return (
      <Fragment>
        <Form>
          <Form.Group>
            <TopicSelect/>
            <TimePeriodSelect/>
            <Message visible info>
              <Message.Header>PROOF OF CONCEPT</Message.Header>
              <p>
                This Dashboard is a <strong>proof of concept</strong> and as such is <em>not</em> the final product.
              </p>
            </Message>
          </Form.Group>
        </Form>
        <Divider/>
        <DashboardData topic={topic} timePeriod={timePeriod}/>
        <Message>
          The information on this page is refreshed every hour.
        </Message>
      </Fragment>
    );
  }

}

const Dashboard = connect(mapStateToProps, null)(DashboardRedux);

export default Dashboard;