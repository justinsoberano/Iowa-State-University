
let astericks = "";

function drawTriangle(size) {
   for(let i = 1; i <= size; i++) {
      for(let j = 0; j < i; j++) {
         astericks += "*";
      }
      console.log(astericks)
      astericks = "";
   }
}