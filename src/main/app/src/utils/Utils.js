import {DATE_FORMAT, TIME_PERIOD_SELECT_ENUM, TIME_PERIOD_TYPE} from "./Constants";
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

export const getMonthStartDate = (timePeriodType) => {
  switch (timePeriodType) {
    case TIME_PERIOD_TYPE.CURRENT:
      return moment().startOf('month');
    case TIME_PERIOD_TYPE.LAST:
      return moment().subtract(1, 'month').startOf('month');
    case TIME_PERIOD_TYPE.BEFORE:
      return moment().subtract(2, 'month').startOf('month');
    default:
      return 0;
  }
};

export const getMonthEndDate = (timePeriodType) => {
  switch (timePeriodType) {
    case TIME_PERIOD_TYPE.CURRENT:
      return moment().endOf('month');
    case TIME_PERIOD_TYPE.LAST:
      return moment().subtract(1, 'month').endOf('month');
    case TIME_PERIOD_TYPE.BEFORE:
      return moment().subtract(2, 'month').endOf('month');
    default:
      return 0;
  }
};

export const getQuarterStartDate = (timePeriodType) => {
  switch (timePeriodType) {
    case TIME_PERIOD_TYPE.CURRENT:
      return moment().startOf('quarter');
    case TIME_PERIOD_TYPE.LAST:
      return moment().subtract(1, 'quarter').startOf('quarter');
    case TIME_PERIOD_TYPE.BEFORE:
      return moment().subtract(2, 'quarter').startOf('quarter');
    default:
      return 0;
  }
};

export const getQuarterEndDate = (timePeriodType) => {
  switch (timePeriodType) {
    case TIME_PERIOD_TYPE.CURRENT:
      return moment().endOf('quarter');
    case TIME_PERIOD_TYPE.LAST:
      return moment().subtract(1, 'quarter').endOf('quarter');
    case TIME_PERIOD_TYPE.BEFORE:
      return moment().subtract(2, 'quarter').endOf('quarter');
    default:
      return 0;
  }
};

export const getYearStartDate = (timePeriodType) => {
  switch (timePeriodType) {
    case TIME_PERIOD_TYPE.CURRENT:
      return moment().startOf('year');
    case TIME_PERIOD_TYPE.LAST:
      return moment().subtract(1, 'year').startOf('year');
    case TIME_PERIOD_TYPE.BEFORE:
      return moment().subtract(2, 'year').startOf('year');
    default:
      return 0;
  }
};

export const getYearEndDate = (timePeriodType) => {
  switch (timePeriodType) {
    case TIME_PERIOD_TYPE.CURRENT:
      return moment().endOf('year');
    case TIME_PERIOD_TYPE.LAST:
      return moment().subtract(1, 'year').endOf('year');
    case TIME_PERIOD_TYPE.BEFORE:
      return moment().subtract(2, 'year').endOf('year');
    default:
      return 0;
  }
};

export const formDateText = (timePeriod, timePeriodType) => {
  let headerText;

  switch (timePeriod) {
    case TIME_PERIOD_SELECT_ENUM.WEEKLY:
      headerText = moment(getWeekStartDate(timePeriodType)).format(DATE_FORMAT.WEEK) + ' to ' + moment(getWeekEndDate(timePeriodType)).format(DATE_FORMAT.WEEK);
      break;
    case TIME_PERIOD_SELECT_ENUM.MONTHLY:
      headerText = moment(getMonthStartDate(timePeriodType)).format(DATE_FORMAT.WEEK) + ' to ' + moment(getMonthEndDate(timePeriodType)).format(DATE_FORMAT.WEEK);
      break;
    case TIME_PERIOD_SELECT_ENUM.QUARTERLY:
      headerText = moment(getQuarterStartDate(timePeriodType)).format(DATE_FORMAT.WEEK) + ' to ' + moment(getQuarterEndDate(timePeriodType)).format(DATE_FORMAT.WEEK);
      break;
    case TIME_PERIOD_SELECT_ENUM.ANNUAL:
      headerText = moment(getYearStartDate(timePeriodType)).format(DATE_FORMAT.YEAR);
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
