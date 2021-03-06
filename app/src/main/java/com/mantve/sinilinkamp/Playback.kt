package com.mantve.sinilinkamp

enum class Playback (val payload: ByteArray){
    VolumeDown(byteArrayOf(0x7e,0x05,0x06,0x00, 0x89.toByte())),
    Previous(byteArrayOf(0x7e,0x05,0x07,0x00, 0x8a.toByte())),
    Pause(byteArrayOf(0x7e,0x05,0x01,0x00, 0x84.toByte())),
    Next(byteArrayOf(0x7e,0x05,0x08,0x00, 0x8b.toByte())),
    VolumeUp(byteArrayOf(0x7e,0x05,0x05,0x0, 0x88.toByte()))
}