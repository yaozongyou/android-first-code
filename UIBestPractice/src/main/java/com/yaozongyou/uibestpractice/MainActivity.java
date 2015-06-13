package com.yaozongyou.uibestpractice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.EditText;
import android.widget.Button;
import java.util.List;
import java.util.ArrayList;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View;

public class MainActivity extends Activity
{
    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private List<Msg> msgList = new ArrayList<Msg>();

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        initMsgs();
        adapter = new MsgAdapter(MainActivity.this, R.layout.msg_item, msgList);
        inputText = (EditText)findViewById(R.id.input_text);
        send = (Button)findViewById(R.id.send);
        msgListView = (ListView)findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String content = inputText.getText().toString();
                        if (!"".equals(content)) {
                            Msg msg = new Msg(content, Msg.TYPE_SENT);
                            msgList.add(msg);
                            adapter.notifyDataSetChanged();
                            msgListView.setSelection(msgList.size());
                            inputText.setText("");
                        }
                    }
                });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you.", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
