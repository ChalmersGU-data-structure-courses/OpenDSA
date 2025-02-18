.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['what-is-a-debugger', 'debugging-terms', 'debugging-a-memory-pool', 'the-eclipse-console', 'conditional-breakpoints'];</script>

.. _debug:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "debug";ODSA.SETTINGS.MODULE_LONG_NAME = "Debugging In Eclipse";ODSA.SETTINGS.MODULE_CHAPTER = "Java Programming, repetition"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:44"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='java';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Jordan Sablan
   :requires: debugging
   :satisfies: Eclipse debugging
   :topic:

Debugging In Eclipse
====================

Debugging In Eclipse
--------------------

In this tutorial we will show basic debugging steps. To begin debugging a
program, select the Debug mode by clicking the icon circled in blue below.

.. odsafig:: Images/Debug1.png
   :width: 600
   :align: center
   :capalign: center
   :figwidth: 90%
   :alt: Debug view

What Is A Debugger?
~~~~~~~~~~~~~~~~~~~


A debugger is a piece of software that allows a programmer to run their code
as if it were executing normally, while allowing stops and pauses at any point to
dump information from the program. It gives unprecendented knowledge to what
happens in each step of the program.
For more information about debuggers, refer to the |external_link1|.

.. |external_link1| raw:: html

   <a href="http://en.wikipedia.org/wiki/Debugger" target = "_blank">Wikipedia entry</a>


Debugging Terms
~~~~~~~~~~~~~~~

1. Resume - Resuming will run the code until another breakpoint is hit or until
the program finishes (see the figure below for icon).

2. Step Into - Stepping into will drop you into the next function called. For
example, stepping into the ``bst.add()`` call in the image above will take you to
the bst class and inside the add method (see the figure below for icon).

3. Step Over - Stepping Over will execute the current line of code and progress
to the next line of code, stopping there (see the figure below for icon).

4. Step Return - Code will be executed until the next return statement
(see the figure below for icon).

5. Breakpoint - A breakpoint is a stopping point in your code. When you set a
breakpoint, the debugger will stop execution anytime it reaches there. You may
add a breakpoint by clicking on the left hand side bar of the Eclipse code
window (see the figure above).

.. odsafig:: Images/DebugList.png
   :align: center
   :capalign: center
   :figwidth: 90%
   :alt: Debug Steps

*\*Note - There are a few more step functions not documented here, you can
learn more about them* |external_link2|.

.. |external_link2| raw:: html

   <a href="http://help.eclipse.org/luna/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Ftasks%2Ftask-stepping.htm" target = "_blank">here</a>


Debugging A Memory Pool
~~~~~~~~~~~~~~~~~~~~~~~

For some programs you may need to analyze byte chunks.
In other words, for a region of memory, what does it represent and
what information does it contain?
Doing so can be difficult due to a combination of factors.
So how do we meaningfully analyze a chunk of bytes?
Consider the following code.

.. codeinclude:: Tutorials/MainByteArrayDebug

This program spawns a 1000 byte array and then places 1 of
27 characters chosen randomly in a position randomly chosen in one of the 1000
places. Suppose you wanted to look at the contents of the byte array. How would
you do this?
Eclipse lets us do it with a memory dump in the **Variables** window.

.. odsafig:: Images/DebugMemoryPool1.png
   :width: 600
   :align: center
   :capalign: center
   :figwidth: 90%
   :alt: Debug Steps

By default Eclipse will interpret a byte as an integer.

.. odsafig:: Images/DebugMemoryPoolRaw1.png
   :align: center
   :capalign: center
   :figwidth: 90%
   :alt: Debug Steps

However there are a number of other ways to interpret the byte values. You
can open the Preferences window by selecting **Window** → **Preferences**.
Then you will be able to select the raw dump information. In this case I
chose ASCII text so I can see the byte values interpreted as ASCII characters.

