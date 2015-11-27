package htb.com.childrenapp.UI.Main;


import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import htb.com.childrenapp.Base.BaseActivity;
import htb.com.childrenapp.Base.CAFragmentAdapter;
import htb.com.childrenapp.Core.CoreManager;
import htb.com.childrenapp.Core.User.UserCore;
import htb.com.childrenapp.Core.User.UserInfo;
import htb.com.childrenapp.R;

/**
 * Created by weidong_wu on 15/11/13.
 * 邮箱:wwdhao163@163.com
 */
public class ui_home extends BaseActivity {

    private String[][] RadioButton_Info = {
            {},
            {"师资", "班级", "动态", "我"},
            {"消息", "小朋友", "家长", "我"},
            {"消息", "宝贝", "老师", "我"}
    };

    private RadioButton rd_Objs[] = new RadioButton[4];

    private UserCore userCore;

    private Fragment m_CurrentFragment;

    @Override
    public void init() {
        setContentView(R.layout.ui_home);
        userCore = (UserCore) CoreManager.instance().getCore(UserCore.class);
        initRadioButtonTxtInfo();
    }

    /**
     * 设置tab的文字信息
     */
    private void initRadioButtonTxtInfo() {
        rd_Objs[0] = (RadioButton) findViewById(R.id.rb_1);
        rd_Objs[0].setChecked(true);
        rd_Objs[1] = (RadioButton) findViewById(R.id.rb_2);
        rd_Objs[2] = (RadioButton) findViewById(R.id.rb_3);
        rd_Objs[3] = (RadioButton) findViewById(R.id.rb_4);
        //UserInfo userInfo = userCore.getUserInfo();
        //if(userInfo == null) {
        //UserInfo    userInfo = (UserInfo)userCore.getUserInfoObj(getContext());
        //}
        radioButtonListener m_radioBtnListener = new radioButtonListener();
        int roleId = 1;//userInfo.getRole();
        for (int idx = 0; idx < 4; idx++) {
            String info = RadioButton_Info[roleId][idx];
            rd_Objs[idx].setText(info);
            rd_Objs[idx].setOnCheckedChangeListener(m_radioBtnListener);
        }

        Fragment_page_left fragment_page_left = Fragment_page_left.newInstance();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container_fragment_content, fragment_page_left, Fragment_page_left.KeyTag);
        transaction.addToBackStack(null);
        transaction.commit();
        m_CurrentFragment = fragment_page_left;
    }


    /**
     * radiobutton 的监听事件
     */
    private class radioButtonListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int idx = buttonView.getId();
            Fragment toShow;
            if (isChecked) {
                Fragment fragment_1 = fragmentManager.findFragmentByTag(Fragment_page_left.KeyTag);

                Fragment fragment_2 = fragmentManager.findFragmentByTag(Fragment_page_left_center.KeyTag);

                Fragment fragment_3 = fragmentManager.findFragmentByTag(Fragment_page_right_center.KeyTag);

                Fragment fragment_4 = fragmentManager.findFragmentByTag(Fragment_page_right.KeyTag);

                if (idx == R.id.rb_1) {
                    toShow = (fragment_1 == null) ? Fragment_page_left.newInstance() : fragment_1;
                    switchContent(m_CurrentFragment, toShow, R.id.container_fragment_content, Fragment_page_left.KeyTag);
                    m_CurrentFragment = toShow;
                } else if (idx == R.id.rb_2) {
                    toShow = (fragment_2 == null) ? Fragment_page_left_center.newInstance() : fragment_2;
                    switchContent(m_CurrentFragment, toShow, R.id.container_fragment_content, Fragment_page_left_center.KeyTag);
                    m_CurrentFragment = toShow;
                } else if (idx == R.id.rb_3) {
                    toShow = (fragment_3 == null) ? Fragment_page_right_center.newInstance() : fragment_3;
                    switchContent(m_CurrentFragment, toShow, R.id.container_fragment_content, Fragment_page_right_center.KeyTag);
                    m_CurrentFragment = toShow;
                } else if (idx == R.id.rb_4) {
                    toShow = (fragment_4 == null) ? Fragment_page_right.newInstance() : fragment_4;
                    switchContent(m_CurrentFragment, toShow, R.id.container_fragment_content, Fragment_page_right.KeyTag);
                    m_CurrentFragment = toShow;
                }

            }
        }
    }


}
