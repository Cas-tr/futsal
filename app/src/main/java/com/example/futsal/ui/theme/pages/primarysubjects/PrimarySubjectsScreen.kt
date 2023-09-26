package com.example.futsal.ui.theme.pages.primarysubjects

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.example.futsal.data.PrimarySubjectsRepository
import com.example.futsal.navigation.ROUTE_HOME
import com.example.futsal.ui.theme.FutsalTheme
import com.example.futsal.ui.theme.oswaldFontFamily




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimarySubjectsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
            .padding(0.dp, 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var context = LocalContext.current
        Text(
            text = "PRIMARY SECTION ",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textDecoration = TextDecoration.Underline,
            fontFamily = oswaldFontFamily,
            color = Color.Red

        )
        var grade by remember { mutableStateOf(TextFieldValue("")) }
        var subject by remember { mutableStateOf("") }
        var isExpanded by remember { mutableStateOf(false) }
        var time by remember { mutableStateOf("") }

        OutlinedTextField(
            value = grade,
            onValueChange = { grade = it },
            label = { Text(text = "Grade ") },
            modifier = Modifier.border(
                width = 5.dp,
                brush = Brush.horizontalGradient(listOf(Color.Red)),
                shape = RectangleShape
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))

        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }
        ) {
            TextField(
                value = subject,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
                label = { Text(text = "Subject") }
            )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Mathematics") },
                    onClick = {
                        subject = "Mathematics"
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "English") },
                    onClick = {
                        subject = "English"
                        isExpanded = false

                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Kiswahili") },
                    onClick = {
                        subject = "Kiswahili"
                        isExpanded = false

                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Science") },
                    onClick = {
                        subject = "Science"
                        isExpanded = false

                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Social Studies") },
                    onClick = {
                        subject = "Social Studies"
                        isExpanded = false

                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "CRE") },
                    onClick = {
                        subject = "CRE"
                        isExpanded = false

                    }
                )
            }
            TextField(
                value = time,
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                label = { Text(text = "Time") },
                modifier = Modifier.menuAnchor(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Morning") },
                    onClick = {
                        time = "Morning"
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Evening") },
                    onClick = {
                        time = "Evening"
                        isExpanded = false
                    })
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Button(
            onClick = {
                var primarySubjectsRepository =
                    PrimarySubjectsRepository(navController, context)
                primarySubjectsRepository.savePrimarySubjects(
                    grade.text.trim(), subject.trim(),
                    time.trim()
                )
                navController.navigate(ROUTE_HOME)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(text = "Submit")
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PrimarySubjectsScreenPreview() {
    FutsalTheme {
        PrimarySubjectsScreen(rememberNavController())

    }



}

