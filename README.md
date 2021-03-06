# Trackmania COTD Rest-API

**IMPORTANT** I am currently extending the api. This will affect the endpoints of the api. So if you plan using it, be aware that it might change soon! :)

DISCLAIMER! This is NOT an official API! Its not related to Nadeo or Trackmania.

Based on the leaderboard by <a href="https://docs.google.com/spreadsheets/d/e/2PACX-1vSVwwjM2OoIEWwoiKy1CqMY9oKJ2EXqWvch_gPIrOzL8WtsSoYZ-KjsiZpR3Ygt3U08VW9fxFpRyv6R/pubhtml#"> pointerzio</a>.

Please contact me, if you plan to use this API in your own projects!

* Twitter: <a href="https://twitter.com/SoWieMarkus">SoWieMarkus</a>
* Discord: Markus#2348

## Documentation

**IMPORTANT** The `zone` attribute is NOT a Json-Object ^^' You ask why? ... Well I was to lazy to create a new repository to store the zones. So if you want to use the zone object make sure to deserialize it! ^^

**IMPORTANT** If a position is equal to 0 the player did not finish during this Cup. So he gets no points for that. Because I thought I set this value to -1, the best result attribute is set to -1, if the player never finished a cup during that time. I know thats very dirty and if I have the time I will change this.

### Get the result of a cup of the day

GET `/cotd/<year>/<month>/<day>`

* Example: <a href="http://api.sowiemarkus.com/cotd/2021/4/26">COTD from 26th of april 2021.</a>

```javascript
{
  id: 344,
  name: "Cup of the Day 2021-04-26",
  year: 2021,
  month: 4,
  day: 26,
  startTime: 1619457390,
  endTime: 1619464590,
  leaderBoardId: 1179,
  players: 2532,
  playerResult: [
    {
      id: "cotd_player_result_2021_4_26_0",
      accountId: "fb678553-f730-442a-a035-dfc50f4a5b7b",
      displayName: "mime..",
      zone: "{"name":"Dolnośląskie","flag":"dolnoslaskie","parent":{"name":"Poland","flag":"POL","parent":{"name":"Europe","flag":"europe","parent":{"name":"World","flag":"WOR"}}}}",
      position: 1
    },
    {
      id: "cotd_player_result_2021_4_26_1",
      accountId: "0691c70d-c69d-46a9-a209-6600d708b4bd",
      displayName: "Wavy.Aynyx",
      zone: "{"name":"Maine-et-Loire","flag":"Maine-et-Loire","parent":{"name":"Pays-de-la-Loire","flag":"Pays-de-la-Loire","parent":{"name":"France","flag":"FRA","parent":{"name":"Europe","flag":"europe","parent":{"name":"World","flag":"WOR"}}}}}",
      position: 2
    },
    ...
  ]
}
```

### Get monthly leaderboard

GET `/cotd/<year>/<month>`

* Example: <a href="http://api.sowiemarkus.com/cotd/2021/4">COTD leaderboard of april 2021.</a>

```javascript
{
  id: "cotd_leaderboard_2021_4",
  year: 2021,
  month: 4,
  leaderBoardPlayers: [
    {
      id: "2021_4_fb678553-f730-442a-a035-dfc50f4a5b7b",
      amountFirst: 1,
      amountSecond: 0,
      amountThird: 0,
      displayName: "mime..",
      accountId: "fb678553-f730-442a-a035-dfc50f4a5b7b",
      zone: "{"name":"Dolnośląskie","flag":"dolnoslaskie","parent":{"name":"Poland","flag":"POL","parent":{"name":"Europe","flag":"europe","parent":{"name":"World","flag":"WOR"}}}}",
      bestResult: 1,
      averagePosition: 1,
      position: 1,
      points: 1000
    },
    ...
  ]
}
```


### Get the global leaderboard

GET `/cotd/global`

* Example: <a href="http://api.sowiemarkus.com/cotd/global">Global COTD leaderboard.</a>

```javascript
{
  id: "global",
  year: 0,
  month: 0,
  leaderBoardPlayers: [
    {
      id: "2021_4_fb678553-f730-442a-a035-dfc50f4a5b7b",
      amountFirst: 1,
      amountSecond: 0,
      amountThird: 0,
      displayName: "aTTax.GranaDy",
      accountId: "fb678553-f730-442a-a035-dfc50f4a5b7b",
      zone: "{"name":"Dresden","flag":"dresden","parent":{"name":"Saxony","flag":"saxony","parent":{"name":"Germany","flag":"GER","parent":{"name":"Europe","flag":"europe","parent":{"name":"World","flag":"WOR"}}}}",
      bestResult: 4, // in a row Kappa
      averagePosition: 1,
      position: 1,
      points: 1000
    },
    ...
  ]
}
```

### Get the overview of all possible months

GET `/cotd/overview`

* Example: <a href="http://api.sowiemarkus.com/cotd/overview">Overview of all possible months</a>

```javascript
{
  overView: [
    {
      year: 2020,
      month: 11
    },
    {
      year: 2020,
      month: 12
    },
    {
      year: 2021,
      month: 1
    },
    {
      year: 2021,
      month: 2
    },
    {
      year: 2021,
      month: 3
    },
    {
      year: 2021,
      month: 4
    }
  ]
}
```

### Get results of one player 

GET `/cotd/summary/<year>/<month>/<accountId>` 

Examples: 
* `/cotd/summary/2021/1/05477e79-25fd-48c2-84c7-e1621aa46517` (<a href="http://api.sowiemarkus.com/cotd/summary/2021/1/05477e79-25fd-48c2-84c7-e1621aa46517">click here</a>) - Returns the stats of a player (here it is GranaDy) for january of 2021
* `/cotd/global/05477e79-25fd-48c2-84c7-e1621aa46517` (<a href="http://api.sowiemarkus.com/cotd/global/05477e79-25fd-48c2-84c7-e1621aa46517">click here</a>) - Returns the all time stats of a player (here it is GranaDy)

```javascript
{
  month: 1,
  year: 2021,
  playerResults: [
    {
      position: 24,
      year: 2021,
      month: 1,
      day: 1
    },
    {
      position: 2,
      year: 2021,
      month: 1,
      day: 2
    },
    {
      position: 1,
      year: 2021,
      month: 1,
      day: 3
    },
    ...
  ]
}


```


