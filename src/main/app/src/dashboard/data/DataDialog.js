import React, {Component} from 'react';
import {Button, Header, Modal} from "semantic-ui-react";
import {connect} from "react-redux";
import {formDateText, formHeaderText, formTopicDisplay, toTitleCase} from "../../utils/Utils";
import {setModalOpen} from "../../redux/actions";
import DialogDataArea from "./DialogDataArea";

const mapStateToProps = state => {
  return {
    topic: state.topic,
    timePeriod: state.timePeriod,
    modalOpen: state.modalOpen,
    modalDataProperty: state.modalDataProperty,
    modalTimePeriodType: state.modalTimePeriodType
  };
};

const mapDispatchToProps = dispatch => {
  return {
    setModalOpen: modalOpen => dispatch(setModalOpen(modalOpen))
  };
};

class DataDialogRedux extends Component {

  render() {

    let {topic, timePeriod, modalOpen, modalDataProperty, modalTimePeriodType, closeModal} = this.props;

    let topicDisplay = formTopicDisplay(topic);
    let timePeriodDisplay = toTitleCase(timePeriod);
    let timePeriodTypeDisplay = formHeaderText(timePeriod, modalTimePeriodType);
    let dateRangeDisplay = formDateText(timePeriod, modalTimePeriodType);

    return (
      <Modal open={modalOpen} onClose={closeModal}>
        <Modal.Content>
          <Header as='h2'>
            <Header.Content>
              {modalDataProperty}
              <Header.Subheader>
                {topicDisplay} | {timePeriodDisplay} | {timePeriodTypeDisplay} ({dateRangeDisplay})
              </Header.Subheader>
            </Header.Content>
          </Header>
          <DialogDataArea topic={topic} timePeriod={timePeriod} modalDataProperty={modalDataProperty}
                          modalTimePeriodType={modalTimePeriodType}/>
        </Modal.Content>
        <Modal.Actions>
          <Button negative onClick={() => this.props.setModalOpen(false)}>Close</Button>
        </Modal.Actions>
      </Modal>
    )
  }
}

const DataDialog = connect(mapStateToProps, mapDispatchToProps)(DataDialogRedux);

export default DataDialog;
