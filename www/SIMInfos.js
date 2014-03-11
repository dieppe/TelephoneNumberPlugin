var exec = require("cordova/exec");

var TelephoneNumber = function () {};

TelephoneNumber.NO_TELEPHONE_NUMBER = 0;

TelephoneNumber.prototype.get = function (success, fail) {
    exec(success,fail,'TelephoneNumber','get',[]);
};

module.exports = TelephoneNumber;