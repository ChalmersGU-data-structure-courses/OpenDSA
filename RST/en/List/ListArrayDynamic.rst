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

        if arraySize >= size of internalArray
            resizeArray(2 * size of internalArray)


So the ``add`` method will look like follows.

.. codeinclude:: ChalmersGU/DynamicArrayList
   :tag: DynamicArrayListAdd

.. TODO::
   Animation example for dynamic addition

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

.. TODO::
   Deletion, and dynamic shrinking

.. codeinclude:: ChalmersGU/DynamicArrayList
   :tag: DynamicArrayListRemove

.. TODO::
   Animation example for dynamic addition


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

