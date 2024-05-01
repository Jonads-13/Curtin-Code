<!DOCTYPE html>
<html>
<head>
    <title>Calculation Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            color: #333;
        }

        #resultContainer {
            background-color: #fff;
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        p {
            font-size: 18px;
            color: #333;
        }

        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>


<?php
if (isset($_GET['expression'])) {
    
    //Strng obtained through controlled expression tag
    $expression = $_GET['expression'];

    try {
// Evaluate the expression
//Through PHP code therefore is not suspetable to javascript attack
        $result = eval("return $expression;");

        // Display the HTML content
        echo "<h1>Calculation Result</h1>";
        echo "<p>Expression: $expression</p>";
        echo "<p>Result: $result</p>";
    } catch (Exception $e) {
        // Handle exceptions and display an error message
        echo "<h1>Calculation Result</h1>";
        echo "<p>Error: " . $e->getMessage() . "</p>";
    }
} else {
    // Display a message when no expression is provided
    echo "<h1>Calculation Result</h1>";
    echo "<p>No expression provided.</p>";
}
?>



<h1>Calculation Result</h1>
    <div id="resultContainer">
        <!-- The result will be displayed here using JavaScript -->
    </div>

    <script>
        // Wrapped in a function and use the window.onload event to ensure the DOM is fully loaded
        window.onload = function() {
            var params = new URLSearchParams(window.location.search);
            var userInput = params.get('expression');
            var resultContainer = document.getElementById('resultContainer');
            try {
                //Decoded input can still contain now raw script values to be filtered out
		    var decodedInput = decodeURIComponent(userInput);
		    // Define a regular expression pattern for allowed mathematical expressions
		    // Mitigation strategy 1 to filter the decoded message to ensure only mathematic values are accepted
       		 var allowedExpressionPattern = /^[\d+\-*\/().\s]+$/;

        	if (!allowedExpressionPattern.test(decodedInput)) {
            		throw new Error("Invalid expression. Only mathematical operations are allowed.");
        }
                //Mitigation level 2 applying the Use strict and indirect use of the eval method
                var result = eval?.(`"use strict";(${String(decodedInput)})`);
                //Use TextContent instead of innerHTML so the whole aspect is only considered text anyway
                resultContainer.textContent = "Result: " + result;
            } catch (error) {
                resultContainer.testContent = "Error: " + error.message;
            }
        };
    </script>
</body>
</html>


