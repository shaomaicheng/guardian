package clei.guardian

import android.util.Base64
import android.util.Log
import io.flutter.plugin.common.MethodChannel
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 *  create by chenglei at 2018/10/20
 */


interface Methodhandler {
    fun handle(result: MethodChannel.Result, argument: Any) {
        result.notImplemented()
    }
}


class ENCRYPTAES256BASE64Handler : Methodhandler {
    override fun handle(result: MethodChannel.Result, argument: Any) {
        argument as ArrayList<*>
        val str = argument[0] as String
        val key = argument[1] as String
        Log.e(javaClass.simpleName, "str: $str; key:$key")

        val encryptResult =  base64Encode(
                encryptAES(str.toByteArray(), key.toByteArray())
        )
        encryptResult?.let {
            result.success(it)
        }
    }
}

fun base64Encode(data:ByteArray?): String? {
    return if (data == null) {
        null
    } else {
        Base64.encodeToString(data, Base64.DEFAULT)
    }
}

fun encryptAES(str: ByteArray?, key: ByteArray?): ByteArray? {
    if (str == null || key == null) return null

    try {
        val keySpec = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)
        return cipher.doFinal(str)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}