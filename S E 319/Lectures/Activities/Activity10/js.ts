function myresolve(t) {
    return "Sucess!! Promise waited [" + t + "]ms";
}
function sleep(t) {
    let mypromise = new Promise((resolve, reject) => {
        setTimeout(() => { resolve(myresolve(t)) }, t)
    });
    mypromise.then(
        result => alert(result),
        error => alert(error)
    );
}
sleep(3000);
