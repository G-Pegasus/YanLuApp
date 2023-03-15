package com.tongji.yanluapp.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tongji.lib_common.bean.TodoResponse
import com.tongji.lib_common.network.ApiResponse2
import com.tongji.lib_common.network.apiService1
import com.tongji.yanluapp.app.App
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.request
import retrofit2.HttpException
import kotlin.math.log

/**
 * @author: Kana (Tongji)
 * @date: 2022/9/14 16:28
 * @description:
 * @email: tongji0x208@gmail.com
 */
class SubjectViewModel : BaseViewModel() {
    val todoLiveData: LiveData<List<TodoResponse>?>
        get() = _todoLiveData
    private val _todoLiveData = MutableLiveData<List<TodoResponse>?>()

    val errorLiveData: LiveData<Boolean>
        get() = _errorLiveData
    private val _errorLiveData = MutableLiveData<Boolean>()

    fun getTodoByTime(time: String) {
        viewModelScope.launch {
            val data: ApiResponse2<List<TodoResponse>>
            try {
                data = apiService1.getTodoByTime(time)
                if (data.code != 200 || data.msg != "获取成功") {
                    _errorLiveData.postValue(true)
                } else {
                    _todoLiveData.postValue(data.data)
                }
            }catch (e:HttpException){
                if (e.message()=="Unauthorized"){
                    Toast.makeText(App.context, "请先登录！", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun addTodo(
        title: String,
        time: String,
        detail: String
    ) {
        viewModelScope.launch {
            try {
                val data = apiService1.addTodo(title, time, detail)
                if (data.code != 200 || data.msg != "新增成功") {
                    _errorLiveData.postValue(true)
                } else {
                    _errorLiveData.postValue(false)
                }
            }catch (e:HttpException){
                if (e.message()=="Unauthorized"){
                    Toast.makeText(App.context, "请先登录！", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun updateTodo(
        id: String,
        title: String,
        detail: String,
        time: String
    ) {
        viewModelScope.launch {
            try {
                val data = apiService1.updateTodo(id, title, detail, time)
                if (data.code != 200 || data.msg != "更改成功") {
                    _errorLiveData.postValue(true)
                } else {
                    _errorLiveData.postValue(false)
                }
            }catch (e:HttpException){
                if (e.message()=="Unauthorized"){
                    Toast.makeText(App.context, "请先登录！", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun deleteTodo(id: String) {
        viewModelScope.launch {
            try {
                val data = apiService1.deleteTodo(id)
                if (data.code != 200 || data.msg != "删除成功") {
                    _errorLiveData.postValue(true)
                } else {
                    _errorLiveData.postValue(false)
                }
            }catch (e:HttpException){
                if (e.message()=="Unauthorized"){
                    Toast.makeText(App.context, "请先登录！", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}