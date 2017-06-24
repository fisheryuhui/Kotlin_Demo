package fisher.com.kotlindeom.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import fisher.com.kotlindemo.R
import fisher.com.kotlindeom.Adapter.ComicPagerAdapter
import fisher.com.kotlindeom.Adapter.PictureStoryDetailAdapter
import fisher.com.kotlindeom.Model.Comic
import fisher.com.kotlindeom.netWrok.ComicSource
import fisher.com.kotlindeom.netWrok.ImageSource
import kotlinx.android.synthetic.main.activity_comic.*
import kotlinx.android.synthetic.main.activity_picture_story.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread

class PictureStoryDetailActivity : AppCompatActivity() {

    companion object {
        val PICTURE_DETAIL_URL = "url"
    }

    var mData = ArrayList<Comic>()
    lateinit var url: String
    lateinit var adapter: PictureStoryDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_story)

        url = intent.getStringExtra(PICTURE_DETAIL_URL)
        adapter = PictureStoryDetailAdapter(mData, supportFragmentManager)
        picture_story_Pagers.adapter = adapter
        picture_story_Pagers.offscreenPageLimit = 2
        loading.visibility = View.VISIBLE
        load()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun load() {
        async {
            val data = ImageSource().obtain(url)
            mData = data
            uiThread {
                loading.visibility = View.GONE
                adapter.refreshData(mData)
            }
        }
    }
}
