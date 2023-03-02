
fetch("movies.json")
    .then(response => response.json())
    .then(movies => loadMovies(movies));

function loadMovies(myMovies) {
    var imgMovie1 = document.getElementById("imgMovie1"); // Avengers
    var imgMovie2 = document.getElementById("imgMovie2"); // The Last Airbender
    var imgMovie3 = document.getElementById("imgMovie3"); // The Iron Giant
    var imgMovie4 = document.getElementById("imgMovie4"); // Mulan
    var txtMovie1 = document.getElementById("txtMovie1"); // Avengers
    var txtMovie2 = document.getElementById("txtMovie2"); // The Last Airbender
    var txtMovie3 = document.getElementById("txtMovie3"); // The Iron Giant
    var txtMovie4 = document.getElementById("txtMovie4"); // Mulan

    function loadMovies(myMovies) {
        var imgMovie1 = document.getElementById("imgMovie1"); // Avengers
        var imgMovie2 = document.getElementById("imgMovie2"); // The Last Airbender
        var imgMovie3 = document.getElementById("imgMovie3"); // The Iron Giant
        var imgMovie4 = document.getElementById("imgMovie4"); // Mulan
        var txtMovie1 = document.getElementById("txtMovie1"); // Avengers
        var txtMovie2 = document.getElementById("txtMovie2"); // The Last Airbender
        var txtMovie3 = document.getElementById("txtMovie3"); // The Iron Giant
        var txtMovie4 = document.getElementById("txtMovie4"); // Mulan

        if (myMovies.movies[i].title === "Avengers") {
            imgMovie1.appendChild(imgMovie1);
            txtMovie1.appendChild(txtMovie1);
        } else if (myMovies.movies[i].title === "The Last Airbender") {
            imgMovie2.appendChild(imgMovie2);
            txtMovie2.appendChild(txtMovie2);
        } else if (myMovies.movies[i].title === "The Iron Giant") {
            imgMovie3.appendChild(imgMovie3);
            txtMovie3.appendChild(txtMovie3);
        } else if (myMovies.movies[i].title === "Mulan") {
            imgMovie4.appendChild(imgMovie4);
            txtMovie4.appendChild(txtMovie4);
        }
    }
}
