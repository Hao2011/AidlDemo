package com.hao.www.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class RemoteService extends Service {

    private MyIBinder myIBinder;
    private List<Book> books = new ArrayList<>();
    public RemoteService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        myIBinder = new MyIBinder();
        return myIBinder;
    }
    public void addBook(){
        for (int i = 0; i < 10; i++) {
            Book book = new Book("数学" + i, +i);
            books.add(book);
        }
    }
    class MyIBinder extends IBookManager.Stub{

        @Override
        public List<Book> getBooks() throws RemoteException {
            addBook();
            return books;
        }
    }
}
