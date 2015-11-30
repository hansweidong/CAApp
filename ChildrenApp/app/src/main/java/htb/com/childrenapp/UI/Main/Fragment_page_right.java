package htb.com.childrenapp.UI.Main;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import htb.com.childrenapp.CAApplication;
import htb.com.childrenapp.Framework.widget.CircularImage;
import htb.com.childrenapp.R;
import htb.com.childrenapp.UI.Login.ui_login;

/**
 * Created by weidong_wu on 15/11/18.
 * 邮箱:wwdhao163@163.com
 */
public class Fragment_page_right extends Fragment {

    public   static final String KeyTag = "FRAGMENT_PAGE_RIGHT";
    private Bundle bundle;

    private TextView userinfo_userName,userinfo_userName_btn;
    private TextView userinfo_userType_btn,userinfo_userType;
    private TextView userinfo_phoneNumber_btn;
    private TextView userinfo_checkPsw_btn;
    private CircularImage imageHead;

    public static Fragment_page_right newInstance(){
        Fragment_page_right instance = new Fragment_page_right();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((savedInstanceState!=null)&&savedInstanceState.containsKey(KeyTag)){
            bundle = savedInstanceState.getBundle(KeyTag);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.ui_userinfo,container,false);
        init(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(KeyTag,bundle);
    }


    private void init(View view){
        userinfo_userName = (TextView)view.findViewById(R.id.userinfo_username);
        userinfo_userName_btn = (TextView)view.findViewById(R.id.userinfo_username_btn);
        userinfo_userType_btn = (TextView)view.findViewById(R.id.userinfo_usertype_btn);
        userinfo_userType = (TextView)view.findViewById(R.id.userinfo_usertype);
        userinfo_phoneNumber_btn = (TextView)view.findViewById(R.id.userinfo_phoneNumber_btn);
        userinfo_checkPsw_btn = (TextView)view.findViewById(R.id.userinfo_checkPsw_btn);
        imageHead = (CircularImage)view.findViewById(R.id.imageHead);
        BtnClickedListener btnClickedListener = new BtnClickedListener();
        imageHead.setOnClickListener(btnClickedListener);
        ((LinearLayout) view.findViewById(R.id.linear_name)).setOnClickListener(btnClickedListener);
        ((LinearLayout) view.findViewById(R.id.linear_phoneNumber)).setOnClickListener(btnClickedListener);
        ((LinearLayout) view.findViewById(R.id.linear_fixPsw)).setOnClickListener(btnClickedListener);
        ((Button) view.findViewById(R.id.userinfo_exit)).setOnClickListener(btnClickedListener);

    }


    private class BtnClickedListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.linear_name){
                Intent intent = new Intent();
                intent.putExtra(ui_fix_userinfo.OPERATOR_KEY, ui_fix_userinfo.FIX_USERNAME_KEY);
                intent.setClass(getContext(), ui_fix_userinfo.class);
                startActivity(intent);
                //((Activity)getContext()).overridePendingTransition(android.R.anim.slide_in_left, R.anim.slide_in_right);
            }else if (v.getId() == R.id.linear_phoneNumber){
                Intent intent = new Intent();
                intent.putExtra(ui_fix_userinfo.OPERATOR_KEY, ui_fix_userinfo.FIX_USERPHONENUMBER_KEY);
                intent.setClass(getContext(), ui_fix_userinfo.class);
                startActivity(intent);
                //((Activity)getContext()).overridePendingTransition(android.R.anim.slide_in_left,R.anim.slide_in_right);
            }else if (v.getId() == R.id.linear_fixPsw) {
                Intent intent = new Intent();
                intent.putExtra(ui_fix_userinfo.OPERATOR_KEY, ui_fix_userinfo.FIX_USERPASSWORD_KEY);
                intent.setClass(getContext(), ui_fix_userinfo.class);
                startActivity(intent);
                //((Activity)getContext()).overridePendingTransition(android.R.anim.slide_in_left, R.anim.slide_in_right);
            }else if (v.getId() == R.id.userinfo_exit) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ui_login.class);
                startActivity(intent);
                CAApplication.instance().mList.clear();
                ((Activity)(getContext())).finish();
            }else if (v.getId() == R.id.imageHead){

            }
        }
    }
}
