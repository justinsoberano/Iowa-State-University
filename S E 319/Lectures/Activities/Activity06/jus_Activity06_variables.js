/*
Justin Soberano
Feb 8, 2023
Activity06 - Variables
*/

console.log("Activity06 - Variables");

// Q1
console.log("Q1 --------------------");

var var1 = "Iowa";
console.log(var1);

var var1 = 124;
console.log(var1);

console.log("Yes, we can redeclare a variable with var.");

//Q2
console.log("Q2 --------------------");
let var2 = "Ames";
console.log(var2);

var2 = 124;
console.log("No");

console.log("We cannot redeclare a variable with let.");
console.log("We can reassign a variable with let.")

//Q3
console.log("Q3 --------------------");
const var3 = "ISU";
console.log(var3);

console.log("No, we cannot redeclare a variable with const.");

//Q4
console.log("Q4 --------------------");
let var4;
const var5 = 0;
console.log("A constant variable must be initialized when declared.");

//Q5
console.log("Q5 --------------------");
const var6 = 3.1415;
console.log("No, we cannot reassign a constant variable.");

//Q6
let firstName = "Justin";
console.log("No, we cannot use a space in a variable name.");
let numbers = [1, 2];
console.log("No, we cannot start a variable name with a number.");
let cityState ="Ames Iowa";
console.log("No, we cannot use a dash in a variable name.");

//Q7
let mainCity = "DesMoines";
console.log("This is the capital : ", mainCity);

//Q8
if (5 === 5) {
    var var7 = 100;
}
console.log(var7);
if (5 === 5) {
    let var8 = 100;
}

console.log("We cannot access a variable declared with let outside of its block scope.");
