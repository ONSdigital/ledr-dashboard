export const ROUTER_PATH = {
  BASE: 'ledr-dashboard-poc-ui',
  DASHBOARD: '/',
  LOGOUT: 'logout',
};

export const TOPIC_OPTIONS_ENUM = {
  BIRTHS: 'births',
  DEATHS: 'deaths'
};

export const TIME_PERIOD_SELECT_ENUM = {
  WEEKLY: 'WEEKLY',
  MONTHLY: 'MONTHLY',
  QUARTERLY: 'QUARTERLY',
  ANNUAL: 'ANNUAL'
};

export const TIME_PERIOD_TYPE = {
  CURRENT: 'current',
  LAST: 'last',
  BEFORE: 'before',
}

export const TIME_PERIOD_ENUM = {
  WEEK_CURRENT: 'WEEK_CURRENT',
  WEEK_LAST: 'WEEK_LAST',
  WEEK_BEFORE: 'WEEK_BEFORE',

  MONTH_CURRENT: 'MONTH_CURRENT',
  MONTH_LAST: 'MONTH_LAST',
  MONTH_BEFORE: 'MONTH_BEFORE',

  QUARTER_CURRENT: 'QUARTER_CURRENT',
  QUARTER_LAST: 'QUARTER_LAST',
  QUARTER_BEFORE: 'QUARTER_BEFORE',

  YEAR_CURRENT: 'YEAR_CURRENT',
  YEAR_LAST: 'YEAR_LAST',
  YEAR_BEFORE: 'YEAR_BEFORE'
};

export const TIME_PERIOD_HEADER = {
  WEEK_CURRENT: 'Current Week',
  WEEK_LAST: 'Last Week',
  WEEK_BEFORE: 'Week Before',

  MONTH_CURRENT: 'MONTH_CURRENT',
  MONTH_LAST: 'MONTH_LAST',
  MONTH_BEFORE: 'MONTH_BEFORE',

  QUARTER_CURRENT: 'QUARTER_CURRENT',
  QUARTER_LAST: 'QUARTER_LAST',
  QUARTER_BEFORE: 'QUARTER_BEFORE',

  YEAR_CURRENT: 'YEAR_CURRENT',
  YEAR_LAST: 'YEAR_LAST',
  YEAR_BEFORE: 'YEAR_BEFORE'
};

export const API_ENDPOINT = 'http://localhost:7051/ledr-dashboard-poc/topic';

export const DATA_PROPERTY = {
  OUTSTANDING_GEOGRAPHY: 'Outstanding Geography'
};

export const WEEK_DATE_FORMAT = 'ddd DD/MM/YYYY';

export const ERROR_MESSAGE = {
  DATABASE_CONNECTION_ERROR: "Unable to connect to the Database.\nPlease refresh or try again later."
};