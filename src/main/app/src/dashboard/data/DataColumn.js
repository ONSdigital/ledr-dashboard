import React, {Component, Fragment} from 'react';
import DataList from "./DataList";

class DataColumn extends Component {

  shouldComponentUpdate(nextProps) {
    if (nextProps.statData !== this.props.statData) {
      return true
    }
    return false
  }

  render() {

    let {statData, timePeriodType} = this.props;

    return (
      <Fragment>
        <DataList statData={statData} timePeriodType={timePeriodType}/>
      </Fragment>
    );
  }
}

export default DataColumn;
