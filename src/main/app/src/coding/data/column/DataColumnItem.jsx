import React from 'react';
import {Form, Grid, List, Progress} from "semantic-ui-react";

/**
 *
 * @param header - header text
 * @param description - description text
 * @param label - label text
 * @param onclick - methods to call in onclick
 * @returns {*}
 * @constructor
 */
const DataColumnItem = ({labelText, count, percent, onClick}) => {

  let barColour = 'red';

  if (percent >= 75)
    barColour = 'green';
  if (percent < 75 && percent >= 50)
    barColour = 'orange';

  return (
    <Form>
      <List>
        <List.Item>
          <List.Content>
            <Grid columns={2}>
              <Grid.Row>
                <Grid.Column>
                  <Form.Field className='progress-label'>
                    <label>{labelText} {count}</label>
                  </Form.Field>
                </Grid.Column>
                {onClick &&
                <Grid.Column textAlign='right'>
                  <a onClick={onClick}>view more</a>
                </Grid.Column>
                }
              </Grid.Row>
            </Grid>
            <Progress percent={percent} progress color={barColour}/>
          </List.Content>
        </List.Item>
      </List>
    </Form>
  )
};

export default DataColumnItem;