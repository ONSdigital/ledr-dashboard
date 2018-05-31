export const ROUTER_PATH = {
  BASE: 'ledr-dashboard-poc-ui',
  DASHBOARD: '/',
  LOGOUT: 'logout',
};

export const LEDR_MENU = {
  HOME: {
    key: 'home',
    name: 'Home',
    link: ROUTER_PATH.LEDR_BASE + ROUTER_PATH.HOME,
  }
};

export const TOPIC_OPTIONS_ENUM = {
  BIRTHS: 'births',
  DEATHS: 'deaths'
};

export const WEEK_DATE_ENUM = {
  CURRENT_WEEK: 'current_week',
  LAST_WEEK: 'last_week',
  WEEK_BEFORE: 'week_before'
};

export const WEEK_DATE_FORMAT = 'ddd DD/MM/YYYY';