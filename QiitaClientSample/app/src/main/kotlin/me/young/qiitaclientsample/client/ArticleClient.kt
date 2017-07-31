package me.young.qiitaclientsample.client

import me.young.qiitaclientsample.model.Article
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by Akihito on 2017/07/30.
 */
interface ArticleClient {

    @GET("/api/v2/items")
    fun search(@Query("query") query: String): Observable<List<Article>>
}