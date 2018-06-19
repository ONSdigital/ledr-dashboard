import React, {Component} from 'react'
import {Link, withRouter} from 'react-router-dom';
import {Menu} from "semantic-ui-react";
import {ROUTER_PATH} from "../utils/Constants";

class NavBar extends Component {

  handleItemClick = (e, {id}) => {
    this.setState({activeItem: id});
  };

  render() {

    const activeItem = this.props.location.pathname;

    return (
      <Menu inverted borderless color='blue' id='main-menu'>

        <Link to={ROUTER_PATH.DASHBOARD}>
          <Menu.Item className='nav-bar-desktop-item' id={ROUTER_PATH.DASHBOARD} as="span"
                     active={activeItem === ROUTER_PATH.DASHBOARD} onClick={this.handleItemClick}
                     link>LEDR Dashboard</Menu.Item>
        </Link>

        <Menu.Menu position="right">
          <Link to={ROUTER_PATH.LOGOUT}>
            <Menu.Item className='nav-bar-desktop-item' id={ROUTER_PATH.LOGOUT} as="span"
                       active={activeItem === ROUTER_PATH.LOGOUT} onClick={this.handleItemClick}
                       link>Log Out</Menu.Item>
          </Link>
        </Menu.Menu>
      </Menu>
    );
  }

}

const NavigationBar = withRouter(NavBar);

export default NavigationBar