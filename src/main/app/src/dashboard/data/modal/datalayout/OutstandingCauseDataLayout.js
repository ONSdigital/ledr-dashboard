import React, {Fragment} from 'react';
import {nullChecker, round} from "../../../../utils/Utils";
import {Divider, Header, Label, List} from "semantic-ui-react";

/**
 * Comment with @params added to remove 'unresolved variable' warning in WebStorm
 * @param statData
 * @param statData.nonInquestReceived
 * @param statData.nonInquestReceivedOutstandingNonNeonates
 * @param statData.nonInquestReceivedOutstandingNeonates
 * @param statData.nonInquestReceivedErrorsAndWarningsNonNeonates
 * @param statData.nonInquestReceivedErrorsAndWarningsNeonates
 * @param statData.inquestPartV
 * @param statData.inquestPartVOutstandingNonNeonates
 * @param statData.inquestPartVOutstandingNeonates
 * @param statData.inquestPartVErrorsAndWarningsNonNeonates
 * @param statData.inquestPartVErrorsAndWarningsNeonates
 * @param statData.inquestAdjourned
 * @param statData.inquestAdjournedOutstandingBlue
 * @param statData.inquestAdjournedOutstandingYellow
 */
const OutstandingCauseDataLayout = ({statData}) => {

  let nonInquestReceivedDisplay = nullChecker(statData.nonInquestReceived);
  let nonInquestReceivedOutstandingNonNeonatesDisplay = nullChecker(statData.nonInquestReceivedOutstandingNonNeonates);
  let nonInquestReceivedOutstandingNonNeonatesPercent = round((statData.nonInquestReceivedOutstandingNonNeonates / statData.nonInquestReceived) * 100, 0);
  let nonInquestReceivedOutstandingNeonatesDisplay = nullChecker(statData.nonInquestReceivedOutstandingNeonates);
  let nonInquestReceivedOutstandingNeonatesPercent = round((statData.nonInquestReceivedOutstandingNeonates / statData.nonInquestReceived) * 100, 0);
  let nonInquestReceivedErrorsAndWarningsNonNeonatesDisplay = nullChecker(statData.nonInquestReceivedErrorsAndWarningsNonNeonates);
  let nonInquestReceivedErrorsAndWarningsNonNeonatesPercent = round((statData.nonInquestReceivedErrorsAndWarningsNonNeonates / statData.nonInquestReceived) * 100, 0);
  let nonInquestReceivedErrorsAndWarningsNeonatesDisplay = nullChecker(statData.nonInquestReceivedErrorsAndWarningsNeonates);
  let nonInquestReceivedErrorsAndWarningsNeonatesPercent = round((statData.nonInquestReceivedErrorsAndWarningsNeonates / statData.nonInquestReceived) * 100, 0);

  let inquestPartVDisplay = nullChecker(statData.inquestPartV);
  let inquestPartVOutstandingNonNeonatesDisplay = nullChecker(statData.inquestPartVOutstandingNonNeonates);
  let inquestPartVOutstandingNonNeonatesPercent = round((statData.inquestPartVOutstandingNonNeonates / statData.inquestPartV) * 100, 0);
  let inquestPartVOutstandingNeonatesDisplay = nullChecker(statData.inquestPartVOutstandingNeonates);
  let inquestPartVOutstandingNeonatesPercent = round((statData.inquestPartVOutstandingNeonates / statData.inquestPartV) * 100, 0);
  let inquestPartVErrorsAndWarningsNonNeonatesDisplay = nullChecker(statData.inquestPartVErrorsAndWarningsNonNeonates);
  let inquestPartVErrorsAndWarningsNonNeonatesPercent = round((statData.inquestPartVErrorsAndWarningsNonNeonates / statData.inquestPartV) * 100, 0);
  let inquestPartVErrorsAndWarningsNeonatesDisplay = nullChecker(statData.inquestPartVErrorsAndWarningsNeonates);
  let inquestPartVErrorsAndWarningsNeonatesPercent = round((statData.inquestPartVErrorsAndWarningsNeonates / statData.inquestPartV) * 100, 0);

  let inquestAdjournedDisplay = nullChecker(statData.inquestAdjourned);
  let inquestAdjournedOutstandingBlueDisplay = nullChecker(statData.inquestAdjournedOutstandingBlue);
  let inquestAdjournedOutstandingBluePercent = round((statData.inquestAdjournedOutstandingBlue / statData.inquestAdjourned) * 100, 0);
  let inquestAdjournedOutstandingYellowDisplay = nullChecker(statData.inquestAdjournedOutstandingYellow);
  let inquestAdjournedOutstandingYellowPercent = round((statData.inquestAdjournedOutstandingYellow / statData.inquestAdjourned) * 100, 0);

  return (
    <Fragment>
      <Header as='h3'>
        <Header.Content>
          Non-Inquest Received
          <Header.Subheader>
            {nonInquestReceivedDisplay}
          </Header.Subheader>
        </Header.Content>
      </Header>
      <List link selection>
        <List.Item>
          <List.Content>
            <List.Header>Outstanding Non-Neonates</List.Header>
            <List.Description>
              {nonInquestReceivedOutstandingNonNeonatesDisplay}
              <Label className='list-label' color='grey' circular>{nonInquestReceivedOutstandingNonNeonatesPercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
        <List.Item>
          <List.Content>
            <List.Header>Outstanding Neonates</List.Header>
            <List.Description>
              {nonInquestReceivedOutstandingNeonatesDisplay}
              <Label className='list-label' color='grey' circular>{nonInquestReceivedOutstandingNeonatesPercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
        <List.Item>
          <List.Content>
            <List.Header>Errors and Warnings Non-Neonates</List.Header>
            <List.Description>
              {nonInquestReceivedErrorsAndWarningsNonNeonatesDisplay}
              <Label className='list-label' color='grey' circular>{nonInquestReceivedErrorsAndWarningsNonNeonatesPercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
        <List.Item>
          <List.Content>
            <List.Header>Errors and Warnings Neonates</List.Header>
            <List.Description>
              {nonInquestReceivedErrorsAndWarningsNeonatesDisplay}
              <Label className='list-label' color='grey' circular>{nonInquestReceivedErrorsAndWarningsNeonatesPercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
      </List>
      <Divider/>

      <Header as='h3'>
        <Header.Content>
          Inquest (Part V)
          <Header.Subheader>
            {inquestPartVDisplay}
          </Header.Subheader>
        </Header.Content>
      </Header>
      <List link selection>
        <List.Item>
          <List.Content>
            <List.Header>Outstanding Non-Neonates</List.Header>
            <List.Description>
              {inquestPartVOutstandingNonNeonatesDisplay}
              <Label className='list-label' color='grey' circular>{inquestPartVOutstandingNonNeonatesPercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
        <List.Item>
          <List.Content>
            <List.Header>Outstanding Neonates</List.Header>
            <List.Description>
              {inquestPartVOutstandingNeonatesDisplay}
              <Label className='list-label' color='grey' circular>{inquestPartVOutstandingNeonatesPercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
        <List.Item>
          <List.Content>
            <List.Header>Errors and Warnings Non-Neonates</List.Header>
            <List.Description>
              {inquestPartVErrorsAndWarningsNonNeonatesDisplay}
              <Label className='list-label' color='grey' circular>{inquestPartVErrorsAndWarningsNonNeonatesPercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
        <List.Item>
          <List.Content>
            <List.Header>Errors and Warnings Neonates</List.Header>
            <List.Description>
              {inquestPartVErrorsAndWarningsNeonatesDisplay}
              <Label className='list-label' color='grey' circular>{inquestPartVErrorsAndWarningsNeonatesPercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
      </List>
      <Divider/>

      <Header as='h3'>
        <Header.Content>
          Inquest Adjourned
          <Header.Subheader>
            {inquestAdjournedDisplay}
          </Header.Subheader>
        </Header.Content>
      </Header>
      <List link selection>
        <List.Item>
          <List.Content>
            <List.Header>Outstanding Blue</List.Header>
            <List.Description>
              {inquestAdjournedOutstandingBlueDisplay}
              <Label className='list-label' color='grey' circular>{inquestAdjournedOutstandingBluePercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
        <List.Item>
          <List.Content>
            <List.Header>Outstanding Yellow</List.Header>
            <List.Description>
              {inquestAdjournedOutstandingYellowDisplay}
              <Label className='list-label' color='grey' circular>{inquestAdjournedOutstandingYellowPercent}%</Label>
            </List.Description>
          </List.Content>
        </List.Item>
      </List>
    </Fragment>
  )
};

export default OutstandingCauseDataLayout;
