package com.huangzhiwei.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangzhiwei on 16/3/11.
 */
public class IRemoteService extends Service{


    private ArrayList<Person> persons;
    /**
     * 当客户端绑定到该服务时
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<Person>();
        return iBinder;
    }


//    private IBinder iBinder = new ITestAidl.Stub(){
//
//
//        @Override
//        public int add(int num1, int num2) throws RemoteException {
//            Log.d("TAG","收到了远程请求输入的参数是"+num1 +"和"+num2);
//
//            return num1+num2;
//        }
//    };
    private IBinder iBinder = new ITestAidl.Stub() {
        @Override
        public List<com.huangzhiwei.aidl.Person> add(com.huangzhiwei.aidl.Person person) throws RemoteException {
            persons.add(person);
            return persons;
        }
    };

}
