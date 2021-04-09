<div>
  <br>
  <p>
    <b>City Current Weather Viewer Demo</b>
  </p>

  <p>
    <sub>Built by
      <a href="https://github.com/Ranger-118">Henry Hu</a>
    </sub>
  </p>

<details>
  <summary><i>Table of contents</i></summary>

---

- [Project Reference](#Project Reference)
  - [Release](#Release)
  - [Changelog](#Changelog)
  - [User Manual](#User Manual)
- [Technical Design](#Technical Design)
  - [Sequence Diagram](#Sequence Diagram)
  - [Class Diagram](#Class Diagram)



---
## Project Reference


### **Release**

1. Before all operations, you have to make sure your environment installed with maven and JDK
2. [Clone this repo](https://gitee.com/hken_17/weather-demo.git) with git.
3. Run `./start.sh` for compilation and application startup



---

### **Changelog**

Check branch and run `git log` for details.



---

### **User Manual**

1. Check and run the steps in [Release](#Release) first
2. After the service is built, open the browser and go to http://localhost:8080/page.html
3. To check one of the city current weather, you may select any one of the city in the drop down list
4. If you wish to add another city to the list, type the city name (make sure it's correct) in the field and click <kbd>Add</kbd> button
5. If you wish to remove the city from the list, you can click <kbd>Remove</kbd> button



---
## Technical Design


### **Sequence Diagram**

``` sequence
title: City Current Weather Viewer Demo
participant HTML Page
participant Controller
participant Service
participant Repository
participant Weather Public API

HTML Page->Controller: Get All Cities
Controller->Service: Get All Cities
Service->Repository: Get All Cities
Repository-->Service: Return city list
Service-->Controller: Return city list
Controller-->HTML Page: Return city list
HTML Page->Controller: Get weather for selected city
Controller->Service: Get weather for selected city
Service->>Weather Public API: Call Public API
Weather Public API-->>Service: Respond the weather info
Service-->Controller: Respond the weather info
Controller-->HTML Page: Respond the weather info
HTML Page->Controller: Add one city into the list
Controller->Service: Add one city into the list
Service->Repository: Add one city into the list
Repository-->Service: Added city
Service-->Controller: Respond with added city weather
Controller-->HTML Page: Respond with added city weather
HTML Page->Controller: Remove one city from the list
Controller->Service: Remove one city from the list
Service->Repository: Remove one city from the list
Controller-->HTML Page: Return true to HTML Page
```



---
### **Class Diagram**

![class-diagram](/class-diagram.png)