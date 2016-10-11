
import React, { Component } from 'react';
import {
  StyleSheet,
  Text,
  View,
  TouchableHighlight
} from 'react-native';

export default class SplashPage extends Component {
  componentWillMount() {
    /*
    var navigator = this.props.navigator;
    setTimeout(() => {
      navigator.replace({
        id: 'LoginPage',
      });
    }, 1000);
    */
  }

  navDataDemoButtonClicked(){
    var navigator = this.props.navigator;
    navigator.push({id: 'NavDataTestPage'});
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          FruiTREC
        </Text>
        <Text style={styles.instructions}>
          A placeholder for an awesome app.
        </Text>
        <TouchableHighlight style={styles.button} onPress={this.navDataDemoButtonClicked.bind(this)}>
            <View>
              <Text>NavData Demo</Text>
            </View>
        </TouchableHighlight>
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
