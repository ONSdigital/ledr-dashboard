import {SET_SELECT_TOPIC} from "../constants/action-types"

export const setSelectTopic = topic => ({
    type: SET_SELECT_TOPIC,
    payload: topic
});