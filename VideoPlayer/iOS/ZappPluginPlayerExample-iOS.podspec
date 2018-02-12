Pod::Spec.new do |s|

  s.name             = "ZappPluginPlayerExample-iOS"
  s.version          = '1.0.0'
  s.summary          = "An EXample of video player framework for Zapp iOS."
  s.description      = <<-DESC
                        An EXample of video player framework for Zapp iOS.
                       DESC
  s.homepage         = "https://github.com/applicaster/ZappPluginPlayerExample-iOS"
  s.license          = 'MIT'
  s.author           = { "Udi Lumnitz" => "u.lumnitz@applicaster.com" }
  s.source           = { :git => "git@github.com:applicaster/ZappPluginPlayerExample-iOS.git", :tag => s.version.to_s }

  s.platform     = :ios, '9.0'
  s.requires_arc = true

  s.public_header_files = 'ZappPluginPlayerExample-iOS/**/*.h'
  s.source_files = 'ZappPluginPlayerExample-iOS/**/*.{h,m,swift}'

  s.resources = [
                  "ZappPluginPlayerExample-iOS/**/*.xcassets",
                  "ZappPluginPlayerExample-iOS/**/*.storyboard",
                  "ZappPluginPlayerExample-iOS/**/*.xib",
                  "ZappPluginPlayerExample-iOS/**/*.png"]

  s.xcconfig =  { 'CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES' => 'YES',
                  'ENABLE_BITCODE' => 'NO',
                  'OTHER_LDFLAGS' => '$(inherited)',
                  'FRAMEWORK_SEARCH_PATHS' => '$(inherited) "${PODS_ROOT}"/**',
                  'LIBRARY_SEARCH_PATHS' => '$(inherited) "${PODS_ROOT}"/**'
                }

  s.dependency 'ZappPlugins'
  s.dependency 'ApplicasterSDK'

end
