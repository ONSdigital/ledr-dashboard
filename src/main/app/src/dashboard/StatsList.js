import React, {Component} from 'react';
import {Label, List, Segment} from "semantic-ui-react";
import {round} from "../utils/Utils";
import {DATA_PROPERTY} from "../utils/Constants";

class StatsList extends Component {

  shouldComponentUpdate(nextProps) {
    return nextProps.statData !== this.props.statData;
  }

  render() {

    let {statData} = this.props;

    let {
      recordsReceived, fullyCoded, outstandingGeographyFull, outstandingOccupation, outstandingCause
    } = statData;

    let fullyCodedPercent = round((fullyCoded / recordsReceived) * 100, 0);
    let outstandingGeographyPercent = round((outstandingGeographyFull / recordsReceived) * 100, 0);
    let outstandingOccupationPercent = round((outstandingOccupation / recordsReceived) * 100, 0);
    let outstandingCausePercent = round((outstandingCause / recordsReceived) * 100, 0);

    return (
      <Segment attached>
        <List link selection>
          <List.Item>
            <List.Content>
              <List.Header>Records received:</List.Header>
              <List.Description>{recordsReceived}</List.Description>
            </List.Content>
          </List.Item>
          <List.Item>
            <List.Content>
              <List.Header>Fully coded:</List.Header>
              <List.Description>{fullyCoded + ' '}
                {(() => {
                  if (fullyCodedPercent >= 75)
                    return <Label color='green' circular>{fullyCodedPercent}%</Label>;
                  if (fullyCodedPercent < 75 && fullyCodedPercent >= 50)
                    return <Label color='orange' circular>{fullyCodedPercent}%</Label>;
                  else
                    return <Label color='red' circular>{fullyCodedPercent}%</Label>;
                })()}
              </List.Description>
            </List.Content>
          </List.Item>
          <List.Item onClick={() => this.props.show(DATA_PROPERTY.OUTSTANDING_GEOGRAPHY.PARENT)}>
            <List.Content>
              <List.Header>Outstanding geography:</List.Header>
              <List.Description>
                {outstandingGeographyFull + ' '}
                <Label color='grey' circular>{outstandingGeographyPercent}%</Label>
              </List.Description>
            </List.Content>
          </List.Item>
          <List.Item>
            <List.Content>
              <List.Header>Outstanding occupation: </List.Header>
              <List.Description>
                {outstandingOccupation + ' '}
                <Label color='grey' circular>{outstandingOccupationPercent}%</Label>
              </List.Description>
            </List.Content>
          </List.Item>
          <List.Item>
            <List.Content>
              <List.Header>Outstanding cause: </List.Header>
              <List.Description>
                {outstandingCause + ' '}
                <Label color='grey' circular>{outstandingCausePercent}%</Label>
              </List.Description>
            </List.Content>
          </List.Item>
        </List>
      </Segment>
    );
  }
}

export default StatsList;
