package fisher.com.kotlindeom.netWrok

import fisher.com.kotlindeom.Model.News
import fisher.com.kotlindeom.Model.NewsContainer
import fisher.com.kotlindeom.Model.TravelStory
import fisher.com.kotlindeom.getHtml
import fisher.com.kotlindeom.log
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

/**
 * Created by fisher on 2017/6/7.
 */
class TravelStorySource : Source<TravelStory> {
    override fun obtain(url: String): ArrayList<TravelStory> {
        val list = ArrayList<TravelStory>()
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val elements = doc.select("div.news-content.tw-item").select("div.news-item")
        elements.myForEach {
            val story = doTask(it)
            list.add(story)
        }

        return list
    }

    fun <T> List<T>.myForEach(doTask: (T) -> Unit) {
        for (item in this) {
            doTask(item)
        }
    }

    fun doTask(it: Element): TravelStory {
        val  b = it.select("script")[0].data()
        val  a = b.substring(b.indexOf("(")+1,b.indexOf(","))
        val coverurl = a.replace("\"","")
        val title = it.select("div.news-txt").select("a").text()
        var link = it.select("a").attr("href")
        link = "http://n.quanjing.com"+ link
        val  data = TravelStory(coverurl,title,link)
        return data
    }

}