public class LinkedListKReverse {
    public static class Node {
        int val;
        Node next;
        public Node (int val, Node next) {
            this.val=val;
            this.next=next;
        }
    }

    public static void main(String args[]) {
        int[] list = new int[]{1,2,3,4,5,6,7,8,9,10, 11, 12, 13, 14};
        Node head = createLinkedList(list, 0);
        int k=6;
        head = reverseKLinkList(head, k);
        while(head!=null) {
            System.out.print(head.val + " ");
            head=head.next;
        }
    }

    static Node createLinkedList(int[]list, int idx) {
        if(list.length==idx)
            return null;

        Node head = new Node(list[idx], createLinkedList(list,idx+1));
        return head;
    }

    static Node reverseKLinkList(Node head, int k) {
        Node st = head;
        Node end = head;
        int i=0;
        while(st!=null && end!=null) {
            if(i%2==0) {
                end = traverseK(st, k);
                if(end==null)
                    return head;
                st = end.next;
            }
            else {
                Node tempSt=st;
                Node tempEnd = traverseK(st, k);
                if(tempEnd!=null) {
                    st=tempEnd.next;
                }
                tempEnd.next=null;
                end.next = reverseList(tempSt, null);
                tempSt.next=st;
                end=tempSt;
            }
            i++;
        }
        return head;
    }

    static Node traverseK(Node head, int k) {
        if(head==null)
            return head;
        for(int i=0;i<k-1;i++) {
            if(head.next!=null) {
                head=head.next;
            }
        }
        return head;
    }

    static Node reverseList(Node head, Node next) {
        if(head.next==null) {
            head.next=next;
            return head;
        }

        Node temp = head.next;
        head.next=next;
        return reverseList(temp, head);
    }
}
