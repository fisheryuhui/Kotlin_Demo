package fisher.com.kotlindeom.netWrok

import android.util.Log
import fisher.com.kotlindeom.Model.PictureStory
import fisher.com.kotlindeom.getHtml
import org.jsoup.Jsoup

/**
 * Created by fisher on 2017/6/9.
 */
class PictureStorySource : Source<PictureStory> {
    override fun obtain(url: String): ArrayList<PictureStory> {
        val list = ArrayList<PictureStory>()
        val html = getHtml(url)
        val doc = Jsoup.parse(html)
        val elements = doc.select("div.lst").select("li")

        elements.filter {
            val link = it.select("div.pe_u_thumb").select("a").attr("href")
            link.endsWith(".html")
        }.forEach {
            var link = it.select("div.pe_u_thumb").select("a").attr("href")
            val title = it.select("div.pe_u_thumb").select("img").attr("alt")
            var cover_url = it.select("div.pe_u_thumb").select("img").attr("src")
            link = link.split(".")[0]
            link = "http://www.28lu.com" + link
            cover_url = "http://www.28lu.com" + cover_url
            val data = PictureStory(title, cover_url, link)
            list.add(data)
        }

        return list
    }
}