package com.example.loginapp.data

data class Message(
    val text:String,
    val type:Int
){
    constructor(): this("",0)
}
