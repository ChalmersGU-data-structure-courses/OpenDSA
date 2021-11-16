.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['enqueueing-elements', 'dequeueing-elements', 'linked-queue-full-implementation', 'comparison-of-array-based-and-linked-queues', 'stack-and-queue-summary-questions'];</script>

.. _QueueLinked:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "QueueLinked";ODSA.SETTINGS.MODULE_LONG_NAME = "Linked Queues";ODSA.SETTINGS.MODULE_CHAPTER = "Linear Structures"; ODSA.SETTINGS.BUILD_DATE = "2021-11-16 15:06:47"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/ChalmersGU/CGU-Styles.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: queue
   :satisfies: linked queue
   :topic: Lists

Linked Queues
=============

Linked Queues
-------------

The linked queue implementation is an adaptation of the linked list.
The only thing is that we have to add a pointer to the rear node
in the queue, to be able to add new elements efficiently.

.. inlineav:: LinkedQueue-Intro-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked Queue Intro
   :output: show    

|

.. codeinclude:: ChalmersGU/LinkedQueue
   :tag: LinkedQueueInit

Enqueueing Elements
-------------------------

.. inlineav:: LinkedQueue-Enqueue-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked Queue Enqueue
   :output: show   

|

.. codeinclude:: ChalmersGU/LinkedQueue
   :tag: LinkedQueueEnqueue

|

.. avembed:: Exercises/ChalmersGU/LinkedQueue-Enqueue-PRO.html ka
   :module: QueueLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Linked Queue Enqueue Exercise


Dequeueing Elements
-------------------------

.. inlineav:: LinkedQueue-Dequeue-CON ss
   :points: 0.0
   :required: False
   :threshold: 1.0
   :long_name: Linked Queue Dequeue
   :output: show 

|

.. codeinclude:: ChalmersGU/LinkedQueue
   :tag: LinkedQueueDequeue

|
   
.. avembed:: Exercises/ChalmersGU/LinkedQueue-Dequeue-PRO.html ka
   :module: QueueLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Linked Queue Dequeue Exercise


Linked Queue, Full Implementation
-------------------------------------

Here is the full implementation for linked queues.

.. codeinclude:: ChalmersGU/LinkedQueue
   :tag: LinkedQueue


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
----------------------------------

.. avembed:: Exercises/ChalmersGU/StackQueue-Summary-QUIZ.html ka
   :module: QueueLinked
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Stack/Queue Summary Exercise

.. odsascript:: AV/ChalmersGU/LinkedQueue-Intro-CON.js
.. odsascript:: AV/ChalmersGU/LinkedQueue-Enqueue-CON.js
.. odsascript:: AV/ChalmersGU/LinkedQueue-Dequeue-CON.js
