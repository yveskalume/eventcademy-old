# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# MavericksViewModel loads the Companion class via reflection and thus we need to make sure we keep
# the name of the Companion object.
-keepclassmembers class ** extends com.airbnb.mvrx.MavericksViewModel {
    ** Companion;
}

# Members of the Kotlin data classes used as the state in Mavericks are read via Kotlin reflection which cause trouble
# with Proguard if they are not kept.
# During reflection cache warming also the types are accessed via reflection. Need to keep them too.
-keepclassmembers,includedescriptorclasses,allowobfuscation class ** implements com.airbnb.mvrx.MavericksState {
   *;
}

# The MavericksState object and the names classes that implement the MavericksState interface need to be
# kept as they are accessed via reflection.
-keepnames class com.airbnb.mvrx.MavericksState
-keepnames class * implements com.airbnb.mvrx.MavericksState

# MavericksViewModelFactory is referenced via reflection using the Companion class name.
-keepnames class * implements com.airbnb.mvrx.MavericksViewModelFactory