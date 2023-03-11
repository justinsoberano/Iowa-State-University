fetch("data.json")
    .then(function (response) { return response.json(); })
    .then(function (data) { return dataToHtml(data); });

// Universal data grabber and append for all block images.
function dataToHtml(data) {
    for(let i = 0; i < data.blocks.length; i++) {
        let blockID = "block" + i;
        var block = document.getElementById(blockID);
        // If statement checks if the block exists in that webpage. 
        if(block == null) {continue;} 
        let blockImage = data.blocks[i].image;
        append(blockImage, block);
    }
}
function append(picture, id) {
    let image = document.createElement("div");
    image.innerHTML = "<img class=\"card-img-top\" src=\"" + picture + "\" alt=\"Minecraft Block\" />";
    id.appendChild(image);
}