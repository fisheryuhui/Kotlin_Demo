package fisher.com.kotlindeom.netWrok

import fisher.com.kotlindeom.Model.Cover
import fisher.com.kotlindeom.Model.PictureStory
import fisher.com.kotlindeom.getHtml
import org.jsoup.Jsoup

/**
 * Created by fisher on 2017/6/7.
 */
class BookSource : Source<PictureStory> {
    override fun obtain(url: String): ArrayList<PictureStory> {
        val list = ArrayList<PictureStory>()
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val elements = doc.select("div.lst").select("li")

        elements.filter {
            val link = it.select("div.pe_u_thumb").select("a").attr("href")
            link.endsWith(".html")
        }.forEach {
            var image_cover  = it.select("div.pe_u_thumb").select("img").attr("src")
            val title = it.select("div.pe_u_thumb").select("img").attr("alt")
            var link = it.select("div.pe_u_thumb").select("a").attr("href")
            link = link.split(".")[0]
            link = "http://www.28lu.com" + link
            image_cover = "http://www.28lu.com" + image_cover

            val data = PictureStory(title,image_cover,link)
            list.add(data)
        }

        return list
    }
}