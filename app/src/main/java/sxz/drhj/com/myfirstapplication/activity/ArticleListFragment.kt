package sxz.drhj.com.myfirstapplication.activity

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import sxz.drhj.com.myfirstapplication.interfaces.ArticleListView
import sxz.drhj.com.myfirstapplication.model.Article
import sxz.drhj.com.myfirstapplication.presenter.ArticleListPresenter

@RequiresApi(Build.VERSION_CODES.M)
class ArticleListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener,
        Icon.OnDrawableLoadedListener, ArticleListView {
    //界面定义省略
    private val mPresenter: ArticleListPresenter = ArticleListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //关联View到Presenter中
        mPresenter.attach(this)
        mPresenter.fetchLastedArticles()
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onRefresh() {
        mPresenter.loadNextPageArticles()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detach()
    }


    override fun onFetchedArticles(result: List<Article>) {

    }

    override fun clearCacheArticles() {

    }

    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }

    override fun onDrawableLoaded(p0: Drawable?) {

    }

}