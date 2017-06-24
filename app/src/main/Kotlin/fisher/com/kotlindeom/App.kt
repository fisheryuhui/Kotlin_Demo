package fisher.com.kotlindeom

import android.app.Application
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso

/**
 * Created by fisher on 2017/6/2.
 */
class App : Application() {
    private val TAG = App::class.java.simpleName
    override fun onCreate() {
        super.onCreate()
        val max = Runtime.getRuntime().maxMemory().toInt()
        Picasso.Builder(this).memoryCache(LruCache(max / 8)).build()
    }

}