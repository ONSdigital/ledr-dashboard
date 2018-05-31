import React, {Component, Fragment} from 'react';
import {Divider} from "semantic-ui-react";
import TopicSelectComponent from "./TopicSelectComponent";
import {TOPIC_OPTIONS_ENUM} from "../utils/Constants";
import {connect} from "react-redux";
import DashboardGeneric from "./DashboardGeneric";

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
        <TopicSelectComponent/>
        <Divider/>
        <DashboardGeneric topic={topic}/>
      </Fragment>
    );
  }

}

const Dashboard = connect(mapStateToProps, null)(DashboardRedux);

export default Dashboard;