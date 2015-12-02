package htb.com.childrenapp.UI.Main;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import htb.com.childrenapp.R;

/**
 * Created by weidong_wu on 15/11/18.
 * 邮箱:wwdhao163@163.com
 */
public class fragment_page_left_center extends Fragment {

    public   static final String KeyTag = "FRAGMENT_PAGE_LEFT_CENTER";
    private Bundle bundle;

    public static fragment_page_left_center newInstance(){
        fragment_page_left_center instance = new fragment_page_left_center();
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
        View view = inflater.inflate(R.layout.fragmet_home_left,container,false);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(KeyTag,bundle);
    }
}
