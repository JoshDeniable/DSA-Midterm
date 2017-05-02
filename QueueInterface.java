package Midterm;

public interface QueueInterface<T> {

  // Determines whether a queue is empty.
  // Returns true if the queue is empty;
  // otherwise returns false.
  public boolean isEmpty();

  // Adds an item at the back of a queue.
  // Precondition: newItem is the item to be inserted. 
  // Postcondition: If the operation was successful, newItem
  // is at the back of the queue. Some implementations
  // may throw QueueException if newItem cannot be added
  // to the queue.
  public void enqueue(T newItem) throws QueueException;

  // Retrieves and removes the front of a queue.
  // Postcondition: If the queue is not empty, the item that
  // was added to the queue earliest is removed. If the queue is 
  // empty, the operation is impossible and QueueException is thrown.
  public T dequeue() throws QueueException;

  // Removes all items of a queue.
  // Postcondition: The queue is empty.
  public void dequeueAll();

  // Retrieves the item at the front of a queue.
  // Postcondition: If the queue is not empty, the item
  // that was added to the queue earliest is returned. 
  // If the queue is empty, the operation is impossible
  // and QueueException is thrown.
  public T peek() throws QueueException;


  public String toString();
}
