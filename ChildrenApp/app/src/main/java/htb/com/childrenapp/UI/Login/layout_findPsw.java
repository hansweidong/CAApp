package htb.com.childrenapp.UI.Login;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import htb.com.childrenapp.Base.BaseFragment;
import htb.com.childrenapp.Framework.common.Utils;
import htb.com.childrenapp.R;

/**
 * 找回密码
 * Created by Administrator on 2015/11/27.
 */
public class layout_findPsw {

    private EditText accountEdittext;
    private Button btn_user_register;
    private BaseFragment activity;
    private Handler handler;

    public layout_findPsw(BaseFragment activity,FrameLayout frameLayout,View view,Handler handler){
        this.activity = activity;
        this.handler = handler;
        init(view);
    }

    private void init(View view){
        ((RelativeLayout)view.findViewById(R.id.pwdRel)).setVisibility(View.GONE);
        ((RelativeLayout)view.findViewById(R.id.role)).setVisibility(View.GONE);
        accountEdittext = (EditText)view.findViewById(R.id.accountEdittext);
        btn_user_register = (Button)view.findViewById(R.id.btn_user_register);
        btn_user_register.setOnClickListener(new BtnClickedListener());
        btn_user_register.setText("确定");
    }

    private class BtnClickedListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_user_register){
                if (Utils.isNetworkConnected(activity)==false){
                    activity.ToastMessage("设备网络未连接");
                    return;
                }

                String userName = accountEdittext.getText().toString();

                if (userName==null || userName.compareTo("")==0){
                    activity.ToastMessage("请填手机号!");
                    return ;
                }


            }
        }
    }
}
