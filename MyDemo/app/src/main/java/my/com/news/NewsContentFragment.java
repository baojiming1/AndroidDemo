package my.com.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/18.
 */
public class NewsContentFragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.news_content_frag,container, false );
        return view;
    }

    //刷新内部体中的数据
    public void refresh(String newTitle, String newContent){
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.news_content_lineLayout);
        TextView tv1 = (TextView) layout.findViewById(R.id.new_content_title);
        TextView tv2 = (TextView) layout.findViewById(R.id.new_content_content);
        tv1.setText(newTitle);
        tv2.setText(newContent);
        layout.setVisibility(View.VISIBLE);
    }
}
