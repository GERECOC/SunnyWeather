/*
* 本文件定义了网络层的相关代码
* */
package com.sunnyweather.android.logic.network

import PlaceResponse
import com.sunnyweather.android.SunnyWeatherApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    /*
    * 彩云天气的访问地址类型如下
    * https://api.caiyunapp.com/v2/place?query=北京&token={token}&lang=zh_CN
    * 使用@GET注解,表示当调用searchPlaces方法时retrofit会发起一条get请求,请求的地址就是@GET注解中传入的集体参数(这里只需要传入请求地址的相对路径即可,
    * 根路径在创建retrofit对象时设置)
    * 使用@Query(查询)注解对GET请求的参数进行声明,这样当发起网络请求时,Retrofit会自动按照带参数GET请求的格式,将这个参数构建到请求地址中去
    * 因为搜索城市数据的api中只有query这个参数需要动态指定,所以使用@Query注解的方式来实现,另两个参数是不会变的,因此固定写在@GET注解中即可
    * 将searchPlaces的返回值声明成Call<PlaceResponse>,这样retrofit就会将服务器返回的json数据自动解析成PlaceResponse对象
    * */
    @GET("V2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query")query:String): Call<PlaceResponse>

}