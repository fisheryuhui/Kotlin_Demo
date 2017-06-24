package fisher.com.kotlindeom.netWrok

import fisher.com.kotlindeom.Model.Comic
import fisher.com.kotlindeom.getHtml
import org.jsoup.Jsoup

/**
 * Created by fisher on 2017/6/7.
 */
class ComicSource : Source<Comic> {
    override fun obtain(url: String): ArrayList<Comic> {
        val html = getHtml(url)
        val doc = Jsoup.parse(html)

        val elements = doc.select("div.mangaContentMainImg").select("img")
        val list = ArrayList<Comic>()

        for (element in elements) {
            var comicUrl: String
            val temp = element.attr("src")
            if (temp.contains(".png") or  temp.contains(".jpg") or  temp.contains(".JPEG")) {
                comicUrl = temp
            } else if ("" != element.attr("data-original")) {
                comicUrl = element.attr("data-original")
            } else {
                continue
            }
            val comic = Comic(comicUrl)
            list.add(comic)
        }
        return list
    }

}