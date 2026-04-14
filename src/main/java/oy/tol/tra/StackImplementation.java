package oy.tol.tra;

// TODO: Antti remove method implementations before giving to students.
/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {

   private Object [] itemArray;
   private int capacity;
   private int currentIndex = -1;
   private static final int DEFAULT_STACK_SIZE = 10;

   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
     // TODO: call the constructor with size parameter with default size of 10.
     this(DEFAULT_STACK_SIZE);
   }

   /** TODO: Implement so that
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      if (capacity < 2) {
         throw new StackAllocationException("Stack capacity must be at least 2.");
      }
      try {
         itemArray = new Object[capacity];
         this.capacity = capacity;
      } catch (OutOfMemoryError e) {
         throw new StackAllocationException("Cannot allocate memory for stack of capacity " + capacity);
      }
   }

   @Override
   public int capacity() {
      // TODO: Implement this
         return capacity;

   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this
       if (element == null) {
         throw new NullPointerException("Cannot push null element to stack.");
       }
       if (currentIndex >= capacity - 1) {
         reallocateInternalArray();
       }
       currentIndex++;
       itemArray[currentIndex] = element;

   }
   


   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      // TODO: Implement this
       if (isEmpty()) {
         throw new StackIsEmptyException("Cannot pop from empty stack.");
       }
       @SuppressWarnings("unchecked")
       E element = (E) itemArray[currentIndex];
       itemArray[currentIndex] = null;
       currentIndex--;
       return element;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      // TODO: Implement this
         if (isEmpty()) {
            throw new StackIsEmptyException("Cannot peek from empty stack.");
         }
         return (E) itemArray[currentIndex];
      }

   @Override
   public int size() {
      // TODO: Implement this
      return currentIndex + 1;
   }

   

   @Override
   public void clear() {
      // TODO: Implement this
      while (!isEmpty()) {
         itemArray[currentIndex] = null;
         currentIndex--;
      }
      
   }

   @Override
   public boolean isEmpty() {
      // TODO: Implement this
      return currentIndex == -1;
      
   }

   // TODO: Implement copying the stack to a larger new internal array
   // if the stack gets full and more elements need to be pushed to stack.
   private void reallocateInternalArray() throws RuntimeException {
      int newCapacity = capacity * 2;
      try {
         Object[] newArray = new Object[newCapacity];
         System.arraycopy(itemArray, 0, newArray, 0, capacity);
         itemArray = newArray;
         capacity = newCapacity;
      } catch (OutOfMemoryError e) {
         throw new StackAllocationException("Cannot allocate memory for stack of capacity " + newCapacity);
      }
      
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("[");
      for (var index = 0; index <= currentIndex; index++) {
         builder.append(itemArray[index].toString());
         if (index < currentIndex) {
            builder.append(", ");
         }
      }
      builder.append("]");
      return builder.toString();
   }
}
