package me.young.qiitaclientsample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import me.young.qiitaclientsample.R
import me.young.qiitaclientsample.adapter.ArticleListAdapter
import me.young.qiitaclientsample.bindView
import me.young.qiitaclientsample.model.Article
import me.young.qiitaclientsample.model.User

class MainActivity : AppCompatActivity() {

    val listView : ListView by bindView(R.id.list_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listAdapter = ArticleListAdapter(applicationContext)
        listAdapter.articles = listOf(dummyArticle("だみ声かずこ", "かずお"), dummyArticle("ハスキーたろいも", "たろう"))

        listView.adapter = listAdapter

        listView.setOnItemClickListener { adapterView, view, position, id ->
            val article = listAdapter.articles[position]
            ArticleActivity.createIntent(this, article).let { startActivity(it) }
        }

/*
        val articleView = ArticleView(applicationContext)
        val article = Article("123", "title", "http://example.com/xx.html", User("1234", "ama", "http://example.com/image.png"))
        Toast.makeText(this, article.title, Toast.LENGTH_SHORT).show()

        articleView.setArticle(article)
        setContentView(articleView)
*/
    }

    private fun dummyArticle(title : String, userName : String) : Article =
            Article(id = "",
                    title = title,
                    url = "https://google.com/",
                    user = User(id = "",
                            name = "taro",
                            profileImageURL = "http://example.com/image.png"
                            ))
}

private fun ListView.setOnClickListener(any: Any) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
}
