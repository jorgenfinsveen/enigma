# Database Directory

Contains a .csv file, "sentences.csv" per 30.09.22. This file contains a collection of sentences, along with their type, and the amount of words in the sentence. Theese attributes are divided by a semicolon " ; ", and there will be several sentences/entries separated by a lineshift.

This sentences constitutes the sentence-pool from which the server can pick its tasks from. The corresponding attributes, type and count, are considered to be the correct answer to the analysis of these sentences.

The FileHandler.java program will read the content of the .csv-file, and insert a placeholder for it, a so-called proxy, which will be distributed to the server instead of it getting direct access to the database-content.
