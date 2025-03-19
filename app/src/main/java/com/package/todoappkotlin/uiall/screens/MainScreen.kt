package com.package.todoappkotlin.uiall.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.package.todoappkotlin.data.local.Todo
import com.package.todoappkotlin.uiall.viewmodels.TodoViewModel
import com.package.todoappkotlin.Util.NotificationHelper
import androidx.compose.foundation.layout.systemBarsPadding
import com.package.todoappkotlin.R

@Composable
fun MainScreen(viewModel: TodoViewModel, notificationHelper: NotificationHelper) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var todoToUpdate by remember { mutableStateOf<Todo?>(null) }

    // Ana ekran arka planı: çok açık pembe
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(), // Durum çubuğu ve navigasyon alanlarını otomatik padding ekler
        color = Color(0xFFFFF1F5)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Üst kısım: Uygulama logosu için alan
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Uygulama Logosu",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Yeni todo ekleme kısmı
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Contents") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        if (title.isNotBlank() && description.isNotBlank()) {
                            viewModel.addTodo(
                                todo = Todo(title = title, description = description),
                                notificationHelper = notificationHelper
                            )
                            title = ""
                            description = ""
                        }
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Add Note")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider()

            // Todo listesini göster
            val todoList by viewModel.todos.collectAsState()
            LazyColumn {
                items(todoList) { todo ->
                    TodoItem(
                        todo = todo,
                        onDelete = { viewModel.deleteTodo(it, notificationHelper) },
                        onUpdate = { todoToUpdate = it }
                    )
                }
            }
        }
    }

    // Güncelleme dialog'unu göster
    todoToUpdate?.let { todo ->
        UpdateTodoDialog(
            todo = todo,
            onDismiss = { todoToUpdate = null },
            onUpdate = { updatedTodo ->
                viewModel.updateTodo(updatedTodo, notificationHelper)
            }
        )
    }
}
