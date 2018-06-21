import React, {Component} from 'react';
import {Form} from "semantic-ui-react";
import {TIME_PERIOD_OPTIONS} from "../../utils/Arrays";
import {connect} from "react-redux";
import {setSelectTimePeriod} from "../../redux/actions/index";
import {TIME_PERIOD_SELECT_ENUM} from "../../utils/Constants";

const mapStateToProps = state => {
  return {timePeriod: state.timePeriod};
};

const mapDispatchToProps = dispatch => {
  return {
    setSelectTimePeriod: timePeriod => dispatch(setSelectTimePeriod(timePeriod))
  };
};

class TimePeriodSelectRedux extends Component {

  handleTimePeriodChangeSelect = (e, {value}) => {
    let timePeriod = value;
    this.setState({timePeriod}, () => {
      this.props.setSelectTimePeriod(timePeriod);
    });
  };

  constructor() {
    super();

    this.state = {
      timePeriod: TIME_PERIOD_SELECT_ENUM.WEEKLY,
    };

  }

  render() {

    let {timePeriod} = this.state;

    return (
      <Form.Select label='Time Period' value={timePeriod} options={TIME_PERIOD_OPTIONS} placeholder='Time Period'
                   onChange={this.handleTimePeriodChangeSelect}/>
    );
  }
}

const TimePeriodSelect = connect(mapStateToProps, mapDispatchToProps)(TimePeriodSelectRedux);

export default TimePeriodSelect;
