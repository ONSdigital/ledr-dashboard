import React, {Component} from 'react';
import {Form, Radio} from "semantic-ui-react";
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

/**
 * This class renders the component for selecting a topic
 */
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
      <Form>
        <Form.Group inline>
          <Form.Field>
            <label>Topic:</label>
          </Form.Field>
          <Form.Field>
            <Radio
              label='Deaths'
              name='topicRadioGroup'
              value={TOPIC_ENUM.DEATH}
              checked={topic === TOPIC_ENUM.DEATH}
              onChange={this.handleTopicChangeSelect}/>
          </Form.Field>
          <Form.Field>
            <Radio
              label='Births'
              name='topicRadioGroup'
              value={TOPIC_ENUM.BIRTH}
              checked={topic === TOPIC_ENUM.BIRTH}
              onChange={this.handleTopicChangeSelect}/>
          </Form.Field>
        </Form.Group>
      </Form>
    )
  }
}

const TopicSelect = connect(mapStateToProps, mapDispatchToProps)(TopicSelectRedux);

export default TopicSelect;
