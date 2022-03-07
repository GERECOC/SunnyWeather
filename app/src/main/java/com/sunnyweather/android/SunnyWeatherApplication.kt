/*
* 使用MVVM分层架构设计,由于从ViewModel层就不再持有Activity的引用,因此经常会出现"缺Context"的情况,本文件为本项目提供一种全局获取Context的方式
*
* */
package com.sunnyweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/*
* 当程序启动的时候应该初始化SunnyWeatherApplication类,而不是默认的Application类,因此必须在AndroidManifest.xml文件中的<application>标签下指定
* */
class SunnyWeatherApplication :Application(){
    companion object{
        //将context设置为静态变量很容易会产生内存泄漏问题,所以这是一种有风险的做法,但是由于这里获取的并不是Activity或者service的context
        //而是application的,它全局只会存在一份实例,并且在整个程序的生命周期内都不会回收,因此不存在内存泄漏,所以可以使用如下注解,让Android Studio
        //忽略警告提示
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        //添加彩云天气申请的令牌
        const val TOKEN = "彩云令牌申请暂时未通过,先用这串字符串代替"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}