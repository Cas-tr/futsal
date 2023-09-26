package com.example.futsal.ui.theme.pages.primarysubjects

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futsal.data.PrimarySubjectsRepository
import com.example.futsal.models.PrimarySubjects
import com.example.futsal.navigation.ROUTE_UPDATE_PRIMARY_SUBJECTS
import com.example.futsal.ui.theme.FutsalTheme


@Composable
fun ViewPrimarySubjectsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        var context = LocalContext.current
        var primarySubjectsRepository = PrimarySubjectsRepository(navController, context)


        val emptyPrimarySubjectsState = remember { mutableStateOf(PrimarySubjects("","","","")) }
        var emptyPrimarySubjectsListState = remember { mutableStateListOf<PrimarySubjects>() }

        var primarySubjects = primarySubjectsRepository.viewPrimarySubjects(emptyPrimarySubjectsState, emptyPrimarySubjectsListState)


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
                items(primarySubjects){
                    PrimarySubjectsItem(
                        grade = it.grade,
                        subject = it.subject,
                        time = it.time,
                        id = it.id,
                        navController = navController,
                        primarySubjectsRepository = primarySubjectsRepository
                    )
                }
            }
        }
    }
}


@Composable
fun PrimarySubjectsItem(grade:String,subject:String,time:String, id:String,
                navController: NavHostController, primarySubjectsRepository: PrimarySubjectsRepository
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = grade)
        Text(text = subject)
        Text(text = time)
        Button(onClick = {
            primarySubjectsRepository.deletePrimarySubjects(id)
        }) {
            Text(text = "Delete")
        }
        Button(onClick = {
            navController.navigate(ROUTE_UPDATE_PRIMARY_SUBJECTS +"/$id")
        }) {
            Text(text = "Update")
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)

@Composable
fun ViewPrimarySubjectsScreenPreview(){
FutsalTheme {
ViewPrimarySubjectsScreen(rememberNavController())

}
}