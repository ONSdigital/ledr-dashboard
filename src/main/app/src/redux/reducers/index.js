import {SET_SELECT_TIME_PERIOD, SET_SELECT_TOPIC,} from "../constants/action-types";
import {TIME_PERIOD_SELECT_ENUM, TOPIC_OPTIONS_ENUM} from "../../utils/Constants";

const initialState = {
  topic: TOPIC_OPTIONS_ENUM.BIRTHS,
  timePeriod: TIME_PERIOD_SELECT_ENUM.WEEKLY
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