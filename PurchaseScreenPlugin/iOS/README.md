# Purchase Screen Plugin Example for iOS

This is an example project for creating a screen plugin with in-app purchases for the Applicaster Zapp Platform. You can use this example project as a reference for how to build your own plugin.

If you are not familiar with Zapp please visit [our website](http://applicaster.com/?page=product) for more details.

The full [Zapp](http://zapp.applicaster.com) plugins documentation is available [here](https://developer-zapp.applicaster.com).

## Getting Started
Clone this project `$ git clone https://github.com/applicaster/zapp-plugins-examples`.

CD into `zapp-plugins-examples/PurchaseScreenPlugin/iOS/PurchaseScreenPlugin` 

Run `$ pod install` in order to set the workspace.

Open `PurchaseScreenPlugin.xcworkspace` with Xcode 10.2.1.

### ZPPluggableScreenProtocol

`Screen Plugins` are plugins that are presented as standalone screens, A user can trigger the launch of a screen from navigation bar, root (menu) or click on any cell inside application. More about it you can read at [docs](https://developer-zapp.applicaster.com/ui-builder/ios/ScreenPlugin.html).


### In-app Purchases

In-app purchases were implemented based on ApplicasterIAP framework. This framework makes setup of in-app purchases more easily providing callback based API to communicate with Apple Store. More about it you can read inside [repository](https://github.com/applicaster/applicaster-iap-framework) and [wiki](https://github.com/applicaster/applicaster-iap-framework/wiki/iOS)