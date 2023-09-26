package com.example.futsal.ui.theme.pages.secondarySubjects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.futsal.data.SecondarySubjectsRepository
import com.example.futsal.models.SecondarySubjects
import com.example.futsal.navigation.ROUTE_UPDATE_SECONDARY_SUBJECTS


@Composable
fun ViewSecondarySubjectsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var secondarySubjectsRepository = SecondarySubjectsRepository(navController, context)

        val emptySecondarySubjectState = remember{ mutableStateOf(SecondarySubjects("","","","")) }
        var emptySecondarySubjectStateListState = remember { mutableStateListOf<SecondarySubjects>() }

        var secondarySubjects = secondarySubjectsRepository .viewSecondarySubjects(emptySecondarySubjectState, emptySecondarySubjectStateListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "All STUDENTS",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(secondarySubjects){
                    SecondarySubjectsItem(
                        grade = it.grade,
                        subject = it.subject,
                        time = it.time ,
                        id = it.id,
                        navController = navController,
                        secondarySubjectsRepository = secondarySubjectsRepository
                    )


                }
            }
        }
    }
}


@Composable
fun SecondarySubjectsItem(grade:String, subject:String,time:String, id:String,
                navController: NavHostController, secondarySubjectsRepository: SecondarySubjectsRepository
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = grade)
        Text(text = subject)
        Text(text = time)
        Button(onClick = {
            secondarySubjectsRepository.deleteSecondarySubject(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE_SECONDARY_SUBJECTS +"/$id")
        }) {
            Text(text = "Update")
        }
    }
}