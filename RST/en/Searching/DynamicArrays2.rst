One of the most fundamental data structures is the *array*. Given a position in the array, you can get and set the element at that position:

<<code>>

In low-level programming languages, such as C, arrays have a fixed size: you choose the size of the array when you create it, and you cannot change the size later. Java arrays are like this too. But often when we create an array, we don't know how big it should be. For example, maybe we are reading in records from a file into an array, but we don't know how many records there are. What we need is an array that can change its size.

An array that can change its size is called a dynamic array. Most programming languages provide a class that implements a dynamic array. For example, ArrayList in Java is a dynamic array. In Python, a list is a dynamic array. Dynamic arrays can be used just like normal arrays, except that it is also possible to change their size, e.g. by adding new items to the end.

<<code>>



Dynamic arrays

A dynamic array supports the following operations:

* Item get(int i) - return the item at index i. Can throw an exception if i is out of bounds.
* void put(int i, Item item) - modify the item at index i. Can throw an exception if i is out of bounds.
* int length() - return the capacity of the array
* void resize(int i) - change the capacity of the dynamic array. If the new capacity is bigger than the old one, the new elements are initialised to null.

It is often useful to have two more operatons:

* void add(Item item) - add an item to the end of the array, increasing its capacity by 1
* void removeLast() - remove the last item from the array, reducing its capacity by 1

but these can be implemented in terms of the other operations:

void add(Item x):
  int oldLength = this.length()
  this.resize(oldLength+1)
  this.set(oldLength, x)

void removeLast():
  this.resize(this.length()-1)

We would of course like these operations to be efficient. get() and put() must take O(1) time, and adding a sequence of n items, starting from an empty array, should also take O(n) time - the same requirement we had in Chapter XXXX. How can we do this?

One idea would be to implement a dynamic array as just an array, and implement resize() by creating a new array and copying the contents of the old array into it. But this would have exactly the same problems as it did in Chapter XXX - in particular, calling add() n times would take O(n^2) time. Instead, we are going to use the same ideas as we did there. We are going to allow the array to contain slack space - that is, the size of the array can be higher than what the user requests - and when it is time to grow the array, we are going to double it in size.

One slight complication is that it may not be enough to double the array size when it gets full. For example, suppose the array has a size of 10, and the user calls resize(1000000) - we cannot just double the size to 20, it has to be at least 1000000. To solve this, if the user calls resize(n) with n more than twice the current array size, we will expand the array to have size n. If  n is less than twice the current array size, we will double the array size. Here is the implementation:

void resize(int n):
  if n < size:
    size = n
  if n > size:
    newSize = max(n, size*2)
    blahblahblah

Garbage collection
Shrinking the array but be careful
