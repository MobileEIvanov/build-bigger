# Build it Bigger - Joke Generator app 
## Gradle for Android and Java Final Project

The project initial work is based on the *Udacity* code which can be found [here](https://github.com/udacity/ud867/tree/master/FinalProject)



## Project Architecture

The project has four modules:
- **Core Application Module - "app"** with free and paid features.
- **Java Library Module - "libJokeGenerator"** provides the jokes to the backend
- **Backend Module with Google Cloud SDK - "backend"** suplies the generated jokes
- **Android Libarary Module - "libJokeVisualizer"** - displayes the provided jokes
 
### Prerequisites

**IMPORTANT!!!** Before running the project the local server for Cloud SDK should be started in order to suply the application with content.


## Version Log

v.1.0
- Android library for Joke visualization
- Java Library for Joke generation
- Backend module for providing Java library generated jokes
- Free and Paid versions
- AdMob support for the FREE version with included banner and fullscreen add implementation (Interstetial add)


### Coding style 
The project follows the general coding style for Java and Android.


## Built With

* [ClouldSDK](https://cloud.google.com/sdk/docs/) - Google clould solution
* [AdMob Google Mobile Adds](https://developers.google.com/admob/android/quick-start)
* [Gradle for Android](https://developer.android.com/studio/releases/gradle-plugin) 

## Contributing
Please read [CONTRIBUTING.md] for details on our code of conduct, and the process for submitting pull requests to us.

## Authors
* **[Udacity Repo](https://github.com/udacity/ud867/tree/master/FinalProject)
* **[EIvanov](https://github.com/MobileEIvanov)**

## Acknowledgments
* [Udacity Repo](https://github.com/udacity/ud867/tree/master/FinalProject)
* [Headless Fragment Implementation](http://luboganev.github.io/blog/headless-fragments/) 

## License

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


