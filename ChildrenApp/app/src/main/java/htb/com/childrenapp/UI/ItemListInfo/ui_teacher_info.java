package htb.com.childrenapp.UI.ItemListInfo;

import htb.com.childrenapp.Base.BaseFragment;
import htb.com.childrenapp.R;

/**
 * Created by Administrator on 2015/12/2.
 */
public class ui_teacher_info extends BaseFragment {
    public static final String TEACHER_POS_KEY = "TEACHER_POS_KEY";
    private int teacher_pos = 0;
    @Override
    public void init() {
        setContentView(R.layout.ui_teacherinfo);
        isCanDoubleClick = false;
    }

}
