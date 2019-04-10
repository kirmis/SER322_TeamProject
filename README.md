# SER322_TeamProject
SER322 Semester Project - Spring 2019

Team: Alper Mencek, Manolito Ramirez, and Ryan Kirmis

The Armory is a database application that represents a video game library, similar to Steam or 
Origin. The application allows users to browse through a library of video games. Users of different 
types (standard users, premium users, review users) will be able to login with a username and 
password. Additionally, the application allows users to search for different games by inputting 
keywords, such as the developer of the game, publisher of the game, the genre of a game, etc. 
Lastly, review users have the ability to review games and post their reviews to the application 
so that other users can view them.


INSTRUCTIONS FOR SETTING UP APPLICATION:

1. Create the database by loading the SQL Scripts: TheArmoryDDL.sql and TheArmoryDML.sql located in /SQL_Scripts/.

2. In MySQL, create the following new user so that the application can access the database:

    CREATE USER 'armoryuser'@'localhost' IDENTIFIED BY 'pass';
    GRANT ALL PRIVILEGES ON TheArmory.* TO 'armoryuser'@'localhost';
    
3. The application can be started through Gradle. When in the home directory of the project, enter 
   "gradle run" to launch the application.
   

FOR SETTING UP IN ECLIPSE:

1. Import project by going to File->New->Java Project. Unselect "Use default location" and choose the directory of the project (this file's parent directory). Click Finish.

2. Ensure that the JDBC driver is part of the build path by right-clicking the project in the Package Explorer and going to Build Path->Configure Build Path. Under the "Libraries" tab, select "Add JARS" and navigate to lib/mysql-connector-java-8.0.15.jar. Select OK then "Apply and close".

3. Ensure src/main/resources directory is part of classpath by right clicking on project in the Package Explorer and going to Run As->Run Configurations. Under the "Classpath" tab, select "User Entries", then the "Advanced" button on the right. Select "Add Folders" and navigate to the src/main/resources directory. Select OK then Apply. The application should be able to run by starting from the MainFrame.java class now.

