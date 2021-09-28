.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: list ADT
   :satisfies: static array-based list
   :topic: Lists

Static Array-Based Lists
===============================

First we give a static implementation for array-based lists,
named ``StaticArrayList``.
This inherits from the :ref:`List ADT <CourseAPI>`,
and so must implement all of the member functions of ``List``.

Unlike normal arrays, lists can change size: we can add elements to and remove from them.
How can this be implemented?
Well, what we *don't* want to do is to create a completely new array every time elements
are added or removed. So instead we will use an underlying array which is larger than we need.

Internal variables
--------------------

Therefore we will need two internal variables:
the underlying array, and a counter telling how much of the array that is actually used.
When we create a new array-list we have to tell what the largest possible capacity is.
Then the underlying array is initialised, and the counter is set to 0 because there are
no elements yet.

.. inlineav:: StaticArrayList-Vars-CON ss
   :long_name: Static Array-based List Variables Slideshow
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/StaticArrayList-Vars-CON.js
   :output: show

|

.. codeinclude:: ChalmersGU/StaticArrayList
   :tag: StaticArrayListInit


Getting and setting values
-----------------------------

Random access to any element in the list is quick and easy.

.. inlineav:: StaticArrayList-Intro-CON ss
   :long_name: Static Array-based List Intro Slideshow
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/StaticArrayList-Intro-CON.js
   :output: show

As you can see below, there are no loops in the methods
``get`` and ``set``, which means that both 
require :math:`\Theta(1)` time.

.. codeinclude:: ChalmersGU/StaticArrayList
   :tag: StaticArrayListGetSet


Adding elements
-------------------

Because the array-based list implementation is defined to store list
elements in contiguous cells of the array, the ``add``
and ``remove`` methods must maintain this property.

Appending elements at the tail of an array-based list is super-fast.

.. inlineav:: StaticArrayList-Append-CON ss
   :long_name: Static Array-based List Append Slideshow
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/StaticArrayList-Append-CON.js
   :output: show


However, adding an element at the head of the list requires shifting
all existing elements in the array by one position toward the tail.

.. inlineav:: StaticArrayList-Add-CON ss
   :long_name: Static Array-based List Insertion Slideshow
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/StaticArrayList-Add-CON.js
   :output: show

Therefore, if we want to add an element at position :math:`i`, then
:math:`n - i - 1` elements must shift toward the tail to leave room for the new element.
In the worst case, adding elements requires moving all :math:`n` elements,
which is :math:`\Theta(n)`.

.. codeinclude:: ChalmersGU/StaticArrayList
   :tag: StaticArrayListAdd


Add Practice Exericse
~~~~~~~~~~~~~~~~~~~~~~~~

.. avembed:: Exercises/ChalmersGU/StaticArrayList-Add-PRO.html ka
   :long_name: Array-based List Add Exercise


Removing elements
----------------------------

Removing an element from the head of the list is
similar to adding in that all remaining elements must shift.
But now we have to shift toward the head to fill in the gap,
instead of toward the tail.
If we want to remove the element at position :math:`i`, then
:math:`n - i - 1` elements must shift toward the head, as shown in the
following slideshow. 

.. inlineav:: StaticArrayList-Remove-CON ss
   :long_name: Static Array-based List Remove
   :links: AV/ChalmersGU/CGU-Styles.css
   :scripts: AV/ChalmersGU/StaticArrayList-Remove-CON.js
   :output: show

In the worst case, insertion or removal each requires moving all
:math:`n` elements, which is :math:`\Theta(n)`.

.. codeinclude:: ChalmersGU/StaticArrayList
   :tag: StaticArrayListRemove


Remove Practice Exericise
~~~~~~~~~~~~~~~~~~~~~~~~~

.. avembed:: Exercises/ChalmersGU/StaticArrayList-Remove-PRO.html ka
   :long_name: Array-based List Remove Exercise


Static Array-based List Summary Questions
------------------------------------------------

.. avembed:: Exercises/ChalmersGU/StaticArrayList-Summary-QUIZ.html ka
   :long_name: Static Array-based List Summary


Static Array-based List: Full code
------------------------------------------------

Finally, here is the full source code for the class ``StaticArrayList``.

.. codeinclude:: ChalmersGU/StaticArrayList
   :tag: StaticArrayList

