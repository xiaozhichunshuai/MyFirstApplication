package sxz.drhj.com.myfirstapplication.utils;

import android.app.Activity;
import android.app.ProgressDialog;

public class DialogUtils {

    private static ProgressDialog progressDialog;

    public static ProgressDialog createSimpleProgressDialog(Activity activity, String message) {
        if (!activity.isFinishing()) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage(message);
            progressDialog.setCancelable(true);
            progressDialog.show();
            return progressDialog;
        }
        return null;
    }

    public static void dismissProgressDialog(Activity activity) {
        if (activity != null) {
            progressDialog.cancel();
        }
    }
}
