function myresolve(t) {
    return "Sucess!! Promise waited [" + t + "]ms";
}
function sleep(t) {
    var mypromise = new Promise(function (resolve, reject) {
        setTimeout(function () { resolve(myresolve(t)); }, t);
    });
    mypromise.then(function (result) { return alert(result); }, function (error) { return alert(error); });
}
sleep(3000);
