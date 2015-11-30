package htb.com.childrenapp.UI.Main;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import htb.com.childrenapp.Base.adapter.teacherItemAdapter;
import htb.com.childrenapp.CAApplication;
import htb.com.childrenapp.Core.CoreManager;
import htb.com.childrenapp.Core.User.TeacherInfo;
import htb.com.childrenapp.Core.User.UserCore;
import htb.com.childrenapp.Core.User.UserType;
import htb.com.childrenapp.R;

/**
 * Created by weidong_wu on 15/11/18.
 * 邮箱:wwdhao163@163.com
 */
public class Fragment_page_right_center extends Fragment {

    public   static final String KeyTag = "FRAGMENT_PAGE_RIGHT_CENTER";
    private Bundle bundle;
    private GridView teachers_gridView;
    View view = null;
    public static Fragment_page_right_center newInstance(){
        Fragment_page_right_center instance = new Fragment_page_right_center();
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
        if (CAApplication.m_currentRoleType== UserType.UserTypeEnum.DIRECTOR||CAApplication.m_currentRoleType == UserType.UserTypeEnum.TEACHER) {
            view = inflater.inflate(R.layout.ui_teachers, container, false);
        }else{
            //家长端的处理
        }

        initUI(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(KeyTag,bundle);
    }


    private void initUI(View view) {
        UserCore userCore = ((UserCore) CoreManager.instance().getCore(UserCore.class));
        if (CAApplication.m_currentRoleType == UserType.UserTypeEnum.DIRECTOR || CAApplication.m_currentRoleType == UserType.UserTypeEnum.TEACHER){
            teachers_gridView = (GridView) view.findViewById(R.id.teachers_gridView);
            for (int idx = 0; idx < 15; idx++) {
                TeacherInfo teacherInfo = new TeacherInfo();
                teacherInfo.setTeacherName("测试老师");
                userCore.addTeacher(teacherInfo);
            }
            teacherItemAdapter m_teacherItemAdaper = new teacherItemAdapter(getContext(), userCore.getTeacherList());
            teachers_gridView.setAdapter(m_teacherItemAdaper);
        }else{
            //家长端的处理
        }
    }


    //=====================================================

    //=====================================================
}
