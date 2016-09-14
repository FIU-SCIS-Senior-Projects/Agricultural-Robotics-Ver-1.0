/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
*/

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';
import PolylineCreatorMap from './app/components/PolylineCreatorMap.js';

class AndroidPlaygroundProject extends Component {
  render() {
    const { region } = this.props;
    console.log(region);

    return (
      <PolylineCreatorMap></PolylineCreatorMap>
    );
  }
}

const styles = StyleSheet.create({
});

AppRegistry.registerComponent('AndroidPlaygroundProject', () => AndroidPlaygroundProject);
