public class List 
{
    //Constructors
    List(ListNode initNode)
    {
        head_ = initNode;
        tail_ = initNode;
        count_ = 1;
        initNode.setPrev(null);
        initNode.setNext(null);
    }
    List(String initData)
    {
        head_ = new ListNode(initData);
        tail_ = head_;
        count_ = 1;
    }
    List()
    {
        head_ = null;
        tail_ = null;
        count_ = 0;
    }

    //Member Variables
    private int count_;
    private ListNode head_;
    private ListNode tail_;

    //Accessor Methods
    public boolean isEmpty()
    {
        if (head_ == null) return true;
        else return false;
    }
    public int getCount()
    {
        return count_;
    }
    public ListNode getHead()
    {
        return head_;
    }
    public ListNode getTail()
    {
        return tail_;
    }
    public void printList(int dir)
    {
        if (dir > 0)
        {
            ListNode curr = head_;
            while (curr != null)
            {
                System.out.println(curr.getData());
                curr = curr.getNext();
            }
        }
        else
        {
            ListNode curr = tail_;
            while (curr != null)
            {
                System.out.println(curr.getData());
                curr = curr.getPrev();
            }
        }
    }

    //Mutator Methods
    public void enqueue(ListNode inNode) // Adds node to the end of the list
    {
        //Set pointers for incoming node
        inNode.setPrev(tail_);
        inNode.setNext(null);

        //Adjust pointers of existing node and list
        if (head_ == null) head_ = inNode;
        if (!(tail_ == null)) tail_.setNext(inNode);
        tail_ = inNode;

        count_ += 1;
    }
    public void enqueue(String inData) // Adds new node containing inData to the end of the list
    {
        ListNode inNode = new ListNode(inData);

        //Set pointers for incoming node
        inNode.setPrev(tail_);
        inNode.setNext(null);

        //Adjust pointers of existing node and list
        if (head_ == null) head_ = inNode;
        if (!(tail_ == null)) tail_.setNext(inNode);
        tail_ = inNode;

        count_ += 1;
    }
    public String dequeue() // Removes and returns the data of the node at the beginning of the list
    {
        //Case for empty List
        if (head_ == null)
        {
            System.err.println("\tERR: attempted dequeue from empty list");
            return "";
        }
        
        //Store the head to return
        String prevHead = head_.getData();

        //Adjust pointers of existing node and list
        if (head_.getNext() == null)
        {
            tail_ = null;
        }
        else head_.getNext().setPrev(null);
        head_ = head_.getNext();

        count_ -= 1;

        //Return the dequeued node data
        return prevHead;
    }
    public String pop() // Removes and returns the datat of the node at the end of the list
    {
        //Case for empty List
        if (tail_ == null)
        {
            System.err.println("\tERR: attempted pop from empty list");
            return "";
        }
        
        //Store the tail to return
        String prevTail = tail_.getData();

        //Adjust pointers of existing node and list
        if (tail_.getPrev() == null)
        {
            head_ = null;
        }
        else tail_.getPrev().setNext(null);
        tail_ = tail_.getPrev();

        count_ -= 1;

        //Return the dequeued node data
        return prevTail;
    }
}

class ListNode 
{
    //Constructor
    ListNode(String initData)
    {
        data_ = initData;
        prev_ = null;
        next_ = null;
    }

    //Member Variables
    private String data_;
    private ListNode prev_;
    private ListNode next_;

    //Accessor Methods
    public String getData()
    {
        return data_;
    }
    public ListNode getPrev()
    {
        return prev_;
    }
    public ListNode getNext()
    {
        return next_;
    }

    //Mutator Methods
    public void setData(String data)
    {
        data_ = data;
    }
    public void setPrev(ListNode prev)
    {
        prev_ = prev;
    }
    public void setNext(ListNode next)
    {
        next_ = next;
    }
}