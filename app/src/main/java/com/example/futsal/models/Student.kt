package com.example.futsal.models

class Student {
    var name:String = ""
    var email:String = ""
    var phoneNumber :String = ""
    var levelOfEducation = ""
    var userId:String = ""

    constructor(name: String, email: String, phoneNumber: String, levelOfEducation: String, userId: String) {
        this.name = name
        this.email = email
        this.phoneNumber = phoneNumber
        this.levelOfEducation = levelOfEducation
        this.userId = userId
    }
    constructor()
}

