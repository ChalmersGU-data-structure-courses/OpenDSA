/*global ODSA */
// Written by Jun Yang and Cliff Shaffer, modified by Peter Ljunglöf
$(document).ready(function() {
  "use strict";

  var arrValues = [13, 12, 20, 8, 3, "", "", ""];
  var maxSize = arrValues.length;
  var arraySize = 5;
  var addValue = 23;

  var av_name = "CGUStaticArrayListAppendCON";
  var av = new JSAV(av_name);
  var pseudo = av.code({
    "url": "../../../SourceCode/Pseudo/ChalmersGU/StaticArrayList.txt",
    "lineNumbers": false,
    "startAfter": "/* *** ODSATag: StaticArrayListAdd *** */",
    "endBefore": "/* *** ODSAendTag: StaticArrayListAdd *** */"
  });

  var arr = av.ds.array(arrValues, {indexed: true, top: 27, left: 10});
  arr.hide();

  var arrow1 = av.g.line(176, 18, 176, 43,
                         {"arrow-end": "classic-wide-long",
                          opacity: 100, "stroke-width": 2});
  arrow1.hide();
  var label = av.label("add(5, 23)", {before: arr, left: 80, top: -10}).hide();

  var arrMS = av.ds.array([8], {indexed: false, left: 150, top: 85});
  arrMS.hide();
  var labelMaxSize = av.label("size of internalArray", {before: arrMS, left: 10, top: 89});
  labelMaxSize.hide();

  var arrLS = av.ds.array([5], {indexed: false, left: 150, top: 120});
  arrLS.hide();
  var labelListSize = av.label("arraySize", {before: arrLS, left: 60, top: 124});
  labelListSize.hide();

  pseudo.addClass([3,4], "greyedout");

  // Slide 1
  av.umsg("Adding elements to the tail of an array-based list is easy.");
  for (let i = arraySize; i < maxSize; i++) arr.addClass(i, "unused");
  av.displayInit();

  // Slide 2
  av.umsg(`
Here is an array-based list with ${arraySize} elements. 
We will append the value ${addValue} to the end of the list.
`);
  label.show();
  pseudo.highlight(1);
  labelMaxSize.show();
  arr.show();
  arrMS.show();
  arrLS.show();
  labelListSize.show();
  av.step();

  // Slide 3
  av.umsg("We simply add the value into the empty position after the current last element.");
  pseudo.unhighlight(1);
  pseudo.highlight(5);
  arrMS.unhighlight(0);
  arr.value(arraySize, addValue);
  arr.highlight(arraySize);
  av.step();

  // Slide 4
  av.umsg("We increase <code>arraySize</code> by 1.");
  pseudo.unhighlight(5);
  pseudo.highlight(6);
  arr.unhighlight(arraySize);
  arr.removeClass(arraySize, "unused");
  arrLS.value(0, 6);
  av.step();

  // Slide 5
  av.umsg("Appending to the end of the list therefore requires constant time.");
  arrLS.unhighlight(0);
  pseudo.unhighlight(6);
  av.recorded();
});

