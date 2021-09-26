package kf.hifos.edu.dto

data class UserTodoDTO(
    val id: Long?,
    val userId: String,
    val todoContent: String,
    val isCompleted: String?,
    val regDate: String?,
)