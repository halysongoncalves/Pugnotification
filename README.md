# PugNotification
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.halysongoncalves/pugnotification/badge.svg)](http://search.maven.org/#artifactdetails|com.github.halysongoncalves|pugnotification|1.2.0|)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PugNotification-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1688)

![Screenshots](https://raw.githubusercontent.com/halysongoncalves/pugnotification/master/art/screenshot.png)

# Download

Download [the latest AAR][1] or grab via Maven:
```xml
<dependency>
  <groupId>com.github.halysongoncalves</groupId>
  <artifactId>pugnotification</artifactId>
  <version>1.2.0</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.github.halysongoncalves:pugnotification:1.2.0'
```

# Introduction

You're probably tired of writing code to display notifications in your applications, the library abstracts all the notifications construction process for you in a single line of code. Magic? Lie? I summarize in: productivity.
To further improve productivity, pugnotification from release 1.2.0 now has support Android Wear.

```java
PugNotification.with(context)
    .load()
    .identifier(identifier)
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
    .smallIcon(smallIcon)
    .largeIcon(largeIcon)
    .button(icon, title, pendingIntent)
    .click(cctivity, bundle)
    .dismiss(activity, bundle)
    .color(color)
    .ticker(ticker)
    .when(when)
    .vibrate(vibrate)
    .lights(color, ledOnMs, ledOfMs)
    .sound(sound) 
    .autoCancel(autoCancel)
    .simple()
    .build();
```

Many common pitfalls in building cases are handled automatically by PugNotification:

Custom Notifications depend on RemoteViews and Android has RemoteView support for Api's below Jelly Bean.
Notifications without no messages and title does not make sense there.
In addition to these treatments, there are other held by Picasso Library Square:

Handling ImageView recycling and download cancelation in an adapter.
Complex image transformations with minimal memory use.
Automatic memory and disk caching.


# Simple Notification

Simple notification with just text and message.

```java
PugNotification.with(context)
    .load()
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
    .smallIcon(R.drawable.pugnotification_ic_launcher)
    .largeIcon(R.drawable.pugnotification_ic_launcher)
    .custom()
    .background(url)
    .build();
```


# Custom Notification

We have changed the way the library handles the download images for custom notifications. Previously disrespectfully because of the Picasso library. But many users were asking for the option of being able to choose how to download the image.
So we serve the requests and modify the library to allow the download of image management as an example:

```java
PugNotification.with(context)
    .load()
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
    .smallIcon(R.drawable.pugnotification_ic_launcher)
    .largeIcon(R.drawable.pugnotification_ic_launcher)
    .color(android.R.color.background_dark)
    .custom()
    .background(url)
    .setImageLoader(Callback)
    .setPlaceholder(R.drawable.pugnotification_ic_placeholder)
    .build();  
```


# Wear Notification

PugNotification from release 1.2.0 started to support all types of notifications to Android Wear. We try to anticipate us to make life easier for developers to develop applications for wearable.

```java
      PugNotification.with(mContext).load()
          .smallIcon(R.drawable.pugnotification_ic_launcher)
          .autoCancel(true)
          .largeIcon(R.drawable.pugnotification_ic_launcher)
          .title(title)
          .message(message)
          .bigTextStyle(bigtext)
          .wear()
          .background(Bitmap)
          .setRemoteInput(icon, title, pendingIntent, remoteInput)
          .setPages(List<Notification> listNotification)
          .setHideIcon(Boolean)
          .build();
```
# What's New
Now just the client implement the ImageLoader interface and implement a way to manage the download of the image. Below we use the Picasso:

```java
    @Override
    public void load(String uri, final OnImageLoadingCompleted onCompleted) {
        viewTarget = getViewTarget(onCompleted);
        Picasso.with(this).load(uri).into(viewTarget);
    }
    
    private static Target getViewTarget(final OnImageLoadingCompleted onCompleted) {
        return new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                onCompleted.imageLoadingCompleted(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
    }
```
PugNotification supports placeholders if download the image in the background is not successful. The library already have a default placeholder size 622x384.


# ProGuard

If you use the Picasso to manage the download of the image, add the line below to your proguard file:

```
-dontwarn com.squareup.okhttp.**
```

# Contributing

If you would like to contribute code you can do so through GitHub by forking the repository and sending a pull request.

When submitting code, please make every effort to follow existing conventions and style in order to keep the code as readable as possible. Please also make sure your code compiles by running gradlew clean and gradlew assemble.


# License

    Copyright 2013 Halyson L. Gonçalves, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: http://repo1.maven.org/maven2/com/github/halysongoncalves/pugnotification/1.2.0/pugnotification-1.2.0.aar
