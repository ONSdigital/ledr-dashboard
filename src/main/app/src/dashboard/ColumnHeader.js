import React, {Component} from 'react';
import {Header} from "semantic-ui-react";
import {formDateText, formHeaderText} from "../utils/Utils";

class ColumnHeader extends Component {

  shouldComponentUpdate(nextProps) {
    if (nextProps.timePeriod !== this.props.timePeriod) {
      return true
    }
    if (nextProps.timePeriodType !== this.props.timePeriodType) {
      return true
    }
    return false
  }

  render() {

    let {timePeriod, timePeriodType} = this.props;

    let headerText = formHeaderText(timePeriod, timePeriodType);
    let subHeaderText = formDateText(timePeriod, timePeriodType);

    return (
      <Header as='h3' attached='top'>
        <Header.Content>
          {headerText}:
          <Header.Subheader>
            {subHeaderText}
          </Header.Subheader>
        </Header.Content>
      </Header>
    );
  }
}

export default ColumnHeader;
