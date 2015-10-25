import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
 private int length = 0;
 private Node first;
 private Node last;
 
 private class Node {
  Item item;
  Node next;
 }
 
 public Deque() {
 }
 // construct an empty deque    
 public boolean isEmpty()                 // is the deque empty?    
 {
  return first == null;
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
 } 
 
 public void addLast(Item item)           // add the item to the end    
 {
  if (item == null) throw new NullPointerException("Fail to add a null item");
  length++;
  Node oldLast = last;
  last = new Node();
  last.item = item;
  oldLast.next = last;
  if (size() == 1) first = last;
 }
 
 public Item removeFirst()                // remove and return the item from the front    
 {
  if (!isEmpty()) {
   length--;
   Item it = first.item;
   first = first.next;
   return it;   
  } else throw new NoSuchElementException("Remove from an empty deque");
 }
 
 public Item removeLast()                 // remove and return the item from the end   
 {
  if (!isEmpty()) {
   length--;
   Item it = last.item;
   last = null;
   return it;
  } else throw new NoSuchElementException("Remove from an empty deque");
 }
 
 public Iterator<Item> iterator()         // return an iterator over items in order from front to end
 {
  return new DequeIterator();
 } 
 
 private class DequeIterator implements Iterator<Item> {
  private Node current = first;   
  public void remove() { throw new UnsupportedOperationException("Remove unsupported"); }
  public boolean hasNext() { return current == null; }
  public Item next() {
   if (isEmpty()) throw new NoSuchElementException("Iterate in an empty deque");
   Item it = current.item;
   current = current.next;
   return it;
  }
 }
// public static void main(String[] args)   // unit testing 
} 