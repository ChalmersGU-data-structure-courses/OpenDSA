.. raw:: html

   <script>ODSA.SETTINGS.MODULE_SECTIONS = [''];</script>

.. _eclipseparameters:


.. raw:: html

   <script>ODSA.SETTINGS.DISP_MOD_COMP = true;ODSA.SETTINGS.MODULE_NAME = "eclipseparameters";ODSA.SETTINGS.MODULE_LONG_NAME = "Using Parameters in Eclipse";ODSA.SETTINGS.MODULE_CHAPTER = "Java Programming, repetition"; ODSA.SETTINGS.BUILD_DATE = "2022-11-16 09:58:44"; ODSA.SETTINGS.BUILD_CMAP = true;JSAV_OPTIONS['lang']='en';JSAV_EXERCISE_OPTIONS['code']='java';</script>


.. |--| unicode:: U+2013   .. en dash
.. |---| unicode:: U+2014  .. em dash, trimming surrounding whitespace
   :trim:


.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Jordan Sablan
   :requires: Command line parameters
   :satisfies: Eclipse command line parameters
   :topic:

===========================
Using Parameters in Eclipse
===========================

Using Parameters in Eclipse
~~~~~~~~~~~~~~~~~~~~~~~~~~~

Sometimes your projects developed in Eclipse need to use command line parameters.
You can |external_link2| of the material.

.. |external_link2| raw:: html

   <a href="https://www.youtube.com/watch?v=0tpsXmchBfk&feature=youtu.be" target="_blank">view a tutorial</a>

1. Open your project and select the drop down arrow next to the Green Run Arrow
(circled below). If you cannot see this arrow then you may be in the wrong
Eclipse perspective. ``Select Window`` -> ``Open Perspective`` -> ``Java``.

.. odsafig:: Images/ParametersStep1.png
   :width: 600
   :align: center
   :capalign: justify
   :figwidth: 90%
   :alt: Step 1

2. You will now see a ``Run Configurations`` window. Select the ``Java
Application`` section. Now depending on whether or not you have ran this project
previously you may or may not have a configuration saved. If you have one saved
already then continue on to Step 3. Otherwise select ``Java Application`` and
right click on ``new``.

.. odsafig:: Images/ParametersStep2.png
   :width: 600
   :align: center
   :capalign: justify
   :figwidth: 90%
   :alt: Step 2

3. You will now have a profile inside the ``Java Application`` window. Select the
``arguments`` tab and add the command line arguments that you would like to run
(in this case I am adding the -h argument).

.. odsafig:: Images/ParametersStep3.png
   :width: 600
   :align: center
   :capalign: justify
   :figwidth: 90%
   :alt: Step 3

4. You may now hit Run and the program will launch with the parameters you
provided. The Console window will display any output from your program.

.. odsafig:: Images/ParametersStep4.png
   :width: 600
   :align: center
   :capalign: justify
   :figwidth: 90%
   :alt: Step 4

5. To change the parameters you can either create a new profile by starting from step one,
   or edit an existing profile.

