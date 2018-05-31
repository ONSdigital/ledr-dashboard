import {SET_SELECT_TOPIC,} from "../constants/action-types";

const initialState = {
  topic: ''
};

const rootReducer = (state = initialState, action) => {
  switch (action.type) {
    case SET_SELECT_TOPIC:
      return {...state, topic: action.payload};
    default:
      return state;
  }
};

export default rootReducer;