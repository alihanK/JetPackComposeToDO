package com.package.todoappkotlin.data.repository

import com.package.todoappkotlin.data.local.Todo
import com.package.todoappkotlin.data.local.TodoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao) {
    fun getTodos(): Flow<List<Todo>> = todoDao.getAllTodos()
    suspend fun addTodo(todo: Todo) = todoDao.insertTodo(todo)
    suspend fun updateTodo(todo: Todo) = todoDao.updateTodo(todo)
    suspend fun deleteTodo(todo: Todo) = todoDao.deleteTodo(todo)
}
