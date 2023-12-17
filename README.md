This project targets these objectives:
1. MVVM structure
2. Hilt usage
3. Paging library 3 with offline caching
4. Pagination for Grid View
5. Retrofit GET call using Coroutines

In **master branch**, there are a total of 3 screens:
1. The main movie grid listing screen
2. The settings screen to switch the theme
3. The movie details screen

All data that is fetched remotely and stored locally in preferences and Room Database is being provided via Hilt
The repository solely focuses on data preferences. This repository was created for scalability.

Live Data has not been explicitly used in this project. I have relied on flow to handle the stream of data.
Glide library has been used to display images.
PagingDataAdapter has been used to display the paginated data
View binding has been used in this project.

The details of API have been fetched from https://developer.themoviedb.org/reference/movie-popular-list

App flow:
Once you have installed the app, you may find it in your app drawer by the name `Movie house`
When the user opens the app, the first screen is the listing screen which fetches the list of movies from database that were initially retrieved from an API
There are 2 clickable item types in this screen:
1. Settings button -> Navigate to settings screen
2. Movie tile -> Navigate to movie details screen

App provides support for offline caching.
App relies on system navigation buttons/gestures to navigate back as app currently follow a no action bar theme.

Some area of improvements observed:
1. When the connection comes back online, the app should resume paging. Currently, user must restart the app after connection loss and regain to load new pages.
2. Unit tests could have been written
3. Loading screens could have been added


In **sprint branch**, there are a total of 2 screens:
1. The main movie grid listing screen
2. The movie details screen
The sprint branch handles the data without the use of pagination and only first 20 elements are displayed. This branch is **not** the focus of the project

