import React, {Component} from 'react';
import {Header} from "semantic-ui-react";
import {TIME_PERIOD_SELECT_ENUM, TIME_PERIOD_TYPE, WEEK_DATE_FORMAT} from "../utils/Constants";
import moment from "moment";

class ColumnHeader extends Component {

  getWeekStartDate = (timePeriodType) => {
    switch (timePeriodType) {
      case TIME_PERIOD_TYPE.CURRENT:
        return moment().weekday(-1).valueOf();
      case TIME_PERIOD_TYPE.LAST:
        return moment().weekday(-8).valueOf();
      case TIME_PERIOD_TYPE.BEFORE:
        return moment().weekday(-15).valueOf();
      default:
        return 0;
    }
  };

  getWeekEndDate = (timePeriodType) => {
    switch (timePeriodType) {
      case TIME_PERIOD_TYPE.CURRENT:
        return moment().weekday(5).valueOf();
      case TIME_PERIOD_TYPE.LAST:
        return moment().weekday(-2).valueOf();
      case TIME_PERIOD_TYPE.BEFORE:
        return moment().weekday(-9).valueOf();
      default:
        return 0;
    }
  };


  formHeaderText = (timePeriod, timePeriodType) => {
    let headerText;

    switch (timePeriod) {
      case TIME_PERIOD_SELECT_ENUM.WEEKLY:
        switch (timePeriodType) {
          case TIME_PERIOD_TYPE.CURRENT:
            headerText = 'Current Week';
            break;
          case TIME_PERIOD_TYPE.LAST:
            headerText = 'Last Week';
            break;
          case TIME_PERIOD_TYPE.BEFORE:
            headerText = 'Week Before';
            break;
          default:
            break;
        }
        break;
      case TIME_PERIOD_SELECT_ENUM.MONTHLY:
        switch (timePeriodType) {
          case TIME_PERIOD_TYPE.CURRENT:
            headerText = 'Current Month';
            break;
          case TIME_PERIOD_TYPE.LAST:
            headerText = 'Last Month';
            break;
          case TIME_PERIOD_TYPE.BEFORE:
            headerText = 'Month Before';
            break;
          default:
            break;
        }
        break;
      case TIME_PERIOD_SELECT_ENUM.QUARTERLY:
        switch (timePeriodType) {
          case TIME_PERIOD_TYPE.CURRENT:
            headerText = 'Current Quarter';
            break;
          case TIME_PERIOD_TYPE.LAST:
            headerText = 'Last Quarter';
            break;
          case TIME_PERIOD_TYPE.BEFORE:
            headerText = 'Quarter Before';
            break;
          default:
            break;
        }
        break;
      case TIME_PERIOD_SELECT_ENUM.ANNUAL:
        switch (timePeriodType) {
          case TIME_PERIOD_TYPE.CURRENT:
            headerText = 'Current Year';
            break;
          case TIME_PERIOD_TYPE.LAST:
            headerText = 'Last Year';
            break;
          case TIME_PERIOD_TYPE.BEFORE:
            headerText = 'Year Before';
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }

    return headerText;
  };

  formSubHeaderText = (timePeriod, timePeriodType) => {
    let headerText;

    switch (timePeriod) {
      case TIME_PERIOD_SELECT_ENUM.WEEKLY:
        headerText = moment(this.getWeekStartDate(timePeriodType)).format(WEEK_DATE_FORMAT) + ' to ' + moment(this.getWeekEndDate(timePeriodType)).format(WEEK_DATE_FORMAT);
        break;
      case TIME_PERIOD_SELECT_ENUM.MONTHLY:
        switch (timePeriodType) {
          case TIME_PERIOD_TYPE.CURRENT:
            headerText = 'Current Month Sub';
            break;
          case TIME_PERIOD_TYPE.LAST:
            headerText = 'Last Month Sub';
            break;
          case TIME_PERIOD_TYPE.BEFORE:
            headerText = 'Month Before Sub';
            break;
          default:
            break;
        }
        break;
      case TIME_PERIOD_SELECT_ENUM.QUARTERLY:
        switch (timePeriodType) {
          case TIME_PERIOD_TYPE.CURRENT:
            headerText = 'Current Quarter';
            break;
          case TIME_PERIOD_TYPE.LAST:
            headerText = 'Last Quarter';
            break;
          case TIME_PERIOD_TYPE.BEFORE:
            headerText = 'Quarter Before';
            break;
          default:
            break;
        }
        break;
      case TIME_PERIOD_SELECT_ENUM.ANNUAL:
        switch (timePeriodType) {
          case TIME_PERIOD_TYPE.CURRENT:
            headerText = 'Current Year';
            break;
          case TIME_PERIOD_TYPE.LAST:
            headerText = 'Last Year';
            break;
          case TIME_PERIOD_TYPE.BEFORE:
            headerText = 'Year Before';
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }

    return headerText;
  };


  render() {

    let {timePeriod, timePeriodType} = this.props;

    let headerText = this.formHeaderText(timePeriod, timePeriodType);
    let subHeaderText = this.formSubHeaderText(timePeriod, timePeriodType);

    return (
      <Header as='h3' attached='top'>
        <Header.Content>
          {headerText}:
          <Header.Subheader>
            {subHeaderText}
          </Header.Subheader>
        </Header.Content>
      </Header>
    );
  }
}

export default ColumnHeader;
