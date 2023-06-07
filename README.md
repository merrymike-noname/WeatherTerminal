# WeatherTerminal
Console application for getting weather data

This application takes weather data from https://openweathermap.org and presents it to user in different ways, which user can choose using commands

The architecture is built using 3 patterns:
- MVC (WeatherCoomection + Controller + Console + Application)
- Singleton (WeatherConnection, Controller), 
- Command (All classes from package "command", interface Command)

The application runs in console and allows user to execute commands:
|	-cw -- get current weather
|	-tw -- weather till the end of this day
|	-cdw -- choose a day to get its weather at 12 p.m. (not farther than 5 days from current date)
|	-fdf -- get five day forecast (for 12 p.m. each day)
|	-help -- get list of commands
|	-city -- change city
|	-ex -- exit

