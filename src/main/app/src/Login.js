import React, {Component} from 'react'
import {Button, Form, Grid, Header, Segment} from "semantic-ui-react";

class Login extends Component {

  render() {
    return (
      <Grid textAlign='center' verticalAlign='middle'>
        <Grid.Row>
          <Grid.Column width={5}/>
          <Grid.Column width={6}>
            <Header as='h2' textAlign='center'>
              Log in to LEDR Dashboard
            </Header>
            <Form size='large'>
              <Segment>
                <Form.Input fluid icon='user' iconPosition='left' placeholder='E-mail address'/>
                <Form.Input fluid icon='lock' iconPosition='left' placeholder='Password' type='password'/>
                <Button color='blue' fluid size='large'>
                  Login
                </Button>
              </Segment>
            </Form>
          </Grid.Column>
          <Grid.Column width={5}/>
        </Grid.Row>
      </Grid>
    )
  }

}

export default Login;