# Otrium Interview Usecase 📘:	
Otrium usecase with a requirement to implement a simplistic android app that fetches a GitHub user profile screen based on the specified design provided. The user data is to be fetched using the official GitHub GraphQL API.

# Built With 🛠	
* Kotlin
* Kotlin Coroutines
* Kotlin Flows
* ViewModel
* Room Database.
* Jetpack Navigation Component
* Junit4
* Mockk (mocking dependencies)
* Dagger Hilt
* OkkHttp
* ApolloGraphQl
* Gradle Version Catalog


# Architecture  🏗
This app uses multi-module MVVM (Model View View-Model) with clean architecture :

![otrium mvvm clean architecture drawio](https://github.com/oscarnipps/otrium-usecase/assets/31051567/4d8b8768-2161-4190-a53d-696519567f42)


# Multi Module Structure 💈	
![otrium app architecture drawio](https://github.com/oscarnipps/otrium-usecase/assets/31051567/c369a092-c557-4806-82db-8284551830bd)


# App Implementation Flow 👷‍♂️
Utilizing the single source of truth principle, the single source of truth was made to be the local cache which is observed by the ui (i.e using stateFlow and flows for observing the stream of data). Having a specified cache duration of 24hours , the implementation assumption was:

- If the cache has been exceeded then proceed to get the result from the api (via the grapghql appollo client) and then update the local database
- The UI only observes the changes to the local database and does not bother about the API and just like ✨Magic ✨ the UI always gets the latest update to changes
- Every force refresh (either when the app is viewed for the first time or via the swipe to refresh functionality) by passes the cache , regardless of the cache duration and updates the local source with the result
  
![otrium logic flow drawio](https://github.com/oscarnipps/otrium-usecase/assets/31051567/dc13a4c2-bb49-42a5-b2ee-a4a87e0218e5)


# App Screens UI 🎨

|           |           | 
| ------------- |:-------------: |
| ![otrium 1](https://github.com/oscarnipps/otrium-usecase/assets/31051567/2c459afd-0877-4acf-8a1f-31f067fb9ca4)  |  ![otrium 2](https://github.com/oscarnipps/otrium-usecase/assets/31051567/3508e8e0-17ec-498b-ba60-6794b37cc7bb)  |


# Caveats 👷‍♂️
The following caveats were missed out or not properly handled as a result of certain external factors

- Though gradle version catalog was used to share dependencies across the different modules , a classical option would be to use the kotlin buildSrc which is quite similar but offers more flexibility. Also there was some repetitive dependencies which could have been in it's own separate gradle file and applied where needed
- The UI makes uses of different recyclerviews and adapters which is not ideal but could be refactored to make use of Concat Adapter for the horizontal stacked recycler views and the titles
- Error handling for network state would have been better handled



