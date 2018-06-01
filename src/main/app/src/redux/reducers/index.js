import {SET_SELECT_TIME_PERIOD, SET_SELECT_TOPIC,} from "../constants/action-types";

const initialState = {
  topic: '',
  timePeriod: ''
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case SET_SELECT_TOPIC:
      return {...state, topic: action.payload};
    case SET_SELECT_TIME_PERIOD:
      return {...state, timePeriod: action.payload};
    default:
      return state;
  }
};

export default rootReducer;