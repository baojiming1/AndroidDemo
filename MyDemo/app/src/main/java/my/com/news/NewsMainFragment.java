package my.com.news;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/18.
 */
public class NewsMainFragment extends Fragment implements AdapterView.OnItemClickListener {

    private View view;

    private List<News> data;

    private NewsAdapter adapter;

    private Boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_item_main, container, false);
        ListView lv = (ListView) view.findViewById(R.id.news_listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        return view;
    }

    //当活动创建的时候，我们要先判断当前屏幕是否支持2屏
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isTwoPane = getActivity().findViewById(R.id.news_content_layout) != null;
        if( isTwoPane ){
            isTwoPane = true; // 可以找到news_content_layout布局时，为双页模式
        }else{
            isTwoPane = false; // 找不到news_content_layout布局时，为单页模式
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news  = data.get(position);
        if( isTwoPane ){                    //双页模式 则刷新NewsContentFragment中的内容
            NewsContentFragment newsContentFragment = (my.com.news.NewsContentFragment) getActivity().getFragmentManager().findFragmentById(R.id.news_content_fragment);

            newsContentFragment.refresh(news.getTitle(), news.getContent());
        }else{                              // 如果是单页模式，则直接启动NewsContentActivity

            NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
        }

    }

    //初始化数据方法
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        data = getNews();
        adapter = new NewsAdapter(getActivity(), R.layout.news_item, data);
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("Succeed in College as a Learning Disabled Student");
        news1.setContent("College freshmen will soon learn to live with a"+
                "roommate, adjust to a new social scene and survive less-than-stellar"+
        "dining hall food. Students with learning disabilities will face these"+
        "transitions while also grappling with a few more hurdles.");
        newsList.add(news1);
        News news2 = new News();
        news2.setTitle("Google Android exec poached by China's Xiaomi");
        news2.setContent("China's Xiaomi has poached a key Google executive"+
                "involved in the tech giant's Android phones, in a move seen as a coup"+
        "for the rapidly growing Chinese smartphone maker.");
        newsList.add(news2);
        return newsList;
    }
}
