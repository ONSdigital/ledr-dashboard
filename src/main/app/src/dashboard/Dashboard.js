import React, {Component, Fragment} from 'react'
import {Grid, Header, Menu, Segment, Table} from "semantic-ui-react";
import {VictoryPie} from 'victory'

class Dashboard extends Component {

  render() {

    return (
      <Fragment>
        <Header as='h1'>June 2018</Header>
        <Header as='h2'>Life Events Processing (Mid June 2017)</Header>
        <Grid columns={2}>
          <Grid.Row>
            <Grid.Column>
              <Menu attached='top' widths={1} inverted>
                <Menu.Item>
                  Live & Still Birth Registrations
                </Menu.Item>
              </Menu>
              <Segment attached='bottom'>
                <Grid columns={2} verticalAlign='middle'>
                  <Grid.Row>
                    <Grid.Column>
                      <VictoryPie
                        data={[
                          {x: "Fully Coded", y: 6254},
                          {x: "Awaiting Coding", y: 105}
                        ]}
                        colorScale={['#99CCFF', '#FF9900']}
                      />
                    </Grid.Column>
                    <Grid.Column>
                      <Table celled>
                        <Table.Header>
                          <Table.Row>
                            <Table.HeaderCell>Live</Table.HeaderCell>
                            <Table.HeaderCell>Still</Table.HeaderCell>
                            <Table.HeaderCell> </Table.HeaderCell>
                          </Table.Row>
                        </Table.Header>
                        <Table.Body>
                          <Table.Row>
                            <Table.Cell>62401</Table.Cell>
                            <Table.Cell>252</Table.Cell>
                            <Table.Cell>Received</Table.Cell>
                          </Table.Row>
                          <Table.Row style={{background: '#99CCFF'}}>
                            <Table.Cell>62299</Table.Cell>
                            <Table.Cell>249</Table.Cell>
                            <Table.Cell>Fully Coded</Table.Cell>
                          </Table.Row>
                          <Table.Row style={{background: '#FF9900'}}>
                            <Table.Cell>102</Table.Cell>
                            <Table.Cell>3</Table.Cell>
                            <Table.Cell>Awaiting Coding</Table.Cell>
                          </Table.Row>
                          <Table.Row>
                            <Table.Cell>8.45</Table.Cell>
                            <Table.Cell>9.37</Table.Cell>
                            <Table.Cell>Days to Process</Table.Cell>
                          </Table.Row>
                        </Table.Body>
                      </Table>
                    </Grid.Column>
                  </Grid.Row>
                </Grid>
              </Segment>
            </Grid.Column>
            <Grid.Column>
              <Menu attached='top' widths={1} inverted>
                <Menu.Item>
                  Death Registrations
                </Menu.Item>
              </Menu>
              <Segment attached='bottom'>
                <Grid columns={2} verticalAlign='middle'>
                  <Grid.Row>
                    <Grid.Column>
                      <VictoryPie
                        data={[
                          {x: "Fully Coded", y: 6254},
                          {x: "Awaiting Coding", y: 105}
                        ]}
                        colorScale={['#99CCFF', '#FF9900']}
                      />
                    </Grid.Column>
                    <Grid.Column>
                      <Table celled>
                        <Table.Header>
                          <Table.Row>
                            <Table.HeaderCell>Death</Table.HeaderCell>
                            <Table.HeaderCell> </Table.HeaderCell>
                          </Table.Row>
                        </Table.Header>
                        <Table.Body>
                          <Table.Row>
                            <Table.Cell>50499</Table.Cell>
                            <Table.Cell>Received</Table.Cell>
                          </Table.Row>
                          <Table.Row style={{background: '#99CCFF'}}>
                            <Table.Cell>49207</Table.Cell>
                            <Table.Cell>Fully Coded</Table.Cell>
                          </Table.Row>
                          <Table.Row style={{background: '#FF9900'}}>
                            <Table.Cell>1292</Table.Cell>
                            <Table.Cell>Awaiting Coding</Table.Cell>
                          </Table.Row>
                          <Table.Row>
                            <Table.Cell>3.63</Table.Cell>
                            <Table.Cell>Days to Process</Table.Cell>
                          </Table.Row>
                        </Table.Body>
                      </Table>
                    </Grid.Column>
                  </Grid.Row>
                </Grid>
              </Segment>
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Fragment>
    )

  }

}

export default Dashboard