/*global ODSA */
// Written by Jun Yang and Cliff Shaffer, modified by Peter Ljunglöf
$(document).ready(function() {
  "use strict";
  var arrValues = [13, 12, 20, 8, "", "", "", ""];
  var maxSize = arrValues.length;
  var arraySize = 4;

  var av_name = "CGUDynamicArrayListRemoveCON";
  var av = new JSAV(av_name);
  var leftMargin = 5,
      nodeWidth = 30,
      theTop = 35,
      arrow1_x = 22 + nodeWidth;

  var pseudo = av.code({
    "url": "../../../SourceCode/Pseudo/ChalmersGU/DynamicArrayList.txt",
    "lineNumbers": false,
    "startAfter": "/* *** ODSATag: DynamicArrayListRemove *** */",
    "endBefore": "/* *** ODSAendTag: DynamicArrayListRemove *** */"
  });

  var arr = av.ds.array(arrValues, {indexed: true, top: 20, left: 10});
  arr.hide();

  var arrValues2 = ["", "", "", ""];
  var arr2 = av.ds.array(arrValues2, {indexed: true, top: 70, left: 10});
  arr2.hide();

  var label1 = av.label(`remove(${arraySize-1})`,
                        {before: arr, left: 100, top: -10}).hide();

  var label2 = av.label(`remove(${arraySize-2})`,
                        {before: arr, left: 60, top: -10}).hide();

  var arrMS = av.ds.array([maxSize], {indexed: false, left: 150, top: 130}).hide();
  var labelMS = av.label("size of internalArray", {before: arrMS, left: 10, top: 134}).hide();

  var arrLS = av.ds.array([arraySize], {indexed: false, left: 150, top: 165}).hide();
  var labelLS = av.label("arraySize", {before: arrLS, left: 70, top: 169}).hide();

  var arrRem = av.ds.array([""], {indexed: false, left: 150, top: 200}).hide();
  var labelRem = av.label("removed", {before: arrRem, left: 70, top: 204}).hide();

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
We will remove the last element in the list.
`);
  label1.show();
  pseudo.highlight(1);
  av.step();

  // Slide 3
  av.umsg(`
We copy the the value to be removed, and decrease <code>arraySize</code>.
We don't have to move any elements, since we're deleting from the end of the list.
`);
  pseudo.unhighlight(1);
  pseudo.highlight([4,5,6,7,8,9]);
  labelRem.show();
  arrRem.show();
  av.effects.copyValue(arr, arraySize-1, arrRem, 0);
  arrRem.highlight(0);
  arr.value(arraySize-1, "");
  arr.addClass(arraySize-1, "unused");
  arrLS.value(0, arraySize-1);
  arrLS.highlight(0);
  av.step();

  // Slide 3
  arrRem.unhighlight(0);
  arrLS.unhighlight(0);
  av.umsg("The internal array now contains 3 elements, which is larger than 8/3, so we don't have to shrink it.");
  pseudo.unhighlight([4,5,6,7,8,9]);
  pseudo.highlight(10);
  av.step();

  // Slide 3
  av.umsg("Now we can return the removed value.");
  pseudo.unhighlight(10);
  pseudo.highlight(12);
  arrRem.highlight(0);
  av.step();

  // Slide 2
  label1.hide();
  labelRem.hide();
  arrRem.hide();
  arrRem.value(0, "");
  arrRem.unhighlight(0);
  pseudo.unhighlight(12);
  av.umsg("");
  av.step();

  // Slide 2
  av.umsg(`Now we want to remove the last value once again.`);
  label2.show();
  pseudo.highlight(1);
  av.step();

  // Slide 3
  av.umsg(`
We copy the the value to be removed, and decrease <code>arraySize</code>.
We don't have to move any elements, since we're deleting from the end of the list.
`);
  pseudo.unhighlight(1);
  pseudo.highlight([4,5,6,7,8,9]);
  labelRem.show();
  arrRem.show();
  av.effects.copyValue(arr, arraySize-2, arrRem, 0);
  arrRem.highlight(0);
  arr.value(arraySize-2, "");
  arr.addClass(arraySize-2, "unused");
  arrLS.value(0, arraySize-2);
  arrLS.highlight(0);
  av.step();

  // Slide 3
  arrRem.unhighlight(0);
  arrLS.unhighlight(0);
  av.umsg("But now the internal array contains only 2 elements, which is fewer than 8/3, so we have to shrink it.");
  pseudo.unhighlight([4,5,6,7,8,9]);
  pseudo.highlight(10);
  av.step();

  // Slide 3
  av.umsg("Let's make it half the size.");
  pseudo.unhighlight(10);
  pseudo.highlight(11);
  av.step();

  // Slide 3
  av.umsg("First we create a new temporary array.");
  for (let i = 0; i < arrValues2.length; i++) arr2.addClass(i, "unused");
  arr2.show();
  av.step();

  // Slide 3
  av.umsg("Then we copy all elements from the older internal array.");  
  for (let i = 0; i < arraySize-2; i++) {
    arr2.removeClass(i, "unused");
    av.effects.copyValue(arr, i, arr2, i);
    av.step();
  }

  // Slide 3
  av.umsg("Now we can make the temporary array the internal array, and continue with the <code>remove</code> method.");
  arr.hide();
  arr2.translateY(-50);
  av.step();

  // Slide 3
  av.umsg("Finally we can return the removed value.");
  pseudo.unhighlight(11);
  pseudo.highlight(12);
  arrRem.highlight(0);
  av.step();

  // Slide 5
  av.umsg(`
Removing from the end of the list is constant, most of the time. 
But now and then we have to resize the array, and then it is linear in the number of elements.
`);
  label2.hide();
  labelRem.hide();
  arrRem.unhighlight(0);
  pseudo.unhighlight(12);
  av.recorded();
});
