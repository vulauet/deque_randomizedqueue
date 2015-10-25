import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
 private int length = 0;
 private Node first;
 private Node last;
 
 private class Node {
  Item item;
  Node next;
  Node prev;
 }
 
 public Deque() {
 }
 // construct an empty deque    
 public boolean isEmpty()                 // is the deque empty?    
 {
  return length == 0;
 }
 
 public int size()                        // return the number of items on the deque    
 {
  return length;
 }
 
 public void addFirst(Item item)          // add the item to the front
 {
  if (item == null) throw new NullPointerException("Fail to add a null item");
  length++;
  Node oldFirst = first;
  first = new Node();
  first.item = item;
  first.next = oldFirst;
  if (size() == 1) last = first;
  else if (size() > 1) oldFirst.prev = first;
 } 
 
 public void addLast(Item item)           // add the item to the end    
 {
  if (item == null) throw new NullPointerException("Fail to add a null item");
  length++;
  Node oldLast = last;
  last = new Node();
  last.item = item;
  if (size() == 1) first = last;
  else {
   oldLast.next = last;
   last.prev = oldLast;   
  }
 }
 
 public Item removeFirst()                // remove and return the item from the front    
 {
  if (!isEmpty()) {
   length--;
   Item it = first.item;
   first = first.next;
   if (isEmpty())
    last = first;
   return it;   
  } else throw new NoSuchElementException("Remove from an empty deque");
 }
 
 public Item removeLast()                 // remove and return the item from the end   
 {
  if (!isEmpty()) {
   length--;
   Item it = last.item;
   Node oldLast = last.prev;
   last = oldLast;
   if (isEmpty()) first = last;
   return it;
  } else throw new NoSuchElementException("Remove from an empty deque");
 }
 
 public Iterator<Item> iterator() // return an iterator over items in order from front to end
 {
  return new DequeIterator();
 } 
 
 private class DequeIterator implements Iterator<Item> {
  private Node current = first;
  private int count = length;  
  public void remove() { throw new UnsupportedOperationException("Remove unsupported"); }
  // public boolean hasNext() { return current != null; }
  public boolean hasNext() { return count > 0; }
  public Item next() {
   if (!hasNext()) throw new NoSuchElementException("Iterate in an empty deque");
   count--;
   Item it = current.item;
   current = current.next;
   return it;
  }
 }
// public static void main(String[] args)   // unit testing 
} 