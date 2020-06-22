# Android Hospital List
Demo code highlighting use of Room, ViewModel, Coroutines etc. Download the source and run using Android Studio. 

The entry screen is LoaderActivity which has a corresponding LoaderViewModel. 

The view model has an instance variable called dataIsLoaded of type MutableLiveData<Boolean> which is initially set to false.

The view model when initialized checks the SharedPrefences to see if a timestamp of value long exists that is not equeal to 0L. 

If it does exist then we know there is data and dataIsLoaded is set to true. The LoaderActivity is observing this value and when it changes to true a button is displayed asking the user to click to see the list of hopitals. 

If it does not exist then the LoaderActivity displays text saying data is being downloaded and will take a few moments. The view model calls the APIService to get the data from the internet, parses the returned data and creates entities in the Room database. When the parsing is finished dataIsLoaded is set to true and a timestamp (seconds since 1970 is stored in SharedPreferences). 

The thinking behind using a timestamp as a long value rather than a simple boolean is that we may (or may not) have updated data at some future point. If so then we could add a timer that tries to update the data every hour or once a day.

Once the data has loaded and the user has clicked to go to the hopsital list they see a very simple list with all the hospitals ordered by name. There's two buttons above to act as filters - one is show all and the other is show Cornwall hospitals. The HospitalListActivity has a very simple accompanying view model HospitalViewModel which has the connection to the hospitals repo and holds the allHospitals LiveData and a function to get the filtered list.  


There are some tests around the Room database. To run the tests right click the package and select "Run Tests". A production ready codebase would have more tests but these show the intent.
