import React, {Component} from 'react';
import {Button, Header, List, Modal} from "semantic-ui-react";
import {connect} from "react-redux";
import {formDateText, formHeaderText, formTopicDisplay, nullChecker, toTitleCase} from "../../utils/Utils";

const mapStateToProps = state => {
  return {
    topic: state.topic,
    timePeriod: state.timePeriod
  };
};

class DataDialogRedux extends Component {

  shouldComponentUpdate(nextProps) {
    return nextProps.modalOpen !== this.props.modalOpen
  }

  render() {

    let {modalOpen, closeModal, data, topic, timePeriod, timePeriodType} = this.props;

    let dataType;
    let statData;

    if (data) {
      dataType = data.dataType;
      statData = data.statData;
    }

    let topicDisplay = formTopicDisplay(topic);
    let timePeriodDisplay = toTitleCase(timePeriod);
    let timePeriodTypeDisplay = formHeaderText(timePeriod, timePeriodType);
    let dateRangeDisplay = formDateText(timePeriod, timePeriodType);

    return (
      <Modal open={modalOpen} onClose={closeModal}>
        <Modal.Content>
          <Header as='h2'>
            <Header.Content>
              {dataType}
              <Header.Subheader>
                {topicDisplay} | {timePeriodDisplay} | {timePeriodTypeDisplay} ({dateRangeDisplay})
              </Header.Subheader>
            </Header.Content>
          </Header>
          <List link selection>
            {statData &&
            Object.keys(statData).map((key) => {
              return (
                <List.Item key={key}>
                  <List.Content>
                    <List.Header>{key}</List.Header>
                    <List.Description>{nullChecker(statData[key])}</List.Description>
                  </List.Content>
                </List.Item>
              )
            })
            }
          </List>
        </Modal.Content>
        <Modal.Actions>
          <Button negative onClick={closeModal}>Close</Button>
        </Modal.Actions>
      </Modal>
    )
  }
}

const DataDialog = connect(mapStateToProps, null)(DataDialogRedux);

export default DataDialog;
