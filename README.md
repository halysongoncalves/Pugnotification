# PugNotification
=================


### Download

Download [the latest JAR][1] or grab via Maven:
```xml
<dependency>
  <groupId>com.github.halysongoncalves</groupId>
  <artifactId>pugnotification</artifactId>
  <version>1.0.1</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.github.halysongoncalves:pugnotification:1.0.1'
```
---

### Introduction

You're probably tired of writing code to display notifications in your applications, the library abstracts all the notifications construction process for you in a single line of code. Magic? Lie? I summarize in: productivity.

```java
PugNotification.with(context)
    .load()
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
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

---

### Simple Notification

Simple notification with just text and message.

```java
PugNotification.with(context)
    .load()
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
    .smallIcon(R.drawable.pugnotification_ic_launcher)
    .largeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.pugnotification_ic_launcher))
    .custom()
    .background(url)
    .build();
```

---

### Custom Notification

PugNotification supports placeholders if download the image in the background is not successful. The library already have a default placeholder size 622x384.

```java
PugNotification.with(context)
    .load()
    .title(title)
    .message(message)
    .bigTextStyle(bigtext)
    .smallIcon(R.drawable.pugnotification_ic_launcher)
    .largeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.pugnotification_ic_launcher))
    .custom()
    .background(url)
    .setPlaceholder(R.drawable.pugnotification_ic_placeholder)
    .build();  
```

Using the picasso library for working with images. The request will be repeated three times before the error placeholder is shown.

---

### ProGuard 

If you are using ProGuard make sure you add the following option:

```
-dontwarn com.squareup.okhttp.**
```

---

### Contributing

If you would like to contribute code you can do so through GitHub by forking the repository and sending a pull request.

When submitting code, please make every effort to follow existing conventions and style in order to keep the code as readable as possible. Please also make sure your code compiles by running gradlew clean and gradlew assemble.

---

License
--------

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

[1]: http://repo1.maven.org/maven2/com/github/halysongoncalves/pugnotification/1.0.1/pugnotification-1.0.1.aar