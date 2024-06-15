# Requirements (implemented)

- Main menu interface with user login fields, difficulty selection and game description
  - Error window when trying to play without being logged
- Settings window accessible from main menu
  - Dark mode toggle
  - Volume slider
- Game UI (fully resizable and responsive)
  - Zoomable and pannable map and image
  - Resizable map for better experience
  - Score display and accumulated score display
  - Current round and max round display
  - Button to confirm a guess
  - Clickable map to set marker for current guess

# Requirements (to be implemented in future iterations)
- Secure user login with password
- Online scoreboard
- Much more images of all faculties
- More difficulty levels
- More game modes
- Port the app to web and mobile
- Fully implement difficulties when we have more images
- Use already implemented filters for difficulty and gamemodes

## App flow

- The user access any difficulty+ mode from the main menu after typing its user name
- The game starts with 0 points and in round 1
- The user has to analyse the image and pick the point in the map where the picture was taken and click "Make Guess" button
- They will be rewarded points based on their precision (from 0 to 100 each round)
- Their round number will be saved between game session
- They will be only able to do a certain number of rounds per day. They cannot play if they have played the max number of round

+We did not activate this feature as there are such a small amount of images
that it did not make sense have them separated into categories.
But the database is fully prepared for that with a column with difficulties and we 
have the filter logic for that in the domain.
