# Tools Directory

This directory contains programs which are not a part of the application in a technical speaking, but acts as tools which can be utilized for gathering or processing data from the database.


### FileHandler.java

Reads a .csv-file from the database directory and converts each line to an instance of the Proxy-class, which will be collected into an ArrayList of Proxy-objects, which will be the returned data after executing this program.


### Proxy.java

A class which acts as a place-holder for an entry/line in the .csv-file in the database directory, where the three components of a file-entry, sentence, type, and counter, are stored in separate object-fields which can be accessed through accessor methods.
