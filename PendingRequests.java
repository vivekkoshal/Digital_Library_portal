import Includes.*;

public class PendingRequests {
    private int length = 0;
    private Node<RequestData> front;
    private Node<RequestData> back;

    public boolean insert(Node<RequestData> insnode) {
      
        if(length == 0){
            front = insnode;
            back = insnode;
            back.next = null;
            front.previous = null;
            length++;
            return true;
        }

        back.next = insnode;
        insnode.previous = back;
        back = insnode;
        insnode.next = null;
        length++;
        return true;
    }

    public boolean delete(Node<RequestData> delnode) {
    
        Node<RequestData> temp = front;
        while(temp != null){
            if(temp == delnode){
                break;
            }
            temp = temp.next;
        }
//case 0: if delnode not found in the LL:
        if(temp == null){
            System.out.println("delnode is not in PendingRequests");
            return false;
        }

        //case :
        if(length== 0){
            return false;
        }

        //case1: temp se pehle node h hi nhi to general case ki 'temp.previous.next' command me dikkat aaegi
        if(temp == front){
            front= temp.next;
            front.previous = null;
            length --;
            return true;
        }

        //case2: temp ke aage node nhi h to general case ki 'temp.next.previous' command me dikkat aaegi
        if(temp.next == null){
            back = temp.previous;
            back.next = null;
            length --;
            return true;
        }

        //case3(general): agar temp ke aage peeche dono jagah node hai
        temp.previous.next = temp.next;
        temp.next.previous = temp.previous;
        temp.next= null;
        temp.previous = null;
//        Node<RequestData>  prev = temp.previous;
//        Node<RequestData>  next = temp.next;
//
//        prev.next = next;
//        next.previous = prev;

        length--;

        return true;
    }

    public Node<RequestData> find(int ISBN) {
        /*
         * Your code here.
         */
        Node<RequestData> nrd = null;

        Node<RequestData> temp = front;
        while(temp!=back.next){
            if(temp.data.ISBN == ISBN){
                nrd = temp;
                break;
            }
            temp = temp.next;
        }
        return nrd;
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
