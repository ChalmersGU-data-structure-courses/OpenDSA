.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Peter Ljunglöf
   :requires: static array-based list
   :satisfies: array-based list
   :topic: Lists

Dynamic Array-Based Lists
===============================

The problem with a static array-based list is that it has a limited capacity.
If we try to add new elements when the internal array is full,
the method will throw an exception.

Resizing the internal array
------------------------------

How can we modify our class to allow for any number of elements?
One solution is to create a larger internal array whenever the capacity is exceeded,
and copy over all elements to the new one.

.. codeinclude:: ChalmersGU/DynamicArrayList
   :tag: DynamicArrayListResize

So, how large should the new internal array be?
As discussed in the module about
:ref:`string building <StringReading>`,
it's not a good idea to increase the size by a constant.
Instead we should **grow the array by a factor**, i.e. multiply not add!
For simplicity, let's double the size of the internal array when we need to resize,
which means that we add the following if-clause to the ``add`` method:

::

        if listSize >= size of internalArray
            resizeArray(2 * size of internalArray)


That's the only difference from the ``StaticArrayList.add`` method.
So the dynamic ``add`` method will look like this.

.. codeinclude:: ChalmersGU/DynamicArrayList
   :tag: DynamicArrayListAdd

|

.. inlineav:: CGUDynamicArrayListAppendCON ss
   :long_name: Dynamic Array-based List Addition Slideshow
   :links: AV/ChalmersGU/CGUStyles.css
   :scripts: AV/ChalmersGU/CGUDynamicArrayListAppendCON.js
   :output: show


Complexity analysis
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. TODO::
   Complexity analysis


Practice Exercise
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. TODO::
   Exercise for dynamic addition


Shrinking the internal array
--------------------------------

We don't have to change anything else in the code from ``StaticArrayList``
to have a working dynamic array list that has room for any number of elements.

But the problem is that if we first build a large list with 1000's of elements,
and then remove most of them, we will still have a large internal array where
almost all cells are unused.
So, let's resize the array also when removing elements!
When the array contains too many unused cells, we shrink it to half the size.

Now, it's important that we **dont'** shrink the array when it's half full.
Why is that? Let's consider the following sequence of additions and deletions:

- append an element to the end
- remove the last element
- append another element to the end
- remove it
- append another one
- remove it
- ...

If we're unlucky and the initial list is full, then the first append will have to resize the array.
Then when we remove that element, the list becomes less than half-full, and we have to resize again.
Then the next append will resize, and the next remove will also resize. And so on...
This will lead to a linear-time resize every time we append/remove, and so
the final complexity will be linear (per operation). Which is not what we want.

How can we alleviate this?
The solution is to wait even longer until we shrink the internal array!
E.g., we can shrink the array (i.e., halve it), when it is only 1/3 full.
So we can add the following lines to the end of the ``remove`` method:

::

        if listSize < size of internalArray / 3
            resizeArray(size of internalArray / 2)


That's the only difference from the ``StaticArrayList.remove`` method.
So the dynamic ``remove`` method will look like this.

.. codeinclude:: ChalmersGU/DynamicArrayList
   :tag: DynamicArrayListRemove

|

.. inlineav:: CGUDynamicArrayListRemoveCON ss
   :long_name: Dynamic Array-based List Deletion Slideshow
   :links: AV/ChalmersGU/CGUStyles.css
   :scripts: AV/ChalmersGU/CGUDynamicArrayListRemoveCON.js
   :output: show


Complexity analysis
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. TODO::
   Complexity analysis


Practice Exercise
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. TODO::
   Exercise for dynamic addition


Dynamic Array-based List: Full code
------------------------------------------------

Finally, here is the full source code for the class ``DynamicArrayList``.
Note that now the constructor doesn't take any capacity argument,
since the internal array will automatically grow when needed.

.. codeinclude:: ChalmersGU/DynamicArrayList
   :tag: DynamicArrayList

