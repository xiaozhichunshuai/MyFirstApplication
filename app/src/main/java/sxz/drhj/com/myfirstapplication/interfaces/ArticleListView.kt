package sxz.drhj.com.myfirstapplication.interfaces

import sxz.drhj.com.myfirstapplication.model.Article

/**
 * 展示文章列表的MVP View接口
 */
interface ArticleListView : MvpView {
    fun onFetchedArticles(result: List<Article>)
    fun clearCacheArticles()
}