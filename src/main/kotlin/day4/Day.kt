package day4

import Solution
import java.security.MessageDigest

class Day4 : Solution {
    fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }

    override fun partOne(data: String): String {
        val md = MessageDigest.getInstance("MD5")
        val x = md.digest(data.toByteArray()).toHex()
        if (x.take(5).contentEquals("00000"))
            return "0"
        var counter = 1

        while (true){
            val hash = md.digest((data + "$counter").toByteArray()).toHex()
            if (hash.take(5).contentEquals("00000"))
                return counter.toString()

            counter += 1
        }
    }


    override fun partTwo(data: String): String {
        val md = MessageDigest.getInstance("MD5")
        val x = md.digest(data.toByteArray()).toHex()
        if (x.take(6).contentEquals("000000"))
            return "0"
        var counter = 1

        while (true){
            val hash = md.digest((data + "$counter").toByteArray()).toHex()
            if (hash.take(6).contentEquals("000000"))
                return counter.toString()

            counter += 1
        }
    }

}