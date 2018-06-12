import React, {Component} from 'react';
import {Form, Message} from "semantic-ui-react";
import TimePeriodSelect from "./TimePeriodSelect";
import TopicSelect from "./TopicSelect";

class DashboardMenu extends Component {

  render() {

    return (
      <Form>
        <Form.Group>
          <TopicSelect/>
          <TimePeriodSelect/>
          <Message visible info>
            <Message.Header>PROOF OF CONCEPT</Message.Header>
            <p>
              This Dashboard is a <strong>proof of concept</strong> and as such is <em>not</em> the final product.
            </p>
          </Message>
        </Form.Group>
      </Form>
    );
  }

}

export default DashboardMenu;