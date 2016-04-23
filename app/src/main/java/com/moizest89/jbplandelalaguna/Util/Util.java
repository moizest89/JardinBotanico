package com.moizest89.jbplandelalaguna.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by @moizest89 in SV on 4/10/16.
 */
public class Util {


    public final static Integer ANIMATION_DURATION = 1200;

    public static void changeActivity(Context context, Class<?> target, Bundle options, boolean finish) {
        Intent i = new Intent(context, target);

        if (options != null)
            i.putExtras(options);

        context.startActivity(i);
        // Close the activity so the user won't able to go back this
        // activity pressing Back button
        if (finish)
            ((Activity) context).finish();

    }

    public static String modifyDropboxUrl(String originalUrl)
    {
        String newUrl = originalUrl.replace("www.dropbox." ,"dl.dropboxusercontent.");

        //just for sure for case if www is missing in url string
        newUrl = newUrl.replace("dropbox.", "dl.dropboxusercontent.");

        return newUrl;
    }


    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
