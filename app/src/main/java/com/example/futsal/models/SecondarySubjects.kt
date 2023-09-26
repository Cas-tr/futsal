package com.example.futsal.models

class SecondarySubjects {
    var grade :String= ""
    var subject :String = ""
    var time :String = ""
    var id :String = ""

    constructor(grade: String, subject: String, time: String, id: String) {
        this.grade = grade
        this.subject = subject
        this.time = time
        this.id = id
    }
    constructor()
}