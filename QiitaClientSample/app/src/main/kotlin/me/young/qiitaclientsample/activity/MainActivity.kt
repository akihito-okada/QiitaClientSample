package me.young.qiitaclientsample.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import me.young.qiitaclientsample.R
import me.young.qiitaclientsample.adapter.ArticleListAdapter
import me.young.qiitaclientsample.bindView
import me.young.qiitaclientsample.client.ArticleClient
import me.young.qiitaclientsample.model.Article
import me.young.qiitaclientsample.model.User
import me.young.qiitaclientsample.toast
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : RxAppCompatActivity() {

    val progressBar : ProgressBar by bindView(R.id.progress_bar)
    val listView : ListView by bindView(R.id.list_view)
    val queryEditText: EditText by bindView(R.id.query_edit_text)
    val searchButton: Button by bindView(R.id.search_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://qiita.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        val articleClient = retrofit.create(ArticleClient::class.java)

        val listAdapter = ArticleListAdapter(applicationContext)
//        listAdapter.articles = listOf(dummyArticle("だみ声かずこ", "かずお"), dummyArticle("ハスキーたろいも", "たろう"))

        listView.adapter = listAdapter

        listView.setOnItemClickListener { adapterView, view, position, id ->
            val article = listAdapter.articles[position]
            ArticleActivity.createIntent(this, article).let { startActivity(it) }
        }

        searchButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            articleClient.search(queryEditText.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate {
                        progressBar.visibility = View.GONE
                    }
                    .bindToLifecycle(this)
                    .subscribe({
                        queryEditText.text.clear()
                        listAdapter.articles = it
                        listAdapter.notifyDataSetChanged()
                    }, {
                        toast("error : $it")
                    })
        }
    }

    private fun dummyArticle(title : String, userName : String) : Article =
            Article(id = "",
                    title = title,
                    url = "https://google.com/",
                    user = User(id = "",
                            name = "taro",
                            profileImageUrl = "http://example.com/image.png"
                            ))
}
