import java.util.*;

import Includes.*;

public class LibraryStorage {
    // HashMap
    // process return
    private HashMap<Integer, BookData> storage;
    private RequestQueue rqQueue;
    private PendingRequests prLinkedList;

    public LibraryStorage() {
        storage = new HashMap<Integer, BookData>();
        for (int i=100001; i<100011; i++) {
            BookData book = new BookData();
            MyDate dateor = new MyDate();
            dateor.month = 0;
            dateor.year = 0;
            book.BorrowedStatus = false;
            book.BorrowedByUserID = 0;
            book.ISBN = i;
            book.Publisher = "publisher";
            book.Author = "author";
            book.DateOfReturn = dateor;
            storage.put(i, book);
        }

        rqQueue = new RequestQueue();
        prLinkedList = new PendingRequests();
    }

    public void push(int ISBN, int UserID) {
        rqQueue.push(ISBN, UserID);
        return;
    }

    public boolean processQueue() {
        int tity = rqQueue.getFront().ISBN;
        int butt = rqQueue.getFront().UserID;

//        case0: book requested by uder is not in library: user to mc hota h
        if(storage.get(tity) == null){
            System.out.println("mc jaake amazon se kharidle nhi h hmare pas ye \uD83E\uDD2C");
            rqQueue.pop();
            return false;
        }

//        case1:
        if(storage.get(tity).BorrowedStatus == false){

            rqQueue.pop();

            //updating storage and book details:
            storage.get(tity).BorrowedStatus = true;
            storage.get(tity).BorrowedByUserID = butt;
            storage.get(tity).DateOfReturn.month++;         //date me ek baar bt h, ye to pata h ki issued date se ek mahine baad wapis deni h, but date of issue  kaise pata kare??
            storage.get(tity).DateOfReturn.year++;          //date me ek baar bt h, ye to pata h ki issued date se ek mahine baad wapis deni h, but date of issue  kaise pata kare??

            return true;
        }

//        case2:
        if(storage.get(tity).BorrowedStatus == true){

            Node<RequestData> node = new Node<>();
            node.data = rqQueue.getFront();
            prLinkedList.insert(node);                 //naya node bana k pending me daal diya
            rqQueue.pop();                              // prRequests me se hata diya pehla node

            //updating storage and book details:
                    // no change bcoz book hasn't been borrowed


            return false;
        }


        return false;
    }

    public boolean processReturn(BookData book) {          // I have assumed this takes BookData object as argument, can also work with ISBN perhaps

//        case1: not in pending requests:
        if(prLinkedList.find(book.ISBN) == null){

            //updating storage and book details:
            storage.get(book.ISBN).BorrowedStatus = false;
            storage.get(book.ISBN).DateOfReturn.year = 0;
            storage.get(book.ISBN).DateOfReturn.month = 0;
            storage.get(book.ISBN).BorrowedByUserID = 0;
        }

//        case2: yes it is in pending requests:
        else{
            Node<RequestData> node = prLinkedList.find(book.ISBN);
            prLinkedList.delete(node);

            //updating storage and book details:
            storage.get(book.ISBN).BorrowedStatus = true;
            storage.get(book.ISBN).DateOfReturn.month++;        // iss baar bt nhi h bcoz date of return jo pehle thi vhi iss baar ki date of issue h
            storage.get(book.ISBN).DateOfReturn.year++;     //shyd year change nhi krna tha
            storage.get(book.ISBN).BorrowedByUserID = node.data.UserID;
        }




        return false;
    }

    public String rqQueueToString(){
        return rqQueue.toString();
    }

    public String prLinkedListToString(){
        return prLinkedList.toString();
    }
    
}
