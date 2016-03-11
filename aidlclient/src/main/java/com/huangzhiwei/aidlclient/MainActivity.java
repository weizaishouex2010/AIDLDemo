package com.huangzhiwei.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.huangzhiwei.aidl.ITestAidl;
import com.huangzhiwei.aidl.Person;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdNum1;
    private EditText mEdNum2;
    private EditText mEdRes;

    private Button mBtnAdd;

    ITestAidl iTestAild;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iTestAild = ITestAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //回收
            iTestAild = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        bindService();
    }

    private void initView() {
        mEdNum1 = (EditText) findViewById(R.id.et_num1);
        mEdNum2 = (EditText) findViewById(R.id.et_num2);
        mEdRes = (EditText) findViewById(R.id.et_res);

        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

//        int num1 =  Integer.parseInt(mEdNum1.getText().toString());
//        int num2 = Integer.parseInt(mEdNum2.getText().toString());
//        try {
//            //调用远程的服务
//            int res = iTestAild.add(num1,num2);
//            mEdRes.setText(res+"");
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            mEdRes.setText("Error");
//        }
        try {
            List<Person> persons = iTestAild.add(new Person("ABC",21));

            Log.d("Persons",persons.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindService() {
        //获取到服务端
        Intent intent = new Intent();
        //显示绑定服务
        intent.setComponent(new ComponentName("com.huangzhiwei.aidl","com.huangzhiwei.aidl.IRemoteService"));
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
