package task3;

public interface MyBuffer<T> {
	public void add(T value);
	public T get();
	public boolean isFull();
	public boolean isEmpty();
}
