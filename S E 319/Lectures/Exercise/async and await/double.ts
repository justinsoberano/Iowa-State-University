
function doublein2seconds(x) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {resolve(x * 2);}, 2000);
    });
}

async function addAsync(x) {
    const a = await doublein2seconds(x);
    console.log("First Number :" + a);
    const b = await doublein2seconds(a);
    console.log("Second Number :" + b);
    const c = await doublein2seconds(b);
    console.log("Third Number :" + c);
    
}

let x = 2;
addAsync(x);

