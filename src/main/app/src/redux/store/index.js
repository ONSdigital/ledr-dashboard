import {createStore} from "redux";
import rootReducer from "../reducers/index";


const store = createStore(rootReducer,
  //This property allows for viewing Redux state in https://github.com/zalmoxisus/redux-devtools-extension
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);

export default store;