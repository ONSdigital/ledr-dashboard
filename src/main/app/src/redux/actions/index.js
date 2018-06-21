import {
  SET_MODAL_DATA_PROPERTY,
  SET_MODAL_OPEN, SET_MODAL_TIME_PERIOD_TYPE,
  SET_SELECT_TIME_PERIOD,
  SET_SELECT_TOPIC
} from "../constants/action-types"

/**
 * Sets topicProperty (Value taken from Arrays.TOPIC_OPTIONS) from TopicSelect Component
 * @param topic
 * @returns {{type: string, payload: *}}
 */
export const setSelectTopic = topic => ({
  type: SET_SELECT_TOPIC,
  payload: topic
});

/**
 * Sets timePeriod property (Value taken from Arrays.TIME_PERIOD_OPTIONS) from TimePeriodSelect Component
 * @param timePeriod
 * @returns {{type: string, payload: *}}
 */
export const setSelectTimePeriod = timePeriod => ({
  type: SET_SELECT_TIME_PERIOD,
  payload: timePeriod
});

/**
 * Sets modalOpen property (either true or false) from DataList for use in DataModal
 * @param modalOpen
 * @returns {{type: string, payload: *}}
 */
export const setModalOpen = modalOpen => ({
  type: SET_MODAL_OPEN,
  payload: modalOpen
});

/**
 * Sets Data Property to be used in Modal (e.g. Outstanding Geography) based on list item clicked
 * @param modalDataProperty
 * @returns {{type: string, payload: *}}
 */
export const setModalDataProperty = modalDataProperty => ({
  type: SET_MODAL_DATA_PROPERTY,
  payload: modalDataProperty
});

/**
 * Sets Time Period Type (taken from Constants.TIME_PERIOD_TYPE) for use in DataModal
 * @param modalTimePeriodType
 * @returns {{type: string, payload: *}}
 */
export const setModalTimePeriodType = modalTimePeriodType => ({
  type: SET_MODAL_TIME_PERIOD_TYPE,
  payload: modalTimePeriodType
});