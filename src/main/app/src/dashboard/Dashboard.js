import React, {Component, Fragment} from 'react';
import {Divider, Form, Message} from "semantic-ui-react";
import TopicSelectComponent from "./TopicSelectComponent";
import {TOPIC_OPTIONS_ENUM} from "../utils/Constants";
import {connect} from "react-redux";
import DashboardData from "./DashboardGeneric";
import TimePeriodSelectComponent from "./TimePeriodSelectComponent";

const mapStateToProps = state => {
  return {
    topic: state.topic
  };
};

class DashboardRedux extends Component {

  constructor() {
    super();

    this.state = {
      topic: TOPIC_OPTIONS_ENUM.BIRTHS
    };

  }

  componentWillReceiveProps(nextProps) {

    if (nextProps.topic !== this.props.topic) {
      let topic = nextProps.topic;
      this.setState({topic})
    }

  }

  render() {

    let {topic} = this.state;

    return (
      <Fragment>
        <Form>
          <Form.Group>
            <TopicSelectComponent/>
            <TimePeriodSelectComponent/>
            <Message visible info>
              <Message.Header>PROOF OF CONCEPT</Message.Header>
              <p>
                This Dashboard is a <strong>proof of concept</strong> and as such is <em>not</em> the final product.
              </p>
            </Message>
          </Form.Group>
        </Form>
        <Divider/>
        <DashboardData topic={topic}/>
        <Message>
          The information on this page is refreshed every hour.
        </Message>
      </Fragment>
    );
  }

}

const Dashboard = connect(mapStateToProps, null)(DashboardRedux);

export default Dashboard;