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
    <h1>Calculation Result</h1>
    <div id="resultContainer">
        <!-- The result will be displayed here using JavaScript -->
    </div>

    <script>
        // Wrap your code in a function and use the window.onload event to ensure the DOM is fully loaded
        window.onload = function() {
            var params = new URLSearchParams(window.location.search);
            //extracts the string put after the parameter 'expression'
            var userInput = params.get('expression');
            var resultContainer = document.getElementById('resultContainer');
            try {
                //decoding the URI component allows for non standard strings to be interpretted as string
                //WHich occurs when sending '+' and '/' over the URL
                var decodedInput = decodeURIComponent(userInput);

                //This is the unsafe function, will run any script code given to it as a script. 
                var result = eval(decodedInput);

                //Feeds the result directly into the div space provided. 
                resultContainer.innerHTML = "<p>Result: " + result + "</p>";
            } catch (error) {
                resultContainer.innerHTML = "<p class='error'>Error: " + error.message + "</p>";
            }
        };
    </script>
</body>
</html>


