.. This file is part of the OpenDSA eTextbook project. See
.. http://opendsa.org for more details.
.. Copyright (c) 2012-2020 by the OpenDSA Project Contributors, and
.. distributed under an MIT open source license.

.. avmetadata::
   :author: Nick Smallbone
   :satisfies: string builder
   :topic: String builder

Building a string
=================

Maybe have an introductory section about reading a file? Then dynamic
arrays can be a relatively short later section.

Split into two sections? One for Java programmers and one for Python
programmers.

Almost every programming language supports arrays. Java is no
exception. Here is an example of using arrays in Java:

int[] arr = {3,1,4,1,5,9}	arr = [3,1,4,1,5,9]
...println(arr[2])		print(arr[2])
arr[2] = 8		arr[2] = 8
...println(arr[2])		print(arr[2])

Arrays in Java have a fixed size: you decide the size when you create
the array, and there is no way to resize it afterwards. That is often
fine, but sometimes you don't know ahead of time how big an array
should be. For example, the user might be typing in some text, and you
need an array big enough to store the whole text. In this situation,
we need adynamic array.

A dynamic array is like an array, except that its size can change. A
simple dynamic array class might implement the following operations:
<<code>>

In Java, the ArrayList class implements a dynamic array. Here is an
example of it in action. You can do all the same things as an array,
plus make the array larger:

In this section we shall study how dynamic arrays work: given a
programming language (such as Java) which only supports fixed-length
arrays, how can we make a class for variable-length arrays (like
ArrayList)? The ideas we see in this section show up in many of the
data structures in this book.

An example program: Suppose we want to write a program that reads in
an input text and reverses it. For example, if we type in the input
"hello, world", it should print out "dlrow ,olleh". Now, Java has the
method ... that reads the entire input into a String, but to
illustrate how dynamic arrays work, we will not use that method;
rather, we will read the input a character at a time. (In fact, the
... method uses a dynamic array internally.) Suppose that we have
already defined two methods: boolean endOfInput() - returns true if
all characters have been read char nextChar() - reads and returns the
next character from the input.

Then here is how we can use a dynamic array to read in and reverse the
input:

The idea is simply to read in each character in turn, adding it to the
end of the dynamic array. Then loop through the contents of the array
in reverse order, printing out each character.

We would like this program to be reasonably efficient. We will look in
more detail how to measure the efficiency of algorithms in Section ...
But for now, let us say the following:

* It should be possible to run the program on inputs of, say, several megabytes in a few seconds.
* The runtime should _scale linearly_: if we run the program on an
  input ten times as big, it should take ten times as long.

A first attempt: Let us have a first go at implementing the
DynamicArray class.

The idea is that a DynamicArray should just contain an array. The get
and set methods should just read and write that array. Whenever we
want to add an element to the array, we create a new array, one
element larger than the old array, then copy the contents of the
dynamic array there, then store the new element at the end. <<code>>

If you compile and run the example program, using this implementatio
nof dynamic arrays, you should see that it works: the string is
successfully reverse. So indeed this is a correct implementation of
dynamic arrays. However, it is a bad one. Why?

The problem is that this implementation is inefficient. If we run the
example program on a large input, it takes a very long time indeed to
run. For example, using the text of the 19.. Encyclopaedia Britannica
(XXX MB), it takes XXX weeks! Hardly a reasonable length of time to
wait.

Why does it take so long? To find out, we will analyse the behaviour
of the program. On a file of 100MB, the program makes 100 million
calls to add(). Now, every time we call add(), a new array is created,
and the old array is copied to the new array. However, copying this
array takes time. And the longer the array, the longer it takes to
copy it. In particular, if the old array has length n, then the line

newArray[i] = array[i]

is executed n times. So, if the array is twice as big (2n), we would
expect this copying to take twice as long (the line is executed 2n
times instead of n).

Suppose we run the file-reversing program on the input "hello". How
many times is the line

newArray[i] = array[i]

executed in total?

* First we read in 'h'. oldArray has a length of 0, so the line is not executed.
* Then we read in 'e'. oldArray contains "h", so 1 element is copied.
* Then we read in 'l'. oldArray contains "he", so 2 elements are copied.
* Then we read in another 'l'. oldArray contains "hel", so 3 elements are copied.
* Then we read in 'o'. oldArray contains "hell", so 4 elements are copied.

In total we copy 0+1+2+3+4 = 10 elements, which doesn't seem too much.

What if we read in a string of length 10, say "gadzooks!!"? Then we
will copy 0+1+2+3+4+5+6+7+8+9 = 45 characters.

What if we read in a string of length n? Generalising, we will copy
0+1+2+...+(n-1) characters. This number is equal to n(n-1)/2. (You can
check that this formula works for the examples above.)

