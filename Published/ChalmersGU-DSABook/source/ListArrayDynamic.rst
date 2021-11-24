.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['resizing-the-internal-array', 'how-much-to-increase-the-array-size', 'growing-by-a-constant-amount', 'growing-by-a-constant-factor', 'constant-amount-vs-constant-factor', 'shrinking-the-internal-array', 'dynamic-array-based-list:-full-code'];</script>

.. _ListArrayDynamic:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "ListArrayDynamic";ODSA.SETTINGS.MODULE_LONG_NAME = "Dynamic Array-Based Lists";ODSA.SETTINGS.MODULE_CHAPTER = "Linear Structures"; ODSA.SETTINGS.BUILD_DATE = "2021-11-24 16:47:50"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/ChalmersGU/CGU-Styles.css

.. odsalink:: AV/List/ListArrayDynamicZoomCON.css

.. odsalink:: AV/List/ListArrayDynamicCON.css
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
=========================

The problem with a static array-based list is that it has a limited capacity.
If we try to add new elements when the internal array is full,
the method will throw an exception.

Resizing the internal array
------------------------------

How can we modify our class to allow for any number of elements?
One solution is to create a larger internal array whenever the capacity is exceeded,
and copy over all elements to the new one.

.. codeinclude:: ChalmersGU/API/DynamicArrayList
   :tag: DynamicArrayListResize

So, how large should the new internal array be? For now, let's
**double the size of the internal array** when we need to resize,
which means that we add the following if-clause to the ``add`` method:

::

        if listSize >= size of internalArray
            resizeArray(size of internalArray * 2)


That's the only difference from the ``add`` method from **StaticArrayList**.
So the dynamic ``add`` method will look like this.


.. codeinclude:: ChalmersGU/API/DynamicArrayList
   :tag: DynamicArrayListAdd

As explained below, we don't have to double the size, but we can multiply by 3 or 1.5 or 1.1.
The important point is that we don't add a constant number, but increase the size by a factor.
This factor is the ``CapacityMultiplier`` in the code above.

.. inlineav:: DynamicArrayList-Append-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Dynamic Array-based List Addition Slideshow
   :output: show

How much to increase the array size
-----------------------------------

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
following small program under different resizing strategies::

  list = new dynamic array list
  for i in 1...n:
      list.add(i)

The program builds a list of length `n` by repeatedly calling :math:`add`.
In this case, we could have used a static array-based list of capacity
:math:`n`. So we would like the dynamic array-based list to have
comparable performance to the static array-based list. This means that
the program ought to take `linear time`.

Growing by a constant amount
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

What happens if we only grow the internal array by 1 element when we resize it?

::

        if listSize >= size of internalArray
            resizeArray(size of internalArray + 1)

Every time we call ``add``, the internal array will be resized.
Resizing the array takes linear time, because if the internal array
has size :math:`n`, it has to copy :math:`n` elements from the
internal array to the new array. To put it another way, the loop body
``newArray[i] = internalArray[i]`` will be executed :math:`n` times.

Now suppose we run the program above to create a list of :math:`n`
elements.  Adding up all the calls to ``resizeArray`` that happen, how
many times does an array element get copied from the internal array to
the new array (that is, how many times does the statement ``newArray[i]
= internalArray[i]`` get executed)?

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
still quadratic [1]_. When :math:`n = 1,000,000`, the total number of
elements copied is about :math:`5,000,000,000`, still far too many.

In short, **growing the array size by a constant amount is bad**,
because a loop that repeatedly adds to the array will take quadratic time.

Growing by a constant factor
~~~~~~~~~~~~~~~~~~~~~~~~~~~~

One way to think about the problem is: as the array gets bigger,
resizing it gets more expensive. So, to make up for that, when the array
is bigger we need to grow it by more, so that we don't have to resize
as often. One way to do this is to always double the array size when
it gets full. This turns out to work well!

Suppose that we run the example program with :math:`n = 1000`, i.e. we
add 1000 elements to the list. As before, the internal array initially
has a size of 1. What calls to ``resizeArray`` happen, and how many
elements get copied each time?

* ``resizeArray(2)``, copying 1 element
* ``resizeArray(4)``, copying 2 elements
* ``resizeArray(8)``, copying 4 elements
* ``resizeArray(16)``, copying 8 elements
* ``resizeArray(32)``, copying 16 elements
* ``resizeArray(64)``, copying 32 elements
* ``resizeArray(128)``, copying 64 elements
* ``resizeArray(256)``, copying 128 elements
* ``resizeArray(512)``, copying 256 elements
* ``resizeArray(1024)``, copying 512 elements

You can see that the array gets resized a whole lot at the beginning
-- but as it gets bigger, it gets resized much less often. We can read
off how many elements get copied: :math:`1+2+4+8+16+32+64+128+256+512 = 1023`.

Since the array starts from size 1 and always doubles, the array size
is always a power of two. So to calculate the total number of elements
copied, instead of adding up all the terms by hand, we can use the
formula :math:`2^0+2^1+2^2+...+2^n = 2^{n+1}-1` (with :math:`512=2^9`).

Suppose that we now choose :math:`n=1,000,000`. How many elements get
copied? In this case the final array size will be :math:`2^{20} = 1,048,576`.
The array size will eventually grow from :math:`2^{18}` to :math:`2^{19}`
to :math:`2^{20}` elements, with the final call to ``resizeArray``
copying :math:`2^{19}` elements. Using the formula above, the total number
of elements copied is :math:`2^0+2^1+2^2+...+2^{19} = 2^{20}-1 = 1,048,575`.

