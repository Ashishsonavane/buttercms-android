# Buttercms
This repository is an example of using buttercms client from [android library](https://github.com/ButterCMS/buttercms-kotlin).

<img src="https://github.com/deAnnaa/buttercms/blob/master/screenshots/screenshot.png" width=20% height=20%> <img src="https://github.com/deAnnaa/buttercms/blob/master/screenshots/screenshot_1.png" width=20% height=20%> <img src="https://github.com/deAnnaa/buttercms/blob/master/screenshots/screenshot_2.png" width=20% height=20%> <img src="https://github.com/deAnnaa/buttercms/blob/master/screenshots/screenshot_3.png" width=20% height=20%>

The app is connected to a sample ButterCMS instance. It demonstrates usage of the following main object available in ButterCMS:
- Pages
- Blog posts
- Collections

Pages and Collections data classes are generic and needs to be defined by user. 
For this sample are these classes prepared: 
- [Page](https://github.com/deAnnaa/buttercms/blob/master/app/src/main/java/com/example/buttercms/model/Page.kt)
- [HomePage](https://github.com/deAnnaa/buttercms/blob/master/app/src/main/java/com/example/buttercms/model/HomePage.kt)
- [Collection](https://github.com/deAnnaa/buttercms/blob/master/app/src/main/java/com/example/buttercms/model/Collection.kt)


## Installation
Requirements: Android Studio (min 4.2+ version)
- Download Sample application from Github
- 
``
git clone https://github.com/ButterCMS/buttercms-ios.git
``

- Open file buttercms
- Build and Run

## Amend to your needs
First of all you need to create instance of ButterCMS client. 
For that you need to change apikey in MainActivity.kt, line 17

``
val client = ButterCMS("your_api_key")
``

Then you need to change models to reflect you configuration of pages, collections and posts. 
You can find existing ones in folder Model.
