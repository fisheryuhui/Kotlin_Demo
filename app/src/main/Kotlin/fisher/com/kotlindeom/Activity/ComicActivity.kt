package fisher.com.kotlindeom.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import fisher.com.kotlindemo.R
import fisher.com.kotlindeom.Adapter.ComicPagerAdapter
import fisher.com.kotlindeom.Model.Comic
import fisher.com.kotlindeom.netWrok.ComicSource
import fisher.com.kotlindeom.netWrok.ImageSource
import kotlinx.android.synthetic.main.activity_comic.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class ComicActivity : AppCompatActivity() {
    companion object {
        val INTENT_COMIC_URL = "url"
    }

    var mData = ArrayList<Comic>()
    lateinit var url: String
    lateinit var adapter: ComicPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)

        url = intent.getStringExtra(INTENT_COMIC_URL)
        adapter = ComicPagerAdapter(mData, supportFragmentManager)
        comicPagers.adapter = adapter
        comicPagers.offscreenPageLimit = 2

        load()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun load() {
        async {
            val data = ComicSource().obtain(url)
            mData = data
            uiThread {
                adapter.refreshData(mData)
            }
        }
    }
}
