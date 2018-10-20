package clei.guardian

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar


val ENCRYPTAES256BASE64 = "encryptAES2Base64"

class GuardianPlugin(): MethodCallHandler {

  init {
    MethodManager.register(ENCRYPTAES256BASE64, ENCRYPTAES256BASE64Handler())
  }

  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "guardian")
      channel.setMethodCallHandler(GuardianPlugin())
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    MethodManager.dispatch(call.method, result, call.arguments)
  }
}


object MethodManager {
  private val map = HashMap<String, Methodhandler>()

  fun register(name:String, handler: Methodhandler) {
    map[name] = handler
  }

  fun dispatch(name:String, result:Result, argument: Any) {
    if (map.contains(name)) {
      map[name]?.handle(result, argument)
    }
  }
}
