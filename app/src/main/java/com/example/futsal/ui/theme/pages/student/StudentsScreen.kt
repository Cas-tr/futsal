package com.example.futsal.ui.theme.pages.student

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.futsal.data.StudentRepository
import com.example.futsal.navigation.ROUTE_HOME
import com.example.futsal.navigation.ROUTE_PRIMARY_SUBJECTS
import com.example.futsal.navigation.ROUTE_SECONDARY_SUBJECTS
import com.example.futsal.ui.theme.FutsalTheme
import com.example.futsal.ui.theme.oswaldFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var context = LocalContext.current
        Text(
            text = "STUDENT",
            fontSize = 30.sp,
            fontFamily = oswaldFontFamily,
            color = Color.Red,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.padding(20.dp),
            fontWeight = FontWeight.Bold
        )
        var name by remember { mutableStateOf(TextFieldValue("")) }
        var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
        var email by remember { mutableStateOf(TextFieldValue("")) }
        var isExpanded by remember { mutableStateOf(false) }
        var levelOfEducation by remember { mutableStateOf("") }




        TextField(
            value = name,
            onValueChange = { name = it },
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null)},
            label = { Text(text = "Name *") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value =phoneNumber ,
            onValueChange ={phoneNumber = it},
            leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null)},
            label = { Text(text = "Phone Number* ")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.background(Color.White))
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value =email ,
            onValueChange ={email= it},
            label = { Text(text = "Email*")},
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = null)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.background(Color.White))

        Spacer(modifier = Modifier.height(20.dp))
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}
        ) {
            TextField(
                value = levelOfEducation,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                label = { Text(text = "Level of Education")}
            )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false}
            ){
                DropdownMenuItem(
                    text = { Text(text = "Primary") },
                    onClick = {
                        levelOfEducation = "Primary"
//                      isExpanded = false
//                      navController.navigate(ROUTE_PRIMARY_SUBJECTS)
                    })
                DropdownMenuItem(
                    text = { Text(text = "Secondary") },
                    onClick = {
                        levelOfEducation = "Secondary"
//                      isExpanded = false
//                      navController.navigate(ROUTE_SECONDARY_SUBJECTS)
                    }
                )}}
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {
            navController.navigate(ROUTE_HOME)
            var studentRepository = StudentRepository(navController,context)
            studentRepository.saveStudent(name.text.trim(), email .text.trim(),
                phoneNumber .text.trim(),levelOfEducation.trim())}) {
            Text(text = "Submit")

        }
    }
}



@Preview(showBackground = true, showSystemUi=true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun StudentsScreenPreview() {
    FutsalTheme {
        StudentsScreen(rememberNavController())
    }
}



    
