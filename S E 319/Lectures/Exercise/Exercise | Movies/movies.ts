
fetch("movies.json")
    .then(response => response.json())
    .then(data => dataToHtml(data));

function dataToHtml(data) {
    let mainContainer = document.getElementById("goodmovies")

    for(let i = 0; i < data.movies.length; i++) {
        let movieTitle = data.movies[i].title;
        let movieYear = data.movies[i].year;
        let movieUrl = data.movies[i].url;
        
        let mytr = document.createElement("div");
        mytr.innerHTML = `
            <h1> ${movieTitle} </h1>
            <p> ${movieYear} </p>
            <img src="${movieUrl}" width = "200" alt="Movie Poster">
            `;
        mainContainer.appendChild(mytr);
    }
}