.. odsafig:: Images/DebugMemoryPoolPreferences.png
   :width: 300
   :height: 350
   :align: center
   :capalign: center
   :figwidth: 90%
   :alt: Debug Steps

Now when we view the memory location we can see the character stored there. We
can also change our display preferences to see the hex value or the
unsigned value as well.

* ASCII Text View Enabled:

  .. odsafig:: Images/DebugValue1.png
     :align: center
     :capalign: center
     :figwidth: 90%
     :alt: ASCII View

* Hex View Enabled:

  .. odsafig:: Images/DebugValue2.png
     :align: center
     :capalign: center
     :figwidth: 90%
     :alt: Hex View

* Unsigned View Enabled:

  .. odsafig:: Images/DebugValue3.png
     :align: center
     :capalign: center
     :figwidth: 90%
     :alt: Unsiged View

* All Three Views Enabled:

  .. odsafig:: Images/DebugValue4.png
     :align: center
     :capalign: center
     :figwidth: 90%
     :alt: All Three Views


The Eclipse Console
~~~~~~~~~~~~~~~~~~~

So you can now view memory, set breakpoints and even step through functions to
the points you care about. Eclipse's **Display**
window is a debug terminal. If the **Display** window is not visible you can
enable it using **Window** → **Show View** → **Display**. Once the **Display** window is open
you can turn on autocomplete by right clicking and selecting **Content Assist**, or
by hitting CTRL-SPACE (similar to the completion assist Eclipse provides for coding).

The **Display** window allows you to write Java code and then execute it. It is a live
window where you can write code, execute methods and demo code without having to
recompile.  Anytime you pause your program, you may access local variables,
change them, and print out information about them. In the below example
I have chosen to print the String *loc* (from the byte array sample code)
out to the output. While this particular example may not appear useful
(as the program will print *loc* anyway), imagine you are debugging a program.
You know a bug occurs, now instead of stopping and
recompiling your program with bug changes, you can rapidly prototype
your replacement code and test it on the spot to ensure success.

.. odsafig:: Images/DebugDisplay1.png
   :align: center
   :capalign: center
   :figwidth: 90%
   :alt: Result Of Running Display

Conditional Breakpoints
~~~~~~~~~~~~~~~~~~~~~~~

Sometimes your bug might appear after a lot of processing. You cannot just set a
breakpoint to a line of code, because that line is executed 10,000 times before
the bug occurs. What you really want is to stop at a line only when some other
condition (i.e., a variable being a certain value, or after X amount of times)
is true. However, extending your code with additional instructions to catch
those conditions may lead to issues later if you forget to remove them.

Fortunately many debuggers have the functionality to only stop at breakpoints
when needed.

Step 1: Create a breakpoint. For this example I will be using the source code
from the memory pool that is randomly filled with characters. I want to hit my
breakpoint if and only if the value of the randomly generated index (*ind*) is 10.

.. odsafig:: Images/DebugConditionalBreakpoint.png
   :align: center
   :capalign: justify
   :figwidth: 90%
   :alt: Breakpoint set

Step 2: Right click on the breakpoint and select **Breakpoint Properties**.

Step 3: You will then be presented with a screen similar to the one in the picture below.
There are two options to halt at a breakpoint: by hitcount and when a condition
is satisfied. For this example I have chosen to stop my program only when the
randomly generated index is equal to 10. Choose the option you would like, if
you choose conditional, create an arbitrary boolean expression to satisfy it.

.. odsafig:: Images/DebugConditional.png
   :align: center
   :capalign: justify
   :figwidth: 90%
   :scale: 50%
   :alt: Breakpoint condition

*Conclusion*:
By making use of conditional breakpoints you can speed up debugging and eliminate
the chance of accidentally progressing too far.
For more information on using breakpoints,
check out |external_link3|.

.. |external_link3| raw:: html

   <a href="https://wiki.eclipse.org/FAQ_How_do_I_set_a_conditional_breakpoint%3F" target = "_blank">Eclipse's official documentation</a>

