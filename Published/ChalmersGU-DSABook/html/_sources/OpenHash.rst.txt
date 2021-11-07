.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['separate-chaining-or-open-hashing', 'alternatives-to-a-linked-list', 'resizing-is-important', 'implementing-separate-chaining', 'resizing-the-internal-table', 'exercise', 'full-implementation'];</script>

.. _OpenHash:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "OpenHash";ODSA.SETTINGS.MODULE_LONG_NAME = "Separate Chaining";ODSA.SETTINGS.MODULE_CHAPTER = "Hash Tables"; ODSA.SETTINGS.BUILD_DATE = "2021-11-07 21:13:21"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='pseudo';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:



.. odsalink:: AV/Hashing/openhashCON.css
.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata:: 
   :author: Cliff Shaffer, Peter Ljunglöf
   :requires: hash function
   :satisfies: open hashing
   :topic: Hashing

Separate Chaining
=================

Separate Chaining, or Open Hashing
----------------------------------

While the goal of a hash function is to minimize collisions,
some collisions are unavoidable in practice.
Thus, hashing implementations must include some form of collision
resolution policy.
Collision resolution techniques can be broken into two classes:
:term:`separate chaining`
(also called :term:`open hashing <open hash system>`) and
:term:`open addressing`
(also called :ref:`closed hashing  <HashCSimple>`).
(Yes, it is confusing when "open hashing" means the opposite of
"open addressing", but unfortunately, that is the way it is.)
The difference between the two has to do with whether
collisions are stored outside the table (separate chaining/open hashing), or
whether collisions result in storing one of the records at another
slot in the table (open addressing/closed hashing).

The simplest form of separate chaining defines each slot in the
hash table to be the head of a linked list.
All records that hash to a particular slot are placed on that slot's
linked list.
The following figure illustrates a hash table where each
slot points to a linked list to hold the records associated with that slot.
The hash function used is the simple mod function.

.. inlineav:: openhashCON dgm

Records within a slot's list can be ordered in several ways:
by insertion order, by key value order, or by frequency-of-access
order.
Ordering the list by key value provides an advantage in the case of an 
unsuccessful search, because we know to stop searching the list once we
encounter a key that is greater than the one being searched for.
If records on the list are unordered or ordered by frequency, then an
unsuccessful search will need to visit every record on the list.

Given a table of size :math:`M` storing :math:`N` records,
the hash function will (ideally) spread the records evenly among the
:math:`M` positions in
the table, yielding on average :math:`N/M` records for each list.
This value, :math:`N/M`, is commonly called the :term:`load factor`.

Assuming that the table has more slots than there are records to be
stored, we can hope that few slots will contain more than one record.
In the case where a list is empty or has only one record,
a search requires only one access to the list.
Thus, the average cost for hashing should be :math:`\Theta(1`).
However, if clustering causes many records to hash to only a few of
the slots, then the cost to access a record will be much higher
because many elements on the linked list must be searched.

Separate chaining is most appropriate when the hash table is kept in main
memory, with the lists implemented by a standard in-memory linked list.
Storing an separate chaining hash table on disk in an efficient way is
difficult, because members of a given linked list might be stored on
different disk blocks.
This would result in multiple disk accesses when searching for a
particular key value, which defeats the purpose of using hashing.

There are similarities between separate chaining and
:term:`Binsort`.
One way to view separate chaining is that each record is simply placed in a bin.
While multiple records may hash to the same bin, this initial binning
should still greatly reduce the number of records accessed by
a search operation.
In a similar fashion, a simple Binsort reduces the number of
records in each bin to a small number that can be sorted in some
other way.

Alternatives to a linked list
----------------------------------

