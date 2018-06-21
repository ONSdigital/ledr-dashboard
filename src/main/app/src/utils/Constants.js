export const ROUTER_PATH = {
  DASHBOARD: '/',
  LOGOUT: '/logout',
};

export const TOPIC_ENUM = {
  BIRTH: 'births',
  DEATH: 'deaths'
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
};

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

export const API_ENDPOINT = {
  DASHBOARD: 'http://localhost:7051/ledr-dashboard-poc/topic',
  DASHBOARD_MOCK: 'http://localhost:7051/ledr-dashboard-poc/test/topic',
  POPUP: 'http://localhost:7051/ledr-dashboard-poc',
};

export const DATA_PROPERTY_DASHBOARD = {
  RECORDS_RECEIVED: 'Records Received',
  FULLY_CODED: 'Fully Coded',
  OUTSTANDING_GEOGRAPHY: 'Outstanding Geography',
  OUTSTANDING_CAUSE: 'Outstanding Cause',
};

export const DATA_PROPERTY_POPUP = {
  OUTSTANDING_GEOGRAPHY: {
    USUAL_RESIDENCE: 'Usual Residence',
    PLACE_OF_EVENT: 'Place of Event',
    PLACE_OF_BIRTH: 'Place of Birth'
  }
};

export const DATE_FORMAT = {
  WEEK: 'ddd DD/MM/YYYY',
  YEAR: 'YYYY'
};

export const ERROR_MESSAGE = {
  DATABASE_CONNECTION_ERROR: "Unable to connect to the Database.\nPlease refresh or try again later."
};