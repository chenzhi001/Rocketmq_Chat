package com.chen.rocketmq_chat.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chen.rocketmq_chat.R;
import com.chen.rocketmq_chat.mq.Productor;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * Created by ${BaLe} on 2018/7/6.
 */
public class HomeActivity extends Activity implements Productor.onSenResoultlistner {

    private EditText editText;
    private Button btnSend;
    private Productor productor;
    private EditText ipet;
    private String TOPIC = "BenchmarkTest";
    private String TAG = "Animal";
    private String key = "AnimalId";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        editText = findViewById(R.id.et_msgcontent);
        btnSend = findViewById(R.id.btn_send);
        ipet = findViewById(R.id.et_ip);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString().trim();
                String ip = ipet.getText().toString().trim();
                try {
                    if (productor == null) {
                        productor = new Productor(ip,HomeActivity.this);
                    }
                    productor.sendMesage(msg, TOPIC, key, TAG);
                } catch (MQClientException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onSendSuccess() {

    }

    @Override
    public void onSendFailed() {

    }

    @Override
    public void onSenResult(SendResult sendResult) {

    }
}
