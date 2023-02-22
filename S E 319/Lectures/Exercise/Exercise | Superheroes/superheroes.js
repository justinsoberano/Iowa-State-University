fetch("superheroes.json")
    .then(function (response) { return response.json(); })
    .then(function (data) { return dataToHtml(data); });
function dataToHtml(data) {
    var mainContainer = document.getElementById("tb1");
    console.log(mainContainer);
    for (var i = 0; i < data.superheroes.length; i++) {
        var firstName = data.superheroes[i].firstName;
        var lastName = data.superheroes[i].lastName;
        var job = data.superheroes[i].job;
        var mytr = document.createElement("tr");
        mytr.innerHTML = "\n        <tr>\n            <td> ".concat(firstName, " ").concat(lastName, "</td>\n            <td> ").concat(job, " </td>\n        <tr> ");
        mainContainer.appendChild(mytr);
    }
}
