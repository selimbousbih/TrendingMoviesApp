# TrendingMoviesApp

MovieTrends is an Android application that shows the latest trending movies and provides movie discovery by genres.


## Features

- **Latest content:** Launch the app to get the daily trending movies.
- **Dynamic genres:** Refresh the home page to get a different list of genres.
- **Movie info:** Open to display the movie overview as well as the cast.

## Screenshots

| **Home** | **Movie Details** |
| :------------: | :------------: | 
| ![HOME](https://user-images.githubusercontent.com/26390229/188983690-451b6260-0cbe-4cad-b498-c4cdea63047d.png) | ![DETAILS](https://user-images.githubusercontent.com/26390229/165500168-a6d999cc-200b-4bb2-b2f0-3a7641ed703d.png) | 


## Architecture and Components

1. The application is made with the mvvm architecture.
2. **UI components**: A separate module for reusable components that are easily updated using kotlin view binding and data binding, example: [MovieComponent.kt](components/src/main/java/com/selim/components/movie/MovieComponent.kt).
3. **Error handling:** The repository outputs a result of type [Either](app/src/main/java/com/selim/trends/functional/Either.kt) wich can have one of two possible outcomes, Failure or Success, so either returning a Failure with a specified error or the correct result of the operation
