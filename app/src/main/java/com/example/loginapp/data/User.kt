package com.example.loginapp.data

data class User(val email: String, val secondName:String, val password:String){
    constructor(): this("","", "")
}