package dataStructure;

public class DoubleLinkedListCircle<T>
{
    private NodeDLL<T> head = null, tail = null;
    private int NodeCounter = 0;

    @Override
    public String toString()
    {
        NodeDLL<T> p;
        String s = "";
        int marchCounter = 0;
        for(p = head; marchCounter != NodeCounter ; p = p.next)
        {
            s += p.info.toString() + " ";
            marchCounter++;
        }
        return s;
    }

    public int getNodeCounter()
    {
        return this.NodeCounter;
    }

    public int getListsize()
    {
        return this.NodeCounter;
    }

    public void clear()
    {
        head = tail = null;
    }

    public boolean isEmpty()
    {
        return head == null && tail == null;
    }

    public NodeDLL<T> getHead()
    {
        return this.head;
    }

    public NodeDLL<T> getTail()
    {
        return this.tail;
    }

    public void addToHead(T el)
    {
        if(isEmpty())
        {
            head = new NodeDLL(null,el,null);
            tail = head;
            head.next = tail;
            head.prev = tail;
        }
        else
        {
            head = new NodeDLL(tail, el, head);
            head.next.prev = head;
            tail.next = head;
        }
        NodeCounter++;
    }

    public void addToTail(T element)
    {
        if(isEmpty())
        {
            addToHead(element);
        }
        else if (head == tail)
        {
            tail.next = new NodeDLL(head,element,head);
            tail = tail.next;
            head.prev = tail;
        }
        else
        {
            tail.next = new NodeDLL(tail,element,head);
            tail = tail.next;
            head.prev = tail;
        }
        NodeCounter++;
    }

    public T deleteFromTail()
    {
        if(isEmpty()) {return null;}
        NodeDLL<T> buffer = tail;
        T el = buffer.info;

        if(head == tail)
        {
            head = tail = null;
        }
        else
        {
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }
        NodeCounter--;
        return el;
    }

    public T deleteFromHead()
    {
        if(isEmpty()) {return null;}
        NodeDLL<T> buffer = head;
        T el = buffer.info;
        if(head == tail){head = tail = null;}
        else
        {
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }
        NodeCounter--;
        return el;
    }

    public int delete(T element)
    {
        if(isEmpty()) {return -999;}
        NodeDLL<T> marcher = head;
        if(head == tail)
        {
            if(element == head.info)
            {
                deleteFromHead();
                return 1;
            }
            else
            {
                return -999;
            }
        }
        if(head.info == element)
        {
            deleteFromHead();
            return 1;
        }
        if(tail.info == element)
        {
            deleteFromTail();
            return 1;
        }
        int marcherCounter = 0;
        while(marcherCounter != NodeCounter && marcher.info != element)
        {
            marcher = marcher.next;
            marcherCounter++;
        }
        if(marcherCounter >= NodeCounter) {return -999;}
        marcher.prev.next = marcher.next;
        marcher.next.prev = marcher.prev;
        NodeCounter--;
        return 1;
    }

    public NodeDLL find(T element)
    {
        NodeDLL<T> marcher;
        marcher = head;
        int marcherCounter = 0;
        while(marcherCounter != NodeCounter && ((Comparable)marcher.info).compareTo(element) != 0)
        {
            marcher = marcher.next;
            marcherCounter++;
        }
        if(marcherCounter == NodeCounter) {return null;}
        return marcher;
    }

}
