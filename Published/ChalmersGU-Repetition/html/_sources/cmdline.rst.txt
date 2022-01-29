.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = ['what-s-a-cli'];</script>

.. _cmdline:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "cmdline";ODSA.SETTINGS.MODULE_LONG_NAME = "Command Line Basics";ODSA.SETTINGS.MODULE_CHAPTER = "Java Programming, repetition"; ODSA.SETTINGS.BUILD_DATE = "2022-01-29 13:39:59"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='java';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Jordan Sablan
   :requires:
   :satisfies: Commmand line
   :topic:

Command Line Basics
===================

What's A CLI?
-------------

A Command Line Interface or CLI is a simple text only interface. A user provides
a command with or without some additional information and then the command is
executed.

Basics:

1. When you execute a command you must type the name of the command. In addition
the command must be located in your ``PATH``.

   - What's a ``PATH``? A ``PATH`` is a list of directories where executables will be located. Common directories in ``PATH`` are ``/bin/`` and ``/usr/bin/``.

2. When you execute a command, you often will need to provide additional
information in the form of arguments.

   +----------------------------+------------------------------------------------------------------------------------------------+
   | Common arguments           | Meanings                                                                                       |
   +============================+================================================================================================+
   |       ``-h``               | displays help information                                                                      |
   +----------------------------+------------------------------------------------------------------------------------------------+
   |       ``-v``               | increases information output, v is for verbosity                                               |
   +----------------------------+------------------------------------------------------------------------------------------------+
   |       ``--version``        | displays version information                                                                   |
   +----------------------------+------------------------------------------------------------------------------------------------+

   |

   Below is an example of running

   ::

      file -h

   .. odsafig:: Images/hexample.png
      :alt: The argument -h usually displays basic help information

      The argument -h is often used to display basic help information


3. If you can not get enough information from the -h argument you can
   make use of the ``man`` (for manual) command ([*]_). The ``man``
   command takes the name of a command as an argument and brings up
   its manual page. This contains a more in depth explanation of the
   command and its usage.

   Below is an example of running

   ::

      man ls

   .. odsafig:: Images/manexample.png
      :alt: An example of the man command
      :scale: 70%

      The ``man`` command is extremely useful for learning about the syntax/usage of a command

Further reading:

This was a very brief introduction to the terminal. It was included
only to give some context to further chapters that make use of
arguments. For more information feel free to |external_link|.

.. |external_link| raw:: html

   <a href="https://help.ubuntu.com/community/UsingTheTerminal" target="_blank">check this resource</a>

.. [*] The man command should be available in any GNU-style terminal. If you are using Windows the native Command Prompt does not have a ``man`` command.

|

.. Does not work with new LTI support.
.. Give the terminal a try.

.. .. avembed:: AV/Tutorials/terminal.html ka
      :long_name: Terminal

