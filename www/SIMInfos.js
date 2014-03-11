var exec = require("cordova/exec");

var SIMInfos = function () {};

SIMInfos.NO_TELEPHONE_NUMBER = 0;

SIMInfos.prototype.getPhoneNumber = function (success, fail) {
    exec(success,fail,'SIMInfos','getPhoneNumber',[]);
};

SIMInfos.prototype.getCountry = function (success, fail) {
    exec(success,fail,'SIMInfos','getCountry',[]);
};

module.exports = SIMInfos;