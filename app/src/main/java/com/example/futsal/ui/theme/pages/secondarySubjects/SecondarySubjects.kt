package com.example.futsal.ui.theme.pages.secondarySubjects

import android.content.res.Configuration
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
import com.example.futsal.data.SecondarySubjectsRepository
import com.example.futsal.navigation.ROUTE_HOME
import com.example.futsal.ui.theme.FutsalTheme
import com.example.futsal.ui.theme.oswaldFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondarySubjectsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var context = LocalContext.current
        Text(
            text = "SECONDARY SECTION ",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textDecoration = TextDecoration.Underline,
            fontFamily = oswaldFontFamily,
            color = Color.Red
        )

        var form by remember { mutableStateOf(TextFieldValue("")) }
        var subject by remember { mutableStateOf("") }
        var isExpanded by remember { mutableStateOf(false) }
        var time by remember { mutableStateOf("") }

        TextField(
            value = form,
            onValueChange = { form = it },
            label = { Text(text = "Form") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(20.dp))
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
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
                    })
                DropdownMenuItem(
                    text = { Text(text = "Kiswahili") },
                    onClick = {
                        subject = "Kiswahili"
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Chemistry") },
                    onClick = {
                        subject = "Chemistry"
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Physics") },
                    onClick = {
                        subject = "Physics"
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Biology") },
                    onClick = {
                        subject = "Biology"
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Geography") },
                    onClick = {
                        subject = "Geography"
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "History") },
                    onClick = {
                        subject = "History"
                        isExpanded = false
                    })
                DropdownMenuItem(
                    text = { Text(text = "Cre") },
                    onClick = {
                        subject = "Cre"
                        isExpanded = false
                    })
                Spacer(modifier = Modifier.height(20.dp))
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
                    Spacer(modifier = Modifier.height(20.dp))


                }




            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            var secondarySubjectsRepository = SecondarySubjectsRepository(navController,context)
            secondarySubjectsRepository.saveSecondarySubject(form.text.trim(), subject .trim(),
                time.trim())
            navController.navigate(ROUTE_HOME)
        },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White)){
            Text(text = "Submit")

        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SecondarySubjectsScreenPreview() {
    FutsalTheme {
        SecondarySubjectsScreen(rememberNavController())
    }

}