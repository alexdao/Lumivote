Lumivote [![Build Status](https://travis-ci.org/alexdao/Lumivote.svg?branch=master)](https://travis-ci.org/alexdao/Lumivote)
==========

Android app to make available the political activities in the United States, including the 2016 election, the Senate, the House, bills, candidates, votes, etc. 

We hope to make the U.S. government and politics more transparent, and therefore, accountable, for all. Our website can be found at http://lumivote.com/

### About

Written by Alex Dao. 

### Setup
To test this app locally, simply clone the repo and import to Android Studio. Make sure that your SDK, platform-tools, and other related Android development libraries are up to date. 

Either an emulator or Android device is required for testing. 

### Code structure
All source files are located in the /src/ directory. Java files are broken into 3 main directories: api, bus, and ui. 

api: Client connections to various API's (Lumivote, Huffington Post, Sunlight, US-images, etc) via Retrofit, GSON, and OKHttp.

bus: Eventbus used for communication with JSON response callbacks, implemented using Otto.

ui: All fragment and activity classes.

The rest of the source files can be located in the /res/ directory, which contains all xml layouts, values, strings, styles, colors, drawables, etc.

### Libraries
App built with the help of these libs:
* [Picasso](http://square.github.io/picasso/)
* [ButterKnife](http://jakewharton.github.io/butterknife/)
* [Retrofit](http://square.github.io/retrofit/)
* [OKHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Otto](http://square.github.io/otto/)
* [BottomSheet](https://github.com/Flipboard/bottomsheet)

Data taken from the following public APIs:
* [United States Images](https://github.com/unitedstates/images)
* [Sunlight Congress API](http://developer.nytimes.com/docs/read/congress_api)
* [GovTrack.us](https://www.govtrack.us)
* [congress-legislators Database](https://github.com/unitedstates/congress-legislators)

Special thanks to:
* [jsonschema2pojo](http://www.jsonschema2pojo.org/)
* Jiawei Zhang for website development
* Soojee Chung for website design

License
--------

    Copyright 2016 Alex Dao.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
