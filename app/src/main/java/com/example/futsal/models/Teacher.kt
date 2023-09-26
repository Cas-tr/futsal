package com.example.futsal.models

class Teacher {
    var name:String = ""
    var email:String = ""
    var phoneNumber :String = ""
    var levelOfEducation = ""
    var school = ""
    var userId:String = ""

    constructor(name: String, email: String, phoneNumber: String, levelOfEducation: String,school:String, userId: String
    ) {
        this.name = name
        this.email = email
        this.phoneNumber = phoneNumber
        this.levelOfEducation = levelOfEducation
        this.school = school
        this.userId = userId
    }
    constructor()
}