.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Nick Smallbone
   :satisfies: dynamic arrays
   :topic: Dynamic Arrays

Dynamic Arrays
==============

How not to do it
----------------

How to do it
------------

We need two internal (private) class variables:

- the internal storage - an array which is larger than the current size, and
- the current size of the dynamic array.

.. codeinclude:: ChalmersGU/DynamicArray
      :tag: DynamicArrayInit

Appending an element at the end of the array:

.. codeinclude:: ChalmersGU/DynamicArray
      :tag: DynamicArrayAppend

Removing the last element:

.. codeinclude:: ChalmersGU/DynamicArray
      :tag: DynamicArrayRemove

Resizing the array if necessary:

.. codeinclude:: ChalmersGU/DynamicArray
      :tag: DynamicArrayResize

Iterating through the array:

.. codeinclude:: ChalmersGU/DynamicArray
      :tag: DynamicArrayIterator


A note for Pythoninstas
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Python doesn't have fixed-size arrays – the Python lists are actually already dynamic arrays!
And, they are implemented in the same way as we have done it here (except they are implemented in C).

This means that the Python code here is a bit of a hack. It would be very simple to implement the DynamicArray
as a normal Python list, but that wouldn't tell us anything about how they are implemented.
So instead we use the following kind of Python code to initialise an array of 123 elements:
``array = [None] * 123``


Full code
~~~~~~~~~~

.. codeinclude:: ChalmersGU/DynamicArray
      :tag: DynamicArray


