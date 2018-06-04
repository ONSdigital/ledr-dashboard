import {TIME_PERIOD_SELECT_ENUM, TIME_PERIOD_TYPE, WEEK_DATE_FORMAT} from "./Constants";
import moment from "moment/moment";

/**
 * Used instead of Math.round() as that can have rounding errors due to floating-number arithmetic.
 * Source: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/round
 * @param number
 * @param precision
 * @returns number
 */
export const round = (number, precision) => {
  let shift = function (number, precision) {
    let numArray = ("" + number).split("e");
    return +(numArray[0] + "e" + (numArray[1] ? (+numArray[1] + precision) : precision));
  };

  let finalValue = shift(Math.round(shift(number, +precision)), -precision);

  if (isNaN(finalValue)) {
    finalValue = 0;
  }

  return finalValue;
};


export const toTitleCase = (str) => {
  return str.replace(/\w\S*/g, function (txt) {
    return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
  });
};

export const getWeekStartDate = (timePeriodType) => {
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

export const getWeekEndDate = (timePeriodType) => {
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

export const formDateText = (timePeriod, timePeriodType) => {
  let headerText;

  switch (timePeriod) {
    case TIME_PERIOD_SELECT_ENUM.WEEKLY:
      headerText = moment(getWeekStartDate(timePeriodType)).format(WEEK_DATE_FORMAT) + ' to ' + moment(getWeekEndDate(timePeriodType)).format(WEEK_DATE_FORMAT);
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

export const formHeaderText = (timePeriod, timePeriodType) => {
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
