.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['using-a-list-of-key-value-pairs', 'how-to-remove-keys-from-the-map', 'using-linked-key-value-nodes', 'removing-keys', 'linked-maps:-full-code'];</script>

.. _ListMap:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "ListMap";ODSA.SETTINGS.MODULE_LONG_NAME = "Implementing Maps using Lists";ODSA.SETTINGS.MODULE_CHAPTER = "Linear Structures"; ODSA.SETTINGS.BUILD_DATE = "2021-11-19 16:27:05"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Peter Ljunglöf
   :requires: linked list
   :satisfies: list map
   :topic: Lists

Implementing Maps using Lists
==============================

It is not difficult to implement a **Map** using a list.
The problem is that all the operations -- searching for a key,
updating the value for a key, and removing a key --
will be linear in the number of entries, :math:`O(n)`.

In later chapters we will see how to improve the efficiency, by using

* :ref:`Balanced search trees <BalancedTree>`, which bring down the complexity of the operations to :math:`O(\log n)`.
* :ref:`Hash tables <HashIntro>`, which make the operations constant time, :math:`O(1)`.

But some times it is enough to use a simple list-based implementation.
And in fact, the :ref:`separate chaining hash map <OpenHash>`
requires an underlying simpler map implementation -- and there a linked list works very fine!

Using a list of key-value pairs
---------------------------------

A very simple way of implementing a **Map** using a list, is to use :ref:`key-value pairs <Comparison>`.

.. codeinclude:: ChalmersGU/API/KVPair
   :tag: KVPair

Now we can create a **Map** class that uses an underlying **List** of **KVPair**.
So the only thing we need is really an internal variable referring to the underlying list.

.. codeinclude:: ChalmersGU/API/ListMap
   :tag: Header

Finding the value for a certain key is easy.
We just iterate through all entries and stop whenever we find a matching key.

.. codeinclude:: ChalmersGU/API/ListMap
   :tag: Get

Setting a value for a given key means to search the list
for a matching key, and then updating the value.
If we cannot find the key, we add a new **KVPair** to the list.

.. codeinclude:: ChalmersGU/API/ListMap
   :tag: Put

In this example we're using a linked list, but we could equally well have used
a dynamic array list.
The only thing we have to think about is to add new pairs at the right location
(beginning or end of the list) -- because linked lists prefer adding at the front,
while array lists rather add to the back of the list.

Other methods can be deferred to the underlying list.

.. codeinclude:: ChalmersGU/API/ListMap
   :tag: EmptySize

How to remove keys from the map
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

There is one problem with this simple map implementation -- how to remove keys from it.
One possibility would be to first search for the index where the key is located,
and then remove that index from the list.

But this would be slightly inefficient, because removing an element from a certain position
takes :math:`O(n)` time in the worst case. So, first we find the position
(which takes :math:`O(n)` time), and then we remove it (which takes another :math:`O(n)` time).
This is double the work than it should be, which is unnecessary.

.. codeinclude:: ChalmersGU/API/ListMap
   :tag: Remove

If the **Iterator** interface would include a method for
removing the "current" element from a list, it would be possible to improve the method.
Our simple API doesn't have that possibility, so we have to stick with the slightly slower version.
However, in the "real" Java API, iterators have a "remove-the-current" method,
so it is possible to optimise removal a little bit.
Implementing the ``remove`` method using teh ``delete`` method of
Java Iterators is left as an exercise to the reader.


Using linked key-value nodes
-----------------------------

An alternative to using an underlying list of key-value pairs,
which is also very easy to implement, is to modify the implementation of linked lists just slightly.
The advantage of this solution is that deletion becomes more efficient.

Instead of using nodes with just one value, we used key-value nodes.

.. codeinclude:: ChalmersGU/API/LinkedMap
   :tag: KVNode

Then the internal structure is very much like our previous
:ref:`linked lists implementation <ListLinked>`.
The private variables are the same (except we use a **KVNode** instead of a **Node**).

.. codeinclude:: ChalmersGU/API/LinkedMap
   :tag: Header

Searching for a key simply means to iterating through the key-value node
until we find a matching key.

.. codeinclude:: ChalmersGU/API/LinkedMap
   :tag: Get

Setting a value for a key is similar:
If the key is in the list, we upate the associated value.
If the key is not in the list, we add a new **KVNode** and increase the list size.

Removing keys
~~~~~~~~~~~~~~

To remove a key-value node, we use the same trick as we did for linked lists:
We iterate through the *previous* node instead of the current one.
This is to be able to reassign the pointers from the previous node to the following node.

So, we use two nodes -- the one to be removed, and the previous one.
The loop searches through the nodes until the one to be removed is found,
and then reassigns the pointer for the previous node to the following one.

.. codeinclude:: ChalmersGU/API/LinkedMap
   :tag: Remove


Linked Maps: Full code
------------------------------------------------

Finally, here is the full source code for the class **LinkedMap**.

.. codeinclude:: ChalmersGU/API/LinkedMap
   :tag: LinkedMap


