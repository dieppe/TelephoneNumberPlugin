#import <Cordova/CDV.h>
#import "SIMInfos.h"

@implementation SIMInfos

// Does nothing, impossible on iOS
- (void)getPhoneNumber:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@""];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)getCountry:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    CTTelephonyNetworkInfo *netInfo = [[CTTelephonyNetworkInfo alloc] init];
    if (netInfo) {
        CTCarrier *carrier = [netInfo subscriberCellularProvider];
        if (carrier) {
            NSString *result = [carrier isoCountryCode];
            if (result) {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
            }
        }
    }
    if (!pluginResult) {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"info not available"];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end