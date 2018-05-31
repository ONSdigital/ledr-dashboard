import React, {Component, Fragment} from 'react';
import {Form, Message} from "semantic-ui-react";
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
      topic: TOPIC_OPTIONS_ENUM.BIRTHS,
    };

  }

  render() {

    let {topic} = this.state;

    return (
      <Fragment>
        <Form>
          <Form.Group>
            <Form.Select label='Topic' value={topic} options={TOPIC_OPTIONS} placeholder='Topic'
                         onChange={this.handleTopicChangeSelect}/>
            <Message visible info>
              <Message.Header>PROOF OF CONCEPT</Message.Header>
              <p>
                This Dashboard is a <strong>proof of concept</strong> and as such is <em>not</em> the final product.
              </p>
            </Message>
          </Form.Group>
        </Form>
      </Fragment>
    );
  }
}

const TopicSelectComponent = connect(mapStateToProps, mapDispatchToProps)(TopicSubTopicComponentRedux);

export default TopicSelectComponent;
