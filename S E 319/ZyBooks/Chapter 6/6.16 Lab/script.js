
function isStrongPassword(password) {
    if(password.length < 8){
        return false;
    }
    if(!password.toUpperCase()){
        return false;
    }
    if(password.includes("password")){
        return false;
    } else {
        return true;
    }
}
