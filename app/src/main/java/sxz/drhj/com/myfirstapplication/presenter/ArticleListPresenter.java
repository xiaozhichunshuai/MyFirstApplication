package sxz.drhj.com.myfirstapplication.presenter;

import sxz.drhj.com.myfirstapplication.Base.BasePresenter;
import sxz.drhj.com.myfirstapplication.interfaces.ArticleListView;

/**
 * 文章列表的Presenter负责从网络上加载最新的文章列表。
 * 第一次，先从数据库加载缓存，然后再从网络上加载最新数据。
 */
public class ArticleListPresenter extends BasePresenter<ArticleListView> {
    public static final int FIRST_PAGE = 1;  //第一页数据,代表最新数据
    private int mPageIndex = FIRST_PAGE;  //索引，用于下拉下一页数据
    private boolean isCacheLoaded = false;

    //第一次先从数据库中加载缓存,然后从网络上获取数据
    public void fetchLastedArticles(){
        if(!isCacheLoaded){
            //从数据库加载
        }
        //从网络上获取最新数据
        fetchArticlesAsync(FIRST_PAGE);
    }

    private void fetchArticlesAsync(final int firstPage) {
        mView.onShowLoading();
        //请求文章列表
    }

    public void loadNextPageArticles() {

    }
}
