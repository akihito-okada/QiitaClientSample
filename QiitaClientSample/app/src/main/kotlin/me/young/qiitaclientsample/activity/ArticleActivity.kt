package me.young.qiitaclientsample.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import me.young.qiitaclientsample.R
import me.young.qiitaclientsample.bindView
import me.young.qiitaclientsample.model.Article
import me.young.qiitaclientsample.view.ArticleView

class ArticleActivity : AppCompatActivity() {

    val articleView : ArticleView by bindView(R.id.article_view)
    val webView : WebView by bindView(R.id.web_view)

    companion object {

        private const val ARTICLE_EXTRA: String = "article"

        fun createIntent(context: Context, article: Article): Intent =
                Intent(context, ArticleActivity::class.java)
                        .putExtra(ARTICLE_EXTRA, article)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val article : Article = intent.getParcelableExtra(ARTICLE_EXTRA)
        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }

}
