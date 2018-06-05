import React, {Component} from 'react';
import {Button, Grid, Header, Modal, Statistic} from "semantic-ui-react";
import {connect} from "react-redux";
import {formDateText, formHeaderText, toTitleCase} from "../utils/Utils";

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
    let outstandingGeographyPOB;
    let outstandingGeographyPOE;
    let outstandingGeographyUR;

    if (data) {
      dataType = data.dataType;
      outstandingGeographyPOB = data.outstandingGeographyPOB;
      outstandingGeographyPOE = data.outstandingGeographyPOE;
      outstandingGeographyUR = data.outstandingGeographyUR;
    }

    let dateRangeDisplay = formDateText(timePeriod, timePeriodType);

    let topicDisplay = toTitleCase(topic);
    let timePeriodDisplay = toTitleCase(timePeriod);
    let timePeriodTypeDisplay = formHeaderText(timePeriod, timePeriodType);


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
          <Grid textAlign='center' columns={3}>
            <Grid.Row>
              <Grid.Column>
                <Statistic>
                  <Statistic.Value>{outstandingGeographyUR}</Statistic.Value>
                  <Statistic.Label>Usual residence</Statistic.Label>
                </Statistic>
              </Grid.Column>
              <Grid.Column>
                <Statistic>
                  <Statistic.Value>{outstandingGeographyPOE}</Statistic.Value>
                  <Statistic.Label>Place of event</Statistic.Label>
                </Statistic>
              </Grid.Column>
              <Grid.Column>
                <Statistic>
                  <Statistic.Value>{outstandingGeographyPOB}</Statistic.Value>
                  <Statistic.Label>Place of birth</Statistic.Label>
                </Statistic>
              </Grid.Column>
            </Grid.Row>
          </Grid>
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
