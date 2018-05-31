import React, {Component} from 'react';
import {Label, List, Popup} from "semantic-ui-react";
import {round} from "../utils/Utils";

class StatsList extends Component {

  render() {

    let {statData} = this.props;

    let {
      recordsReceived, fullyCoded, outstandingGeographyFull, outstandingGeographyPOB, outstandingGeographyPOE,
      outstandingGeographyUR, outstandingOccupation, outstandingCause
    } = statData;

    let fullyCodedPercent = round((fullyCoded / recordsReceived) * 100, 0);
    let outstandingGeographyPercent = round((outstandingGeographyFull / recordsReceived) * 100, 0);
    let outstandingOccupationPercent = round((outstandingOccupation / recordsReceived) * 100, 0);
    let outstandingCausePercent = round((outstandingCause / recordsReceived) * 100, 0);

    return (
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
        <Popup
          trigger={
            <List.Item>
              <List.Content>
                <List.Header>Outstanding geography:</List.Header>
                <List.Description>
                  {outstandingGeographyFull + ' '}
                  <Label color='grey' circular>{outstandingGeographyPercent}%</Label>
                </List.Description>
              </List.Content>
            </List.Item>
          }
          content={
            <div>
              <b>Usual residence:</b> {outstandingGeographyUR}<br/>
              <b>Place of event:</b> {outstandingGeographyPOE}<br/>
              <b>Place of birth:</b> {outstandingGeographyPOB}<br/>
            </div>}/>
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
    );
  }
}

export default StatsList;
