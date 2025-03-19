package com.package.todoappkotlin.uiall.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.package.todoappkotlin.data.local.Todo
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TodoItem(
    todo: Todo,
    onDelete: (Todo) -> Unit,
    onUpdate: (Todo) -> Unit
) {
    val gradientColors = listOf(
        Color.White,
        Color(0xFFFFA000) // Koyu sarı tonu
    )

    val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
    val formattedDate = sdf.format(Date(todo.timestamp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(brush = Brush.linearGradient(gradientColors), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(text = todo.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = todo.description, style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { onDelete(todo) }) {
                    Text("Delete")
                }
                Button(onClick = { onUpdate(todo) }) {
                    Text("Update")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
