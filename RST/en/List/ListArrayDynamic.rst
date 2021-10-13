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

So, how large should the new internal array be? For now, let's
**double the size of the internal array** when we need to resize,
which means that we add the following if-clause to the ``add`` method:

::

        if listSize >= size of internalArray
            resizeArray(2 * size of internalArray)


That's the only difference from the ``StaticArrayList.add`` method.
So the dynamic ``add`` method will look like this.

.. codeinclude:: ChalmersGU/DynamicArrayList
   :tag: DynamicArrayListAdd

|

.. inlineav:: DynamicArrayList-Append-CON ss
   :long_name: Dynamic Array-based List Addition Slideshow
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/DynamicArrayList-Append-CON.js
   :output: show

How much to increase the array size
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

In the code above we doubled the size of the internal array whenever
we needed to resize it. But we could have done something else, like:

* Triple the size
* Grow the size by 10%
* Grow the size by 100 elements
* Grow the size by 1 element

But which is best, and why?

There is a tradeoff: if we grow the array by a lot, we might waste
memory. For example, immediately after we double the size, half of the
array's capacity is unused, so we use twice as much memory as needed.
On the other hand, if we grow the array by a small amount, we need to
resize it more often.

We will explore these tradeoffs by looking at the performance of the
following small program under different resizing strategies:

  list = new dynamic array list
  for i in 1..n:
    list.add(i)

The program builds a list of length `n` by repeatedly calling :math:`add`.
In this case, we could have used a static array-based list of capacity
:math:`n`. So we would like the dynamic array-based list to have
comparable performance to the static array-based list. This means that
the program ought to take `linear time`.

Growing by a constant
^^^^^^^^^^^^^^^^^^^^^

What happens if we only grow the internal array by 1 element when we resize it?

::

        if listSize >= size of internalArray
            resizeArray(size of internalArray+1)

Every time we call ``add``, the internal array will be resized.
Resizing the array takes linear time, because if the internal array
has size :math:`n`, then it has to copy :math:`n` elements from the
internal array to the new array. To put it another way, the loop body
``newArray[i] = internalArray[i]`` will be executed :math:`n` times.

Now suppose we run the program above to create a list of :math:`n`
elements.  Adding up all the calls to ``resizeArray`` that happen, how
many times does an array element get copied from the internal array to
the new array (that is, how many times is the statement ``newArray[i]
= internalArray[i]``) get executed?

The array size is initially 1, so we get the following calls to ``resizeArray``:

* ``resizeArray(2)``, copying 1 element
* ``resizeArray(3)``, copying 2 elements
* ``resizeArray(4)``, copying 3 elements
* ...
* ``resizeArray(n-2)``, copying :math:`n-3` elements
* ``resizeArray(n-1)``, copying :math:`n-2` elements
* ``resizeArray(n)``, copying :math:`n-1` elements

In total, there are :math:`1+2+...+(n-1)` element copy operations,
which is equal to :math:`n(n-1)/2 = (n^2-n)/2`.
This means that the program takes `quadratic time`, not linear!

Suppose for example that :math:`n = 1,000,000`. Using the formula
above, the number of times an array element gets copied is
:math:`999999 \times 1000000/2 = 499,999,500,000`. If copying one
array element takes 1 ns, then the program spends nearly 10 minutes
just resizing the array!

What happens if we instead grow the array by 100 elements every time?
You can try the calculation yourself, for say :math:`n = 1,000,000`.
What happens is that ``resizeArray`` gets called 100 times less
often -- so there 100 times fewer elements copied. But the runtime is
still quadratic. When :math:`n = 1,000,000`, the total number of
elements copied is about :math:`5,000,000,000`, still far too many.

In short, **growing the array size by a constant amount is bad**,
because a loop that repeatedly adds to the array will take quadratic time.

Growing by a factor
^^^^^^^^^^^^^^^^^^^

As discussed in the module about
:ref:`string building <StringReading>`,
it's not a good idea to increase the size by a constant.
Instead we should **grow the array by a factor**, i.e. multiply not add!
For simplicity, let's

.. TODO::
   Complexity analysis

.. _ListGrowthTable:

.. topic:: Table

   Amount of elements copied for different list sizes.

   .. math::

      \begin{array}{r||r|r|r||r|r|r}
      \textsf{Final list size} & \textsf{+1} & \textsf{+100} & \textsf{+10,000} & \textsf{+10%} & \textsf{+50%} & \textsf{+100%} 2 \\
      \hline
      \mathsf{10} & \mathsf{45} & \mathsf{0} & \mathsf{0} & \mathsf{45} & \mathsf{25} & \mathsf{15} \\
    \mathsf{100} & \mathsf{4,950} & \mathsf{0} & \mathsf{0} & \mathsf{1,098} & \mathsf{284} & \mathsf{127} \\
    \mathsf{1,000} & \mathsf{499,500} & \mathsf{4,500} & \mathsf{0} & \mathsf{10,867} & \mathsf{2,137} & \mathsf{1,023} \\
    \mathsf{10,000} & \mathsf{49,995,000} & \mathsf{495,000} & \mathsf{0} & \mathsf{105,166} & \mathsf{24,284} & \mathsf{16,383} \\
    \mathsf{100,000} & \mathsf{4,999,950,000} & \mathsf{49,950,000} & \mathsf{450,000} & \mathsf{1,032,977} & \mathsf{276,521} & \mathsf{131,071} \\
    \mathsf{1,000,000} & \mathsf{499,999,500,000} & \mathsf{4,999,500,000} & \mathsf{49,500,000} & \mathsf{10,170,704} & \mathsf{2,099,753} & \mathsf{1,048,575}
      \end{array}

.. _ListGrowthGraph:

.. inlineav:: ListArrayDynamicZoomCON dgm
    :links: AV/List/ListArrayDynamicZoomCON.css
    :scripts: DataStructures/Plot.js AV/List/ListArrayDynamicZoomCON.js
    :align: center

   The performance of different resizing strategies.
   The horizontal axis represents the number of elements added to the list.
   The vertical axis represents how many times the line ``newArray[i] = internalArray[i]`` is executed.


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

        if listSize <= size of internalArray / 3
            resizeArray(size of internalArray / 2)


That's the only difference from the ``StaticArrayList.remove`` method.
So the dynamic ``remove`` method will look like this.

.. codeinclude:: ChalmersGU/DynamicArrayList
   :tag: DynamicArrayListRemove

|

.. inlineav:: DynamicArrayList-Remove-CON ss
   :long_name: Dynamic Array-based List Deletion Slideshow
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/DynamicArrayList-Remove-CON.js
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

