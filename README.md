[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-PugNotification-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1688)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.halysongoncalves/pugnotification/badge.svg)](http://search.maven.org/#artifactdetails|com.github.halysongoncalves|pugnotification|1.8.1|)
[![Build Status](https://api.travis-ci.org/halysongoncalves/Pugnotification.svg)](https://travis-ci.org/halysongoncalves/pugnotification)
[![Coverage Status](https://coveralls.io/repos/halysongoncalves/pugnotification/badge.svg)](https://coveralls.io/r/halysongoncalves/pugnotification)
[![Join the chat at https://gitter.im/halysongoncalves/pugnotification](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/halysongoncalves/pugnotification?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

![soon](https://raw.githubusercontent.com/halysongoncalves/pugnotification/develop/art/soon.png)

# Download

Download [the latest AAR][1] or grab via Maven:
```xml
<dependency>
  <groupId>com.github.halysongoncalves</groupId>
  <artifactId>pugnotification</artifactId>
  <version>1.8.1</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.github.halysongoncalves:pugnotification:1.8.1'
```

# Introduction

![Screenshots](https://raw.githubusercontent.com/halysongoncalves/pugnotification/master/art/screenshot.png)

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
    .flags(Notification.DEFAULT_ALL)
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
    .flags(Notification.DEFAULT_ALL)
    .simple()
    .build();
```

# Progress Notification

Simple notification with progress.

```java
PugNotification.with(context)
    .load()
    .identifier(identifier)
    .smallIcon(R.drawable.pugnotification_ic_launcher)
    .progress()
    .value(progress,max, indeterminate)
    .build();
```

```java
PugNotification.with(context)
    .load()
    .identifier(identifier)
    .smallIcon(R.drawable.pugnotification_ic_launcher)
    .progress()
    .update(identifier,progress,max, indeterminate)
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
    .flags(Notification.DEFAULT_ALL)
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
    .flags(Notification.DEFAULT_ALL)
    .wear()
    .background(Bitmap)
    .setRemoteInput(icon, title, pendingIntent, remoteInput)
    .setPages(List<Notification> listNotification)
    .setHideIcon(Boolean)
    .build();
```
# What's New
*1.8.1

Fix Message Spanned length check

*1.8.0

Adding method for cancellation in tag-based notifications. And added validation to ensure that the color assigned in the method is @ColorRes.

*1.7.0

Added a new type of notification, progress. Now you can assign the basic features of a notification, while also adding a progressbar in the notification.

It was also added new method: ongoing (). With it possible to make false removing the notification.

*1.6.0

Fixed bug that did not allow the award of a sound and made some organizations and otimizaçnoes the code.

Now it is necessary to set one of the flag's: Notification.DEFAULT_ALL, Notification.DEFAULT_SOUND, Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS

*1.5.0

Method has been added that allows informing the existing configuration of the default notice.
By default it is already configured with Notification.DEFAULT_ALL if you want to change just inform the new configuration in the defaults method (int defaults).

*1.4.0

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

[1]: http://repo1.maven.org/maven2/com/github/halysongoncalves/pugnotification/1.8.1/pugnotification-1.8.1.aar
