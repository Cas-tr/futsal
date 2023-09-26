package com.example.futsal.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.futsal.models.SecondarySubjects
import com.example.futsal.models.Student
import com.example.futsal.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


    class StudentRepository(var navController: NavHostController, var context: Context) {
        var authRepository:AuthRepository
        var progress: ProgressDialog

        init {
            authRepository = AuthRepository(navController,context)
            if (!authRepository.isLoggedIn()){
                navController.navigate(ROUTE_LOGIN)
            }
            progress = ProgressDialog(context)
            progress.setTitle("Loading")
            progress.setMessage("Please wait...")
        }


        fun saveStudent(name:String,email:String,phoneNumber :String,levelOfEducation:String){
            var id = System.currentTimeMillis().toString()
            var studentData = Student(name,email,phoneNumber,levelOfEducation,id)
            var studentRef = FirebaseDatabase.getInstance().getReference()
                .child("Student/$id")
            progress.show()
            studentRef.setValue(studentData).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun viewStudent(student: MutableState<Student>, students: SnapshotStateList<Student>): SnapshotStateList<Student> {
            var ref = FirebaseDatabase.getInstance().getReference().child("Student")

            progress.show()
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    progress.dismiss()
                    students.clear()
                    for (snap in snapshot.children){
                        val value = snap.getValue(Student::class.java)
                        student.value = value!!
                        students.add(value)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
            return students
        }

        fun deleteStudent(id:String){
            var delRef = FirebaseDatabase.getInstance().getReference()
                .child("Students/$id")
            progress.show()
            delRef.removeValue().addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(context, "Students deleted", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun updateStudent(name: String,email: String,phoneNumber: String,levelOfEducation: String,id:String){
            var updateRef = FirebaseDatabase.getInstance().getReference()
                .child("Students/$id")
            progress.show()
            var updateData = SecondarySubjects(name,email,levelOfEducation, id)
            updateRef.setValue(updateData).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
