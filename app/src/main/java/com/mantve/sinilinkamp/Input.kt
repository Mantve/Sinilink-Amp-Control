package com.mantve.sinilinkamp

enum class Input (val payload: ByteArray){
    TF(byteArrayOf(0x7e,0x05,0x03,0x00, 0x86.toByte())),
    USB(byteArrayOf(0x7e,0x05,0x04,0x00, 0x87.toByte())),
    BT(byteArrayOf(0x7e,0x05,0x14,0x00, 0x97.toByte())),
    SoundCard(byteArrayOf(0x7e,0x15,0x0c,0x00, 0x98.toByte())),
    AUX(byteArrayOf(0x7e,0x05,0x16,0x0, 0x99.toByte()))
}