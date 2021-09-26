package com.app.hifos.dto

data class UserTodoDTO(
    val id: Long?,
    val userId: String,
    val todoContent: String,
    val isCompleted: String?,
    val regDate: String?,
)