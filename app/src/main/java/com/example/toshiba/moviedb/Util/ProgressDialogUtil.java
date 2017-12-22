package com.example.toshiba.moviedb.Util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by TOSHIBA on 14/12/2017.
 */

public class ProgressDialogUtil {

    public static ProgressDialog showProgressDialog(Context context, String message){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
        return progressDialog;
    }

}
