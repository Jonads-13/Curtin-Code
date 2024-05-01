<!DOCTYPE html>
<html>
<head>
    <title>Simple Calculator</title>
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

        form {
            margin: 20px auto;
            max-width: 400px;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            font-size: 18px;
            margin-bottom: 10px;
            color: #333;
        }

        input[type="text"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            background-color: #333;
            color: #fff;
            padding: 10px 20px;
            border: none;
            font-size: 18px;
            cursor: pointer;
            border-radius: 3px;
        }

        input[type="submit"]:hover {
            background-color: #555;
        }
    </style>


    <script>
        function validateForm() {
            var expressionInput = document.getElementById('expression');
            var userInput = expressionInput.value;

            // Regular expression pattern to match allowed mathematical expressions
            var pattern = /^[0-9+\-*/().\s]+$/;

            if (!pattern.test(userInput)) {
                alert("Invalid input. Please enter a valid mathematical expression.");
                return false; // Prevent form submission
            }

            return true; // Allow form submission
        }
    </script>

</head>

<body>
    <h1>Simple Calculator</h1>
    <!--Only difference here is calling the mitigated calc code.  -->
    <form action="calcmit.html" method="GET" onsubmit="return validateForm();">
        <label for="expression">Enter a mathematical expression:</label>
        <input type="text" id="expression" name="expression" required>
        <br><br>
        <input type="submit" value="Calculate">
    </form>
</body>
</html>
