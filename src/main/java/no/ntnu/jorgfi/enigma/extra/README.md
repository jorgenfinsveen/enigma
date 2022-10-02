# Extra Directory

This directory contains files which are not a part of the actual program, but rather an extension. Upon running this application, the files in this directory will not be used.

### ServerUI.java

The ServerUI was created since the group misunderstood the assignment and though that we were to make a simple interface for a server which again would have access to services in the model-directory, and that we assumed that the client could just be a person writing inputs through the terminal directly to the server.

All though this program is not what is expected by the assignment, we are including it as it can be usefull for testing the Analyzer through a simplified server-interface, as the ServerUI does not use datapackets, but rather just sends data as parameters to class-methods in the programs in the model-directory, as well as it offers different services than the ServerUDP.
