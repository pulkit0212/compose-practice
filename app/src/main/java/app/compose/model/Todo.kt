package app.compose.model

import java.util.UUID

/**
 * Created by pulkit on 19 Feb 2026 at 16:09.
 */
data class Todo(
    val id: String = UUID.randomUUID().toString(),
    val title: String
)