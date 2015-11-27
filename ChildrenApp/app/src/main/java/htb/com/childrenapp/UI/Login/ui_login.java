package htb.com.childrenapp.UI.Login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import htb.com.childrenapp.Base.BaseActivity;
import htb.com.childrenapp.Core.CoreManager;
import htb.com.childrenapp.Core.Login.LoginResponse;
import htb.com.childrenapp.Core.Login.RegisterResponse;
import htb.com.childrenapp.Core.Port;
import htb.com.childrenapp.Core.User.UserCore;
import htb.com.childrenapp.Framework.CAAnimation.CAAnimationManager;
import htb.com.childrenapp.Framework.common.MD5;
import htb.com.childrenapp.Framework.common.Utils;
import htb.com.childrenapp.Framework.http.CAHttpManager;
import htb.com.childrenapp.Framework.http.IProtocolEnt;
import htb.com.childrenapp.R;
import htb.com.childrenapp.UI.Main.ui_home;

/**
 * Created by weidong_wu on 15/11/7.
 * 邮箱:wwdhao163@163.com
 */
public class ui_login extends BaseActivity {

    private Button Login_btn;//登陆按钮
    private BtnListener btnListener;
    private LoginResponse loginResponse = null;
    private EditText edit_userName, edit_password;
    private TextView login_title_txt, forget_password;
    private LinearLayout btn_exit, btn_register;
    private FrameLayout frameLayout = null;
    private LinearLayout layoutContainer;
    private ViewParent parent;
    private Handler m_Handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (frameLayout.getChildAt(ShowLoadId) != null)
                frameLayout.removeViewAt(ShowLoadId);
            if (msg.what == Port.ResponseJsonCode_0) {
                UserCore userCore = (UserCore)CoreManager.instance().getCore(UserCore.class);
                userCore.saveUserInfo(getContext());
                Intent intent = new Intent();
                intent.setClass(getContext(), ui_home.class);
                startActivity(intent);
                finish();
            } else if (msg.what == Port.ResponseJsonCode_1002) {
                ToastMessage("用户不存在");
            } else if (msg.what == Port.ResponseJsonCOde_1008) {
                ToastMessage("手机号已被注册");
            } else if (msg.what == Port.ResponseJsonCOde_1001) {
                ToastMessage("token错误");
            } else if (msg.what == Port.ResponseJsonCOde_1003) {
                ToastMessage("创建showId错误");
            } else if (msg.what == Port.ResponseJsonCOde_1004) {
                ToastMessage("没有手机号码");
            } else if (msg.what == Port.ResponseJsonCOde_1005) {
                ToastMessage("密码错误");
            } else if (msg.what == Port.ResponseJsonCOde_1007) {
                ToastMessage("手机格式不正确");
            } else if (msg.what == Port.ResponseFailureCode) {
                ToastMessage("服务器响应失败");
            } else if (msg.what == Port.ResponseJsonCOde_1009) {
                ToastMessage("用户名已被注册");
            } else if (msg.what == Port.ResponseJsonCOde_1010) {
                ToastMessage("验证码错误");
            } else if (msg.what == Port.ResponseRegisterSuccessCode) {
                RegisterResponse registerResponse = (RegisterResponse) msg.obj;
                sendLoginRequest(registerResponse.getUserName(), registerResponse.getPassword());
            }
        }
    };


    @Override
    public void init() {
        setContentView(R.layout.ui_login);

        initUIInfo();
    }

    private void initUIInfo() {
        btnListener = new BtnListener();
        Login_btn = (Button) findViewById(R.id.btn_login);
        Login_btn.setOnClickListener(btnListener);
        frameLayout = (FrameLayout) findViewById(R.id.ui_login_layout_id);
        edit_userName = (EditText) findViewById(R.id.username);
        edit_password = (EditText) findViewById(R.id.password);
        btn_exit = (LinearLayout) findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(btnListener);
        btn_register = (LinearLayout) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(btnListener);
        login_title_txt = (TextView) findViewById(R.id.login_title_txt);
        forget_password = (TextView) findViewById(R.id.forget_password);
        forget_password.setOnClickListener(btnListener);
        layoutContainer = (LinearLayout) findViewById(R.id.LayeroutContainer);
        parent = layoutContainer.getParent();
//        RelativeLayout UI_TopTab = (RelativeLayout)findViewById(R.id.UI_TopTab);
//        parent.bringChildToFront(UI_TopTab);
    }

    public class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_login) {

                if (Utils.isNetworkConnected(getContext()) == false) {
                    ToastMessage("设备网络未连接");
                    return;
                }

                String userName = edit_userName.getText().toString();
                String password = edit_password.getText().toString();

                if (CheckEditbox(userName, password)) {
                    checkKeyboard();
                    MD5 md5 = new MD5();
                    String md5psw = md5.GetMD5Code(password);
                    sendLoginRequest(userName, md5psw);
                } else {
                    ToastMessage("请输入用户信息!");
                }
            } else if (v.getId() == R.id.btn_register) {
                showRegisterUI();
            } else if (v.getId() == R.id.btn_exit) {
                showLoginUI();
            } else if (v.getId() == R.id.forget_password) {
                showForgetPsw();
            }
        }


    }

    /**
     * 发送登陆请求
     *
     * @param userName
     * @param password
     */
    private void sendLoginRequest(String userName, String password) {
        HashMap<String, String> ParmasMap = new HashMap<String, String>();
        ParmasMap.put("phone", userName);
        ParmasMap.put("password", password);
        IProtocolEnt loginProtocol = new IProtocolEnt();
        loginProtocol.setURL(Port.loginUrl);
        loginProtocol.setParamsMap(ParmasMap);
        loginResponse = new LoginResponse();
        loginResponse.setHandler(m_Handler);
        CAHttpManager.instance().sendPost(loginProtocol, loginResponse);
        if (frameLayout == null) {
            return;
        }
        frameLayout.addView(showLoadingDialog(), ShowLoadId);
    }

    /**
     * 判断密码和用户名是否为空
     *
     * @param userName
     * @param password
     * @return
     */
    private boolean CheckEditbox(String userName, String password) {
        boolean isRet = false;
        if (userName == null || userName.compareTo("") == 0 || password == null || password.compareTo("") == 0) {
            isRet = false;
        } else {
            isRet = true;
        }
        return isRet;
    }

    /**
     * 注册界面的显示
     */
    private void showRegisterUI() {
        btn_exit.setVisibility(View.VISIBLE);
        btn_register.setVisibility(View.GONE);
        login_title_txt.setText(getString(R.string.login_register_txt));
        View regist_view = Utils.createView(this, R.layout.ui_register);
        CAAnimationManager.newInstance().UIMoveFromRight(this, layoutContainer, regist_view, R.anim.slide_in_right);
        ((ViewGroup) parent).addView(regist_view, 1);
        new layout_regitster(this, frameLayout, regist_view, m_Handler);
    }

    /**
     * 登陆界面的显示
     */
    private void showLoginUI() {
        btn_exit.setVisibility(View.GONE);
        btn_register.setVisibility(View.VISIBLE);
        login_title_txt.setText(R.string.login_title_txt);
        layoutContainer.setVisibility(View.VISIBLE);
        CAAnimationManager.newInstance().UIMoveToLeft(this, 1, layoutContainer, R.anim.slide_out_left);
    }

    private void showForgetPsw(){
        btn_exit.setVisibility(View.VISIBLE);
        btn_register.setVisibility(View.GONE);
        login_title_txt.setText(getString(R.string.login_forgetPsw_txt));
        View regist_view = Utils.createView(this, R.layout.ui_register);
        CAAnimationManager.newInstance().UIMoveFromRight(this, layoutContainer, regist_view, R.anim.slide_in_right);
        ((ViewGroup) parent).addView(regist_view, 1);
        new layout_findPsw(this, frameLayout, regist_view, m_Handler);
    }
}



