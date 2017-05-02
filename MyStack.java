package Midterm;

public class MyStack<T> implements StackInterface<T> {

	private int top;
	private T[] items;
	
	public MyStack()
	{
		top = -1;
		items = (T[]) new Object[10];
	}
	
	public boolean isEmpty() {
		return (top==-1);
	}

	public void popAll() {
		items = (T[]) new Object[10];
		top = 0;
	}

	public void push(T newItem) throws StackException {
		if(top == items.length-1){resize();}
		items[++top] = newItem;
	}

	public T pop() throws StackException {
		T result = null;
		if(!isEmpty()){
			result = (T)items[top];
			items[top--] = null;
		}
		return result;
	}

	public T peek() throws StackException {
		T result = null;
		if(top!=-1){
			result = (T)items[top];
		}
		return result;
	}

	public void resize(){
		T[] temp = (T[]) new Object[items.length*2];
		for(int i=0; i< items.length; i++){
			temp[i] = items[i];
		}
		items = temp;
	}
}
