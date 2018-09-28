package sxz.drhj.com.myfirstapplication.utils;

import android.util.Log;

import sxz.drhj.com.myfirstapplication.httpProxy.JZBConstants;
/*日志打印*/
public class MyLog {
	public static void d(String tag, String msg) {
		if(JZBConstants.DEBUG_MODE){
			Log.d(tag, msg);
		}
	}
	public static void i(String tag, String msg) {
		if(JZBConstants.DEBUG_MODE){			
			Log.i(tag, msg);
		}
	}
	public static void e(String tag, String msg) {
		if(JZBConstants.DEBUG_MODE){			
			Log.e(tag, msg);
		}
	}
	public static void w(String tag, String msg) {
		if(JZBConstants.DEBUG_MODE){			
			Log.w(tag, msg);
		}
	}
	public static void v(String tag, String msg) {
		if(JZBConstants.DEBUG_MODE){			
			Log.v(tag, msg);
		}
	}
}
