import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom; 
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
 private Item[] rq;
 private int length;
 
 public RandomizedQueue()               // construct an empty randomized queue
 {
  rq = (Item[]) new Object[1];
  length = 0;
 }
 
 public boolean isEmpty()               // is the queue empty?
 {
  return length == 0;
 }
 
 public int size()                      // return the number of items on the queue
 {
  return length;
 }
 
 public void enqueue(Item item)         // add the item
 {
  if (item == null) throw new NullPointerException("Fail to add a null item");
  if (length == rq.length) resize(rq.length*2);
  rq[length++] = item;
 }
 
 private void resize(int capacity)
 {
  Item[] copy = (Item[]) new Object[capacity];
  for (int i = 0; i < length; i++) copy[i] = rq[i];
  rq = copy;
 }
 
 public Item dequeue()                  // remove and return a random item
 {
  if (isEmpty()) throw new NoSuchElementException("Remove from an empty queue");
  int index = StdRandom.uniform(length);
  Item it = rq[index];
  for (int i = index; i < length-1; i++) rq[i] = rq[i+1];
  rq[length--] = null;
  if (length > 0 && length == rq.length/4) resize(rq.length/2); 
  return it;
 }
 
 public Item sample()                   // return (but do not remove) a random item
 {
  if (isEmpty()) throw new NoSuchElementException("Remove from an empty deque");
  return rq[StdRandom.uniform(length)];
 }
 
 public Iterator<Item> iterator()       // return an independent iterator over items in random order
 {
  return new RQIterator();
 }
 
 private class RQIterator implements Iterator<Item> {
  private int count = length;
  public void remove() { throw new UnsupportedOperationException("Remove unsupported"); }
  public boolean hasNext() { return (length > 0) && (count > 0); }
  public Item next() {
   if (!hasNext()) throw new NoSuchElementException("Iterate in an empty deque");  
   count--;   
   return sample();
  }
 }
// public static void main(String[] args) // unit testing
}