For the Encyclopaedia Britannica, n = 100 million = 10^8, so the total
is 10^8(10^8-1)/2 = ... characters copied. A very big number!

A modern processor can carry out approximately 10^10 operations per
second, running at full speed on a carefully-written program. If each
character copy is one operation, then how long would it take to read
in the Encyclopaedia Britannica?

Answer: approximately 5 . 10^5 seconds, or 1 week.

How about the contents of Wikipedia (100G)?

Answer: approximately 5 . 10^11 seconds, or a million weeks (!), or
20,000 years.

We mentioned above that we would like the runtime of the file-reading
program to scale linearly: if we run it on an input ten times as big,
it should take ten times as long. Does our current program satisfy
this?

No: the runtime is proportional to n(n-1)/2 ~ n^2/2, for an input of n
bytes. If the input is 10 times as big, 10n bytes, then the runtime
becomes ~ (10n)^2/2 = 100n^2/2, that is it grows by a factor of 100.
Because the runtime is proportional to the _square_ of the input size,
we say that the program has _quadratic_ growth. In fact, this is why
it does not work well for large inputs. We will see more about linear
and quadratic growth in chapter XXX.

A second attempt: add some slack space

One key idea behind dynamic arrays is to add some _slack space_ to the
array. That is, create an array which is slightly bigger than needed,
so that there are unused elements at the end of the array. That way,
when we call add(), as long as there is some empty space left at the
end of the array, we do not need to do any copying. Only when the
array gets full do we need to copy it.

Here is an implementation of this approach. We store an array, plus
the number of elements stored in the array (minus any slack space).
When the array gets full, we create a new array 100 elements longer.

Exercise: reading in a file of n charcters, how many characters are
copied using this approach? Are our performance problems solves?

Answer: after every 100 calls to add(), the array is copied. So the
number of copies is reduced by a factor of 100. Instead of n^2/2
characters copied, there are approximately n^2/200 characters copied.
The performance improves by a factor of 100.

This is not enough. Reading the Encyclopaedia Britannica will take 100
times less time - 200 years instead of 20,000 years.

The problem is still the quadratic growth. Reading an input 10 times
the size takes 100 times as long.

A better version:
Adding some slack space only delayed the problem. The second key idea
of dynamic arrays is to make the slack space _bigger_ as the array
gets larger.

Specifically, when the array gets full, the next time we do an add(),
we will create an array _twice as big_. So, immediately after the call
to add(), the array will be about 50% full.

Here is how we do this in code:

The question is, does this work? The answer is yes!

Notice that the array initially has a capacity of 1. Every time it
gets full, the array size is doubled. So the capacity of the array
grows as follows: 1, 2, 4, 8, 16, ..., doubling each time, always a
power of 2.

Now suppose that we read in a file of (say) 2^6 = 64 characters. The
array capacity grows: 1, 2, 4, 8, 16, 32, 64. Each time the capacity
grows, the contents of the array are copied. So in total 1+2+4+8+16+32
= 63 characters are copied. (The sum stops at 32 because we never need
to copy the final 64-character array.)

In general, if the file we read is a power of two size, 2^a, the array
capacity will grow as follows: 1, 2, 4, 8, ..., 2^(a-1),2^a. The total
number of characters copied will be 1+2+4+8+...+2^(a-1). By the
geometric series formula this is equal to 2^a-1.

What if the input size is not a power of two? The worst case is when
the input size is one more than a power of two, 2^a+1. In that case,
the array is resized from 2^a to 2^(a+1), for just one more character
to be added. In this case, the array grows as follows: 1, 2, 4, 8,
..., 2^(a-1), 2^a, 2^(a+1). And the number of characters copied is
1+2+4+8+...+2^(a-1)+2^a = 2^(a+1)-1 = 2 . 2^a - 1. So in this case,
the worst case, we have:

2^a+1 characters read in
2 . 2^a - 1 characters copied

And notice that the number of characters copied is less than twice the
input length: 2*(2^a+1) = 2^(a+1)+2 > 2^(a+1)-1. We can say that, when
reading an input of length n, our program copies fewer than 2n
characters. For the Encyclopaedia Britannica, this gives 200 millions
characters copied, instead of 10^16 initially. A vast improvement!

What's more, the number of characters copied grows linearly in the
size of the input: If we read an input 10 times bigger, the number of
characters copied grows roughly 10 times. As such, the runtime of the
program also grows linearly.

Here are some runtimes obtained on a typical computer:


Dynamic arrays in context:

Takeaways:

* Linear vs dynamic growth
* Brute force
* Small changes, simple ideas
* Appropriate use of data structures
