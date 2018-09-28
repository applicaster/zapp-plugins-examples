import ZappPlugins

public class MyAdvertisementPlugin: NSObject, ZPAdPluginProtocol {
    
    //MARK: - ZPAdapterProtocol
    
    public let configurationJSON: NSDictionary?
    
    required override public init() {
        self.configurationJSON = nil
        super.init()
    }
    
    required public init(configurationJSON: NSDictionary?){
        self.configurationJSON = configurationJSON
        super.init()
    }
    
    //MARK: - ZPAdPluginProtocol
    
    public func createAdPresenter(adView:ZPAdViewProtocol, parentVC:UIViewController) -> ZPAdPresenterProtocol {
        return MyAdvertisementPresenter(adView: adView, parentVC: parentVC)
    }
    
    public func size(forInlineBannerSize inlineBannerSize: String) -> CGSize {
        return AdSizeMapper.size(forAdType: .inlineBanner, inlineBannerSize: inlineBannerSize)
    }
    
    public func providerName() -> String {
        return "MyAdvertisementPlugin"
    }
}

