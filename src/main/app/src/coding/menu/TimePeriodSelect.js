import React, {Component} from 'react';
import {Form, Radio} from "semantic-ui-react";
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

/**
 * This class renders the component for selecting a time period
 */
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
      <Form>
        <Form.Group inline>
          <Form.Field>
            <label>Period:</label>
          </Form.Field>
          <Form.Field>
            <Radio
              label='Weekly'
              name='timePeriodRadioGroup'
              value={TIME_PERIOD_SELECT_ENUM.WEEKLY}
              checked={timePeriod === TIME_PERIOD_SELECT_ENUM.WEEKLY}
              onChange={this.handleTimePeriodChangeSelect}/>
          </Form.Field>
          <Form.Field>
            <Radio
              label='Monthly'
              name='timePeriodRadioGroup'
              value={TIME_PERIOD_SELECT_ENUM.MONTHLY}
              checked={timePeriod === TIME_PERIOD_SELECT_ENUM.MONTHLY}
              onChange={this.handleTimePeriodChangeSelect}/>
          </Form.Field>
          <Form.Field>
            <Radio
              label='Quarterly'
              name='timePeriodRadioGroup'
              value={TIME_PERIOD_SELECT_ENUM.QUARTERLY}
              checked={timePeriod === TIME_PERIOD_SELECT_ENUM.QUARTERLY}
              onChange={this.handleTimePeriodChangeSelect}/>
          </Form.Field>
          <Form.Field>
            <Radio
              label='Yearly'
              name='timePeriodRadioGroup'
              value={TIME_PERIOD_SELECT_ENUM.ANNUAL}
              checked={timePeriod === TIME_PERIOD_SELECT_ENUM.ANNUAL}
              onChange={this.handleTimePeriodChangeSelect}/>
          </Form.Field>
        </Form.Group>
      </Form>
    )

  }
}

const TimePeriodSelect = connect(mapStateToProps, mapDispatchToProps)(TimePeriodSelectRedux);

export default TimePeriodSelect;
