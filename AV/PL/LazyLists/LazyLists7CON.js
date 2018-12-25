/*global ODSA */

$(document).ready(function() {
    "use strict";
    var av_name = "LazyLists7CON"; // Illustrate and develop the is.map function
    var av = new JSAV(av_name);
    var n = 31;
//     var arrValues = ["F", "F", "T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T","T", "T"];
    var arrValues = [".", ".", ".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".",".", "."];
    var arr = av.ds.array(arrValues, {indexed: true});
//     var pseudo1 = av.code(
// 	{
// 	    url:'../../../AV/PL/LazyLists/LazyLists7.code.1',
// 	    relativeTo:arr,
//  	    anchor:'left top',
//  	    myAnchor:'left top',
// 	    left: 30,
// 	    top: 70,
//             lineNumbers: true
//         }
//     );
//    var p_label = av.label("p = 2");
    var pseudo1 = av.code(
	{
	    url:'../../../AV/PL/LazyLists/LazyLists7.code.1',
// 	    left: 30,
// 	    top: 60,
            lineNumbers: true
        }
    );

    var p_label = av.label("p = 2", {left: 20, top: 60}).hide();
    
//     var p = av.variable(2, {label: "p = ", relativeTo:arr, anchor:'right top',
//  	    myAnchor:'left top',
// 	    left: 20,
// 	    top: 70});
    
    // Slide 1
    av.umsg("Suppose n = 30.  Trace through the pseudocode below.");
    for (var i = 0; i < n; i++) arr.addClass(i, "narrow"); 
    pseudo1.addClass([1,2,3,4,5,6,7,8], "extrawidth");
    p_label.addClass("emphasize");
    pseudo1.setCurrentLine(1);
    
    av.displayInit();


    // S 2
    pseudo1.setCurrentLine(2);
    arr.value(0,"F");
    arr.value(1,"F");
    for (i = 2; i < n; i++) arr.value(i,"T");
    av.step();

    // S 3
    pseudo1.setCurrentLine(3);
    p_label.show();
    av.step();


    
    av.recorded();
});
