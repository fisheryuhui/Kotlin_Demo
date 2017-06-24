package fisher.com.kotlindeom

import com.squareup.okhttp.OkHttpClient

/**
 * Created by fisher on 2017/6/2.
 */

object OkClient{
    private val client = OkHttpClient()
    fun instance() = client
}