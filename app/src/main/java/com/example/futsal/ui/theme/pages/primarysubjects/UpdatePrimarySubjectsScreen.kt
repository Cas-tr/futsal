package com.example.futsal.ui.theme.pages.primarysubjects

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futsal.data.PrimarySubjectsRepository
import com.example.futsal.models.PrimarySubjects
import com.example.futsal.ui.theme.FutsalTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePrimarySubjectsScreen(navController: NavHostController, id:String) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        var context = LocalContext.current
        var grade by remember { mutableStateOf("") }
        var subject by remember { mutableStateOf("") }
        var time by remember { mutableStateOf("") }


        var currentDataRef = FirebaseDatabase.getInstance().getReference()
            .child("PrimarySubjects/$id")
        currentDataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var primarySubject = snapshot.getValue(PrimarySubjects::class.java)
                grade = primarySubject!!.grade
                subject = primarySubject!!.subject
                time = primarySubject!!.time
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        Text(
            text = "Add primary subject",
            fontSize = 30.sp,
            fontFamily = FontFamily.Cursive,
            color = Color.Red,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline
        )

        var primarySubjectGrade by remember { mutableStateOf(TextFieldValue(grade)) }
        var primarySubjectSubject by remember { mutableStateOf(TextFieldValue(subject)) }
        var primarySubjectTime by remember { mutableStateOf(TextFieldValue(time)) }

        OutlinedTextField(
            value = primarySubjectGrade,
            onValueChange = { primarySubjectGrade = it },
            label = { Text(text = "Primary Subject Grade *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = primarySubjectSubject,
            onValueChange = { primarySubjectSubject = it },
            label = { Text(text = "Primary Subject *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = primarySubjectTime,
            onValueChange = { primarySubjectTime = it },
            label = { Text(text = "Primary Subject Time *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )


        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            //-----------WRITE THE UPDATE LOGIC HERE---------------//
            var primarySubjectsRepository = PrimarySubjectsRepository(navController, context)
            primarySubjectsRepository.updatePrimarySubjects(
                primarySubjectGrade.text.trim(), primarySubjectSubject.text.trim(),
                primarySubjectTime.text.trim(),id)


        }) {
            Text(text = "Update")
        }

    }
    }




@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun UpdateStudentScreenPreview() {
    FutsalTheme {
        com.example.futsal.ui.theme.pages.student.UpdateStudentsScreen(rememberNavController(),id = "")
    }
}