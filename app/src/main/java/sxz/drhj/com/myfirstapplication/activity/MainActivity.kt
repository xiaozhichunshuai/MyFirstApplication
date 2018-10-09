package sxz.drhj.com.myfirstapplication.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import kotlinx.android.synthetic.main.activity_main.*
import sxz.drhj.com.myfirstapplication.R
import sxz.drhj.com.myfirstapplication.utils.DialogUtils
import sxz.drhj.com.myfirstapplication.utils.MyLog
import sxz.drhj.com.myfirstapplication.utils.MyToast
import java.io.ObjectOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mContext: MainActivity = this

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        message.setText(R.string.title_home)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_dashboard -> {
                        message.setText(R.string.title_dashboard)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_notifications -> {
                        message.setText(R.string.title_notifications)
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false

            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    @Override
    override fun onStart() {
        super.onStart()
        getDataFromNetWork("https://blog.csdn.net/bskfnvjtlyzmv867/article/details/71598263");
    }

    //ActionBar点击返回
    override fun onClick(v: View?) {
        MyToast.makeText(mContext, "回去")
    }

    //获取数据
    private fun getDataFromNetWork(url: String) {
        OkGo.post<String>(url)
                .tag(this)
                .execute(object : StringCallback() {
                    @Override
                    override fun onStart(request: Request<String, out Request<Any, Request<*, *>>>?) {
                        super.onStart(request)
                        MyLog.d("sxz", "onStart")
                        DialogUtils.createSimpleProgressDialog(mContext, "加载数据...")
                    }

                    override fun onSuccess(response: Response<String>?) {
                        MyLog.d("sxz", "onSuccess：" + response?.body())
                    }

                    @Override
                    override fun onError(response: Response<String>?) {
                        super.onError(response)
                        MyLog.d("sxz", "onError")
                        MyToast.makeText(mContext, "网络异常")
                    }

                    @Override
                    override fun onFinish() {
                        super.onFinish()
                        MyLog.d("sxz", "onFinish")
                        DialogUtils.dismissProgressDialog(mContext);
                    }
                })

    }

    /**
     * 访问网络数据
     */
    private fun requestNetWork(url: String) {
        val mUrl: URL = URL(url)
        val openCon: URLConnection = mUrl.openConnection()
        val mUriCon: HttpURLConnection = openCon as HttpURLConnection
        mUriCon.doOutput = true
        mUriCon.doInput = true
        mUriCon.useCaches = false //POST不能使用缓存
        mUriCon.requestMethod = "POST"  //默认是GET
        mUriCon.connect()

        val outputStream = mUriCon.outputStream
        val objOutPutStream: ObjectOutputStream = ObjectOutputStream(outputStream)
        objOutPutStream.writeObject("test data")
        objOutPutStream.flush()
        objOutPutStream.close()

        val inPutStream = mUriCon.inputStream
        objOutPutStream.writeObject("")
        mUriCon.inputStream
    }

    @Override
    override fun onResume() {
        super.onResume()
    }

    @Override
    override fun onPause() {
        super.onPause()
    }

    @Override
    override fun onStop() {
        super.onStop()
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
    }

    @Override
    override fun onRestart() {
        super.onRestart()
    }


}
