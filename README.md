# UMAguessr
## Backend
## Frontend
### Game
- Zoomable image
- Popup map
- Score/round counter
### Menu
- Mode selection buttons
- Scoreboard

## LOG:

### Day 1:

We made up teams for the back and front ends, we created the Github repository and the Trello and we started brainstorming for ideas on how to implement each of the methods and objects. After that we started to try things with Java Swing and we developed some of the first proof of concepts for the map and the clicking. We also developped an class diagram for the frontend model.

<table>
  <tr>
    <th><img width="960" alt="trelloLog" src="https://github.com/JavierStark/UMAguessr/assets/153517059/c80cb0e5-12e0-41f6-9865-b22b67949785"></th>
    <th><img width="330" alt="model" src="https://github.com/JavierStark/UMAguessr/assets/153517059/ea522a86-415d-45e1-b588-00d811abf02c"></th>
  </tr>
</table>

### Day 2:
The frontend team found a way to do zooming on images in the UI using Swing and found a class called Robot which could be used to do unit testing over the GUI when starting with TDD.

### Day 4:
We start today the official sprint after some days of research and planning.
![Screenshot 2024-05-19 170136](https://github.com/JavierStark/UMAguessr/assets/56975675/09b1cdf7-0fe7-4fb0-a1d6-3a8d2b2adfe0)
![Screenshot 2024-05-19 170155](https://github.com/JavierStark/UMAguessr/assets/56975675/5260dfda-3bef-44bb-9ef4-891139bef0fe)
![Screenshot 2024-05-19 170503](https://github.com/JavierStark/UMAguessr/assets/56975675/d46b2863-0f17-4e7b-911a-9e4e84ae09b5)

### Day 5:
We have held our first official sprint meetings, one for each team. In the frontend meeting, we discussed several ways to implement zoomable images in the UI, as the methods considered during the previous days did not fully work. We will keep searching and trying out new implementations until we find one that meets all our expectations. What is more, we have planned a pair programming session for next Sunday morning, where we will focus on resolving every loose end regarding the zoomable image aspect of the UI.

In the backend meeting we discussed the key aspects of the model, and the importance of doing TDD to implement the classes. We had the idea to make an interface "Filter" in the second sprint to filter images by some criteria (by place, by difficulty...) and a class "SecureImage" with only the URL and the ID as attributes and pass them to the Frontend, so that the user cannot have access to the real coordinates at any time. We finally implemented the class "SearchFromFileSystem", which takes a JSON file as an argument with the ID of the pictures, their URL and their real coordinates.

### Day 6:
Today, at first we updated the trello board adding almost all the pending tasks to the first sprint, becouse we realized that the sprint is two weeks long instead of one as we thought.
![Captura](https://github.com/JavierStark/UMAguessr/assets/162295178/b0bae8d0-6cde-4f48-8922-e13490fda641)
![Captura1](https://github.com/JavierStark/UMAguessr/assets/162295178/4eb4316a-d70a-45f4-9226-c95432dc378a)
Then, each team continued with their tasks, where:
The frontend team has integrated the image zoom so that it can be implemented with both the map and the main image, then, this feature has been used with two new panels that are, the main one where the main image will appear, taking up the entire screen, and the other one being where the map should be visible as soon as you approach the mouse, but not visible otherwise. At last, we implemented some limits in the map movement so that you can't get out of the borders of the image moving or zooming it, and limited the zoom that can be done, so that the image doesn't get too small.

The backend team has integrated some basic methods and functions focused on image handling, which required to modify and refactor some of the image-related classes. Also, some tests have been implemented in order to check the correct implementation of the new methods.

### Day 7:
![22May](https://github.com/JavierStark/UMAguessr/assets/162295178/bd89d1c5-e256-4049-aff3-74be1cd39f79)
On the one hand, the frontend team has added some new features to the ZoomableImage class. The zoom panning has been improved, we have defined some strict bounds for the images, and fixed a bug where the top image dissapeared. Then, a new method has been added, which is capable of obtaining the real coordinates of the image on click, regardless of the active zoom.

On the other hand, the backend team has focused on the ScoreService class, which handles the scoring system of the game, although its frontend aspect has not been implemented yet. Also, we have refactored the ImageService class, and added some extra tests to it.

### Day 8:
![23May](https://github.com/JavierStark/UMAguessr/assets/162295178/ae07af87-f5b0-4539-bcf5-bc1581105379)
Some general refactoring has been done to the whole project, mainly improving the readability and minimizing the duplication of the code. Also, the backend team has added new functionalities regarding the image-related classes, while the frontend team has implemented the Marker class, which is used to locate a marker with associated coordinates in the map, which will then be compared to the actual coordinates of the image to calculate the score.

### Day 9:
![24May](https://github.com/JavierStark/UMAguessr/assets/162295178/76e2de2f-7c39-4c29-ab7e-ad1796409272)
Today, we worked during the whole class period, which allowed us to add some major improvements to the code.
First, some issues and problems regarding the repository were fixed, defining three clear and structured branches, and erasing some unused ones. After this, each team focused on their respectives tasks:

The backend team started removing some generic test classes in order to create new separated test cases, one for each main class of the backend package. Also, the JSON file was created, and the available images and some extra test images were added to it with an improved resolution. At last, a new image filter was added, which allows to classificate and discard some images when needed, which was then refactored.

The frontend team began creating some new classes in order to build the menu interface of the game. Also, a scoreCounter component was implemented and joined into the image panel. It is connected to the backend ScoreService and GameService classes, so that it can show the current round and score. Finally, the game logo was designed and then added to the menu:
![UMAGUESSR](https://github.com/JavierStark/UMAguessr/assets/162293615/876d7c06-5f27-4060-9cdd-4e3122cabb64)

### Day 10:
Today the main focused has been refactoring the backend code, where we can highlight the following aspects:
- A final modifier has been added to the Image fields.
- A deprecated URL has been replaced by a new URI in ImageService.
- The image randomizer has been slightly improved in ImageService.
- Some unncecesary conditionals of the ScoreService class have been removed.

### Days 11, 12 & 13:
These days some more refactoring was done. A bug where the images were not loaded from the web when filtered images were asked was fixed; a bufferedImage parameter was added to the HideablePanel and ZoomableImagePanel classes and the Database interface was deleted, as it was not being used. Also, some tests were added to the GameService class, and we started managing the daily session feature. Finally, some extra buttons were added to the menu, improving the UI and giving it access to the GameService class.

### Day 14:
![29May](https://github.com/JavierStark/UMAguessr/assets/162295178/81786af5-a2e0-4f3a-a7cf-bbb90a3e83f4)
We started adding some new images to the database, so the previously added test images were removed, because now the test can be done with the real images. Also, some URLs were fixed in the JSON file.

The frontend team changed the window divider from horizontal into vertical, so that the game interface is more eye-pleasant and does not depend so much on the panel size. Also, the LookAndFeel style was changed to Windows, in order to improve the asthetics of the UI. Some extra minor improvements were done, like changing the labels of the buttons.

The backend team fixed some unusual bugs related to the scoring system, where the score obtained never reached the maximum score. The team also tackled some other bugs in the ImageService class, which showed infinite images.

### Day 15:
![30May](https://github.com/JavierStark/UMAguessr/assets/162295178/a251a0b1-f11e-4909-84fc-29dfd92d0e57)
Today we only modified the backend branch, where at first we implemented new 
whitebox tests adapting them to the data from the new images. Then we replaced 
the JSON data source with PostgreSQL and updated the tests that where 
implementing those features. Finally we integrated the username-based session 
management and the score saving to the database.

### Day 16:
![31May](https://github.com/JavierStark/UMAguessr/assets/162295178/b3140af6-2e12-4444-ac8b-5a2780ab80d5)
Today, we worked during the class period, where we fixed the size of the score 
pannel and button from the UI class, added new images taken from the Sciences 
faculty, merged the backend branch into frontend branch, implemented new methods
in frontend to send the score to the backend classes, and refactored the 
methods that made the button from the UI class to change the current image when 
pressed.
