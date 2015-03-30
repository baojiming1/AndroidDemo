package my.com.chat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import my.com.R;

/**
 * Created by 鲍建明 on 2015/3/16.
 */
public class ChatActivity extends Activity {

    private ListView lv;
    private Button send;
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.chat_activity_main);
        lv = (ListView) findViewById(R.id.chat_listView);

        final List<ChatMsg> data = init();
        final ChatAdapter adapter = new ChatAdapter(this, R.layout.chat_activity_item, data );
        lv.setAdapter(adapter);

        send = (Button) findViewById(R.id.chat_send);
        edit = (EditText) findViewById(R.id.chat_editText);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edit.getText().toString();
                if( !"".equals(msg) ){
                    ChatMsg m = new ChatMsg();
                    m.setType(ChatMsg.SEND);
                    m.setMsg(msg);
                    data.add(m);
                    adapter.notifyDataSetChanged();
                    lv.setSelection(data.size());
                    edit.setText("");
                }
            }
        });
    }

    public List<ChatMsg> init(){
        List<ChatMsg> data  = new ArrayList<>();
        for (int i = 0 ; i <  5; i++){
            ChatMsg msg = new ChatMsg();
            msg.setMsg("测试测试水水水水" + i);
            if( i % 2 != 0){
                msg.setType(ChatMsg.SEND);
            }else {
                msg.setType(ChatMsg.RECEIVED);
            }
            data.add(msg);
        }
        return data;
    }


    class ChatAdapter extends ArrayAdapter<ChatMsg>{

        private int resource;
        public ChatAdapter(Context context, int resource, List<ChatMsg> objects) {
            super(context, resource, objects);
            this.resource = resource;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ChatMsg msg = getItem(position);
            ChatHodler hodler = null;
            if( convertView == null){               //第一次
                hodler = new ChatHodler();
                convertView = LayoutInflater.from(getContext()).inflate(this.resource, null);
                hodler.left = (LinearLayout) convertView.findViewById(R.id.chat_left);
                hodler.leftText = (TextView) convertView.findViewById(R.id.chat_left_text);

                hodler.right = (LinearLayout) convertView.findViewById(R.id.chat_right);
                hodler.rightText = (TextView) convertView.findViewById(R.id.chat_right_text);
                convertView.setTag(hodler);
            }else{
                hodler = (ChatHodler) convertView.getTag();
            }

            if( msg.getType() == ChatMsg.SEND){                 //发送

                hodler.left.setVisibility(View.GONE);
                hodler.right.setVisibility(View.VISIBLE);
                hodler.rightText.setText(msg.getMsg());

            }else if( msg.getType() == ChatMsg.RECEIVED){       //接收
                hodler.right.setVisibility(View.GONE);
                hodler.left.setVisibility(View.VISIBLE);
                hodler.leftText.setText(msg.getMsg());
            }else{                                              //其他全部隐藏
                hodler.right.setVisibility(View.GONE);
                hodler.left.setVisibility(View.GONE);
            }
            return convertView;
        }
    }

    class ChatHodler{
        LinearLayout left;  TextView leftText;
        LinearLayout right; TextView rightText;
    }
}
