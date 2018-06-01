import React, {Component} from 'react';
import {Modal} from "semantic-ui-react";
import Button from "semantic-ui-react/dist/es/elements/Button/Button";

class DataDialog extends Component {

  shouldComponentUpdate(nextProps) {
    return nextProps.modalOpen !== this.props.modalOpen
  }

  render() {

    let {modalOpen, closeModal, data} = this.props;

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

    return (
      <Modal open={modalOpen} onClose={closeModal}>
        <Modal.Header>
          {dataType}
        </Modal.Header>
        <Modal.Content>
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

export default DataDialog
