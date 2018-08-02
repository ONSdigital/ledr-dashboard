import React, {Component} from "react";
import {Button, Form, Grid, Header, Label, Table} from "semantic-ui-react";

class Home extends Component {

  render() {

    return (
      <Grid columns={2} celled>
        <Grid.Row>
          <Grid.Column>
              <Header as='h2'>Deaths</Header>
              <Form>
                <Form.Group inline>
                  <Form.Field>
                    <label>Period:</label>
                  </Form.Field>
                  <Button>Today</Button>
                  <Button>Last Week</Button>
                  <Button>Last Month</Button>
                  <Button>Last Year</Button>
                </Form.Group>
              </Form>
              <Table basic='very'>
                <Table.Body>
                  <Table.Row>
                    <Table.Cell>Deaths Load:</Table.Cell>
                    <Table.Cell><Label color='green' size='large'>Complete</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Link Mortality:</Table.Cell>
                    <Table.Cell><Label color='green' size='large'>Complete</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Extract Cause Coding:</Table.Cell>
                    <Table.Cell><Label color='green' size='large'>Complete</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Load Cause Codes:</Table.Cell>
                    <Table.Cell><Label color='orange' size='large'>In Progress</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Load Part V:</Table.Cell>
                    <Table.Cell><Label color='red' size='large'>Failed</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Extract Uncoded Occupation:</Table.Cell>
                    <Table.Cell><Label color='green' size='large'>Complete</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Load Coded Occupation:</Table.Cell>
                    <Table.Cell><Label color='grey' size='large'>Not Started</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                </Table.Body>
              </Table>
          </Grid.Column>
          <Grid.Column>
              <Header as='h2'>Births</Header>
              <Form>
                <Form.Group inline>
                  <Form.Field>
                    <label>Period:</label>
                  </Form.Field>
                  <Button>Today</Button>
                  <Button>Last Week</Button>
                  <Button>Last Month</Button>
                  <Button>Last Year</Button>
                </Form.Group>
              </Form>
              <Table basic='very'>
                <Table.Body>
                  <Table.Row>
                    <Table.Cell>Births Load:</Table.Cell>
                    <Table.Cell><Label color='green' size='large'>Complete</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Births Notification Load:</Table.Cell>
                    <Table.Cell><Label color='grey' size='large'>Not Started</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Link Births:</Table.Cell>
                    <Table.Cell><Label color='green' size='large'>Complete</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Extract Uncoded Occupation:</Table.Cell>
                    <Table.Cell><Label color='grey' size='large'>Not Started</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                  <Table.Row>
                    <Table.Cell>Load Coded Occupation:</Table.Cell>
                    <Table.Cell><Label color='grey' size='large'>Not Started</Label></Table.Cell>
                    <Table.Cell><a onClick={() => {}}>view more</a></Table.Cell>
                  </Table.Row>
                </Table.Body>
              </Table>
          </Grid.Column>
        </Grid.Row>
      </Grid>
    )

  }

}

export default Home