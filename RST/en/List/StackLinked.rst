.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: stack ADT
   :satisfies: linked stack
   :topic: Lists

Linked Stacks
=============

Linked Stack Implementation
---------------------------

The linked stack implementation is quite simple.
Elements are inserted and removed only from the head of the list.
Here is a visual representation for the linked stack.

.. inlineav:: LinkedStack-Overview-CON dgm
   :long_name: Linked Stack Overview
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/LinkedStack-Overview-CON.js
   :align: center   

|

.. codeinclude:: ChalmersGU/LinkedStack
   :tag: LinkedStackInit

Stack nodes are exactly the same as the :ref:`linked list nodes <LinkedList>` we saw earlier.

.. codeinclude:: ChalmersGU/LinkedStack
   :tag: LinkedStackNode


Linked Stack Push
~~~~~~~~~~~~~~~~~

.. inlineav:: LinkedStack-Push-CON ss
   :long_name: Linked Stack Push
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/LinkedStack-Push-CON.js
   :output: show

|

.. codeinclude:: ChalmersGU/LinkedStack
   :tag: LinkedStackPush

|

.. avembed:: Exercises/List/LstackPushPRO.html ka
   :long_name: Linked Stack Push Exercise


Linked Stack Pop
----------------

.. inlineav:: LinkedStack-Pop-CON ss
   :long_name: Linked Stack Pop
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/LinkedStack-Pop-CON.js
   :output: show

|

.. codeinclude:: ChalmersGU/LinkedStack
   :tag: LinkedStackPop

|

.. avembed:: Exercises/List/LstackPopPRO.html ka
   :long_name: Linked Stack Pop Exercise


Linked stacks: Full implementation
--------------------------------------------

Here is the complete linked stack implementation.

.. codeinclude:: ChalmersGU/LinkedStack
   :tag: LinkedStack

   
Comparison of Array-Based and Linked Stacks
--------------------------------------------

All operations for the array-based and linked stack implementations
take constant time, so from a time efficiency perspective,
neither has a significant advantage.
Another basis for comparison is the total space
required.
The analysis is similar to that done for list implementations.
The array-based stack must allocate an array with more elements than actually needed, and
some of that space is wasted whenever the stack is not full.
The linked stack can shrink and grow but requires the overhead of a
``next`` field for every element.

Implementing two stacks using one array
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

If you need to use two stacks at the same time, you can take advantage of
the one-way growth of the array-based stack
by using a single array to store two stacks.
One stack grows inward from each end as illustrated by the figure
below, hopefully leading to less wasted space.
However, this only works well when the space requirements of the two
stacks are inversely correlated.
In other words, ideally when one stack grows, the other will shrink.
This is particularly effective when elements are taken from
one stack and given to the other.
If instead both stacks grow at the same time, then the free space
in the middle of the array will be exhausted quickly,
and the array has to be resized.

.. _TwoArrayStacks:

.. inlineav:: LinkedStack-Twostack-CON dgm
   :long_name: Two Stacks in the same Array
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/LinkedStack-Twostack-CON.js
   :align: center   
