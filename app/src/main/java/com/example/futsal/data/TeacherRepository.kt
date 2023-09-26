package com.example.futsal.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.futsal.models.Teacher
import com.example.futsal.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


    class TeacherRepository(var navController: NavHostController, var context: Context) {
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


        fun saveTeacher(name:String,email:String,phoneNumber :String,levelOfEducation:String,school:String){
            var id = System.currentTimeMillis().toString()
            var teacherData = Teacher(name,email,phoneNumber,levelOfEducation,school,id)
            var teacherRef = FirebaseDatabase.getInstance().getReference()
                .child("Teacher/$id")
            progress.show()
            teacherRef.setValue(teacherData).addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun viewTeacher(teacher: MutableState<Teacher>, teachers: SnapshotStateList<Teacher>): SnapshotStateList<Teacher> {
            var ref = FirebaseDatabase.getInstance().getReference().child("Teacher")

            progress.show()
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    progress.dismiss()
                    teachers.clear()
                    for (snap in snapshot.children){
                        val value = snap.getValue(Teacher::class.java)
                        teacher.value = value!!
                        teachers.add(value)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
            return teachers
        }

        fun deleteTeacher(id:String){
            var delRef = FirebaseDatabase.getInstance().getReference()
                .child("Teacher/$id")
            progress.show()
            delRef.removeValue().addOnCompleteListener {
                progress.dismiss()
                if (it.isSuccessful){
                    Toast.makeText(context, "Teacher deleted", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun updateTeacher(name: String,email: String,phoneNumber: String,levelOfEducation: String,school: String,id:String){
            var updateRef = FirebaseDatabase.getInstance().getReference()
                .child("Teacher/$id")
            progress.show()
            var updateData = Teacher(name,email,phoneNumber,levelOfEducation,school, id)
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

