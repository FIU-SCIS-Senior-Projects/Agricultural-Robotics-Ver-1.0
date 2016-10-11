
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
      altitude: 0,
      windCompensationThetaAngle: 0,
      windCompensationPhiAngle: 0,
      flyState: 0,
      battery: '0%',
      gps: '0,0',
      accelero: '0,0,0',
      gyro: '0,0,0',
      magneto: '0,0,0'
    };
  }

  componentWillMount() {}

  componentDidMount(){
    var self = this;

    NativeModules.NavDataServiceModule.connect()
    .then((result) => {
      NativeModules.NavDataServiceModule.subscribeAll([
        'attitude',
        'gps',
        'altitude',
        'flyState',
        'battery',
        'gps',
        'accelero',
        'gyro',
        'magneto'
      ])
    })
    .then((result) => {
      self.setState({
        attitudeObserver: DeviceEventEmitter.addListener('attitude', self.navDataDidChange.bind(self)),
        gpsObserver: DeviceEventEmitter.addListener('gps', self.navDataDidChange.bind(self)),
        altitudeObserver: DeviceEventEmitter.addListener('altitude', self.navDataDidChange.bind(self)),
        flyStateObserver: DeviceEventEmitter.addListener('flyState', self.navDataDidChange.bind(self)),
        batteryObserver: DeviceEventEmitter.addListener('battery', self.navDataDidChange.bind(self)),
        acceleroObserver: DeviceEventEmitter.addListener('accelero', self.navDataDidChange.bind(self)),
        gyroObserver: DeviceEventEmitter.addListener('gyro', self.navDataDidChange.bind(self)),
        magnetoObserver: DeviceEventEmitter.addListener('magneto', self.navDataDidChange.bind(self))
      });
    })
  }

  componentWillUnmount(){
    this.state.attitudeObserver.remove();
    this.state.gpsObserver.remove();
    this.state.altitudeObserver.remove();
    this.state.flyStateObserver.remove();
    this.state.batteryObserver.remove();
    this.state.acceleroObserver.remove();
    this.state.gyroObserver.remove();
    this.state.magnetoObserver.remove();
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

    if(e.altitude){
      Object.assign(update, {
        altitude: e.altitude
      });
    }

    if(e.batteryLevel){
      Object.assign(update, {
        battery: e.batteryLevel + '%'
      });
    }

    if(e.flyState){
      Object.assign(update, {
        flyState: e.flyState
      });
    }

    if(e.latitude){
      Object.assign(update, {
        gps: e.latitude + ', ' + e.longitude
      })
    }

    if(e.accX){
      Object.assign(update, {
        accelero: e.accX + ', ' + e.accY + ', ' + e.accZ
      });
    }

    if(e.velX){
      Object.assign(update, {
        gyro: e.velX + ', ' + e.velY + ', ' + e.velZ
      });
    }

    if(e.magX){
      Object.assign(update, {
        magneto: e.magX + ', ' + e.magY + ', ' + e.magZ
      });
    }

    if(e.windCompensationThetaAngle){
      Object.assign(update, {
        windCompensationThetaAngle: e.windCompensationThetaAngle,
        windCompensationPhiAngle: e.windCompensationPhiAngle
      });
    }



    this.setState(update);
  }

  render() {
    let pitch = this.state.pitch,
        yaw = this.state.yaw,
        roll = this.state.roll,
        altitude = this.state.altitude,
        flyState = this.state.flyState,
        windCompensationThetaAngle = this.state.windCompensationThetaAngle,
        windCompensationPhiAngle = this.state.windCompensationPhiAngle,
        latitude = this.state.latitude,
        longitude = this.state.longitude,
        battery = this.state.battery,
        accelero = this.state.accelero,
        gyro = this.state.gyro,
        magneto = this.state.magneto,
        gps = this.state.gps;

    return (
      <View style={styles.container}>
        <Text style={styles.instructions}>pitch: {pitch}</Text>
        <Text style={styles.instructions}>yaw: {yaw}</Text>
        <Text style={styles.instructions}>roll: {roll}</Text>
        <Text style={styles.instructions}>altitude: {altitude}</Text>
        <Text style={styles.instructions}>flyState: {flyState}</Text>
        <Text style={styles.instructions}>gps: {gps}</Text>
        <Text style={styles.instructions}>battery: {battery}</Text>
        <Text style={styles.instructions}>accelero: {accelero}</Text>
        <Text style={styles.instructions}>gyro: {gyro}</Text>
        <Text style={styles.instructions}>magneto: {magneto}</Text>
        <Text style={styles.instructions}>windCompensationThetaAngle: {windCompensationThetaAngle}</Text>
        <Text style={styles.instructions}>windCompensationPhiAngle: {windCompensationPhiAngle}</Text>
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
