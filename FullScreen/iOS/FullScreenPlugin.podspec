Pod::Spec.new do |s|

  s.name             = "FullScreenPlugin"
  s.version          = '1.0.0'
  s.summary          = "An Example Full Screen Plugin"
  s.description      = <<-DESC
                        An EXample of a full screen plugin for Zapp iOS.
                       DESC
  s.homepage         = "https://github.com/applicaster/zapp-plugins-examples/FullScreen/iOS"
  s.license          = 'MIT'
  s.author           = { "Liviu Romascanu" => "l.romasca@applicaster.com" }
  s.source           = { :git => "git@github.com:applicaster/zapp-plugins-examples.git", :tag => s.version.to_s }

  s.platform     = :ios, '10.0'
  s.requires_arc = true

  s.public_header_files = 'FullScreenPlugin/**/*.h'
  s.source_files = 'FullScreenPlugin/**/*.{h,m,swift}'

  s.resources = [
                  "FullScreenPlugin/**/*.xcassets",
                  "FullScreenPlugin/**/*.storyboard",
                  "FullScreenPlugin/**/*.xib",
                  "FullScreenPlugin/**/*.png"]

  s.xcconfig =  { 'CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES' => 'YES',
                  'ENABLE_BITCODE' => 'YES',
                  'OTHER_LDFLAGS' => '$(inherited)',
                  'FRAMEWORK_SEARCH_PATHS' => '$(inherited) "${PODS_ROOT}"/**',
                  'LIBRARY_SEARCH_PATHS' => '$(inherited) "${PODS_ROOT}"/**'
                }

  s.dependency 'ZappPlugins'

end
