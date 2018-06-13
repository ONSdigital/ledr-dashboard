import {
  SET_MODAL_DATA_PROPERTY,
  SET_MODAL_OPEN, SET_MODAL_TIME_PERIOD_TYPE,
  SET_SELECT_TIME_PERIOD,
  SET_SELECT_TOPIC
} from "../constants/action-types"

export const setSelectTopic = topic => ({
  type: SET_SELECT_TOPIC,
  payload: topic
});

export const setSelectTimePeriod = timePeriod => ({
  type: SET_SELECT_TIME_PERIOD,
  payload: timePeriod
});

export const setModalOpen = modalOpen => ({
  type: SET_MODAL_OPEN,
  payload: modalOpen
});

export const setModalDataProperty = modalDataProperty => ({
  type: SET_MODAL_DATA_PROPERTY,
  payload: modalDataProperty
});

export const setModalTimePeriodType = modalTimePeriodType => ({
  type: SET_MODAL_TIME_PERIOD_TYPE,
  payload: modalTimePeriodType
})