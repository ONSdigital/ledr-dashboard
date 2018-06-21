import React, {Component} from 'react';
import {Button, Modal} from "semantic-ui-react";
import {connect} from "react-redux";
import {setModalOpen} from "../../../redux/actions/index";
import DataModalDataArea from "./DataModalDataArea";
import DataModalHeader from "./DataModalHeader";

const mapStateToProps = state => {
  return {
    modalOpen: state.modalOpen,
  };
};

const mapDispatchToProps = dispatch => {
  return {
    setModalOpen: modalOpen => dispatch(setModalOpen(modalOpen))
  };
};

class DataModalRedux extends Component {

  render() {

    let {modalOpen, closeModal} = this.props;

    return (
      <Modal open={modalOpen} onClose={closeModal}>
        <Modal.Content>
          <DataModalHeader/>
          <DataModalDataArea/>
        </Modal.Content>
        <Modal.Actions>
          <Button negative onClick={() => this.props.setModalOpen(false)}>Close</Button>
        </Modal.Actions>
      </Modal>
    )
  }
}

const DataModal = connect(mapStateToProps, mapDispatchToProps)(DataModalRedux);

export default DataModal;
