#import "GuardianPlugin.h"
#import <guardian/guardian-Swift.h>

@implementation GuardianPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftGuardianPlugin registerWithRegistrar:registrar];
}
@end
