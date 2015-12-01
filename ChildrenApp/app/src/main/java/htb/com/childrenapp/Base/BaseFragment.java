package htb.com.childrenapp.Base;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v4.app.FragmentPagerAdapter;

import htb.com.childrenapp.CAApplication;
import htb.com.childrenapp.R;


/**
 * Created by weidong_wu on 15/11/7.
 * 邮箱:wwdhao163@163.com
 */
public abstract class BaseFragment extends FragmentActivity {
    private static final String KeyTag = "BASE_TAG";

    protected boolean isCanDoubleClick = true; //是否需要双击退出应用

    private boolean isExit = false;

    private Context m_Context = null;

    public int ShowLoadId = 1;

    protected FragmentManager fragmentManager;

    protected Bundle bundle;

    protected Fragment mFragmentContent;

    private boolean IntentBundleIsNull = true;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            isExit = false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CAApplication.instance().addActivity(this);
        if ((savedInstanceState != null) && savedInstanceState.containsKey(KeyTag)) {
            bundle = savedInstanceState;
        } else if (getIntent().getExtras()!=null){
            bundle = getIntent().getExtras();
            IntentBundleIsNull = false;
        }

        if(IntentBundleIsNull){
            bundle = new Bundle();
        }

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        m_Context = this;
        fragmentManager = getSupportFragmentManager();
        init();
    }

    /**
     * 初始化activity的信息
     */
    public abstract void init();

    /**
     * 获得当前activity的context
     *
     * @return
     */
    protected Context getContext() {
        return m_Context;
    }

    public void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), R.string.APP_EXIT_TIPS, Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
           CAApplication.instance().exit();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isCanDoubleClick) {
                exit();
                return false;
            }else
                return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /**
     * 显示全屏加载动画
     *
     * @return
     */
    public RelativeLayout showLoadingDialog() {
        RelativeLayout linearLayout = new RelativeLayout(m_Context);
        RelativeLayout.LayoutParams layoutParams_0 = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setLayoutParams(layoutParams_0);
        ImageView imageView = new ImageView(m_Context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.login_progress_bg);
        Animation operatingAnim = AnimationUtils.loadAnimation(m_Context, R.anim.loadanim);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        imageView.setAnimation(operatingAnim);
        linearLayout.addView(imageView);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        return linearLayout;
    }

    /**
     * 通用提示信息
     *
     * @param msg
     */
    public void ToastMessage(String msg) {
        Toast.makeText(m_Context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 虚拟键盘是否已隐藏
     */
    public void checkKeyboard() {
        if (getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
            //隐藏软键盘
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            getWindow().getAttributes().softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(KeyTag, bundle);
    }

    /**
     * 切换Fragment
     *
     * @param from
     * @param to
     * @param FragmentId
     */
    public void switchContent(Fragment from, Fragment to, int FragmentId, String Tag) {
        if (mFragmentContent != to) {
            mFragmentContent = to;
            FragmentTransaction transaction = fragmentManager.beginTransaction();
//                    .setCustomAnimations(  去掉动画
//                            android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            if (!to.isAdded()) {    // 先判断是否被add过
                System.out.println("==========>add new "+to.getClass().toString());
                transaction.addToBackStack(null);
                transaction.hide(from).add(FragmentId, to, Tag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
