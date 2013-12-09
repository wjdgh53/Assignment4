//----------------------------------------------------------------------------
// Heap.java               by Dale/Joyce/Weems                       Chapter 9
//
// Defines all constructs for a heap.
// The dequeue method returns the largest element in the heap.
//----------------------------------------------------------------------------


import java.util.*;

import Interface.PriQOverflowException;
import Interface.PriQUnderflowException;
import Interface.PriQueueInterface;

public class Heap<T extends Comparable<T>> implements PriQueueInterface<T>
{
  private ArrayList<T> elements; 
  private int lastIndex;         
  private int maxIndex;         

  public Heap(int maxSize)
  {
    elements = new ArrayList<T>(maxSize);
    lastIndex = -1;
    maxIndex = maxSize - 1;
  }

  public boolean isEmpty()
  {
    return (lastIndex == -1);
  }

  public boolean isFull()
  
  {
    return (lastIndex == maxIndex);
  }

  private void reheapUp(T element)
 
  {
    int hole = lastIndex;
    while ((hole > 0)    
           &&                                               
      (element.compareTo(elements.get((hole - 1) / 2)) > 0)) 
      {
     
      elements.set(hole,elements.get((hole - 1) / 2)); 
      hole = (hole - 1) / 2;                                
    }
    elements.set(hole, element); 
  }

  public void enqueue(T element) throws PriQOverflowException
  
  {
    if (lastIndex == maxIndex)
      throw new PriQOverflowException("Priority queue is full");
    else
    {
      lastIndex++;
      elements.add(lastIndex,element);
      reheapUp(element);
    }
  }

  private int newHole(int hole, T element)
 
  {
    int left = (hole * 2) + 1;
    int right = (hole * 2) + 2;

    if (left > lastIndex)
      
      return hole;         
    else
    if (left == lastIndex)
     
      if (element.compareTo(elements.get(left)) < 0)             
        
        return left;
      else
       
        return hole;
    else
   
    if (elements.get(left).compareTo(elements.get(right)) < 0)
    
      if (elements.get(right).compareTo(element) <= 0)
       
        return hole;
      else
       
        return right;
    else
   
    if (elements.get(left).compareTo(element) <= 0)
      
      return hole;
    else
     
      return left;
  }

  private void reheapDown(T element)
 
  {
    int hole = 0;      // current index of hole
    int newhole;       // index where hole should move to

    newhole = newHole(hole, element);   // find next hole
    while (newhole != hole)
    {
      elements.set(hole,elements.get(newhole));  // move element up
      hole = newhole;                            // move hole down
      newhole = newHole(hole, element);          // find next hole
    }
    elements.set(hole, element);           // fill in the final hole
  }

  public T dequeue() throws PriQUnderflowException
  // Throws PriQUnderflowException if this priority queue is empty;
  // otherwise, removes element with highest priority from this 
  // priority queue and returns it.
  {
    T hold;      // element to be dequeued and returned
    T toMove;    // element to move down heap

    if (lastIndex == -1)
      throw new PriQUnderflowException("Priority queue is empty");
    else
    {
      hold = elements.get(0);              // remember element to be returned
      toMove = elements.remove(lastIndex); // element to reheap down
      lastIndex--;                         // decrease priority queue size
      if (lastIndex != -1)
         reheapDown(toMove);               // restore heap properties
      return hold;                         // return largest element
    }
  }

  public String toString()
  // Returns a string of all the heap elements.
  {
    String theHeap = new String("the heap is:\n");
    for (int index = 0; index <= lastIndex; index++)
      theHeap = theHeap + index + ". " + elements.get(index) + "\n";
    return theHeap;
  }
}