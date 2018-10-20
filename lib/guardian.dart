import 'dart:async';

import 'package:flutter/services.dart';

class Guardian {
  static const MethodChannel _channel =
      const MethodChannel('guardian');


  static String _encryptAES2Base64method = 'encryptAES2Base64';

  static Future<String> encryptAES2Base64(String str, String key) async {
    final String result = await _channel.invokeMethod(_encryptAES2Base64method, [str, key]);
    return result;
  }
}
