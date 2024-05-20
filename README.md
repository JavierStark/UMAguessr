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
