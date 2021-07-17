# MeteoriteApp_assignment

## [Download APK](https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/apk/MeteoriteApp.apk)
## Assignment details
● The data points are available via the NASA API:   
● view: https://data.nasa.gov/view/ak9y-cwf9  
● documentation: https://dev.socrata.com/foundry/data.nasa.gov/y77d-th95  
● json: https://data.nasa.gov/resource/y77d-th95.json  
● administration: https://data.nasa.gov/login  
The number of requests is limited unless you sign up for X-App-Token (which you can do for
free).  
## Acceptance criteria:
● We would like the meteors to be filtered from the year 1900.  
● If a user selects a meteor, a map should be presented with a pin that indicates the  
location of the fallen meteor. You can include any other details that might be interesting
(size, date, etc.).  
● Users should be able to manage favourite meteors: add a new meteor to their favourites  
and remove meteor from the list. It’s up to you how you want to manage this: using
persistent local storage or using some (out of the box) backend solution.  
● Users should be able to change the sorting of the meteor list to size, location, etc. (any
other sorting that you feel is relevant).  
● The data should be fetched on app launch and the user should have a way to refresh the
data while using the app.  
● Design and UI of the app is completely up to you, but try to keep it simple and clean.   

## Feature Implemented
- Lazy Loading
- Pull to refresh
- Google Map
- Filter Meteorite by Name, Weight, Date etc
- DIFF Utils to ease loading
- Storing in room database
- Unit test cases
- UI test cases for database Implementation

## Architechure and lib used
- MVVM
- Data Binding
- Coroutines
- Clean Architecture
- Dagger Hilt for Dependency Injection
- Room Database For persistency

## Libraries used
- Coroutines - for multi threading
- Databinding and view model for MVVM
- Junit and mockito for Unit testing
- DIffUtils to make recyclerview adapter load items effeciently when refresh Meteor


## How to Run the application
- Download the APK link given above or download the project and open in Android Studio and Sync and click on run on any Emulator or Device
- Click on the CountryFlagAPp in device dashboard
- It will load the Meteorites
- There are 2 tabs Meteorite and Favourite Meteorites
- Click on Map to show the location of the Meteorite
- Click Add to Favourite or remove 
- Scroll doan the list to load more items
- Click on the filter FAB icon at the bottom right
- It will display filter Meteorite by Name, Weight, Date etc


## Here is the screen shot and Gif image for the application
<table>
<tr>
<td>
  <img src="https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/images/ss1.png" width="250" height="400" />
 </td>
<td>
 <img src="https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/images/ss2.png" width="250" height="400"/> 
</td>
  <td>
  <img src="https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/images/ss3.png" width="250" height="400" />
 </td>
<td>
 <img src="https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/images/ss4.png" width="250" height="400"/> 
</td>
</tr>
</table> 

## GIF image
<img src="https://github.com/sibaprasad12/MeteoriteApp_assignment/blob/main/app/images/MeteoriteApp.gif" width="250" height="500" />
