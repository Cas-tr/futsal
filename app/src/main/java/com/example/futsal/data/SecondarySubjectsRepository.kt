package com.example.futsal.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.futsal.models.SecondarySubjects
import com.example.futsal.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SecondarySubjectsRepository(var navController: NavHostController, var context: Context) {
    var authRepository:AuthRepository
    var progress:ProgressDialog

    init {
        authRepository = AuthRepository(navController,context)
        if (!authRepository.isLoggedIn()){
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun saveSecondarySubject(grade:String,subjects:String,time:String){
        var id = System.currentTimeMillis().toString()
        var secondarySubjectsData = SecondarySubjects(grade,subjects,time,id)
        var secondarySubjectsRef = FirebaseDatabase.getInstance().getReference()
            .child("SecondarySubjects/$id")
        progress.show()
        secondarySubjectsRef.setValue(secondarySubjectsData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun viewSecondarySubjects(secondarysubject: MutableState<SecondarySubjects>, secondarySubjects: SnapshotStateList<SecondarySubjects>): SnapshotStateList<SecondarySubjects> {
        var ref = FirebaseDatabase.getInstance().getReference().child("SecondarySubjects")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                secondarySubjects.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(SecondarySubjects::class.java)
                    secondarysubject.value = value!!
                    secondarySubjects.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return secondarySubjects
    }

    fun deleteSecondarySubject(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("SecondarySubjects/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "SecondarySubjects deleted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateSecondarySubject(grade: String,subjects: String,time: String,id:String){
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("SecondarySubjects/$id")
        progress.show()
        var updateData = SecondarySubjects(grade,subjects,time, id)
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