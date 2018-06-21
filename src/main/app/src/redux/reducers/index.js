import {
  SET_MODAL_DATA_PROPERTY,
  SET_MODAL_OPEN, SET_MODAL_TIME_PERIOD_TYPE,
  SET_SELECT_TIME_PERIOD,
  SET_SELECT_TOPIC,
} from "../constants/action-types";
import {
  TIME_PERIOD_SELECT_ENUM,
  TOPIC_ENUM
} from "../../utils/Constants";

const initialState = {
  topic: TOPIC_ENUM.BIRTH,
  timePeriod: TIME_PERIOD_SELECT_ENUM.WEEKLY,
  modalOpen: false,
  modalDataProperty: '',
  modalTimePeriodType: ''
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case SET_SELECT_TOPIC:
      return {...state, topic: action.payload};
    case SET_SELECT_TIME_PERIOD:
      return {...state, timePeriod: action.payload};
    case SET_MODAL_OPEN:
      return {...state, modalOpen: action.payload};
    case SET_MODAL_DATA_PROPERTY:
      return {...state, modalDataProperty: action.payload};
    case SET_MODAL_TIME_PERIOD_TYPE:
      return {...state, modalTimePeriodType: action.payload};
    default:
      return state;
  }
};

export default rootReducer;