package com.package.todoappkotlin.uiall.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.package.todoappkotlin.data.local.Todo
import com.package.todoappkotlin.data.repository.TodoRepository
import com.package.todoappkotlin.Util.NotificationHelper
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {

    val todos = repository.getTodos()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTodo(todo: Todo, notificationHelper: NotificationHelper) {
        viewModelScope.launch {
            try {
                repository.addTodo(todo)
                notificationHelper.sendNotification("Yeni bir todo oluşturdunuz")
            } catch (e: Exception) {
                e.printStackTrace()
                // Hata durumunda kullanıcıya bilgi verilebilir
            }
        }
    }

    fun updateTodo(todo: Todo, notificationHelper: NotificationHelper) {
        viewModelScope.launch {
            repository.updateTodo(todo)
            notificationHelper.sendNotification("Todo güncellendi")
        }
    }

    fun deleteTodo(todo: Todo, notificationHelper: NotificationHelper) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
            notificationHelper.sendNotification("Todo silindi")
        }
    }
}
