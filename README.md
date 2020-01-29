# HackU

## Screenshots
![Image of HackU Assistant](https://i.imgur.com/SNDg0kj.png)

![Image of Interest Profile](https://i.imgur.com/hz5UpZ5.png)

## Inspiration
Based off of our own hackathon experiences and those of our friends, we know that the most chaotic portion of almost every hackathon is team formation - everyone scrambles to find other individuals with skills that compliment theirs & make a strong team. But this is often facilitated via Facebook Groups/Pages or Slack channels that contain hundreds of members, which can be messy and overwhelming for new hackers.

So we came up with HackU: an machine-learning (ML) assisted android app that aids with hackathon team formation and provides a common network by which hackers can connect with others that have similar/complimentary interests & skills.

## What it does
The app allows hackers to sign-in/register via Firebase authentication, after which they'll be greeted with the Watson Assistant HackU. At this stage, the hacker is walked through some initial setup with HackU, such as getting their name, providing them with information and map data on local hackathons, and guiding them towards the interest selector screen. 
The final portion of the app has the hacker using a swipe feature to select which characteristics and development skills they'd like to be associated to their profile.This data would then be taken into HackU's backend to begin funneling hackers with similar interests/skills together to initiate team formation, after which the app would provide them with a chatroom to communicate.

## How we built it
We built the app with Watson Assistant integration in mind as the core feature, with the swipe card and login/registration functionality as supporting systems. For Watson Assistant, we followed open-source documentation on how to create the assistant and conversational flow, how to actually integrate it into Android (via dependencies and code), and how to modify the documentation code to achieve what we had in mind. Regarding the swipe card page, we integrated an easy-to-use open source project and modified the implementation to fit our needs. Lastly, we integrated Firebase with HackU to facilitate account creation/registration and authenticated sign-in.

## Challenges we ran into
The biggest challenge we ran into was getting the Watson Assistant integration to co-operate how we wanted it to in the app. This took the biggest chunk of our development and debugging time as the Watson Assistant integration is one of the core functionalities of the app.The other major challenge we had consisted of dependency conflicts between the androidx and android support libraries that took some time to resolve.

## Accomplishments that we're proud of
We're proud of our accomplishment of successfully integrating the Watson Assistant into the app and having it govern specific flows within the app. It's thanks to this integration that HackU can have a tailored assistance experience for each hacker, and allow us to provide them a more streamlined experience.

## What we learned
We learned a lot about how to properly integrate an ML chatbot into an Android app and begin tailoring a streamlined conversational experience that is context-aware and intuitive. We feel that this is important because as smart assistants improve over time, their integration into our everyday lives will become more pervasive and intertwined

## What's next for HackU
What we have planned in mind next for HackU is:
 - Implement the hacker-to-hacker group chat functionality via Firebase
 - Expand and refine Watson's ML model for better conversational precision and added functionality
 - Implement a viewable personalized hacker profile 
 - Implement funneling algorithm in conjunction with MongoDB
