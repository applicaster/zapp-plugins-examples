Pod::Spec.new do |s|
  s.name                = 'SampleGeneralPlugin'
  s.version             = '0.1.0'
  s.summary             = 'A sample general plugin with support for URL scheme handling.'
  s.description         = 'A sample plugin that integrates to Applicaster Zapp based applications'
  s.homepage            = 'https://github.com/applicaster/ZappGeneralPluginExample-iOS'
  s.license             = 'MIT'
  s.author              = { 'liviur' => 'l.romasca@applicaster.com' }
  s.source              = { :git => 'https://github.com/applicaster/ZappGeneralPluginExample.git', :tag => s.version.to_s }

  s.platform            = :ios, '10.0'
  s.requires_arc        = true

  s.frameworks = 'UIKit'
  s.source_files = 'SampleGeneralPlugin/**/*.{swift}'
  s.resources = [
    "SampleGeneralPluginResources/**/*.xcassets",
    "SampleGeneralPluginResources/**/*.storyboard",
    "SampleGeneralPluginResources/**/*.xib",
    "SampleGeneralPluginResources/**/*.png"
  ]

  s.xcconfig =  { 'CLANG_ALLOW_NON_MODULAR_INCLUDES_IN_FRAMEWORK_MODULES' => 'YES',
                  'ENABLE_BITCODE' => 'YES',
                  'OTHER_LDFLAGS' => '$(inherited)',
                  'FRAMEWORK_SEARCH_PATHS' => '$(inherited) "${PODS_ROOT}"/**',
                  'LIBRARY_SEARCH_PATHS' => '$(inherited) "${PODS_ROOT}"/**',
                  'SWIFT_VERSION' => '5.1'
                }

  s.dependency 'ZappPlugins'
end
