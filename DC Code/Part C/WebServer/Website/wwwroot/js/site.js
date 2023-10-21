// Please see documentation at https://docs.microsoft.com/aspnet/core/client-side/bundling-and-minification
// for details on configuring this project to bundle and minify static web assets.

// Write your JavaScript code.


function loadClients() {
    apiUrl = '/api/Get'
    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            // Handle the data from the API
            document.getElementById('body').innerHTML = data;
        })
        .catch(error => {
            // Handle any errors that occurred during the fetch
            console.error('Fetch error:', error);
        });
}

function startReload () {
    // Call the loadClients function initially
    loadClients();

    // Set the interval to reload the function every 2 seconds
    setInterval(loadClients, 2000);
}