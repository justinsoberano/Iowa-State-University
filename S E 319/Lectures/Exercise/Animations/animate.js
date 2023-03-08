let timerId = null;

window.addEventListener("DOMContentLoaded", function() {
   document.addEventListener("click", startAnimation);
});

function startAnimation(e) {

   // Get mouse coordinates
   let clickX = e.clientX;
   let clickY = e.clientY;  
   
   // TODO: Modify the code below
   moveImage(clickX, clickY);   

   // The code below allows the image to move to wherever the cursor is clicked on at a rate of 10ms.
   if(timerId !== null) {
      clearInterval(timerId);
   }
   timerId = setInterval(() => {moveImage(clickX, clickY)}, 10);
}


function moveImage(x, y) {
   const img = document.querySelector("img"); // Image is stored in an object
   let imgX = parseInt(img.style.left); // determines the location of the X location
   let imgY = parseInt(img.style.top); // determiens the location of the Y location

   // Determine (x,y) coordinates that center the image 
   // around the clicked (x, y) coords
   const centerX = Math.round(x - (img.width / 2)); // This gets the cn
   const centerY = Math.round(y - (img.height / 2));

   // This stops the timer and prevents the image from moving more when it reaches the clicked, X and Y coordinates. 
   if(imgX === centerX && imgY === centerY) {
      clearInterval(timerId);
      timerId = null;
   }
   
   // Move 1 pixel in both directions toward the click
   if (imgX < centerX) {
      imgX++;
   }
   else if (imgX > centerX) {
      imgX--;
   }
   
   if (imgY < centerY) {
      imgY++;
   }
   else if (imgY > centerY) {
      imgY--;
   }
   
   img.style.left = imgX + "px"; // Stores the new X and Y location in the image
   img.style.top = imgY + "px";
}