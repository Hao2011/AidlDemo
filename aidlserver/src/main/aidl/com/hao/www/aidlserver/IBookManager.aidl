// IBookManager.aidl
package com.hao.www.aidlserver;

// Declare any non-default types here with import statements
import com.hao.www.aidlserver.Book;
interface IBookManager {
    List<Book> getBooks();

}
