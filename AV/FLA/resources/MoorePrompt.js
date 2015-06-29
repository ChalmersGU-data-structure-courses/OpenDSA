function TraversePrompt() {
    this.render = function() {
        var winW = window.innerWidth;
        var winH = window.innerHeight;
        var dialogueoverlay = document.getElementById('dialogueoverlay');
        var dialoguebox = document.getElementById('dialoguebox');
        dialogueoverlay.style.display = "block";
        dialogueoverlay.style.height = winH+"px";
        dialoguebox.style.left = (winW/2) - (550/2)+"px";
        dialoguebox.style.top = "100px";
        dialoguebox.style.display = "block";
        document.getElementById('dialogueboxhead').innerHTML = "Traverse:";
        document.getElementById('dialogueboxbody').innerHTML = 'Input String: <input class="newinput" id="first"> <button onclick="deleteInput(0)">Delete Input</button>';
        document.getElementById('dialogueboxfoot').innerHTML = '<button onclick="addNewInput()">Add New Input</button> <button onclick="traverseInput()">Traverse</button> <button onclick="goback()">Cancel</button>';
        document.getElementById('first').focus();
    }
    goback = function() {
        document.getElementById('dialoguebox').style.display = "none";
        document.getElementById('dialogueoverlay').style.display = "none";
    }
    traverseInput = function() {
        var values = [];
        var x = document.getElementById('dialogueboxbody').getElementsByClassName('newinput');
        for (var i = 0; i < x.length; i++) {
            values.push(x[i].value);
        }
        window["traverseInputs"](values);
        this.goback();
    }
    addNewInput = function() {
        var values = [];
        var x = document.getElementById('dialogueboxbody').getElementsByClassName('newinput');
        for (var j = 0; j < x.length; j++) {
            values.push(x[j].value);
        }
        document.getElementById('dialogueboxbody').innerHTML += '<br>Input String: <input class="newinput"> <button onclick="deleteInput(' + values.length + ')">Delete Input</button>';
        x = document.getElementById('dialogueboxbody').getElementsByClassName('newinput');
        for (var k = 0; k < values.length; k++) {
            x[k].value = values[k];
        }
        x[x.length - 1].focus();
    }
    deleteInput = function(input) {
        var values = [];
        var x = document.getElementById('dialogueboxbody').getElementsByClassName('newinput');
        for (var i = 0; i < x.length; i++) {
            if (i != input) {
                values.push(x[i].value);
            }
        }
        document.getElementById('dialogueboxbody').innerHTML = 'Input String: <input class="newinput" id="first"> <button onclick="deleteInput(0)">Delete Input</button>';
        for (var j = 1; j < values.length; j++) {
            document.getElementById('dialogueboxbody').innerHTML += '<br>Input String: <input class="newinput"> <button onclick="deleteInput(' + j + ')">Delete Input</button>';
        }
        x = document.getElementById('dialogueboxbody').getElementsByClassName('newinput');
        for (var k = 0; k < values.length; k++) {
            x[k].value = values[k];
        }
        x[x.length - 1].focus();
    }
}

function NodePrompt() {
    this.render = function(value, is, lab, outputChar) {
        var winW = window.innerWidth;
        var winH = window.innerHeight;
        var dialogueoverlay = document.getElementById('dialogueoverlay');
        var dialoguebox = document.getElementById('dialoguebox');
        dialogueoverlay.style.display = "block";
        dialogueoverlay.style.height = winH+"px";
        dialoguebox.style.left = (winW/2) - (550/2)+"px";
        dialoguebox.style.top = "100px";
        dialoguebox.style.display = "block";
        document.getElementById('dialogueboxbody').innerHTML = 'Initial State:<input type="checkbox" id="initial_state">';
        document.getElementById('dialogueboxbody').innerHTML += '<br>Output Character: <input id="moore">';
        document.getElementById('dialogueboxbody').innerHTML += '<br>State Label: <input id="label">';
        if(!outputChar){
            document.getElementById('dialogueboxhead').innerHTML = "Create Node <b>" + value + ":</b>";
            document.getElementById('dialogueboxfoot').innerHTML = '<button onclick="addNode()">OK</button> <button onclick="cancel()">Cancel</button>';
        }
        else {
            document.getElementById('dialogueboxhead').innerHTML = "Edit Node <b>" + value + ":</b>";
            document.getElementById('dialogueboxfoot').innerHTML = '<button onclick="changeNode()">OK</button> <button onclick="terminate()">Cancel</button>';
            if (is) {
                document.getElementById('initial_state').checked = true;
            }
            if (lab) {
                document.getElementById('label').value = lab;
            }
            if (outputChar != lambda && outputChar != epsilon) {
                document.getElementById('moore').value = outputChar;
            }
        }
        document.getElementById('moore').focus();
    }
    terminate = function() {
        document.getElementById('dialoguebox').style.display = "none";
        document.getElementById('dialogueoverlay').style.display = "none";
    }
    cancel = function() {
        window["cancelNode"]();
        this.terminate();
    }
    addNode = function() {
        var initial_state = document.getElementById('initial_state').checked;
        var node_label = document.getElementById('label').value;
        var output_char = document.getElementById('moore').value;
        if (output_char === "") {
            output_char = emptystring;
        }
        window["createNode"](initial_state, node_label, output_char);
        this.terminate();
    }
    changeNode = function() {
        var initial_state = document.getElementById('initial_state').checked;
        var node_label = document.getElementById('label').value;
        var output_char = document.getElementById('moore').value;
        if (output_char === "") {
            output_char = emptystring;
        }
        window["updateNode"](initial_state, node_label, output_char);
        this.terminate();
    }
}

