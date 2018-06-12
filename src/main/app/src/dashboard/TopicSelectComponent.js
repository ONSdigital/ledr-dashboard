import React, {Component} from 'react';
import {Form} from "semantic-ui-react";
import {TOPIC_OPTIONS} from "../utils/Arrays";
import {connect} from "react-redux";
import {setSelectTopic} from "../redux/actions/index";
import {TOPIC_OPTIONS_ENUM} from "../utils/Constants";

const mapStateToProps = state => {
  return {topic: state.topic};
};

const mapDispatchToProps = dispatch => {
  return {
    setSelectTopic: topic => dispatch(setSelectTopic(topic))
  };
};

class TopicSubTopicComponentRedux extends Component {

  handleTopicChangeSelect = (e, {value}) => {
    let topic = value;
    this.setState({topic}, () => {
      this.props.setSelectTopic(topic);
    });
  };

  constructor() {
    super();

    this.state = {
      topic: TOPIC_OPTIONS_ENUM.BIRTH,
    };

  }

  render() {

    let {topic} = this.state;

    return (
      <Form.Select label='Topic' value={topic} options={TOPIC_OPTIONS} placeholder='Topic'
                   onChange={this.handleTopicChangeSelect}/>
    );
  }
}

const TopicSelectComponent = connect(mapStateToProps, mapDispatchToProps)(TopicSubTopicComponentRedux);

export default TopicSelectComponent;
