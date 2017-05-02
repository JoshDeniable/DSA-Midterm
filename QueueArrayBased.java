package Midterm;

public class QueueArrayBased<T> implements QueueInterface<T>
{
    protected int front; 
    protected int back; 
    protected int numItems; 
    protected T[] items;

	public QueueArrayBased()
    {
        this.numItems = 0;
        this.items = (T[]) new Object[5];
    } 
    
    public boolean isEmpty()
    {
        return (this.numItems==0);
    }

    public T dequeue() throws QueueException
    {
        T result = items[front];
        items[front] = null;
        front = (front + 1) % items.length;
        numItems--;
        return result;
    }
    
    public void enqueue(T newItem) throws QueueException
    {
        if(numItems == items.length){resize();}
        if(numItems == 0){
            front = 0;
            back = -1;
        }
        back = (back+1)%items.length;
        items[back] = newItem;
        numItems++;
    }

    public void dequeueAll()
    {
        this.numItems = 0;
        this.items = (T[]) new Object[5];
    }

    public T peek() throws QueueException
    {
        return items[front];
    }

    public String toString()
    {
    	int index = front;
        String result = "Queue: ";
        for(int i=0; i<numItems; i++){
            result += "\t" + items[index];
            index = (index+1)%items.length;
        }
        return result;
    }

	protected void resize()
    {
        T[] temp = (T[]) new Object[this.items.length * 2];
        int pos=0;
        int index = front;
        while(pos<numItems){
            temp[pos++] = items[index];
            index = (index+1)%items.length;
        }
        front=0;
        back = numItems-1;
        items = temp;
    }
}
