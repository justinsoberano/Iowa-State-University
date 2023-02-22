
fetch("superheroes.json")
    .then(response => response.json())
    .then(data => dataToHtml(data));

function dataToHtml(data) {
    let mainContainer = document.getElementById("tb1");
    console.log(mainContainer)

    for(let i = 0; i < data.superheroes.length; i++) {
        let firstName = data.superheroes[i].firstName;
        let lastName = data.superheroes[i].lastName;
        let job = data.superheroes[i].job;

        let mytr = document.createElement("tr");
        mytr.innerHTML = `
        <tr>
            <td> ${firstName} ${lastName}</td>
            <td> ${job} </td>
        <tr> `;
        mainContainer.appendChild(mytr);
    }
}
