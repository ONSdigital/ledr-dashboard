import {SET_SELECT_TIME_PERIOD, SET_SELECT_TOPIC} from "../constants/action-types"

export const setSelectTopic = topic => ({
    type: SET_SELECT_TOPIC,
    payload: topic
});

export const setSelectTimePeriod = timePeriod => ({
  type: SET_SELECT_TIME_PERIOD,
  payload: timePeriod
});