import React from 'react';
import {Label, List} from "semantic-ui-react";

/**
 *
 * @param header - header text
 * @param description - description text
 * @param label - label text
 * @param onclick - methods to call in onclick
 * @returns {*}
 * @constructor
 */
const DataColumnItemGeneric = ({header, description, label, onclick}) => {
  return (
      onclick ?
      <List.Item as='a' onClick={onclick} className='data-column-item-generic'>
        <List.Content>
          <List.Header>{header}</List.Header>
          <List.Description>
            {description}
            {label && <Label className='list-label' color='grey' circular>{label}%</Label>}
          </List.Description>
        </List.Content>
      </List.Item>
      :
      <List.Item className='data-column-item-generic'>
        <List.Content>
          <List.Header>{header}</List.Header>
          <List.Description>
            {description}
            {label && <Label className='list-label' color='grey' circular>{label}%</Label>}
          </List.Description>
        </List.Content>
      </List.Item>
  )
};

export default DataColumnItemGeneric;