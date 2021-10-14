.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: list ADT
   :satisfies: stack ADT; array-based stack; stack
   :topic: Lists

Stacks
======

Stack Terminology and Implementation
------------------------------------

The :term:`stack` is a list-like structure
in which elements may be inserted or removed from only one end.
While this restriction makes stacks less flexible than lists,
it also makes stacks both efficient (for those operations they can do)
and easy to implement.
Many applications require only the limited form of
insert and remove operations that stacks provide.
In such cases, it is more efficient to use the simpler stack data
structure rather than the generic list.

Despite their restrictions, stacks have many uses.
Thus, a special vocabulary for stacks has developed.
Accountants used stacks long before the invention of the computer.
They called the stack a ":term:`LIFO`" list,
which stands for "Last-In, First-Out."
Note that one implication of the LIFO policy is that stacks
remove elements in reverse order of their arrival.

The accessible element of the stack is called the ``top`` element.
Elements are not said to be inserted, they are :term:`pushed <push>`
onto the stack.
When removed, an element is said to be :term:`popped <pop>` from the
stack.
Here is our :term:`ADT` for stacks:

.. codeinclude:: ChalmersGU/API
   :tag: StackADT

As with lists, there are many variations on stack implementation.
The two main approaches are the :term:`array-based stack`
and the :ref:`linked stack <linked stack> <StackLinked>`, 
which are analogous to array-based and linked lists, respectively.


Dynamic Array-Based Stacks
------------------------------

The dynamic array-based stack contains an internal array (which will grow and shrink dynamically),
and the index of the **top** of the stack.
Or actually, the index is for the next free slot in the array,
which at the same time is the size of the stack.
   
.. codeinclude:: ChalmersGU/DynamicArrayStack
   :tag: DynamicArrayStackInit

The array-based stack implementation is essentially
a simplified version of the array-based list.
The only important design decision to be made is which end of the
array should represent the top of the stack.

.. inlineav:: DynamicArrayStack-Top-CON ss
   :long_name: Array stack top position slideshow
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/DynamicArrayStack-Top-CON.js
   :output: show

Pushing to the Stack
----------------------

.. inlineav:: DynamicArrayStack-Push-CON ss
   :long_name: Array stack push slideshow
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/DynamicArrayStack-Push-CON.js
   :output: show

.. codeinclude:: ChalmersGU/DynamicArrayStack
   :tag: DynamicArrayStackPush

.. avembed:: Exercises/ChalmersGU/DynamicArrayStack-Push-PRO.html ka
   :long_name: Array-based Stack Push Exercise


Popping from the Stack
--------------------------

.. inlineav:: DynamicArrayStack-Pop-CON ss
   :long_name: Array stack pop slideshow
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/DynamicArrayStack-Pop-CON.js
   :output: show

.. codeinclude:: ChalmersGU/DynamicArrayStack
   :tag: DynamicArrayStackPop

.. avembed:: Exercises/ChalmersGU/DynamicArrayStack-Pop-PRO.html ka
   :long_name: Array-based Stack Pop Exercise


Array-based stacks: Full implementation
--------------------------------------------

As you hopefully have noticed, the code for stacks is very similar to the code for lists.
E.g., the internal variables are exactly the same, and the resizing method doesn't change at all.
The main difference is that stacks are even simpler to implement than their list counterparts.

Here is a complete implementation for
the (dynamic) array-based stack class.

.. codeinclude:: ChalmersGU/DynamicArrayStack
   :tag: DynamicArrayStack
