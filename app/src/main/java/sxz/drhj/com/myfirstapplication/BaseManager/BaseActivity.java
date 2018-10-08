package sxz.drhj.com.myfirstapplication.BaseManager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sxz.drhj.com.myfirstapplication.R;
import sxz.drhj.com.myfirstapplication.manager.SystemBarTintManager;
import sxz.drhj.com.myfirstapplication.utils.MyLog;
import sxz.drhj.com.myfirstapplication.utils.MyToast;

/**
 * Activity形影不离的基友
 */
public class BaseActivity extends AppCompatActivity {
    public TextView mActionBarTitle;  //自定义ActionBar的居中标题
    public RelativeLayout mActionBackLayout; //自定义ActionBar的左侧整体布局
    public RelativeLayout mActionNextLayout; //自定义ActionBar的右侧整体布局
    public RelativeLayout mActionNextImageLayout; //自定义ActionBar 图片按钮的右侧整体布局
    public ImageView mActionBarNextImage1;  //自定义ActionBar 图片按钮1
    public ImageView mActionBarNextImage2; //自定义ActionBar 图片按钮2
    public TextView mActionBarBackText; //自定义ActionBar的左侧文字
    public TextView mActionBarNextText;  //自定义ActionBar的右侧文字
    public ImageView mActionBarBackFlag; //自定义ActionBar的左侧箭头
    public ImageView mActionBarNextFlag; //自定义ActionBar的右侧箭头
    public ImageView mBlackAlertCoverImage; //黑屏提示时，actionbar的遮挡图片
    public ActionBar mActionBar;

    /**
     * 自定义ActionBar左侧按键的Id
     */
    public final static int mCustomActionBarActionBackId = R.id.action_back_layout;
    /**
     * 自定义ActionBar右侧按键的Id
     */
    public final static int mCustomActionBarActionNextId = R.id.action_next_layout;
    /**
     * 自定义ActionBar 图片按钮1的Id
     */
    public final static int mCustomActionBarNextImage1Id = R.id.action_next_images1;
    /**
     * 自定义ActionBar 图片按钮2的Id
     */
    public final static int mCustomActionBarNextImage2Id = R.id.action_next_images2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        MyLog.d("sxz","base + onCreate");
        initCustomActionBar();
        initSystemBarManager();
    }

    private void initCustomActionBar() {
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setHomeButtonEnabled(false);
            mActionBar.setDisplayShowHomeEnabled(false);
            mActionBar.setDisplayShowTitleEnabled(false);
            mActionBar.setDisplayShowCustomEnabled(true);
            View actionbarLayout = LayoutInflater.from(this).inflate(R.layout.custom_actionbar_general, new LinearLayout(this), false);
            mActionBar.setCustomView(actionbarLayout);
            mActionBarTitle = actionbarLayout.findViewById(R.id.ab_title);
            mActionNextLayout = actionbarLayout.findViewById(R.id.action_next_layout);
            mActionBackLayout = actionbarLayout.findViewById(R.id.action_back_layout);
            mActionNextImageLayout = actionbarLayout.findViewById(R.id.action_next_images_layout);
            mActionBarNextText = actionbarLayout.findViewById(R.id.action_next_text);
            mActionBarBackText = actionbarLayout.findViewById(R.id.action_back_text);
            mActionBarNextFlag = actionbarLayout.findViewById(R.id.action_next_flag);
            mActionBarBackFlag = actionbarLayout.findViewById(R.id.action_back_flag);
            mBlackAlertCoverImage = actionbarLayout.findViewById(R.id.black_alert_cover_image);
            mActionBarNextImage1 = actionbarLayout.findViewById(R.id.action_next_images1);
            mActionBarNextImage2 = actionbarLayout.findViewById(R.id.action_next_images2);
            mActionBackLayout.setOnTouchListener(mOnTouchListener);
            mActionNextLayout.setOnTouchListener(mOnTouchListener);
            mActionBarNextImage1.setOnTouchListener(mOnTouchListener);
            mActionBarNextImage2.setOnTouchListener(mOnTouchListener);
        }
    }

    public void initSystemBarManager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        //默认顶部系统栏的颜色
        int DEFAULT_SYSTEM_BAR_COLOR = R.color.app_default_blue;
        tintManager.setStatusBarTintResource(DEFAULT_SYSTEM_BAR_COLOR);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @SuppressLint("ClickableViewAccessibility")
    View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.setAlpha(0.5f);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                v.setAlpha(1f);
            }
            return false;
        }
    };

    public void setContentView(int layoutSourceId) {
        View view = LayoutInflater.from(this).inflate(layoutSourceId, null);
        view.setFitsSystemWindows(true);
        setContentView(view);
    }
}
