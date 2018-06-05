import React, {Component} from 'react'
import {Link, withRouter} from 'react-router-dom';
import {Menu} from "semantic-ui-react";
import {ROUTER_PATH} from "../utils/Constants";

const NavBarDesktop = ({leftItems, rightItems, activeItem, onClickEvent}) => (

  <Menu inverted borderless color='blue' id='main-menu'>

    <Link key={ROUTER_PATH.DASHBOARD}
          to={ROUTER_PATH.DASHBOARD}>
      <Menu.Item className='nav-bar-desktop-item'
                 id={ROUTER_PATH.DASHBOARD} as="span"
                 active={activeItem === ROUTER_PATH.DASHBOARD}
                 onClick={onClickEvent} link>LEDR Dashboard</Menu.Item>
    </Link>

    <Menu.Menu position="right" key={ROUTER_PATH.LOGIN}>
      <Link to={ROUTER_PATH.LOGIN}>
        <Menu.Item className='nav-bar-desktop-item'
                   id={ROUTER_PATH.LOGIN} as="span"
                   active={activeItem === ROUTER_PATH.LOGIN}
                   onClick={onClickEvent}
                   link>Login</Menu.Item>
      </Link>
      <Link to={ROUTER_PATH.LOGOUT}>
        <Menu.Item className='nav-bar-desktop-item'
                   id={ROUTER_PATH.LOGOUT} as="span"
                   active={activeItem === ROUTER_PATH.LOGOUT}
                   onClick={onClickEvent}
                   link>Log Out</Menu.Item>
      </Link>
    </Menu.Menu>
  </Menu>
);

class NavBar extends Component {

  handleItemClick = (e, {id}) => {
    this.setState({activeItem: id});
  };

  render() {

    const activeItem = this.props.location.pathname;

    return (
      <NavBarDesktop activeItem={activeItem} onClickEvent={this.handleItemClick}/>
    );
  }

}

const LEDRHeader = () => (
  <NavBarWithRouter/>
);

const NavBarWithRouter = withRouter(NavBar);

export default LEDRHeader