/*根据mvvm架构,本文件用于定义数据模型
  本文件定义的类与属性,是根据彩云天气api返回的JSON格式定义的
* */
import com.google.gson.annotations.SerializedName

//status表示返回状态 places表示获取的地址天气信息
data class PlaceResponse(val status:String, val places: List<Place>)

//由于JSON中一些字段的命名可能与Kotlin的命名规范不太一致,因此这里使用@SerializedName(序列化名称)注解的方式,来让JSON和kotlin字段之间简历映射关系
//彩云天气接口返回值的json数据格式如下 {"name" : "...","location" : {"lat":"...","lng":"..."},"formatted_address"  : "..."}
data class Place(val name : String,val location:Location,@SerializedName("formatted_address") val address:String)

data class Location(val lng:String,val lat : String)