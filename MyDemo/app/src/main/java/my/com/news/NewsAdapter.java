package my.com.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/18.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private int resource;

    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        NewsHodler hodler = null;
        if( convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resource, null);
            hodler = new NewsHodler();
            hodler.tv = (TextView) convertView.findViewById(R.id.news_item_text);
            convertView.setTag(hodler);
        }else{
            hodler = (NewsHodler) convertView.getTag();
        }
        hodler.tv.setText(news.getTitle());
        return convertView;
    }

    class NewsHodler{
        TextView tv;
    }

}
