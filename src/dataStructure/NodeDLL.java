package dataStructure;

public class NodeDLL<T>
{
    public T info;
    public NodeDLL<T> next;
    public NodeDLL<T> prev;
    public NodeDLL(T el)
    {
        info = el;
        next = null;
        prev = null;
    }

    public NodeDLL(NodeDLL<T> prev,T el, NodeDLL<T> forw)
    {
        this.info = el;
        this.next = forw;
        this.prev = prev;
    }

    @Override
    public String toString()
    {
        return info.toString();
    }
}
