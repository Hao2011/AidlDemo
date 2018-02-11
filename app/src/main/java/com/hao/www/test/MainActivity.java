package com.hao.www.test;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hao.www.aidlserver.Book;
import com.hao.www.aidlserver.IBookManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button byId;
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        byId = (Button) findViewById(R.id.button);
        byId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.hao.www.aidlserver", "com.hao.www.aidlserver.RemoteService");
                intent.setComponent(componentName);
                bindService(intent,new MyServiceConnection(), Service.BIND_AUTO_CREATE);
            }
        });

    }
    class MyServiceConnection implements ServiceConnection{

        private static final String TAG = "MyServiceConnection";

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IBookManager iBookManager = IBookManager.Stub.asInterface(service);
            try {
                books = iBookManager.getBooks();
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < books.size(); i++) {
                    sb.append(books.get(i).getName()+":"+books.get(i).getPrice()+",");
                }
                Toast.makeText(MainActivity.this,new String(sb),Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

}
