package htb.com.childrenapp.UI.Login;

import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import java.util.HashMap;

import htb.com.childrenapp.Base.BaseActivity;
import htb.com.childrenapp.Core.Login.RegisterResponse;
import htb.com.childrenapp.Core.Port;
import htb.com.childrenapp.Framework.common.MD5;
import htb.com.childrenapp.Framework.common.Utils;
import htb.com.childrenapp.Framework.http.CAHttpManager;
import htb.com.childrenapp.Framework.http.IProtocolEnt;
import htb.com.childrenapp.R;

/**
 * Created by weidong_wu on 15/11/15.
 * 邮箱:wwdhao163@163.com
 */
public class layout_regitster {

    private EditText accountEdittext,pwdEdittext;
    private Button   btn_user_register;
    private Spinner roleSpinner;
    private BaseActivity activity;
    private int RoleID = 1;
    private FrameLayout frameLayout;
    private Handler handler;
    public layout_regitster(BaseActivity activity,FrameLayout frameLayout,View view,Handler handler){
        this.activity = activity;
        this.frameLayout = frameLayout;
        this.handler = handler;
        initUI(view);
    }

    /**
     * 初始化UI布局
     * @param view
     */
    private void initUI(View view){
        ButtonLinstener buttonLinstener = new ButtonLinstener();
        accountEdittext = (EditText)view.findViewById(R.id.accountEdittext);
        pwdEdittext = (EditText)view.findViewById(R.id.pwdEdittext);
        roleSpinner = (Spinner)view.findViewById(R.id.roleSpinner);
        roleSpinner.setSelection(0);
        roleSpinner.setOnItemSelectedListener(new SprinnerItemLinstener());
        btn_user_register = (Button)view.findViewById(R.id.btn_user_register);
        btn_user_register.setOnClickListener(buttonLinstener);
    }


    private class ButtonLinstener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_user_register){
                String userName = accountEdittext.getText().toString();
                String password = pwdEdittext.getText().toString();

                if (Utils.isNetworkConnected(activity)==false){
                    activity.ToastMessage("设备网络未连接");
                    return;
                }

                if (CheckStringFormat(userName,password)){
                    System.out.println(userName + "-" + password + "-" + RoleID);
                    activity.checkKeyboard();
                    HashMap<String, String> ParmasMap = new HashMap<String, String>();
                    ParmasMap.put("phone", userName);
                    MD5 md5 = new MD5();
                    String md5Psw = md5.GetMD5Code(password);
                    ParmasMap.put("password", md5Psw);
                    ParmasMap.put("role", String.valueOf(RoleID));
                    IProtocolEnt registerProtocol = new IProtocolEnt();
                    registerProtocol.setURL(Port.RegisterUrl);
                    registerProtocol.setParamsMap(ParmasMap);
                    RegisterResponse registerResponse = new RegisterResponse();
                    registerResponse.setHandler(handler);
                    registerResponse.setUserName(userName);
                    registerResponse.setPassword(md5Psw);
                    CAHttpManager.instance().sendPost(registerProtocol, registerResponse);
                    frameLayout.addView(activity.showLoadingDialog(),activity.ShowLoadId);
                }

            }
        }
    }

    private class SprinnerItemLinstener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            RoleID = position+1;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    /**
     * @note 检测信息是否完整
     * @return
     */
    private boolean CheckStringFormat(String userName,String psw){

        boolean IsRet = true;

        if (userName==null || userName.compareTo("")==0){
            this.activity.ToastMessage("请填手机号!");
            IsRet = false;
            return IsRet;
        }

        if ((psw == null || psw.compareTo("")==0)){
            this.activity.ToastMessage("请设置密码!");
            IsRet = false;
            return IsRet;
        }

        if (Utils.isMobileNO(userName)==false){
            this.activity.ToastMessage("手机号码有误!");
            IsRet = false;
            return IsRet;
        }

        if (psw.length()<6){
            this.activity.ToastMessage("密码长度能小于6位!");
            IsRet = false;
            return IsRet;
        }

        return IsRet;
    }
}
