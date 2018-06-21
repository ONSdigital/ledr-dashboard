import React, {Component} from 'react';
import {Form} from "semantic-ui-react";
import {TOPIC_OPTIONS} from "../../utils/Arrays";
import {connect} from "react-redux";
import {setSelectTopic} from "../../redux/actions/index";
import {TOPIC_ENUM} from "../../utils/Constants";

const mapStateToProps = state => {
  return {topic: state.topic};
};

const mapDispatchToProps = dispatch => {
  return {
    setSelectTopic: topic => dispatch(setSelectTopic(topic))
  };
};

class TopicSelectRedux extends Component {

  handleTopicChangeSelect = (e, {value}) => {
    let topic = value;
    this.setState({topic}, () => {
      this.props.setSelectTopic(topic);
    });
  };

  constructor() {
    super();

    this.state = {
      topic: TOPIC_ENUM.BIRTH,
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

const TopicSelect = connect(mapStateToProps, mapDispatchToProps)(TopicSelectRedux);

export default TopicSelect;
