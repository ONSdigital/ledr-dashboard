import React, {Component} from 'react'
import {withRouter} from 'react-router-dom';
import {Menu} from "semantic-ui-react";
import {ROUTER_PATH} from "../utils/Constants";

/**
 * This class renders the top navigation bar for the application
 */
class NavBar extends Component {

  handleItemClick = (e, {id}) => {
    this.setState({activeItem: id});
  };

  render() {

    const activeItem = this.props.location.pathname;

    return (
      <Menu inverted borderless color='blue' id='main-menu'>

        <Menu.Item className='nav-bar-desktop-item' id={ROUTER_PATH.CODING} as="span"
                   onClick={this.handleItemClick}>LEDR Monitor Progress</Menu.Item>

        <Menu.Menu position="right">
          <a href={ROUTER_PATH.LOGOUT}>
            <Menu.Item className='nav-bar-desktop-item' id={ROUTER_PATH.LOGOUT} as="span"
                       active={activeItem === ROUTER_PATH.LOGOUT} onClick={this.handleItemClick}
                       link>Log Out</Menu.Item>
          </a>
        </Menu.Menu>
      </Menu>
    );
  }

}

const NavigationBar = withRouter(NavBar);

export default NavigationBar