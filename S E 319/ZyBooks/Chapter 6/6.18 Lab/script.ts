function divideArray(nums) {
    var evens = [];
    var odds = [];
    for (var i = 0; i < nums.length; i++) {
        if (nums[i] % 2 == 0) {
            evens.push(nums[i]);
        }
        else {
            odds.push(nums[i]);
        }
    }

    var sortedevens = evens.sort();
    var sortedodds = odds.sort();

    console.log("Even numbers:");
    if (sortedevens.length == 0) {
        console.log("None");
    }
    else {
        for (let i = 0; i < sortedevens.length; i++) {
            console.log(sortedevens[i]);
        }
    }
    console.log("Odd numbers:");
    if (sortedodds.length == 0) {
        console.log("None");
    }
    else {
        for (let i = 0; i < sortedodds.length; i++) {
            console.log(sortedodds[i]);
        }
    }
}
