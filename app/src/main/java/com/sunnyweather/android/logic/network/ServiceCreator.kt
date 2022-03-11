/*
* 本文件定义一个retrofit构建器
* */
package com.sunnyweather.android.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    val BASE_URL = "https://api.caiyunapp.com/"

    //private val platform = Retrofit.Builder()
    //private val builder = platform.baseUrl(BASE_URL)
    //private val retrofit = builder.addConverterFactory(GsonConverterFactory.create()).build()

    /*
      1、使用Retrofit.Builder来构建一个retrofit对象
      2、baseUrl()方法用于指定所有Retrofit请求的根路径
      3、addConverterFactory()方法用于指定Retrofit在解析数据时所使用的转换库,这里指定成GsonConverterFactory
    * */
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    /*
    * 通过Retrofit对象的create()方法,传入具体的Service接口所对应的class类型,创建一个该接口的动态代理对象
    * 有了动态代理对象后,就可以随意调用接口定义的所有方法
    * 动态代理讲解:  https://www.itcast.cn/news/20200717/12001737842.shtml
    *               https://www.zhihu.com/question/20794107
    * https://blog.csdn.net/hyy495/article/details/77865869?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_title~default-1.pc_relevant_antiscan&spm=1001.2101.3001.4242.2&utm_relevant_index=4
    * */
    fun <T> create(serviceClass : Class<T>) : T = retrofit.create(serviceClass)

    //泛型实化
    inline fun <reified T> create() : T = create(T::class.java)
}