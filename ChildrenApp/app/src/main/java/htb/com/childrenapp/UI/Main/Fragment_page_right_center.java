package htb.com.childrenapp.UI.Main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import htb.com.childrenapp.Base.adapter.teacherItemAdapter;
import htb.com.childrenapp.CAApplication;
import htb.com.childrenapp.Core.CoreManager;
import htb.com.childrenapp.Core.User.TeacherInfo;
import htb.com.childrenapp.Core.User.UserCore;
import htb.com.childrenapp.Core.User.UserType;
import htb.com.childrenapp.Framework.widget.LoadingLayout;
import htb.com.childrenapp.Framework.widget.pullToRefresh.PullToRefreshView;
import htb.com.childrenapp.R;
import htb.com.childrenapp.UI.ItemListInfo.ui_teacher_info;

/**
 * Created by weidong_wu on 15/11/18.
 * 邮箱:wwdhao163@163.com
 */
public class fragment_page_right_center extends Fragment {

    public   static final String KeyTag = "FRAGMENT_PAGE_RIGHT_CENTER";
    private Bundle bundle;
    private GridView teachers_gridView;
    private LoadingLayout container_loading;

    private PullToRefreshView pullToRefreshView;

    View view = null;
    public static fragment_page_right_center newInstance(){
        fragment_page_right_center instance = new fragment_page_right_center();
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
        container_loading = (LoadingLayout)view.findViewById(R.id.container_loading);
        pullToRefreshView = (PullToRefreshView)view.findViewById(R.id.pull_to_refresh);
        if (CAApplication.m_currentRoleType == UserType.UserTypeEnum.DIRECTOR || CAApplication.m_currentRoleType == UserType.UserTypeEnum.TEACHER){
            teachers_gridView = (GridView) view.findViewById(R.id.teachers_gridView);
            for (int idx = 0; idx < 15; idx++) {
                TeacherInfo teacherInfo = new TeacherInfo();
                teacherInfo.setTeacherName("测试老师");
                userCore.addTeacher(teacherInfo);
            }
            teacherItemAdapter m_teacherItemAdaper = new teacherItemAdapter(getContext(), userCore.getTeacherList());
            teachers_gridView.setAdapter(m_teacherItemAdaper);
            //container_loading.setVisibility(View.VISIBLE);
            //container_loading.showLoading();
            pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    pullToRefreshView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pullToRefreshView.setRefreshing(false);
                        }
                    },2000);
                }
            });
            teachers_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0){

                    }else{
                        Intent intent = new Intent();
                        intent.putExtra(ui_teacher_info.TEACHER_POS_KEY, position);
                        intent.setClass(getContext(), ui_teacher_info.class);
                        startActivity(intent);
                    }
                }
            });
        }else{
            //家长端的处理
        }
    }


    //=====================================================

    //=====================================================
}
