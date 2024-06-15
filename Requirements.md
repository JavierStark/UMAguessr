# Requirements

- Main menu interface with user login fields, difficulty selection and game description
  - Error window when trying to play without being logged
  - Accumulated score display
- Settings window accessible from main menu
  - Dark mode toggle
  - Volume slider
- Game UI (fully resizable and responsive)
  - Zoomable and panable map and image
  - Resizable map for better experience
  - Score display and accumulated score display
  - Current round and max round display
  - Button to confirm a guess
  - Clickable map to set marker for current guess

## App flow

- The user access any difficulty mode from the main menu after typing its user name
- The game starts with 0 points and in round 1
- The user has to analyse the image and pick the point in the map where the picture was taken and click "Make Guess" button
- They will be rewarded points based on their precision (from 0 to 100 each round)
- Their round number will be saved between game session
- They will be only able to do a certain number of rounds per day. They cannot play if they have played the max number of round
