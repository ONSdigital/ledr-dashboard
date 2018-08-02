import React from 'react';
import {nullChecker} from "../../../../utils/Utils";
import {List} from "semantic-ui-react";

const OutstandingGeographyDataView = ({statDataa}) => {

  let {
    outstandingGeographyPOB, outstandingGeographyPOE,
    outstandingGeographyUR
  } = statDataa;

  let outstandingGeographyURDisplay = nullChecker(outstandingGeographyUR);
  let outstandingGeographyPOEDisplay = nullChecker(outstandingGeographyPOE);
  let outstandingGeographyPOBDisplay = nullChecker(outstandingGeographyPOB);

  return (
    <List link selection>
      <List.Item>
        <List.Content>
          <List.Header>Usual Residence</List.Header>
          <List.Description>
            {outstandingGeographyURDisplay}
          </List.Description>
        </List.Content>
      </List.Item>
      <List.Item>
        <List.Content>
          <List.Header>Place of Event</List.Header>
          <List.Description>
            {outstandingGeographyPOEDisplay}
          </List.Description>
        </List.Content>
      </List.Item>
      <List.Item>
        <List.Content>
          <List.Header>Place of Birth</List.Header>
          <List.Description>
            {outstandingGeographyPOBDisplay}
          </List.Description>
        </List.Content>
      </List.Item>
    </List>
  )
};

export default OutstandingGeographyDataView;
