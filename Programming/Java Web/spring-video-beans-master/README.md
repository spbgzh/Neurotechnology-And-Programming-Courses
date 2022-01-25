# Spring Video Beans

This exercise is to practice configuring beans.

The domain area is online video hosting. 
There is a [Video](src/main/java/com/epam/rd/autotasks/confbeans/video/Video.java) class to represent metadata of a video, 
a [Channel](src/main/java/com/epam/rd/autotasks/confbeans/video/Channel.java) class representing some collection of videos
and a [VideoStudio](src/main/java/com/epam/rd/autotasks/confbeans/video/VideoStudio.java) interface defining interface of video factory.

Here goes a description of exercise configurations to create.
Place them into `com.epam.rd.autotasks.confbeans.config` package.

### SingletonChannelConfig
Define three Video beans:
- How to boil water (pubTime: October, 10 2020, 10:10)
- How to build a house (pubTime: October, 10 2020, 10:11)
- How to escape solitude (pubTime: October, 10 2020, 10:12)

At least the first ban must have the name "video1".

Define a channel bean containing those three beans.

### PrototypeChannelConfig
Configure beans just as in SingletonChannelConfig configuration, but make channel bean prototype scoped.
  
### SingletonChannelWithPrototypeVideosConfig
Configure beans just as in SingletonChannelConfig configuration, but make video beans prototype scoped.

### ChannelWithVideoStudioConfig
Define a video studio bean, creating movies of "Cat & Curios" franchise.

First movie has a name "Cat & Curios 1" and was released at October, 18 2001, 10:00.

All sequels has incremented number in their name and releases exactly two years after previous release.

Define a channel bean containing eight movies of the franchise.

Be sure to avoid video beans.

### ChannelWithPhantomVideoStudioConfig
We are configuring the "Cat & Curios" franchise just as in ChannelWithVideoStudioConfig.

But you must not use a bean of video studio.
Use a prototype scoped bean of video instead. 

### ChannelWithInjectedPrototypeVideoConfig
Imagine a video channel releasing a new "Cat Failure Compilation" video each day.

You must configure such a channel, but you must not use video studio beans.
Also, this channel bean must use a prototype scoped video bean to produce another video.

*NB: You should consider extending [Channel](src/main/java/com/epam/rd/autotasks/confbeans/video/Channel.java) class.*
