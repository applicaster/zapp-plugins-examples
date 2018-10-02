import ZappPlugins

public class MyAdvertisementPresenter: NSObject, ZPAdPresenterProtocol {
    var parentVC: UIViewController
    var adView: ZPAdViewProtocol
    var adConfig :ZPAdConfig?
    var interstitial: UIViewController?
    
    //MARK: - ZPAdPresenterProtocol
    
    required public init(adView: ZPAdViewProtocol, parentVC: UIViewController) {
        self.parentVC = parentVC
        self.adView = adView
        super.init()
    }
    
    public func load(adConfig: ZPAdConfig) {
        self.adConfig = adConfig
        adView.stateChanged(adViewState: .loading )
        
        if adConfig.adType == .interstitial {
            interstitial = UIViewController()
            interstitial?.view.backgroundColor = UIColor.yellow
            adView.adLoaded(view: nil)
        }else if adConfig.adType == .screenBanner {
            let view = UIView(frame: CGRect(x: 0, y: 0, width: getSize().width, height: getSize().height))
            view.backgroundColor = UIColor.red
            adView.adLoaded(view: view)
        }else if adConfig.adType == .inlineBanner {
            let view = UIView(frame: CGRect(x: 0, y: 0, width: getSize().width, height: getSize().height))
            view.backgroundColor = UIColor.green
            adView.adLoaded(view: view)
        }
        adView.stateChanged(adViewState:.loaded)
    }
    
    public func getSize() -> CGSize {
        if let adType = adConfig?.adType, let inlineBannerSize = adConfig?.inlineBannerSize {
            return AdSizeMapper.size(forAdType: adType, inlineBannerSize: inlineBannerSize)
        }
        return CGSize.zero
    }
    
    public func showInterstitial() {
        guard let interstitial = interstitial else {
            return
        }
        parentVC.present(interstitial, animated: true) {
            interstitial.dismiss(animated: true, completion: nil)
        }
        adView.stateChanged(adViewState:.impressed)
    }
}

