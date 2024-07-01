import Includes.*;

public class RequestQueue {
    private Node<RequestData> front;
    private Node<RequestData> back;
    private int length = 0;

    public RequestData getFront() {
        return this.front.data;
    }

    public int getLength() {
        
        Node<RequestData> temp = front;
        int count = 1;
        while(temp!=back){
            temp = temp.next;
            count++;
        }

        length = count;

        return this.length;
    }

    public void push(int ISBN, int UserID) {
        
        Node<RequestData> node = new Node<>();

        RequestData dataa = new RequestData();
        dataa.ISBN = ISBN;
        dataa.UserID = UserID;
        node.data = dataa;

        if(length==0){
            front = node;
            back = node;
            back.next = null;
            back.previous = null;
            length++;
            return;
        }

        back.next = node;
        node.previous = back;
        back = node;
        node.next = null;

        length++;
        return;
    }

    public void pop() {      // processing needs to be done before popping, 
        
        if(length==0){
            System.out.println("khali h  bhai");
            return;
        }

        Node<RequestData> temp = front;
        front = front.next;
        front.previous = null;
        temp.next = null;

        length--;
        return;
    }

    public String toString(){
        Node<RequestData> temp = front;
        String s = "Length: " + length + "\n";
        while(temp != null){
            s+=temp.data.toString();
            temp = temp.next;
        }
        return s;
    }
}
