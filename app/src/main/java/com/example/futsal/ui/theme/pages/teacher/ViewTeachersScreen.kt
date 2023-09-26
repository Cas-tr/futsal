package com.example.futsal.ui.theme.pages.teacher

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futsal.data.TeacherRepository
import com.example.futsal.models.Teacher
import com.example.futsal.navigation.ROUTE_UPDATE_TEACHERS
import com.example.futsal.ui.theme.Castro
import com.example.futsal.ui.theme.FutsalTheme

@Composable
fun ViewTeachersScreen(navController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Castro),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var teacherRepository = TeacherRepository(navController, context)


        val emptyTeacherState = remember { mutableStateOf(Teacher("","","","","","","")) }
        var emptyTeacherListState = remember { mutableStateListOf<Teacher>() }

        var teachers = teacherRepository.viewTeacher(emptyTeacherState, emptyTeacherListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All TEACHERS",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(teachers){
                    TeachersItem(
                        name = it.name,
                        email = it.email,
                        phoneNumber = it.phoneNumber,
                        levelOfEducation = it.levelOfEducation,
                        school = it.school,
                        subject = it.subject,
                        id = it.userId,
                        navController = navController,
                        teacherRepository = teacherRepository
                    )
                }
            }
        }
    }
}


@Composable
fun TeachersItem(name:String, email:String, phoneNumber:String, levelOfEducation:String, school:String,subject:String,id:String,
                navController: NavHostController, teacherRepository: TeacherRepository
) {
    var context = LocalContext.current

    Card(modifier = Modifier.fillMaxWidth()) {
        Text(text = name)
        Text(text = email)
        Text(text = phoneNumber)
        Text(text = levelOfEducation)
        Text(text = school)
        Text(text = subject)
        Button(onClick = {
            teacherRepository.deleteTeacher(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE_TEACHERS +"/$id")
        }) {
            Text(text = "Update")
        }
        Button(onClick = {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    Activity(),
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                context.startActivity(intent)
            }

        },colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White) ){
            Text(text = "Contact")
        }

            
        }
    }


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ViewTeachersPreviewScreen(){
    FutsalTheme {
        ViewTeachersScreen(rememberNavController())
    }
}
