package kf.hifos.edu.config

import kf.hifos.edu.dto.UserTodoDTO
import kotlinx.coroutines.runBlocking
import org.junit.Test



class RetrofitServiceTest {
    @Test
    fun 해야할일_몽땅가져오() = runBlocking {
        val list : List<UserTodoDTO>  =  RetrofitService.API.userTodoAll();
        println(list)
    }

    @Test
    fun 해야할일_등록하기() = runBlocking {

        val userId = "User1"
        var todoContent = "PPT 작성"

        var userTodoDTO = UserTodoDTO(null, userId, todoContent, null, null)

        val result : UserTodoDTO = RetrofitService.API.userTodo(userTodoDTO)

        println(result)

    }

}