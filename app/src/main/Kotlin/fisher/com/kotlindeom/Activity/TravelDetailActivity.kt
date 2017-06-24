package fisher.com.kotlindeom.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import fisher.com.kotlindemo.R
import kotlinx.android.synthetic.main.activity_travel_detail.*
import org.jetbrains.anko.webView

class TravelDetailActivity : AppCompatActivity() {
    companion object {
        val TRAVEL_DETAIL_URL = "url"
    }

    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_detail)
        loadData()
        webview.settings.javaScriptEnabled = true
        webview.settings.useWideViewPort = true
        webview.settings.loadWithOverviewMode = true
        webview.loadUrl(url)
    }

    private fun loadData() {
        url = intent.getStringExtra(TRAVEL_DETAIL_URL)
    }
}
