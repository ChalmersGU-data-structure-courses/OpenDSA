.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['linked-dequeue', 'comparison-of-array-based-and-linked-queues', 'stack-and-queue-summary-questions'];</script>

.. _QueueLinked:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "QueueLinked";ODSA.SETTINGS.MODULE_LONG_NAME = "Linked Queues";ODSA.SETTINGS.MODULE_CHAPTER = "Linear Structures"; ODSA.SETTINGS.BUILD_DATE = "2021-10-12 13:09:13"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/List/lqueueCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer
   :requires: queue
   :satisfies: linked queue
   :topic: Lists

Linked Queues
=============

Linked Queues
-------------

The linked queue implementation is a straightforward adaptation
of the linked list.
Here is the linked queue class declaration.

.. codeinclude:: Lists/LQueue
   :tag: LQueue1,LQueue2

|

.. inlineav:: lqueueIntroCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked Queue Intro
   :output: show    
   
|

.. inlineav:: lqueueEnqueueCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked Queue Enqueue
   :output: show   
   
.. avembed:: Exercises/List/LqueueEnqueuePRO.html ka
   :module: QueueLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Linked Queue Enqueue Exercise


Linked Dequeue
--------------

.. inlineav:: lqueueDequeueCON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked Queue Dequeue
   :output: show 
   
.. avembed:: Exercises/List/LqueueDequeuePRO.html ka
   :module: QueueLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Linked Queue Dequeue Exercise


Comparison of Array-Based and Linked Queues
-------------------------------------------

All member functions for both the array-based and linked queue
implementations require constant time.
The space comparison issues are the same as for the equivalent stack
implementations.
Unlike the array-based stack implementation, there is no convenient
way to store two queues in the same array,
unless items are always transferred directly from one queue to the other.

Stack and Queue Summary Questions
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. avembed:: Exercises/List/StackQSumm.html ka
   :module: QueueLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Stack/Queue Summary Exercise

.. odsascript:: AV/List/llist.js
.. odsascript:: AV/List/lqueueIntroCON.js
.. odsascript:: AV/List/lqueueEnqueueCON.js
.. odsascript:: AV/List/lqueueDequeueCON.js
