# Android
This Repository contains snippets of little features I have worked on while I was learning and building Android apps. It is meant to provide the reader with insights I have gained and information about the possible points where newbies could make errors and end up doing hours of (possibly unproductive) research. 

Also, some of the code in this repository has been borrowed, thanks to other repos and stackoverflow.

##Snippet Descriptions##

**Messages**

Does the simple task of importing a user's text messages into the app. This one specifically imports from the inbox only, but that can be extended to sent, outbox and so forth).

**BROWSING**

Reads the user's Browsing Data into the App.

**Camera**

In android, there are 2 ways you can use the camera:

1. If you simply wish to give the functionality of clicking a picture, you can call the Pre-installed Camera App which can supply you with the picture data once the photo has been clicked.

2. Alternatively, you can integrate a camera into your own app like Facebook, Whatsapp, Snapchat and many others do.

The focus here is on the second one. This one is restricted to landscape orientation, though with a little bit of exploring, you could play around with the camera feature all you like. For clicking the picture, tap on the screen. The camera then shows you the clicked image.

**AutoCompleteTextView**

Demonstrates the simple feature of bringing up suggestions when the user starts typing. A Text Box asks for your favourite phone. As you type its name, suggestions begin to appear in (by default) lexicographic order from a list of phones (this list can either be hardcoded (like in this app) or stored in a local database (SQLLite)).
