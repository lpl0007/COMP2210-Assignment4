import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Lanie Louque (lpl0007@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
    Node front;
    Node rear;

    /** The number of nodes in the list. */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////
    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    /**
     * Returns the current size of this collection.
     *
     * @return  the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////
    /**
     * Tests to see if this collection is empty.
     *
     * @return  true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element) {
        if((element == null) || (contains(element))) {return false;}
        Node e = new Node(element);
        if(isEmpty()) {
         front = e;
         rear = e;
        }
        else if(front.element.compareTo(element) > 0) {
         e.next = front;
         front.prev = e;
         front = e;
        }
        else if (rear.element.compareTo(element) < 0) {
         e.prev = rear;
         rear.next = e;
         rear = e;
        }
        else {
         Node n = front;
         Node before = null;
         while (n != null) {
            if (n.element.compareTo(element) > 0) {
               before = n.prev;
               break;
            }
            n = n.next;
         }
         if(before == null) {
            before = n;
         }
         before.next.prev = e;
         e.next = before.next;
         before.next = e;
         e.prev = before;
        }
        size++;
        return true;
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {
        if((isEmpty()) || (element == null) || (!(contains(element)))) {return false;}
        Node e = front;
        while((e != null) && (e.element != element)) {
         e = e.next;
        }
        if(e.element.equals(element)){
         if(e.prev != null) {
            e.prev.next = e.next;
         }
         else {
            front = e.next;
         }
         if(e.next != null) {
            e.next.prev = e.prev;
         }
         else {
            rear = e.prev;
         }
         size--;
         return true;
        }
        return false;
    }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element) {
        if((isEmpty()) || (front == null)) {return false;}
        Node e = front;
        while (e != null) {
         if(e.element.equals(element)) {
            return true;
         }
         e = e.next;
        }
        return false;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {
        if((size == s.size()) && (complement(s).size() == 0)) {
         return true;
        }
        return false;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {
        if((size == s.size()) && (complement(s).size() == 0)) {
         return true;
        }
        return false;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s){
        if(s == null) {return null;}
        LinkedSet<T> newSet = new LinkedSet<T>();
        Node e = front;
        while (e != null) {
         newSet.add(e.element);
         e = e.next;
        }
        Iterator<T> i = s.iterator();
        while(i.hasNext()) {
         newSet.add(i.next());
        }
        return newSet;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s){
        if(s == null) {return null;}
        LinkedSet<T> newSet = new LinkedSet<T>();
        Node e = front;
        while (e != null) {
         newSet.add(e.element);
         e = e.next;
        }
        Iterator<T> i = s.iterator();
        while(i.hasNext()) {
         newSet.add(i.next());
        }
        return newSet;
    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {
        if(s == null) {return null;}
        LinkedSet<T> newSet = new LinkedSet<T>();
        Node e = front;
        while (e != null) {
         if(s.contains((T)e.element)) {
            newSet.add((T)e.element);
         }
         e = e.next;
        }
        return newSet;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s) {
        if(s == null) {return null;}
        LinkedSet<T> newSet = new LinkedSet<T>();
        Node e = front;
        while (e != null) {
         if(s.contains((T)e.element)) {
            newSet.add((T)e.element);
         }
         e = e.next;
        }
        return newSet;
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {
        if(s == null) {return null;}
        LinkedSet<T> newSet = new LinkedSet<T>();
        Node e = front;
        while (e != null) {
         if(!(s.contains((T)e.element))) {
            newSet.add((T)e.element);
         }
         e = e.next;
        }
        return newSet;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s) {
        if(s == null) {return null;}
        LinkedSet<T> newSet = new LinkedSet<T>();
        Node e = front;
        while (e != null) {
         if(!(s.contains((T)e.element))) {
            newSet.add((T)e.element);
         }
         e = e.next;
        }
        return newSet;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator() {
      Iterator<T> it = new Iterator<T>(){
         Node n = front;
      
         public boolean hasNext() {
            return n != null && n.element != null;
         }
      
         public T next() {
            if(n != null) {            
               T r = n.element;
               n = n.next;
               return r;
            }
            return null;
         }
      
         public void remove() {
            throw new UnsupportedOperationException();
         }
        };
      return it;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator() {
      Iterator<T> it = new Iterator<T>(){
         Node n = rear;
      
         public boolean hasNext() {
            return n != null && n.element != null;
         }
      
         public T next() {
            if(n != null) {            
               T r = n.element;
               n = n.prev;
               return r;
            }
            return null;
         }
      
         public void remove() {
            throw new UnsupportedOperationException();
         }
        };
      return it;
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
    public Iterator<Set<T>> powerSetIterator() {
        Iterator<Set<T>> it = new Iterator<Set<T>>(){
         Node n = front;
         int index = 0;
      
         public boolean hasNext() {
            if((index == 0) && ((int) Math.pow(size,2) == 0)) {
               return true;
            }
            return (index < (int) Math.pow(size,2));
         }
      
         public Set<T> next() {
            Set<T> powerSet = new LinkedSet<T>();
            n = front;
            int flag = 1;
            for(int i = 0; i < size; i++) {
               if((index&(1<<i)) != 0) {
                  powerSet.add(n.element);
               }
               n = n.next;
               flag <<= 1;
            }
            index++;
            return powerSet;
         }
      
         public void remove() {
            throw new UnsupportedOperationException();
         }
        };
      return it;
    }



    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////

    // Feel free to add as many private methods as you need.

    ////////////////////
    // Nested classes //
    ////////////////////

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;
        /** a reference to the node before this node. */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }
}