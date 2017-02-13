package xyz.xechoz.demo.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by xechoz.zheng on 2/13/17.
 * Email: zheng1733@gmail.com
 * 功能:
 * 文档:
 */

public class Unit {
    private static DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();

    public static int dp2px(float dp) {
        return (int)((dp * displayMetrics.density) + 0.5);
    }

    public static int px2dp(float px) {
        return (int) ((px - 0.5f) / displayMetrics.density);
    }
}
