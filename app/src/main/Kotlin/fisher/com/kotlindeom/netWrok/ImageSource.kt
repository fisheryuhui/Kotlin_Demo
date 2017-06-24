package fisher.com.kotlindeom.netWrok

import android.util.Log
import fisher.com.kotlindeom.Model.Comic
import fisher.com.kotlindeom.getHtml
import fisher.com.kotlindeom.log
import org.jsoup.Jsoup

/**
 * Created by fisher on 2017/6/7.
 */
class ImageSource : Source<Comic> {
    override fun obtain(url: String): ArrayList<Comic> {
        val list = ArrayList<Comic>()
        val html = getHtml(url + ".html")
        val doc = Jsoup.parse(html)

        val elements = doc.select("div.nextpagec").select("span").select("a")
        val end_page: String?
        val filter_elements = elements.filter { it.select("a").text().contains("尾页") }
        end_page = filter_elements[0].select("a").attr("href")
        val s_page = 1
        val e_page = end_page!!.split("_")[1].split(".")[0].toInt()

        for (i in s_page..e_page) {
            var child_html: String
            if (i == 1) {
                child_html = getHtml(url + ".html")
            } else {
                child_html = getHtml(url + "_" + i + ".html")
            }
            val child_doc = Jsoup.parse(child_html)
            val child_elements = child_doc.select("div.contentpic").select("img")
            child_elements.map {
                val image_url = "http://www.28lu.com/" + it.attr("src")
                val comic = Comic(image_url)
                list.add(comic)
            }

        }

        return list

    }
}