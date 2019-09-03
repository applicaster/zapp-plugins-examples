Pod::Spec.new do |s|

  s.name             = "MyAdvertisementPlugin"
  s.version          = '1.0.0'
  s.summary          = "An Example of advertisement plugin framework for Zapp iOS."
  s.description      = <<-DESC
                        An EXample of advertisement plugin framework for Zapp iOS.
                       DESC
  s.homepage         = "https://github.com/applicaster/zapp-plugins-examples.git"
  s.license          = 'MIT'
  s.author           = { "Udi Lumnitz" => "u.lumnitz@applicaster.com" }
  s.source           = { :git => "git@github.com:applicaster/zapp-plugins-examples.git", :tag => s.version.to_s }

  s.platform     = :ios, '10.0'
  s.requires_arc = true

  s.public_header_files = 'MyAdvertisementPlugin/**/*.h'
  s.source_files = 'MyAdvertisementPlugin/**/*.{h,m,swift}'

  s.resources = [
                  "MyAdvertisementPlugin/**/*.xcassets",
                  "MyAdvertisementPlugin/**/*.storyboard",
                  "MyAdvertisementPlugin/**/*.xib",
                  "MyAdvertisementPlugin/**/*.png"]

  s.xcconfig =  { 'CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES' => 'YES',
                  'ENABLE_BITCODE' => 'YES',
                  'OTHER_LDFLAGS' => '$(inherited)',
                  'FRAMEWORK_SEARCH_PATHS' => '$(inherited) "${PODS_ROOT}"/**',
                  'LIBRARY_SEARCH_PATHS' => '$(inherited) "${PODS_ROOT}"/**',
                  'SWIFT_VERSION' => '5.1'
                  }

  s.dependency 'ZappPlugins'

  s.static_framework = true

end
