import React, {Component} from 'react'
import {Link, withRouter} from 'react-router-dom';
import {Menu} from "semantic-ui-react";
import {ROUTER_PATH} from "../utils/Constants";

/**
 * This class renders the top navigation bar for the application
 */
class SubNavBar extends Component {

  handleItemClick = (e, {id}) => {
    this.setState({activeItem: id});
  };

  render() {

    const activeItem = this.props.location.pathname;

    console.log(activeItem);

    return (
      <Menu borderless id='sub-menu'>
        <Link to={ROUTER_PATH.HOME}>
          <Menu.Item id={ROUTER_PATH.HOME} as='span'
                     active={activeItem === ROUTER_PATH.HOME}
                     onClick={this.handleItemClick}>Home</Menu.Item>
        </Link>
        <Link to={ROUTER_PATH.DATA_QUALITY}>
          <Menu.Item id={ROUTER_PATH.DATA_QUALITY} as='span'
                     active={activeItem === ROUTER_PATH.DATA_QUALITY}
                     onClick={this.handleItemClick}>Data Quality</Menu.Item>
        </Link>
        <Link to={ROUTER_PATH.CODING}>
          <Menu.Item id={ROUTER_PATH.CODING} as='span'
                     active={activeItem === ROUTER_PATH.CODING}
                     onClick={this.handleItemClick}>Coding</Menu.Item>
        </Link>
        <Link to={ROUTER_PATH.DASHBOARD}>
          <Menu.Item id={ROUTER_PATH.DASHBOARD} as='span'
                     active={activeItem === ROUTER_PATH.DASHBOARD}
                     onClick={this.handleItemClick}>Dashboard</Menu.Item>
        </Link>
      </Menu>
    );
  }

}

const SubNavigationBar = withRouter(SubNavBar);

export default SubNavigationBar