There is nothing that requires us to use a linked list as the underlying data structure,
it could be a dynamic array or a balanced search tree too.
(In fact, Java 8's hash tables use a combination of linked lists and balanced trees).

Conceptually, a hash table can use any kind of collection data structure --
the only thing that the actual array does is to partition the large collection into
:math:`M` disjoint collections.
If the hash function is good and distributes the objects evenly among the bins, all operations
will become :math:`M` times faster (because the bins are :math:`M` times smaller than
the original large collection).

Resizing is important
~~~~~~~~~~~~~~~~~~~~~~~

Just as for dynamic arrays, it is important that we resize the internal table when it becomes
too large (or too small). That is, we change the size :math:`M` so that it is proportional to the
number of table entries.

If :math:`M` is always proportional to the number of entries, *and* if we have a good hash function,
the number of elements in a bin will remain approximately constant. And then all operations
will be expected constant time.


Implementing Separate Chaining
----------------------------------

Here we will show the implementation of a **hash map**.
Implementing a hash set is very similar, and even simpler.

A separate chaining hash map consists of an internal array of key-value maps.
We don't have to specify what kind of maps just yet,
but we will use a simple :ref:`linked list map  <ListMap>` because the idea
is that each bin will only contain a couple of entries.
We also have to remember the collected size of the map,
otherwise we would have to calculate a sum every time
``size()`` or ``isEmpty()`` would be called.

To initialise the table, we first create the internal array of the initial
minimum capacity, and then let every array cell be a new empty linked list map.
Note that we put the initialisation in a private method of its own,
so that we can reuse it when resizing the table.

.. codeinclude:: ChalmersGU/SeparateChainingHashMap
   :tag: Header

To get the value for a key, we called the table index for the key, and then
look up the key in the underlying map at that position.

.. codeinclude:: ChalmersGU/SeparateChainingHashMap
   :tag: Get

To set a value for a key, we calculate the table index for the key,
and then we set the value for the key in the underlying map.
If if the old value for the key is null, the key wasn't in the hash table
previously, and then we know that the number of key/value pairs have been increased.
We also have to check if the load factor becomes too large, and then we make the
internal table larger by a factor. 

.. codeinclude:: ChalmersGU/SeparateChainingHashMap
   :tag: Put

To remove a value, we do the same: find the underlying map for the key, and
remove the key/value pair.
If we actually removed the key (i.e., if it existed in the map), then
we decrease the map size.
We also check if the table becomes too sparse, and then decrease the internal table by a factor.

.. codeinclude:: ChalmersGU/SeparateChainingHashMap
   :tag: Remove

The constants for min and max load factors, and the resizing factor, are a bit arbitrary.
With the following values, we ensure that the table on average contains between 0.5 and 2
entries per table index. Increasing these values leads to more better memory usage,
but also more conflicts (i.e., longer search times). 
Also, we enlarge by 50%, or reduce by 33%, each time we resize the table.
Increasing this value means that resizing will happen less often, but instead the memory usage will increase.

.. codeinclude:: ChalmersGU/SeparateChainingHashMap
   :tag: Constants

The load factor :math:`N/M` is easy to calculate.

.. codeinclude:: ChalmersGU/SeparateChainingHashMap
   :tag: LoadFactor


Resizing the internal table
----------------------------------

When we resize the internal table, it is very important that we *do not keep*
the old hash indices for the keys, because they will not be the same
after resizing.
Instead we save the current internal table to a temporary variable,
and reinitialise the table to the new capacity.
Then we iterate through all bins and entries in the old table, and
simply insert them again into the new resized table.

.. codeinclude:: ChalmersGU/SeparateChainingHashMap
   :tag: Resize


Exercise
----------------------------------

.. avembed:: Exercises/Hashing/OpenHashPRO.html ka
   :module: OpenHash
   :points: 1.0
   :required: True
   :threshold: 5
   :exer_opts: JXOP-debug=true&amp;JOP-lang=en&amp;JXOP-code=pseudo
   :long_name: Separate Chaining Proficiency Exercise


Full implementation
-----------------------

Here is the full implementation for separate chaining hash tables.

.. codeinclude:: ChalmersGU/SeparateChainingHashMap
   :tag: SeparateChainingHashMap



.. odsascript:: AV/Hashing/openhashCON.js
