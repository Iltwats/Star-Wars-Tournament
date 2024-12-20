# Star Wars Blaster Tournament
A long time ago, in a galaxy far, far away…
All the mighty heroes take a break from the long war and are enjoying an off-season. Jabba the Hutt, one of the galaxy's most powerful gangsters had in the meantime organized a Blasters duel for the galaxy's greatest.

The rules of the tournament were simple:
  - Each of the players has a match with each other in a round-robin fashion.
  - The players need to shoot on a target with their blasters and are awarded a score out of 100 based on that.
  - At the end of the match, the scores of both the players are compared to identify the winner
  - The player with a higher score (winner) is awarded 3 points
  - The player with a lower score (loser) is awarded 0 points
  - In case of a draw/tie (scores level), both the players are awarded 1 point each

The tournament was a huge hit and Darth Vader emerged as the ultimate winner. He will be awarded the trophy on Star Wars Day (4th of May), but there are a lot of rumors going around that Vader has used Dark Forces on Jabba the Hutt and has declared himself the winner.

Now,  The Republic has chosen you to curate all the results of the tournament into an app that can be used by all and put an end to the dark forces rumor. You have got only 120 minutes to create this app.

The app will consist of the following screens:

1. Points Table
  - This screen will show the points table calculated by the results of all the matches played.
  - The points table will be sorted in descending order by points scored by a player
  - In case of a tie (same points scored by multiple players), sort the players in descending order of the total score of all the matches played by the player

2. Matches Screen
  - Users can click on a player from the Points Screen and land on a detailed screen, where the details of all the matches played by the player will be present
  - This screen should show the most recent match played at the top and the oldest match at the bottom
  - This screen shows the actual score for both the players for the matches where the selected player was participating (the screenshot attached shows all the matches played by Princess Leia)
  - Use proper colors to identify whether the match was won/lost/drawn by the player. Colors to be used:
      - Win - Green
      - Loss - Red
      - Draw - White

May the force be with you
| **Points Table**               | **Matches Screen**                                                                                          |
|-------------------------------|--------------------------------------------------------------------------------------------------------
|![Star Wars Blaster Tournament](https://github.com/user-attachments/assets/8f6ebaeb-ef90-431e-865c-6df5b95c012c)   | ![Matches](https://github.com/user-attachments/assets/cf84e298-dcc1-4dd9-9fc6-4bf746508bbf)          |


**Expectation**: 
 - The UI should be as shown in the illustrations. A lot of polishing of the UI is NOT needed.
 - The code should be complete and correct.
 - Code should be clean, readable, modular, extensible
 - The proper naming convention of the language used should be followed

Extensions (attempt only if time permits):
 - Build a UI that can sort the points table by both ascending and descending order.

All the data needed to create the app has to be fetched from the below provided APIs through a network call. 

 - [Player List](https://jsonkeeper.com/b/IKQQ)
 - [Match List](https://jsonkeeper.com/b/JNYL)

