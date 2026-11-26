package com.example.test6

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Question(val text: String, val answer: Boolean)

val questions = listOf(
    Question("Canberra is the capital of Australia.", true),
    Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
    Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
    Question("The source of the Nile River is in Egypt.", false),
    Question("The Amazon River is the longest river in the Americas.", true),
    Question("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
)

@Composable
fun GeoQuizScreen() {
    var currentIndex by remember { mutableStateOf(0) }
    var correctCount by remember { mutableStateOf(0) }
    var answered by remember { mutableStateOf(false) }

    val question = questions[currentIndex]
    val isLast = currentIndex == questions.lastIndex

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Вопрос ${currentIndex + 1} из ${questions.size}",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            question.text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (!answered) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = {
                        if (question.answer) correctCount++
                        answered = true
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("True")
                }
                Button(
                    onClick = {
                        if (!question.answer) correctCount++
                        answered = true
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("False")
                }
            }
        }

        if (!(answered && isLast)) {
            Button(
                onClick = {
                    currentIndex++
                    answered = false
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = answered
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MaterialTheme { GeoQuizScreen() }
}