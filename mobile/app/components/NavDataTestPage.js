
import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
  DeviceEventEmitter
} from 'react-native';

import { NativeModules } from 'react-native';

export default class NavDataTestPage extends Component {

  constructor(props){
    super(props);
    this.state = {
      pitch: 0,
      yaw: 0,
      roll: 0,
      windCompensationThetaAngle: 0,
      windCompensationPhiAngle: 0,
      latitude: 0,
      longitude: 0
    };
  }

  componentWillMount() {}

  componentDidMount(){
    var self = this;

    NativeModules.NavDataServiceModule.connect()
    .then((result) => {
      NativeModules.NavDataServiceModule.subscribeAll([
        'attitude',
        'gps'
      ])
    })
    .then((result) => {
      self.setState({
        attitudeObserver: DeviceEventEmitter.addListener('attitude', self.navDataDidChange.bind(self)),
        gpsObserver: DeviceEventEmitter.addListener('gps', self.navDataDidChange.bind(self))
      });
    })
  }

  componentWillUnmount(){
    this.state.attitudeObserver.remove();
    this.state.gpsObserver.remove();
    NativeModules.NavDataServiceModule.disconnect();
  }

  navDataDidChange(e){
    console.log(e);
    var update = {};

    if(e.pitch){
      Object.assign(update, {
        pitch: e.pitch,
        yaw: e.yaw,
        roll: e.roll
      });
    }

    if(e.windCompensationThetaAngle){
      Object.assign(update, {
        windCompensationThetaAngle: e.windCompensationThetaAngle,
        windCompensationPhiAngle: e.windCompensationPhiAngle
      });
    }

    if(e.latitude){
      Object.assign(update, {
        latitude: e.latitude,
        longitude: e.longitude
      })
    }

    this.setState(update);
  }

  render() {
    let pitch = this.state.pitch,
        yaw = this.state.yaw,
        roll = this.state.roll,
        windCompensationThetaAngle = this.state.windCompensationThetaAngle,
        windCompensationPhiAngle = this.state.windCompensationPhiAngle,
        latitude = this.state.latitude,
        longitude = this.state.longitude;

    return (
      <View style={styles.container}>
        <Text style={styles.instructions}>pitch: {pitch}</Text>
        <Text style={styles.instructions}>yaw: {yaw}</Text>
        <Text style={styles.instructions}>roll: {roll}</Text>
        <Text style={styles.instructions}>windCompensationThetaAngle: {windCompensationThetaAngle}</Text>
        <Text style={styles.instructions}>windCompensationPhiAngle: {windCompensationPhiAngle}</Text>
        <Text style={styles.instructions}>latitude: {latitude}</Text>
        <Text style={styles.instructions}>longitude: {longitude}</Text>
      </View>
    );
  }

}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
