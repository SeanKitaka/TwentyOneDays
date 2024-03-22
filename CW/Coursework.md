- Module Code:  CS2PJ20
- Assignment report Title:    Spring Android Studio App coursework
- Date (when the work completed): 25/03/2024
- Actual hrs spent for the assignment: 16

## Overview

An Issue that has become prevalent recently is the issue around discipline 
and being able to avoid procrastination. Especially with the introduction of tiktok and the increased use of social media apps, 
many have been able to voice their opinions and talk about their struggles with procrastination and 
being able to be consistent in performing tasks.

In order to combat this, I have decided to develop a habit tracker app, this application is targeted
at people who struggle with maintaining habits and making part of their daily life by prompting them
to record their habits or tasks and keeps track of it.

Outline the objectives of the application development.

For the application to be successful. It has to be:

Easy to use - if people are struggling with forming habits, this shouldn't make it even harder. To 
make it easy and intuitive to use, there will be a focus on developing a simple user interface that 
doesn't have any large painpoints that will dissuade users from continuing to use it. 

Versatile - People have many different habits and goals that they are working towards so this should
be able to accommodate a large range of them. a way that this can be done is by allowing habits to be
recorded through a large range of media: text input, images, videos and audio. 

Motivating - the hard part of building a habit is the delayed gratification, as your not able to see
results or progress instantly, hence this application should be able to keep record and easily 
display the progress that people make over time. A way this can be done is having a few methods of
visualising the progress made, for example, if a person is recording progress of them learning the 
piano, videos that they submit over the past weeks can be displayed to them to highlight how much 
they have improved, or a calendar showing the times that they recorded their habit can be visualised,
to show how consistent they have been and motivate them to continue on this trend.


 

2. Application Specifications
   a. Describe the technical specifications for your application, such as functional
   components, algorithms for the basic functions and advance functions,
   
Each of the pages that the user will interact with would be an activity, which means that it would 
have an xml file and a Java class, these classes will be filled with functions to control the respective activities. 
For example, in the welcome screen there will be on click listeners for all of the buttons.

The information used within authentication will be abstracted from the Firestore database that I will 
be using for the management of user data. I will have a class that is responsible for user information 
and will be used in relation to communicating with firestore to create, retrieve, update and delete data.

I will try to have functions that will  be responsible to actions that the user will be trying to do,
for example, collecting and displaying the recent check-in's and making any milestones that they want to reach. 
and if it is possible within the time constraints, to organise a view of the users friends and their highlights. 


   b. user interface of the application

In relation to the user interface. I am choosing to have the colour blue for the general theme color 
of the application. This is because the color blue is a 'cool' color, so will be visually comforting 
for the users and the colour is often associated with stability and trustworthiness which will be beneficial
in relation to the application as it will make users more comfortable in logging their data with this application.

![blue colour pallette.png](blue%20colour%20pallette.png)

I will try to use rounded edges and a more modern font family as rounded edges can improve usability
by reducing the risk of accidental taps or swipes on sharp corners, especially on mobile devices where
precision can be challenging. This can lead to a smoother and more frustration-free user experience,
encouraging users to interact with the app more frequently.Modern font families are often designed with
legibility and readability in mind, making them suitable choices for digital interfaces. These fonts
typically feature clean and well-defined letterforms, which can improve readability on various screen
sizes and resolutions.

![img.png](img.png)
3. Application Implementation
   a. Examination of the basic functionalities implemented in the application, such as user authentication and personalized elements (timetable for the UoR Student app or levels for a game). Full-screen screen-
   shots should be provided to demonstrate the technical implementation of these features, facilitating review by displaying the code and its
   outcomes as shown in Figure 1.


   b. Provide screenshots of users registered in Firebase to illustrate User
   Authentication as shown in Figure 2.
   

c. Discussion of two additional features included in the application,
   showcasing creativity and understanding of the users' needs or en-
   hancing the gameplay experience. Full-screen screenshots should be
   provided to demonstrate the technical implementation of these addi-
   tional features, facilitating review by displaying both the code and its
   outcomes (e.g., Figure 1).
    

4. Conclusions and Future Work
   a. Concluding remarks (Summary of the project outcomes).
   b. Reflection on overall learning experience and achievements.
   c. Future Work (Proposals on any potential future improvements or expansion for the applica-
   tion including potential updates, new features, and other enhancements).
