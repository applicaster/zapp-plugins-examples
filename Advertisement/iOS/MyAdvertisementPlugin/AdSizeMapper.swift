import ZappPlugins

public class AdSizeMapper {
    static func size(forAdType adType: ZPAdType, inlineBannerSize: String = "") -> CGSize {
        var size = CGSize.zero
        
        switch adType {
        case .inlineBanner:
            if inlineBannerSize == "BANNER" {
                size = CGSize(width: 320, height: 50)
            }else if inlineBannerSize == "BOX_BANNER" {
                size = CGSize(width: 320, height: 150)
            }else if inlineBannerSize == "SMART_BANNER" {
                size = CGSize(width: UIScreen.main.bounds.width, height: 50)
            }
            
        case .screenBanner:
            size = CGSize(width: 320, height: 50)
            
        default:
            size = CGSize(width: 320, height: 50)
        }
        
        return size
    }
}

