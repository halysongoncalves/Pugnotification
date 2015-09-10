########################################### GSON ###########################################
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class br.com.itausolucoes.participante.model.entities.** { *; }

######################################### Retrofit ###########################################
-keep interface com.squareup.okhttp.** { *; }
-keep class com.squareup.okhttp.** { *; }
-keep class retrofit.** { *; }
-keep class javax.inject.* { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-dontwarn retrofit.**

######################################### CRASHLYTICS ###########################################
-keepattributes SourceFile,LineNumberTable
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

######################################### OTTO ###########################################
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}
######################################### PICASSO ###########################################
-dontwarn com.squareup.okhttp.**

######################################### RETROFIT ###########################################
-dontwarn rx.**
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-dontwarn retrofit.**
-dontwarn android.support.**
-dontwarn retrofit.appengine.UrlFetchClient
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keepattributes Signature
-keepattributes *Annotation*

######################################### Annotations ###########################################
-dontwarn org.springframework.**