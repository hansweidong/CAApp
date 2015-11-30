package htb.com.childrenapp.Framework.CAAnimation;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by Administrator on 2015/11/27.
 */
public class CAAnimationManager {

    private static CAAnimationManager caAnimationManager = null;

    private CAAnimationManager(){}

    public synchronized static CAAnimationManager newInstance(){
        if (caAnimationManager == null)
            caAnimationManager = new CAAnimationManager();
        return caAnimationManager;
    }


    /**
     * ui界面从右出现
     * @param context
     * @param fromView
     * @param toView
     * @param isShow
     * @param AnimationId
     * @return
     */
    public boolean UIMoveFromRight(Context context,final View fromView,final View toView,int AnimationId){
        boolean isRet = false;
        Animation animation = AnimationUtils.loadAnimation(context, AnimationId);
        Animation.AnimationListener animationListener = null;
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                fromView.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        animation.setAnimationListener(animationListener);
        toView.setAnimation(animation);
        isRet = true;
        return isRet;
    }

    /**
     * UI界面从左滑出
     * @param context
     * @param DelViewIndex
     * @param toView
     * @param isShow
     * @param AnimationId
     * @return
     */
    public boolean UIMoveToLeft(Context context, final int DelViewIndex,final View toView,int AnimationId){
        boolean isRet = false;
        Animation animation = AnimationUtils.loadAnimation(context, AnimationId);
        Animation.AnimationListener animationListener = null;
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ((ViewGroup)(toView.getParent())).removeViewAt(DelViewIndex);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        animation.setAnimationListener(animationListener);
        (((ViewGroup)(toView.getParent())).getChildAt(DelViewIndex)).setAnimation(animation);
        isRet = true;
        return isRet;
    }
}
