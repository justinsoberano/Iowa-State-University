fetch("movies.json")
    .then(function (response) { return response.json(); })
    .then(function (data) { return dataToHtml(data); });
function dataToHtml(data) {
    var mainContainer = document.getElementById("goodmovies");
    for (var i = 0; i < data.movies.length; i++) {
        var movieTitle = data.movies[i].title;
        var movieYear = data.movies[i].year;
        var movieUrl = data.movies[i].url;
        var mytr = document.createElement("div");
        mytr.innerHTML = "\n            <h1> ".concat(movieTitle, " </h1>\n            <p> ").concat(movieYear, " </p>\n            <img src=\"").concat(movieUrl, "\" width = \"200\" alt=\"Movie Poster\">\n            ");
        mainContainer.appendChild(mytr);
    }
}
