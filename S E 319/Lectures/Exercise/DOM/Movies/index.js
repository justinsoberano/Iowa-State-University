function getInputValue() {
    var movieName = document.forms["my_form"]["inputMovieName"];
    var inputMovieName = movieName.value;
    fetch("movies.json")
        .then(function (response) { return response.json(); })
        .then(function (myMovies) { return loadMovies(myMovies); });
    function loadMovies(myMovies) {
        var mainContainer = document.getElementById("myData");
        for (var i = 0; i < myMovies.length; i++) {
            if (movieName == myMovies.movies[i].title) {
                var title = myMovies.movies[i].title;
                var year = myMovies.movies[i].year;
                var url = myMovies.movies[i].url;
                var div = document.createElement("div");
                div.innerHTML = "\n                <h3> ".concat(title, " </h3>\n                ").concat(year, " <br>\n                <img src=\"").concat(url, "\" alt=\"Movie Poster\" width=\"200\"> <br><br>\n                ");
                mainContainer.appendChild(div);
            }
        }
    }
}
