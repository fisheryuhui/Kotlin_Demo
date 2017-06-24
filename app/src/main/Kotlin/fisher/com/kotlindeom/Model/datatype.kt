package fisher.com.kotlindeom.Model

/**
 * Created by fisher on 2017/6/2.
 */
data class Cover(val coverUrl: String, val title: String, val link: String)

data class Comic(val comicUrl: String)

data class News(val title: String, val createdTime: String, val link: String)

data class NewsContainer(val title: String, val newsList: List<News>)

data class PictureStory(val title: String, val coverUrl: String, val link: String)

data class TravelStory(val coverUrl:String,val title: String,val link: String)