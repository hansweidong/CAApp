package htb.com.childrenapp.Base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import htb.com.childrenapp.Core.User.TeacherInfo;
import htb.com.childrenapp.Framework.widget.CircularImage;
import htb.com.childrenapp.R;

/**
 * Created by Administrator on 2015/11/30.
 */
public class teacherItemAdapter extends BaseAdapter {
    private List<TeacherInfo> teacherInfoList;
    private LayoutInflater inflater;
    public teacherItemAdapter(Context context,List<TeacherInfo> teacherInfoList){
        this.teacherInfoList = teacherInfoList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return teacherInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return teacherInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = null;
        if (convertView == null){
            item = new Item();
            convertView = inflater.inflate(R.layout.ui_item_teachers,null);
            item.circularImage = (CircularImage)convertView.findViewById(R.id.item_teacher_icon);
            item.teacherName = (TextView)convertView.findViewById(R.id.teacher_name);
            convertView.setTag(item);
        }else
            item = (Item)convertView.getTag();
        if (position>0) {
            TeacherInfo teacherInfo = teacherInfoList.get(position);
            item.teacherName.setText(teacherInfo.getTeacherName());
        }else {
            item.circularImage.setImageResource(R.drawable.mobile_live_btn_normal);
            item.teacherName.setText("添加老师");
        }
        return convertView;
    }

    private class Item {
        public CircularImage circularImage;
        public TextView teacherName;
    }
}
