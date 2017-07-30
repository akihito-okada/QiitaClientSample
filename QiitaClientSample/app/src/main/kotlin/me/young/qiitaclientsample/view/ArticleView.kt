package me.young.qiitaclientsample.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import me.young.qiitaclientsample.R
import me.young.qiitaclientsample.bindView
import me.young.qiitaclientsample.model.Article

/**
 * Created by Akihito on 2017/07/30.
 */

class ArticleView : FrameLayout {

    val profileImageView: ImageView by bindView(R.id.profile_image_view)
    val titleTextView: TextView by bindView(R.id.title_text_view)
    val userNameTextView: TextView by bindView(R.id.user_name_text_view)

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)
    }

    fun setArticle(artcle: Article) {
        titleTextView.text = artcle.title;
        userNameTextView.text = artcle.user.name
        profileImageView.setBackgroundColor(Color.RED)

    }
}