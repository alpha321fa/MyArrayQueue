
/** a queue class that uses a one-dimensional array */

public class MyArrayQueue 
{
   // data members
   int front;          // one counterclockwise from first element
   int rear;           // position of rear element of queue
   Object [] queue;    // element array

   // constructors
   /** create a queue with the given initial capacity */
   public MyArrayQueue(int initialCapacity)
   {
      if (initialCapacity < 1)
         throw new IllegalArgumentException
               ("initialCapacity must be >= 1");
      queue = new Object [initialCapacity + 1];
      // default front = rear = 0
   }

   /** create a queue with initial capacity 5 */
   public MyArrayQueue()
   {// use default capacity of 5
      this(5);
   }

   // methods
   /** @return true iff queue is empty */
   public boolean isEmpty()
      {return front == rear;}


   /** @return front element of queue
     * @return null if queue is empty */
   public Object getFrontElement()
   {
      if (isEmpty())
         return null;
      else
         return queue[(front + 1) % queue.length];
   }

   /** @return rear element of queue
     * @return null if the queue is empty */
   public Object getRearElement()
   {
      if (isEmpty())
         return null;
      else
         return queue[rear];
   }

   /** insert theElement at the rear of the queue */
   public void enqueue(Object theElement)	
   {
	  // Add your code here
     //Checks if the queue is full
     if (front == (rear + 1) % queue.length) {
        //Creates a new queue of double length
        Object[] newQueue = new Object[(queue.length) * 2];
        //If the queue array has elements between the front and end of the array,
        //doubling the array will have to be done by adding null elements between where the front and rear elements are located
        //The code below does this by first copying the elements until the rear element is reached
        //and then the rest of the queue is added at a position that is after the added null values.
        if (front > rear) {
           System.arraycopy(queue, 0, newQueue, 0, front);
           System.arraycopy(queue, front, newQueue, ((newQueue.length/2) + front), ((newQueue.length/2) - front));
           front = front + (newQueue.length/2);
        } else {
           //This line of code doubles the array reglalrly when the front element index is less than the rear element index
            System.arraycopy(queue, front+1, newQueue, front+1, queue.length - 1);
        }
        queue = newQueue;
     }
   rear = (rear + 1) % queue.length;
   queue[rear] = theElement;
   }

   /** remove an element from the front of the queue
     * @return removed element */
   public Object dequeue()
   {
	   // Add your code here
      if (isEmpty()){
         return null;
      } else {
         Object frontElement = getFrontElement();
         front = (front + 1) % queue.length;
         queue[front] = null;
         return frontElement;
      }
   }
   
   /** test program */
   public static void main(String [] args)
   {  
      MyArrayQueue q = new MyArrayQueue(3);
      // add a few elements
      q.enqueue("element1");	//q.put(new Integer(1));
      q.enqueue("element2");	//q.put(new Integer(2));
      q.enqueue("element3");	//q.put(new Integer(3));
      q.enqueue("element4");	//q.put(new Integer(4));

      // remove and add to test wraparound array doubling
      q.dequeue();
      q.dequeue();
      q.enqueue("element5");	//q.put(new Integer(5));
      q.enqueue("element6");	//q.put(new Integer(6));
      q.enqueue("element7");	//q.put(new Integer(7));
      q.enqueue("element8");	//q.put(new Integer(8));
      q.enqueue("element9");	//q.put(new Integer(9));
      q.enqueue("element10");	//q.put(new Integer(10));
      q.enqueue("element11");	//q.put(new Integer(11));
      q.enqueue("element12");	//q.put(new Integer(12));

      // delete all elements
      while (!q.isEmpty())
      {
      	  System.out.println("Rear element   : " + q.getRearElement());
    	  System.out.println("Front element  : " + q.getFrontElement());
    	  System.out.println("Removed element: " + q.dequeue() + "\n");
      }
	  if (q.isEmpty()) System.out.println("empty queue");      
   }  
}