Compared to when we grew the array by a fixed size of 1 element, this is
:math:`500,000` times fewer! So this in fact seems to be nice and efficient.

Let us now generalise to an arbitrary :math:`n`. The worst case is when
the final call to ``add`` has to resize the array -- that happens when
:math:`n` is one more than a power of two, :math:`n-1 = 2^k`. In that
case, the final call to ``resizeArray`` grows the array from
:math:`2^k` to :math:`2^{k+1}`, copying :math:`2^k` elements.
The total number of elements copied is :math:`2^0+2^1+2^2+...+2^k
= 2^{k+1} - 1 = 2 \cdot 2^k - 1 = 2(n-1) - 1 = 2n-3`. In fact, we have
just proved the following result.

**Theorem:** When using the array-doubling strategy, calling
``add`` :math:`n` times starting from an empty dynamic array list
causes fewer than :math:`2n` elements to be copied.

In short, the overhead of using a dynamic array list is at most `two
array elements copied per element that we add`. But copying an array
element is an extremely cheap operation, so dynamic array lists
implemented using array doubling have almost no overhead, compared to
static array lists. In particular, the complexity of our example
program is `linear`, just as we wanted.

What happens if we instead grow the array by 50%? In fact, it still
works out fine - the program takes linear time to run. To see this,
you can use the same argument as above, but instead of using the
formula :math:`2^0+2^1+...+2^k = 2^{k+1}`, you have to use the formula
for a general `geometric progression`_. What you get is an overhead of
`three elements copied per element added`. In fact, Java ``ArrayLists``
grow the array by 50% on resizing.

In fact, **growing the array by any constant factor** works, because
the same geometric progression reasoning applies. We can calculate
the exact performance overhead of growing the array by any given factor:

**Theorem:** If we grow the array by a factor of :math:`k` when
resizing it, then the overhead is at most :math:`1+1/k` elements
copied per ``add``. For example, when growing by 20% (k=0.2), the
overhead is 6 elements copied per ``add``.

In short, when resizing a dynamic array list, we should **grow the
array size by a factor**, because this gives only a constant factor
performance overhead compared to using a static array list. We can choose
a large factor (such as 2) if we want fast performance, or a low
factor (such as 20%) if we want to save memory.

Constant amount vs constant factor
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Here is a graph that shows just how big the performance difference is
between the two resizing strategies: growing the array by a constant
amount, and scaling it by a constant factor. The graph plots how many
elements need to be copied, as a function of how many elements we add
to the list.

.. _ListGrowthGraph:

.. inlineav:: ListArrayDynamicZoomCON dgm
    :align: center

Notice that although growing by 10000 seems pretty good at first, for
largest lists it's worse than growing by 10%. We can see this more
clearly if we zoom out the graph, making the *x*-axis go up to
:math:`10,000,000` instead of :math:`1,000,000`:

.. inlineav:: ListArrayDynamicCON dgm
    :align: center

Though you can't see it in the graph, at :math:`x=10,000,000`, growing
by 10000 is **5000 times** slower than growing by 10%! This is because
the "growing by 10000" strategy takes quadratic time: if we do 10 times as many
calls to ``add``, it takes 100 times as long. Quadratic algorithms
always lose to linear algorithms eventually!

.. TODO:
   Exercise for dynamic addition


Shrinking the internal array
--------------------------------

We don't have to change anything else in the code from **StaticArrayList**
to have a working dynamic array list that has room for any number of elements.

But the problem is that if we first build a large list with 1000's of elements,
and then remove most of them, we will still have a large internal array where
almost all cells are unused.
So, let's resize the array also when removing elements!
When the array contains too many unused cells, we shrink it to half the size.

Now, it's important that we *don't* shrink the array when it's half full.
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

        if listSize <= size of internalArray * 1/3
            resizeArray(size of internalArray * 1/2)


That's the only difference from the ``StaticArrayList.remove`` method.

Note that the factors 1/3 and 1/2 are not important, as explained before.
The only thing that matters is that the minimum load factor (1/3) is smaller
than the shrinking factor (1/2). 
So the dynamic ``remove`` method will look like this.

.. codeinclude:: ChalmersGU/API/DynamicArrayList
   :tag: DynamicArrayListRemove

|

.. inlineav:: DynamicArrayList-Remove-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Dynamic Array-based List Deletion Slideshow
   :output: show



.. TODO:
   Exercise for dynamic deletion


Dynamic Array-based List: Full code
------------------------------------------------

Finally, here is the full source code for the class ``DynamicArrayList``.
Note that now the constructor doesn't take any capacity argument,
since the internal array will automatically grow when needed.

In this example, we set the capacity multiplier to 1.5,
meaning that we grow by 50% and shrink by 33% on every resize.
The minimum load factor is set to 50% (which is smaller than 1/1.5 = 67%),
and the minimum array capacity is 8.
All these constants can be changed at will.

.. codeinclude:: ChalmersGU/API/DynamicArrayList
   :tag: DynamicArrayList

.. [1] You can get a precise number by using the formula for an
   `arithmetic progression`_.

.. _arithmetic progression: https://en.wikipedia.org/wiki/Arithmetic_progression
.. _geometric progression: https://en.wikipedia.org/wiki/Geometric_progression

.. odsascript:: AV/ChalmersGU/DynamicArrayList-Append-CON.js
.. odsascript:: DataStructures/Plot.js
.. odsascript:: AV/List/ListArrayDynamicZoomCON.js
.. odsascript:: AV/List/ListArrayDynamicCON.js
.. odsascript:: AV/ChalmersGU/DynamicArrayList-Remove-CON.js
