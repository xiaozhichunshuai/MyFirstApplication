package sxz.drhj.com.myfirstapplication.BaseManager;

import sxz.drhj.com.myfirstapplication.interfaces.MvpView;

public abstract class BasePresenter<T extends MvpView> {

    protected T mView;

    public void attach(T view){
        mView = view;
    }

    public void detach(){
        mView = null;
    }
}
