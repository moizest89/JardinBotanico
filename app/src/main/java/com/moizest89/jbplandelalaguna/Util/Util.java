package com.moizest89.jbplandelalaguna.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

}
