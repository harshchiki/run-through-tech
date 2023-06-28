# Storage
1. Session Storage
2. Local Storage
3. IndexedDB
4. WebSQL
5. Cookies
6. Private State Tokens
7. Interest Groups
8. Shared Storage
9. Cache Storage
for e.g
![](_resources/2023-06-28-11-06-18.png)

## Important links
https://developer.mozilla.org/en-US/docs/Web/API/Web_Storage_API

## Session Storage
* maintains a separate storage area for each given origin thats available for the duration of the page session
  * as long as the browser is open
  * including page reloads and restores
* Storage limit > cookie (atmost 5 MB)
### INteraction with sessionStorage from the Console
Set, Get
```
sessionStorage.setItem("key", "value"); // Save data to sessionStorage
let data = sessionStorage.getItem("key"); // Get saved data from sessionStorage
sessionStorage.removeItem("key"); // Remove saved data from sessionStorage
sessionStorage.clear(); // Remove all saved data from sessionStorage
```

## Local Storage
* same as sessionStorage, but persists even when the browser is closed and reopened.
* no expiration date, gets cleared only through JavaScript, or clearing the Browser Cache/Locally stored data
* Can store more than the session storage.
### Interact with localStorage from the Console
```
localStorage.setItem("myCat", "Tom");
const cat = localStorage.getItem("myCat");
localStorage.removeItem("myCat");
localStorage.clear(); // remove all localStorage items for this origin
```
