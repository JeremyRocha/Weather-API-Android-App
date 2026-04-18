## Overview  
An Android application that allows users to search for cities and view real-time weather data.
The app demonstrates API integration, asynchronous networking, and MVVM architecture, guiding users through a structured data flow from search to detailed weather results.

## Key Features  
- City search with dynamic results using API calls  
- RecyclerView display for selectable city results  
- Detailed weather view for selected locations  
- Real-time data retrieval using HTTP requests  
- JSON parsing and structured data handling  
- Clean architecture using MVVM design pattern

## Tech Stack  
- Language: Java  
- Platform: Android Studio  
- Architecture: MVVM (Model-View-ViewModel)  
- Networking: OkHttp  
- API: Weather API  

## How It Works  
1. User enters a city name in the search bar  
2. ViewModel processes the input and triggers API request  
3. App retrieves matching cities from the Weather API  
4. Results are displayed in a RecyclerView  
5. User selects a city  
6. ViewModel triggers a second API call for detailed weather data  
7. UI updates automatically with the retrieved data  

## Architecture (MVVM)  
- Model: Handles API data and data structures  
- View: UI components (activities, layouts, RecyclerView)  
- ViewModel: Manages business logic, API calls, and UI state  
- This separation improves maintainability, testability, and scalability.

## Application Flow  
- Multi-step API interaction (search → select → fetch details)  
- Asynchronous network handling using OkHttp  
- Dynamic UI updates based on ViewModel state  
- Clear separation between UI and data logic  

## Error Handling 
- Handles empty or invalid search inputs  
- Manages failed API responses and network issues  
- Provides user feedback when data cannot be retrieved  

## Screenshots
**Search Screen**
![Search Screen](https://i.gyazo.com/61bcd1e993f3c72db24c9b4d3afaf89b.png)

**City Results**
![City Results](https://i.gyazo.com/59781bcdd048d94de88c5a78f86c7e61.png)

## Resources Used 
- Weather API  
- Android Developer Documentation  
- OkHttp Documentation  
