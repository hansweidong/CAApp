package htb.com.childrenapp.Base;

import android.database.DataSetObserver;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import htb.com.childrenapp.UI.Main.*;

/**
 * Created by weidong_wu on 15/11/19.
 * 邮箱:wwdhao163@163.com
 */
public class CAFragmentAdapter extends FragmentPagerAdapter {

    private String[] FragmentList;

    public CAFragmentAdapter(FragmentManager fm, String[] FragmentList) {
        super(fm);
        this.FragmentList = FragmentList;
    }

    @Override
    public Fragment getItem(int position) {
//        if (position == 0) {
//            return new Fragment_page_left();
//        } else if (position == 1) {
//            return new Fragment_page_left_center();
//        } else if (position == 2) {
//            return new Fragment_page_right_center();
//        } else if (position == 3) {
//            return new Fragment_page_right();
//        }
        Fragment fragment = new Fragment();
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return FragmentList.length;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        super.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        super.unregisterDataSetObserver(observer);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public float getPageWidth(int position) {
        return super.getPageWidth(position);
    }
}
