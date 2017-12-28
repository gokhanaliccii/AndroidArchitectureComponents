# FlavorHunter
FlavorHunter is an android application for show place details which uses foursquare api. 
Application support search places by nearby location and typed keyword, but due to lack of time 
hardcoded that fields on the *VenueListFragment*. 

## Art
Architecture components and databinding libraries used with MVVM architecture during this application development. Mock and live product flavors defined for application manage configurations easily 
(such as api endpoint ..). On the mock flavor application uses mock api which read json files from assets folder and respond.
For data access created repositories and with extra layers for encapsulate functionality. With this approach user won't know how to access data, only they need to provide dataload callback with search criteria all is that.

RuntimePermission created to easily request whatever need permission. Added integration tests for is really working as expectedly.(Implementations are under the components/permission directory)  




## Todos

* ~~Create Runtime Permission Requester~~
* Location Finder
* Google Map Integration
* ~~Foursquare Integration.~~
* App Design
* Fabric Integration
* ~~Tablet Support~~

