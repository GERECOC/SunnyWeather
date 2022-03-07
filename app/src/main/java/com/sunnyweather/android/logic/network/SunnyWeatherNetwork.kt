/*
* 本文件定义一个统一的网络数据源访问入口,对所有网络请求的api进行封装
* */
package com.sunnyweather.android.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    //使用ServiceCreator创建了一个PlaceService的动态动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>()

    //将searchPlaces函数声明成挂起函数,使用协程通过await()函数实现简化Retrofit回调
    suspend fun searchPlaces(query:String) = placeService.searchPlaces(query).await()

    //借助suspendCoroutine函数将传统的回调机制写法大幅简化
    /*
    * suspendCoroutine函数必须在协程作用域或挂起函数中才能调用,它接收一个Lambda表达式,主要作用是将当前协程立即挂起,然后在一个普通线程中执行Lambda表达式代码
    * Lambda表达式传入一个Continuation参数,调用它的resume()方法或者resumeWithException()可以让协程恢复执行
    * await()函数是一个挂起函数,这里给它声明了一个泛型T,并将await()函数定义成Call<T>的扩展函数,这样所有返回值是Call类型的retrofit网络请求都可以直接调用await()函数
    * */
    private suspend fun <T> Call<T>.await() : T{
        return suspendCoroutine {
            continuation -> enqueue(object : Callback<T>{
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null) continuation.resume(body)
                else continuation.resumeWithException(RuntimeException("response body is null"))
            }


            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
            })
        }
    }
}