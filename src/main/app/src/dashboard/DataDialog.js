import React, {Component} from 'react';
import {Button, Header, Modal} from "semantic-ui-react";
import {connect} from "react-redux";
import {toTitleCase} from "../utils/Utils";

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

    let {modalOpen, closeModal, data, topic, timePeriod} = this.props;

    let dataType;
    let outstandingGeographyPOB;
    let outstandingGeographyPOE;
    let outstandingGeographyUR;

    if (data) {
      dataType = data.dataType;
      outstandingGeographyPOB = data.outstandingGeographyPOB;
      outstandingGeographyPOE = data.outstandingGeographyPOE;
      outstandingGeographyUR = data.outstandingGeographyUR;
    }

    topic = toTitleCase(topic);
    timePeriod = toTitleCase(timePeriod);

    return (
      <Modal open={modalOpen} onClose={closeModal}>
        <Modal.Content>
          <Header as='h2'>
            <Header.Content>
              {dataType}
              <Header.Subheader>
                {topic} / {timePeriod}
              </Header.Subheader>
            </Header.Content>
          </Header>
          <div>
            <b>Usual residence:</b> {outstandingGeographyUR}<br/>
            <b>Place of event:</b> {outstandingGeographyPOE}<br/>
            <b>Place of birth:</b> {outstandingGeographyPOB}<br/>
          </div>
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
