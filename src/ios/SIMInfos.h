#import <CoreTelephony/CTTelephonyNetworkInfo.h>
#import <CoreTelephony/CTCarrier.h>
#import <Cordova/CDVPlugin.h>

@interface SIMInfos : CDVPlugin
{}

- (void)getPhoneNumber:(CDVInvokedUrlCommand*)command; // Does nothing, impossible on iOS
- (void)getCountry:(CDVInvokedUrlCommand*)command;

@end