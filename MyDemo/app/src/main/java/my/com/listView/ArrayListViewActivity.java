package my.com.listView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/11.
 */
public class ArrayListViewActivity extends Activity {

    private ListView lv ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.listview_main);
        final List<Fuirt> data = init();
        FuirtAdapter fa = new FuirtAdapter(this , R.layout.listview_item, data );
        lv = (ListView) findViewById(R.id.array_list_view);
        lv.setAdapter(fa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fuirt f =  data.get(position);
                Toast.makeText(ArrayListViewActivity.this, f.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public List<Fuirt> init(){
        List<Fuirt> list = new ArrayList<Fuirt>();
        for (int i = 0; i < 10; i++){
            Fuirt f = new Fuirt();
            f.setImageId(R.drawable.gs1);
            f.setText("水果" + i);
            list.add(f);
        }
        return list;
    }


     class FuirtAdapter extends ArrayAdapter<Fuirt>{

        private int resource;

        //这里接受三个参数。1.总布局 2.listView_item的详细布局 3.listView的数据
        public FuirtAdapter(Context context, int resource, List<Fuirt> objects) {
            super(context, resource, objects);
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Fuirt fuirt = getItem(position);
            FuirtViewHodler hodler = null;
            //判断系统有没有将子布局回传给我们
            if(convertView == null){                //第一次，没有创建子布局的时候
                convertView = LayoutInflater.from(getContext()).inflate(resource, null);
                hodler = new FuirtViewHodler();
                hodler.iv = (ImageView) convertView.findViewById(R.id.listview_item_image);
                hodler.tv = (TextView) convertView.findViewById(R.id.listview_item_text);
                convertView.setTag(hodler);            //存入缓存中，等待下次使用
            }else{                                  //系统中已经缓存了该子布局，重复利用，能够节省资源的消耗
                hodler = (FuirtViewHodler) convertView.getTag();
            }
            hodler.iv.setImageResource(fuirt.getImageId());
            hodler.tv.setText(fuirt.getText());
            return convertView;                             //将ListView中其中一个View返回,该View会被系统再次调用时被传入
        }



    }

    class FuirtViewHodler {
        ImageView iv ;
        TextView tv;
    }

    class Fuirt {
        private int imageId;
        private String text;

        Fuirt() {
        }

        Fuirt(int imageId, String text) {
            this.imageId = imageId;
            this.text = text;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getImageId() {
            return imageId;
        }

        public String getText() {
            return text;
        }
    }
}
