.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Cliff Shaffer
   :satisfies: algorithm analysis chapter introduction
   :topic: Algorithm Analysis

Chapter Introduction: Algorithm Analysis
========================================

How long will a program take when I run it on a dataset ten times as large?
If a particular program is slow, is it badly implemented or is it solving a hard problem?
What order of improvement can I expect if I switch to a better algorithm?
Questions like these ask us to consider the difficulty of a problem and the efficiency of approaches to solving it.

This chapter introduces the motivation, basic notation, and fundamental techniques of algorithm analysis.
We focus on a methodology known as :term:`asymptotic algorithm analysis`, or simply :term:`asymptotic analysis`.

Asymptotic analysis estimates the resource consumption of an algorithm, called its :term:`complexity`.
Here, resource consumption can mean runtime, memory use, API calls, or any other measure.
Instead of computing this resource consumption exactly, asymptotic analysis is only interested in its :term:`growth rate` (also called :term:`order of growth`).
The growth rate is what determines the resource consumption for large inputs.
Thankfully, growth rate expressions are relatively easy to compare.
This allows us decide which of two algorithms is better at solving the same problem.
Asymptotic analysis also gives algorithm designers a tool for estimating whether a proposed solution is likely to meet the resource constraints for a problem before they implement an actual program.

After reading this chapter, you should understand:

* The concept of :term:`complexity` of an algorithm, the resource usage of an algorithm as a function of an input parameter.
  Different kinds of complexity such as :term:`worst-case<worst case>` and :term:`average-case<average case>`.

* The concept of :term:`growth rate` or :term:`order of growth` of a (mathematical) function.
  How to compute and compare growth rates of functions.
  Notations such as :term:`big-Oh<big-Oh notation>` to describe upper and lower bounds of growth rates.

* The :term:`asymptotic complexity` of an algorithm, which is the growth rate of its complexity.
  Sometimes, this is just called the growth rate of the algorithm.

* The difference between the asymptotic complexity of an :term:`algorithm` (or program) and that of a :term:`problem`.
  The latter is the best asymptotic complexity over all algorithms that solve the problem.

The chapter concludes with a brief discussion of the practical difficulties encountered when empirically measuring the cost of a program, and some principles for code tuning to improve program efficiency.