function EdgePrompt() {
    this.render = function(values) {
        var winW = window.innerWidth;
        var winH = window.innerHeight;
        var dialogueoverlay = document.getElementById('dialogueoverlay');
        var dialoguebox = document.getElementById('dialoguebox');
        dialogueoverlay.style.display = "block";
        dialogueoverlay.style.height = winH+"px";
        dialoguebox.style.left = (winW/2) - (550/2)+"px";
        dialoguebox.style.top = "100px";
        dialoguebox.style.display = "block";
        document.getElementById('dialogueboxbody').innerHTML = 'Transition: <input class="newedge" id="transition"> <button onclick="deleteEdge(0)">Delete Transition</button>';
        if (!values) {
            document.getElementById('dialogueboxhead').innerHTML = "Create Edge:";
            document.getElementById('dialogueboxfoot').innerHTML = '<button onclick="addNewWeight()">Add New Transition</button> <button onclick="addEdge()">Done</button> <button onclick="end()">Cancel</button>';
        }
        else {
            document.getElementById('dialogueboxhead').innerHTML = "Edit Edge:";
            document.getElementById('dialogueboxfoot').innerHTML = '<button onclick="addNewWeight()">Add New Transition</button> <button onclick="changeEdge()">Done</button> <button onclick="end()">Cancel</button>';
            for (var i = 1; i < values.length; i++) {
                document.getElementById('dialogueboxbody').innerHTML += '<br>Transition: <input class="newedge"> <button onclick="deleteEdge(' + i + ')">Delete Transition</button>';
            }
            var x = document.getElementById('dialogueboxbody').getElementsByClassName('newedge');
            for (var j = 0; j < values.length; j++) {
                if (values[j] != lambda && values[j] != epsilon) {
                    x[j].value = values[j];
                }
            }
        }
        document.getElementById('transition').focus();
    }
    end = function() {
        document.getElementById('dialoguebox').style.display = "none";
        document.getElementById('dialogueoverlay').style.display = "none";
    }
    addEdge = function() {
        var values = [];
        var x = document.getElementById('dialogueboxbody').getElementsByClassName('newedge');
        for (var j = 0; j < x.length; j++) {
            if (x[j].value === "") {
                if (values.indexOf(emptystring) == -1) {
                    values.push(emptystring);
                }
            }
            else if (values.indexOf(x[j].value) == -1) {
                values.push(x[j].value);
            }
        }
        var edge_label = values.join("<br>");
        window["createEdge"](edge_label);
        this.end();
    }
    changeEdge = function() {
        var values = [];
        var x = document.getElementById('dialogueboxbody').getElementsByClassName('newedge');
        for (var j = 0; j < x.length; j++) {
            if (x[j].value === "") {
                if (values.indexOf(emptystring) == -1) {
                    values.push(emptystring);
                }
            }
            else if (values.indexOf(x[j].value) == -1) {
                values.push(x[j].value);
            }
        }
        var edge_label = values.join("<br>");
        window["updateEdge"](edge_label);
        this.end();
    }
    addNewWeight = function() {
        var values = [];
        var x = document.getElementById('dialogueboxbody').getElementsByClassName('newedge');
        for (var j = 0; j < x.length; j++) {
            values.push(x[j].value);
        }
        document.getElementById('dialogueboxbody').innerHTML += '<br>Transition: <input class="newedge"> <button onclick="deleteEdge(' + values.length + ')">Delete Transition</button>';
        x = document.getElementById('dialogueboxbody').getElementsByClassName('newedge');
        for (var k = 0; k < values.length; k++) {
            x[k].value = values[k];
        }
        x[x.length - 1].focus();
    }
    deleteEdge = function(edge) {
        var values = [];
        var x = document.getElementById('dialogueboxbody').getElementsByClassName('newedge');
        for (var i = 0; i < x.length; i++) {
            if (i != edge) {
                values.push(x[i].value);
            }
        }
        document.getElementById('dialogueboxbody').innerHTML = 'Transition: <input class="newedge" id="transition"> <button onclick="deleteEdge(0)">Delete Transition</button>';
        for (var j = 1; j < values.length; j++) {
            document.getElementById('dialogueboxbody').innerHTML += '<br>Transition: <input class="newedge"> <button onclick="deleteEdge(' + j + ')">Delete Transition</button>';
        }
        x = document.getElementById('dialogueboxbody').getElementsByClassName('newedge');
        for (var k = 0; k < values.length; k++) {
            x[k].value = values[k];
        }
        x[x.length - 1].focus();
    }
}