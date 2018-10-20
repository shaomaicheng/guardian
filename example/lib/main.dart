import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:guardian/guardian.dart';

void main() => runApp(new MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => new _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _encryptAES2Base64Result = '';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String encryptAES2Base64Result;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      encryptAES2Base64Result = await Guardian.encryptAES2Base64('str1', 'key1');
    } on PlatformException {
      encryptAES2Base64Result = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _encryptAES2Base64Result = encryptAES2Base64Result;
    });
  }

  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      home: new Scaffold(
        appBar: new AppBar(
          title: const Text('Plugin example app'),
        ),
        body: new Center(
          child: new Text('encryptAES2Base64Result: $_encryptAES2Base64Result\n'),
        ),
      ),
    );
  }
}
