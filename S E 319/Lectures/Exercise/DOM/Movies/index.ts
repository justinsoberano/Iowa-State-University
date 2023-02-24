function getInputValue() {
    let movieName = document.forms["my_form"]["inputMovieName"];
    let inputMovieName = movieName.value;

    fetch("movies.json")
        .then(response => response.json())
        .then(myMovies => loadMovies(myMovies))

    function loadMovies(myMovies) {
        var mainContainer = document.getElementById("myData");

        for(var i = 0; i < myMovies.length; i++) {
            if(movieName === myMovies.movies[i].title) {
                let title = myMovies.movies[i].title;
                let year = myMovies.movies[i].year;
                let url =  myMovies.movies[i].url;

                let div = document.createElement("div");
                div.innerHTML = `
                <h3> ${title} </h3>
                ${year} <br>
                <img src="${url}" alt="Movie Poster" width="200"> <br><br>
                `;
                mainContainer.appendChild(div);
            }
        }
    }
}