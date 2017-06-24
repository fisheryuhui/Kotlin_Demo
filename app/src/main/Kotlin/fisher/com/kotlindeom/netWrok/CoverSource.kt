package fisher.com.kotlindeom.netWrok

import fisher.com.kotlindeom.Model.Cover
import fisher.com.kotlindeom.getHtml
import org.jsoup.Jsoup


/**
 * Created by fisher on 2017/6/2.
 */
class CoverSource : Source<Cover> {
    override fun obtain(url: String): ArrayList<Cover> {
        val list = ArrayList<Cover>()
        val html = getHtml(url)
        val doc = Jsoup.parse(html)
        val elements = doc.select("ul.mangeListBox").select("li")
        elements.forEach {
            val coverUrl = it.select("img").attr("src")
            val title = it.select("h1").text() + "\n" + it.select("h2").text()
            val link = "http://ishuhui.net" + it.select("div.magesPhoto").select("a").attr("href")
            val cover = Cover(coverUrl, title, link)
            list.add(cover)
        }

        return list
    }
}