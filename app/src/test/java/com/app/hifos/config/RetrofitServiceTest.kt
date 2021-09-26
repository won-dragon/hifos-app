package com.app.hifos.config

import com.app.hifos.dto.UserTodoDTO
import kotlinx.coroutines.runBlocking
import org.junit.Test



class RetrofitServiceTest {
    @Test
    fun 해야할일_몽땅가져오() = runBlocking {
        val list : List<UserTodoDTO>  =  RetrofitService.API.userTodoSearchAll();
        println(list)
    }

    @Test
    fun 해야할일_등록하기() = runBlocking {

        val userId = "User1"
        var todoContent = "PPT 작성"

        var userTodoDTO = UserTodoDTO(null, userId, todoContent, null, null)

        val result : UserTodoDTO = RetrofitService.API.userTodoReg(userTodoDTO)

        println(result)

    }

    @Test
    fun 해야할일_유저별로_조회하기() = runBlocking {
        val list : List<UserTodoDTO>  = RetrofitService.API.userTodoSearch("User1")

        println(list)
    }

    @Test
    fun 해야할일_유저별_수정하기() = runBlocking {
        val userId = "User1"
        var todoContent = "PPT 작성하냐??"

        var userTodoDTO = UserTodoDTO(null, userId, todoContent, "Y", null)

        val result : UserTodoDTO = RetrofitService.API.userTodoMod(8, userTodoDTO)

        println(result)
    }

    @Test
    fun 해야할일_삭제하기() = runBlocking {
        RetrofitService.API.userTodoRemove(8)
    }


}