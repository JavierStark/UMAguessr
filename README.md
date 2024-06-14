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

The backend team ...(to be done).

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Commit Log

### feat: the size of the map changes with the panel and the zooming of the image is limited
[View Changes](https://github.com/user/repo/commit/feli-ohl)

### @testImage--commit
[View Changes](https://github.com/user/repo/commit/abhimattx)

### feat: add image handling capabilities
[View Changes](https://github.com/user/repo/commit/DVerdeV)

### build: implemented ScoreService and refactores ImageService (and tests)
[View Changes] https://github.com/JavierStark/UMAguessr/commit/b17de47eca85a6e66f540ea444ae242144503985

### feat: realCoordinates on click
[View Changes] https://github.com/JavierStark/UMAguessr/commit/8c9b55c84430d2c458d9ca0ee422f770722367d2

### feat: splitted pane for image and map
[View Changes] https://github.com/JavierStark/UMAguessr/commit/a26c72bd30f4ed1c836ff125230937bcdb9103a4

### Fixed bug with disappearing top image
[View Changes] https://github.com/JavierStark/UMAguessr/commit/ff5022e90e8c0837dbd42ff478c3d48da919e371

### refactor: improved ImageHandler (accepted pull request)
[View Changes](https://github.com/user/repo/commit/e300cfd)

### Merge branch 'develop-backend' into develop-backend-refactor
[View Changes](https://github.com/user/repo/commit/4fbdd2b)

### build: added staying in bound functionality
[View Changes](https://github.com/user/repo/commit/cf44690)

### feat: panning of zoomableimage
[View Changes](https://github.com/user/repo/commit/1e4f327)

### refactored ImageService an added new functionalities
[View Changes](https://github.com/user/repo/commit/db735b5)

### Merge branch 'develop-backend' into develop-backend-remoteimage
[View Changes](https://github.com/user/repo/commit/fbe1c5f)

### refactored ScoreService
[View Changes](https://github.com/user/repo/commit/82d8d06)

### feat: added Marker.java
[View Changes](https://github.com/user/repo/commit/9848d48)

### feat: added map markers
[View Changes](https://github.com/user/repo/commit/9b3e6af)

### feat(image-handling): enhance image handling and add new functionalities
[View Changes](https://github.com/user/repo/commit/ae6bd72)

### refactor: improve readability and maintainability of ScoreService
[View Changes](https://github.com/user/repo/commit/254364c)

### refactor: readability improvements and less duplication
[View Changes](https://github.com/user/repo/commit/0129d40)

### refactored project cleaning and marker.png
[View Changes](https://github.com/user/repo/commit/cf1241a)

### feat: implemented ImageFilter
[View Changes](https://github.com/user/repo/commit/6284982)

### merge: connect frontend with backend
[View Changes](https://github.com/user/repo/commit/1ce88c5)

### Merge branch 'refs/heads/develop-backend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/6715d9c)

### build: updated images.json
[View Changes](https://github.com/user/repo/commit/c2da540)

### Merge remote-tracking branch 'origin/develop-frontend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/1e640d6)

### feat: added image filter
[View Changes](https://github.com/user/repo/commit/0acc320)

### Added new menu classes.
[View Changes](https://github.com/user/repo/commit/c008d54)

### fix: updated images.json
[View Changes](https://github.com/user/repo/commit/19f6c87)

### Merge branch 'refs/heads/develop-backend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/6384e54)

### Fetch remote images.json
[View Changes](https://github.com/user/repo/commit/7faa3dc)

### Merge remote-tracking branch 'refs/remotes/origin/develop-backend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/dcb0a56)

### Create images.json
[View Changes](https://github.com/user/repo/commit/2793f94)

### Replaced deprecated URL constructor
[View Changes](https://github.com/user/repo/commit/ac3d088)

### Remove BackendTest
[View Changes](https://github.com/user/repo/commit/04000bd)

### Merge remote-tracking branch 'origin/develop-backend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/c74a100)

### fix: fixed some issue that occured during merge
[View Changes](https://github.com/user/repo/commit/188efd5)

### fix: fixed small math mistake
[View Changes](https://github.com/user/repo/commit/e00b463)

### Merge branch 'develop-frontend' of https://github.com/JavierStark/UMAguessr into develop-frontend
[View Changes](https://github.com/user/repo/commit/8af4519)

### build: added image files.
[View Changes](https://github.com/user/repo/commit/9e426bd)

### Merge remote-tracking branch 'origin/develop-frontend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/4790673)

### feat: ask backend for score
[View Changes](https://github.com/user/repo/commit/262b227)

### feat: added scoreCounter component
[View Changes](https://github.com/user/repo/commit/76ec728)

### resources: change resolution of placeholders images
[View Changes](https://github.com/user/repo/commit/811df5a)

### WIP on develop-frontend
[View Changes](https://github.com/user/repo/commit/27e0968)

### Merge branch 'refs/heads/develop-frontend' into develop-backend
[View Changes](https://github.com/user/repo/commit/b25c88c)

### refactor: remove unnecesary conditionals in ScoreService
[View Changes](https://github.com/user/repo/commit/a99c3b9)

### refactor: naming in ImageService (image to imageData) for understandability
[View Changes](https://github.com/user/repo/commit/0b130cf)

### refactor: caching of Random in ImageService
[View Changes](https://github.com/user/repo/commit/b3669e0)

### refactor: deprecated URL changed to URI in ImageService
[View Changes](https://github.com/user/repo/commit/c548f1b)

### refactor: final modifier added to Image fields
[View Changes](https://github.com/user/repo/commit/d490943)

### Merge branch 'refs/heads/develop-backend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/6e259b5)

### refactor: bufferedimage parameter to HideablePanel and ZoomableImagePanel; y offset of marker
[View Changes](https://github.com/user/repo/commit/18edb47)

### fix: images not loaded from web when asking for filtered images
[View Changes](https://github.com/user/repo/commit/7ebc59c)

### build: buttons open GameService and new UI
[View Changes](https://github.com/user/repo/commit/b3daab4)

### Merge branch 'develop-backend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/5f04b07)

### feat: GameService tests and daily session management
[View Changes](https://github.com/user/repo/commit/6cbf304)

### Refactor: deleted Database interface
[View Changes](https://github.com/user/repo/commit/f31eabe)

### Merge branch 'refs/heads/develop-backend'
[View Changes](https://github.com/user/repo/commit/29ad2f7)

### fix: .gitignore
[View Changes](https://github.com/user/repo/commit/7b2ebc1)

### refactor: tests naming
[View Changes](https://github.com/user/repo/commit/102e42f)

### fix: infinite images from imageService
[View Changes](https://github.com/user/repo/commit/9ced9ce)

### build: scorePanel changes when the game is played
[View Changes](https://github.com/user/repo/commit/e9ba1d1)

### Merge remote-tracking branch 'origin/develop-frontend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/5396db5)

### style: change lookandfeel to windows
[View Changes](https://github.com/user/repo/commit/deeb309)

### Auto stash before merge of "develop-frontend" and "origin/develop-frontend"
[View Changes](https://github.com/user/repo/commit/89ec4a1)

### FIX!: fixed the Pythagorean Theorem
[View Changes](https://github.com/user/repo/commit/e99231f)

### fix: set window divider to vertical
[View Changes](https://github.com/user/repo/commit/c76400f)

### fix: fixed urls in images.json
[View Changes](https://github.com/user/repo/commit/ada3bac)

### fix: fixed typo in images.json
[View Changes](https://github.com/user/repo/commit/c8c6331)

### build: updated images.json with actual images of the ETSII
[View Changes](https://github.com/user/repo/commit/e53d01a)

### feat: added image folder with images
[View Changes](https://github.com/user/repo/commit/a07165e)

### fix: fixed bug related with the score
The button was doing all of its calculations with the first ID that was passed to it. And it did not change when the image changed. Now it's fixed ;)
[View Changes](https://github.com/user/repo/commit/78eb945)

### fix: solved other problems with merge
[View Changes](https://github.com/user/repo/commit/151bd1d)

### Merge branch 'develop-backend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/5e86f82)

### feat: made some changes.
[View Changes](https://github.com/user/repo/commit/7b1d81f)

### fix: changed button label
[View Changes](https://github.com/user/repo/commit/e37fbf0)

### Merge remote-tracking branch 'origin/develop-backend' into develop-backend
[View Changes](https://github.com/user/repo/commit/29efbb9)

### feat: integrate username-based session management and score saving to DB
[View Changes](https://github.com/user/repo/commit/df04e5b)

### feat: replace JSON data source with PostgreSQL and update tests
[View Changes](https://github.com/user/repo/commit/46704d6)

### test: whitebox tests in backend adapted to new data image
[View Changes](https://github.com/user/repo/commit/33d5955)

### refactor: change current image in ui
[View Changes](https://github.com/user/repo/commit/f16ac35)

### feat: frontend send score to backend
[View Changes](https://github.com/user/repo/commit/544509f)

### Merge branch 'refs/heads/develop-backend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/e0d5765)

### feat: added new images of the Sciences faculty
[View Changes](https://github.com/user/repo/commit/128ca4b)

### fix: fixed size of the score pannel and button
[View Changes](https://github.com/user/repo/commit/533d0d4)

### feat: improved the menu and made some changes.
[View Changes](https://github.com/user/repo/commit/6663c4b)

### merge: solve conflicts
[View Changes](https://github.com/user/repo/commit/5e89b18)

### Merge remote-tracking branch 'origin/develop-frontend' into develop-frontend
[View Changes](https://github.com/user/repo/commit/5ce4127)

### refactor: generalization of game start in menu
[View Changes](https://github.com/user/repo/commit/e899311)

### feat: added user and password text fields.
[View Changes](https://github.com/user/repo/commit/78cf61f)

### feat: player blocked when played 6 times in a day
[View Changes](https://github.com/user/repo/commit/9a51f27)

### feat: added a new button detecter.
This one is supposed to detect the ESC button when pressed and return to the menu frame.
[View Changes](https://github.com/user/repo/commit/e817a47)

### Merge branch 'refs/heads/develop-frontend'
[View Changes](https://github.com/user/repo/commit/c3a7688)

### Merge branch 'refs/heads/develop-backend'
[View Changes](https://github.com/user/repo/commit/eb3a1fa)

### test: all tests fixed
[View Changes](https://github.com/user/repo/commit/f615554)

### test: fix some tests
[View Changes](https://github.com/user/repo/commit/dff235b)

### Merge branch 'Refactor'
[View Changes](https://github.com/user/repo/commit/6dc83b6)

### Refactor: Finished frontend refactor
[View Changes](https://github.com/user/repo/commit/e8a29ab)

### Refactor: finished StartingMenu refactoring
[View Changes](https://github.com/user/repo/commit/df3983b)

### Refactor: More refactoring to StartingMenu
[View Changes](https://github.com/user/repo/commit/b76458b)

### Refactor: More StartingMenu refactoring
[View Changes](https://github.com/user/repo/commit/f256482)

### Refactor: Some more refactoring changes to StartingMenu
[View Changes](https://github.com/user/repo/commit/25ada28)

### Refactor: More StartingMenu refactoring
[View Changes](https://github.com/user/repo/commit/43a45bb)

### Refactor: Some refactoring done to StartingMenu
[View Changes](https://github.com/user/repo/commit/f4669a7)

### Refactor: Refactored Marker ScorePanel and UI
[View Changes](https://github.com/user/repo/commit/b48552b)

### Refactor: refactored HideablePanel and MainLoop
[View Changes](https://github.com/user/repo/commit/eba5db8)

### JavaDoc Commit
[View Changes](https://github.com/user/repo/commit/64568f3)

