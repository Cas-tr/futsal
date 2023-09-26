package com.example.futsal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.futsal.ui.theme.pages.primarysubjects.PrimarySubjectsScreen
import com.example.futsal.ui.theme.pages.secondarySubjects.SecondarySubjectsScreen
import com.example.futsal.ui.theme.pages.home.HomeScreen
import com.example.futsal.ui.theme.pages.student.StudentsScreen
import com.example.futsal.ui.theme.pages.student.UpdateStudentsScreen
import com.example.futsal.ui.theme.pages.student.ViewStudentsScreen
import com.example.futsal.ui.theme.pages.teacher.TeachersScreen
import com.example.futsal.ui.theme.pages.teacher.UpdateTeacherScreen
import com.example.futsal.ui.theme.pages.teacher.ViewTeachersScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_HOME) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }
        composable(ROUTE_TEACHER) {
            TeachersScreen(navController)

        }
        composable(ROUTE_VIEW_TEACHERS){
            ViewTeachersScreen(navController)
        }
        composable(ROUTE_VIEW_STUDENTS){
            ViewStudentsScreen(navController)
        }
        composable(ROUTE_STUDENT){
            StudentsScreen(navController)
        }
        composable(ROUTE_PRIMARY_SUBJECTS){
            PrimarySubjectsScreen(navController)
        }
        composable(ROUTE_SECONDARY_SUBJECTS){
            SecondarySubjectsScreen(navController)
        }
        composable(ROUTE_LOGIN){

        }
        composable(ROUTE_SIGNUP){

        }
        composable(ROUTE_UPDATE_STUDENT+"/{id}"){passedData->
            UpdateStudentsScreen(navController,passedData.arguments?.getString("id")!!)


        }
        composable(ROUTE_UPDATE_TEACHERS+"/{id}"){passedData->
            UpdateTeacherScreen(navController,passedData.arguments?.getString("id")!!)

        }
        composable(ROUTE_UPDATE_TEACHERS+"/{id}"){passedData->
            UpdateTeacherScreen(navController,passedData.arguments?.getString("id")!!)

        }


    }
}