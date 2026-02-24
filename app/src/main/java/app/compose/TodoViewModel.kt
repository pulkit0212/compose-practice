package app.compose

import androidx.lifecycle.ViewModel
import app.compose.model.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Kalmeshwar on 19 Feb 2026 at 15:23.
 */
class TodoViewModel : ViewModel(){
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    fun addTodo(item: String) {
        if (item.isNotBlank()) {
            val addNew = Todo(title = item)
            _todos.value += addNew
        }
    }

    fun deleteTodo(item: Todo) {

        _todos.value -= item
    }
}