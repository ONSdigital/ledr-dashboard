import React, {Component} from 'react';
import {Modal} from "semantic-ui-react";
import Button from "semantic-ui-react/dist/es/elements/Button/Button";

/*const DataDialog = (props) => {

  let data = props.data;
  console.log('data', data);
  let outstandingGeographyPOB;
  let outstandingGeographyPOE;
  let outstandingGeographyUR;

  if (data) {
    outstandingGeographyPOB = data.outstandingGeographyPOB;
    outstandingGeographyPOE = data.outstandingGeographyPOE;
    outstandingGeographyUR = data.outstandingGeographyUR;
  }

  return (
    <Modal open={props.modalOpen} onClose={props.closeModal}>
      <Modal.Header>
        DATA
      </Modal.Header>
      <Modal.Content>
        MORE DATA
        <div>
          <b>Usual residence:</b> {outstandingGeographyUR}<br/>
          <b>Place of event:</b> {outstandingGeographyPOE}<br/>
          <b>Place of birth:</b> {outstandingGeographyPOB}<br/>
        </div>
      </Modal.Content>
      <Modal.Actions>
        <Button negative onClick={props.closeModal}>Close</Button>
      </Modal.Actions>
    </Modal>
  )
}

export default DataDialog;*/


class DataDialog extends Component {
  /*
    shouldComponentUpdate(nextProps, nextState) {

      if (nextProps.modalOpen !== this.props.modalOpen) {
        return false
      }

      if (nextProps.closeModal !== this.props.closeModal) {
        return false
      }

      if (nextProps.data !== this.props.data) {
        return false
      }

      return true;
    }*/

  render() {

    let {modalOpen, closeModal, data} = this.props;

    console.log('modalOpen', modalOpen);
    console.log('closeModal', closeModal);
    console.log('data', data);

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
