/*global ODSA */
// Written by Jun Yang and Cliff Shaffer, modified by Peter Ljunglöf
$(document).ready(function() {
  "use strict";

  var arrValues = [13, 12, 20, ""];
  var maxSize = arrValues.length;
  var arraySize = 3;
  var addValue1 = 8;
  var addValue2 = 23;

  var av_name = "CGUDynamicArrayListAppendCON";
  var av = new JSAV(av_name);
  var pseudo = av.code({
    "url": "../../../SourceCode/Pseudo/ChalmersGU/DynamicArrayList.txt",
    "lineNumbers": false,
    "startAfter": "/* *** ODSATag: DynamicArrayListAdd *** */",
    "endBefore": "/* *** ODSAendTag: DynamicArrayListAdd *** */"
  });

  var arr = av.ds.array(arrValues, {indexed: true, top: 20, left: 10});
  arr.hide();

  var arrValues2 = ["", "", "", "", "", "", "", ""];
  var arr2 = av.ds.array(arrValues2, {indexed: true, top: 70, left: 10});
  arr2.hide();

  var label1 = av.label(`add(${arraySize}, ${addValue1})`,
                        {before: arr, left: 60, top: -10}).hide();

  var label2 = av.label(`add(${arraySize+1}, ${addValue2})`,
                        {before: arr, left: 100, top: -10}).hide();

  var arrMS = av.ds.array([maxSize], {indexed: false, left: 150, top: 130}).hide();
  var labelMS = av.label("size of internalArray", {before: arrMS, left: 10, top: 134}).hide();

  var arrLS = av.ds.array([arraySize], {indexed: false, left: 150, top: 165}).hide();
  var labelLS = av.label("arraySize", {before: arrLS, left: 70, top: 169}).hide();

  // Slide 1
  av.umsg(`Here is an array-based list with ${arraySize} elements.`);
  for (let i = arraySize; i < maxSize; i++) arr.addClass(i, "unused");
  arr.show();
  arrMS.show();
  arrLS.show();
  labelMS.show();
  labelLS.show();
  av.displayInit();

  // Slide 2
  av.umsg(`
Here is an array-based list with ${arraySize} elements. 
We will append the value ${addValue1} to the end of the list.
`);
  label1.show();
  pseudo.highlight(1);
  av.step();

  // Slide 3
  av.umsg("There is still space left in the internal array, so we don't have to resize it.");
  pseudo.unhighlight(1);
  pseudo.highlight(3);
  av.step();

  // Slide 3
  av.umsg("We don't have to move any elements, since we're appending to the end of the list.");
  pseudo.unhighlight(3);
  pseudo.highlight(5);
  av.step();

  // Slide 3
  av.umsg(`So we assign the next empty slot the value ${addValue1}, and increase <code>arraySize</code>.`);
  pseudo.unhighlight(5);
  pseudo.highlight([7,8]);
  arr.value(arraySize, addValue1);
  arr.removeClass(arraySize, "unused");
  arr.highlight(arraySize);
  arrLS.value(0, arraySize+1);
  arrLS.highlight(0);
  av.step();

  // Slide 2
  label1.hide();
  arr.unhighlight(arraySize);
  arrLS.unhighlight(0);
  pseudo.unhighlight([7,8]);
  av.umsg("");
  av.step();

  // Slide 2
  av.umsg(`Now we want to append the value ${addValue2} to the end of the list.`);
  label2.show();
  pseudo.highlight(1);
  av.step();

  // Slide 3
  av.umsg("But the internal array is full, so we have to resize it.");
  pseudo.unhighlight(1);
  pseudo.highlight(3);
  av.step();

  // Slide 3
  av.umsg("Let's make it twice as large.");
  pseudo.unhighlight(3);
  pseudo.highlight(4);
  av.step();

  // Slide 3
  av.umsg("First we create a new temporary array.");
  for (let i = 0; i < arrValues2.length; i++) arr2.addClass(i, "unused");
  arr2.show();
  av.step();

  // Slide 3
  av.umsg("Then we copy all elements from the older internal array.");  
  for (let i = 0; i < arraySize+1; i++) {
    arr2.removeClass(i, "unused");
    av.effects.copyValue(arr, i, arr2, i);
    av.step();
  }

  // Slide 3
  av.umsg("Now we can make the temporary array the internal array, and continue with the <code>add</code> method.");
  arr.hide();
  arr2.translateY(-50);
  av.step();

  // Slide 3
  av.umsg("Again, we don't have to move any elements, since we're appending to the end of the list.");
  pseudo.unhighlight(4);
  pseudo.highlight(5);
  av.step();

  // Slide 3
  av.umsg(`Finally we can assign the next empty slot the value ${addValue2}, and increase <code>arraySize</code>.`);
  pseudo.unhighlight(5);
  pseudo.highlight([7,8]);
  arr2.value(arraySize+1, addValue2);
  arr2.removeClass(arraySize+1, "unused");
  arr2.highlight(arraySize+1);
  arrLS.value(0, arraySize+2);
  arrLS.highlight(0);
  av.step();

  // Slide 5
  av.umsg(`
Appending to the end of the list is constant, most of the time. 
But now and then we have to resize the array, and then it is linear in the number of elements.
`);
  label2.hide();
  arr2.unhighlight(arraySize+1);
  arrLS.unhighlight(0);
  pseudo.unhighlight([7,8]);
  av.recorded();
});

