# SunnyWeather
该项目为安卓学习教程中的一篇示例
该项目遵循mvvm项目架构设计,将程序结构主要分为3部分,Model是数据模型部分,View是界面展示部分,ViewModel连接数据模型跟界面展示
实现业务逻辑和界面展示分离
                                     ______________
                                     |  UI控制层   |
                                     |_____________|
                                           |
                                     _____\|/______
                                     | ViewModel层 |
                                     |_____________|
                                           |
                                     _____\|/_______
                             _______|    仓库层     |____________
                             |      |_______________|           |
                   __________|____________           ___________|___________
                  |       本地数据源      |          |       网络数据源      |
                  |_______(model)________|          |_______(model)________|
                             |                                  |
                   _________\|/_________             __________\|/__________
                   |   本地持久化文件   |             |     Webservice      |
                   |___________________|             |_____________________|
