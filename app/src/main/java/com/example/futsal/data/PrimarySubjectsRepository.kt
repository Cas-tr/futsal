package com.example.futsal.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.futsal.models.PrimarySubjects
import com.example.futsal.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class PrimarySubjectsRepository(var navController: NavHostController,var context:Context) {
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


    fun savePrimarySubjects(grade : String, subject :String, time: String){
        var id = System.currentTimeMillis().toString()
        var primarySubjectsData = PrimarySubjects(grade,subject,time,id)
        var primarySubjectsRef = FirebaseDatabase.getInstance().getReference()
            .child("PrimarySubjects/$id")
        progress.show()
        primarySubjectsRef.setValue(primarySubjectsData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun viewPrimarySubjects(primarySubject:MutableState<PrimarySubjects>, primarySubjects: SnapshotStateList<PrimarySubjects>): SnapshotStateList<PrimarySubjects> {
        var ref = FirebaseDatabase.getInstance().getReference().child("PrimarySubjects")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                primarySubjects.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(PrimarySubjects::class.java)
                    primarySubject.value = value!!
                    primarySubjects.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return primarySubjects
    }

    fun deletePrimarySubjects(id:String){
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("PrimarySubjects/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "PrimarySubject deleted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updatePrimarySubjects(grade: String,subject: String,time: String,id:String){
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("PrimarySubjects/$id")
        progress.show()
        var updateData = PrimarySubjects(grade,subject,time, id)
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