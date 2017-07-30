package me.young.qiitaclientsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import me.young.qiitaclientsample.model.Article
import me.young.qiitaclientsample.model.User
import me.young.qiitaclientsample.view.ArticleView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        setContentView(R.layout.activity_main)
*/
        val articleView = ArticleView(applicationContext)
        val article = Article("123", "title", "http://example.com/xx.html", User("1234", "ama", "http://example.com/image.png"))
        Toast.makeText(this, article.title, Toast.LENGTH_SHORT).show()

        articleView.setArticle(article)
        setContentView(articleView)
    }
}
