/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {
  Component,
  Subscribable
} from 'react';

import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  DeviceEventEmitter,
  Navigator,
  TouchableOpacity
} from 'react-native';

import SplashPage from './app/components/SplashPage';
import NavDataTestPage from './app/components/NavDataTestPage';

console.log('SplashPage', SplashPage);
console.log('NavDataTestPage', NavDataTestPage);

class FruiTREC extends Component {

  render() {
    return (
      <Navigator
          renderScene={this.renderScene.bind(this)}
          initialRoute={{id: 'SplashPage', name: 'Index'}}
          configureScene={(route) => {
            if (route.sceneConfig) {
              return route.sceneConfig;
            }
            return Navigator.SceneConfigs.FloatFromRight;
          }} />
    );
  }

  renderScene(route, navigator) {
    var routeId = route.id;
    console.log('renderScene', route, navigator);
    if (routeId === 'SplashPage') {
      console.log("routeId === 'SplashPage'");
      return (
        <SplashPage
          navigator={navigator} />
      );
    } else if (routeId === 'NavDataTestPage') {
      console.log("routeId === 'NavDataTestPage'");
      return (
        <NavDataTestPage
          navigator={navigator} />
      );
    }

  }
}

AppRegistry.registerComponent('FruiTREC', () => FruiTREC);
