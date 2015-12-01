package htb.com.childrenapp.UI.Main;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import htb.com.childrenapp.Base.BaseFragment;
import htb.com.childrenapp.Framework.common.Utils;
import htb.com.childrenapp.R;

/**
 * Created by Administrator on 2015/11/30.
 */
public class ui_fix_userinfo extends BaseFragment {

    public static final String OPERATOR_KEY = "OPERATOR_KEY";
    public static final String FIX_USERNAME_KEY = "TAG_FIX_USERNAME";
    public static final String FIX_USERPHONENUMBER_KEY = "TAG_FIX_PHONENUMBER";
    public static final String FIX_USERPASSWORD_KEY = "TAG_FIX_PASSWORD";

    private LinearLayout btn_back_common,btn_yes_common;
    private TextView commond_title_txt;
    private EditText fix_info_edt;
    private String str_oprt;
    @Override
    public void init() {
        setContentView(R.layout.ui_fix_userinfo);
        InitUI();
    }

    private void InitUI(){
        isCanDoubleClick = false;
        btn_back_common = (LinearLayout)findViewById(R.id.btn_back_common);
        btn_yes_common = (LinearLayout)findViewById(R.id.btn_yes_common);
        fix_info_edt = (EditText)findViewById(R.id.fix_info_edt);
        commond_title_txt = (TextView)findViewById(R.id.commond_title_txt);
        str_oprt = bundle.getString(OPERATOR_KEY);
        if (str_oprt.equals(FIX_USERNAME_KEY)){
            commond_title_txt.setText("修改用户名");
        }else if (str_oprt.equals(FIX_USERPHONENUMBER_KEY)){
            commond_title_txt.setText("修改手机号");
        }else if (str_oprt.equals(FIX_USERPASSWORD_KEY)){
            commond_title_txt.setText("修改密码");
        }
        btnClickedListener m_btnClickListener = new btnClickedListener();


        btn_yes_common.setOnClickListener(m_btnClickListener);
        btn_back_common.setOnClickListener(m_btnClickListener);
    }

    private class btnClickedListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.btn_yes_common) {
                if (Utils.isNetworkConnected(getContext())==false) {
                    ToastMessage("网络未连接");
                    return;
                }
                String edtStr = fix_info_edt.getText().toString();
                if (Utils.CheckEditbox(edtStr)==false){
                    ToastMessage("请输入信息");
                    return;
                }
                if (str_oprt.equals(FIX_USERNAME_KEY)) {

                } else if (str_oprt.equals(FIX_USERPHONENUMBER_KEY)) {
                    if (Utils.isMobileNO(edtStr)==false){
                        ToastMessage("请输入正确的电话号码");
                        return;
                    }
                } else if (str_oprt.equals(FIX_USERPASSWORD_KEY)) {
                    if (str_oprt.length()<6){
                        ToastMessage("密码长度需6位以上");
                        return;
                    }
                }
            }else if (v.getId()==R.id.btn_back_common){
                finish();
            }
        }
    }
}
