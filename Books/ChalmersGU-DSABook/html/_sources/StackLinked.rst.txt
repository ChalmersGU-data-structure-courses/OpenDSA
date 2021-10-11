.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['linked-stack-implementation', 'linked-stack-push', 'linked-stack-pop', 'linked-stacks:-full-implementation', 'comparison-of-array-based-and-linked-stacks'];</script>

.. _StackLinked:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "StackLinked";ODSA.SETTINGS.MODULE_LONG_NAME = "Linked Stacks";ODSA.SETTINGS.MODULE_CHAPTER = "Linear Structures"; ODSA.SETTINGS.BUILD_DATE = "2021-10-11 15:14:50"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/List/lstackCON.css
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
A header node is not used because no special-case code is required
for lists of zero or one elements.

Here is a visual representation for the linked stack.

.. inlineav:: lstackDiagramCON dgm
   :align: center   

   
.. codeinclude:: ChalmersGU/LinkedStack
   :tag: LinkedStackInit



Linked Stack Push
~~~~~~~~~~~~~~~~~

.. inlineav:: lstackPushCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked stack push
   :output: show

.. codeinclude:: ChalmersGU/LinkedStack
   :tag: LinkedStackPush

.. avembed:: Exercises/List/LstackPushPRO.html ka
   :module: StackLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Linked Stack Push Exercise


Linked Stack Pop
----------------

.. inlineav:: lstackPopCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked stack pop
   :output: show
   
.. codeinclude:: ChalmersGU/LinkedStack
   :tag: LinkedStackPop

.. avembed:: Exercises/List/LstackPopPRO.html ka
   :module: StackLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Linked Stack Pop Exercise


Linked stacks: Full implementation
--------------------------------------------

Here is the complete linked stack implementation.

.. codeinclude:: ChalmersGU/LinkedStack
   :tag: LinkedStack

   
Comparison of Array-Based and Linked Stacks
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

All operations for the array-based and linked stack implementations
take constant time, so from a time efficiency perspective,
neither has a significant advantage.
Another basis for comparison is the total space
required.
The analysis is similar to that done for list implementations.
The array-based stack must declare a fixed-size array initially, and
some of that space is wasted whenever the stack is not full.
The linked stack can shrink and grow but requires the overhead of a
link field for every element.

When implementing multiple stacks, sometimes you can take advantage of
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
in the middle of the array will be exhausted quickly.

.. _TwoArrayStacks:

.. inlineav:: lstackTwostackCON dgm
   :align: center

.. odsascript:: AV/List/llist.js
.. odsascript:: AV/List/lstackDiagramCON.js
.. odsascript:: AV/List/lstackPushCON.js
.. odsascript:: AV/List/lstackPopCON.js
.. odsascript:: AV/List/lstackTwostackCON.js
