### Psp
* Desc: Stands for personal software process. This program helps keeps track of time spent in the phases of development, and mistakes made in coding
* Status: Currently this records data in a csv file; automated analysis of the file is on the todo list 
* Tags: Haskell, GTK

### PortletReviewer
* Desc: A static analysis tool for portlet developement. Checks for all common mistakes and suggests remediation
* Status: Works and is useful
* Tags: Haskell, Java Portlets
* more at the wiki page [https://github.com/mderosa/hdbc/wiki/PortletReviewer]

### DbDiff
* Version: 0.7
* Desc: Compares two separate oracle database schemas and prints the differences to standard out. The program does an existance comparison on all user objects, and then does a detail comparison on the common tables, views, stored procedures and functions
* Status: Works - I may want to make it less verbose
* more at the wiki page [https://github.com/mderosa/hdbc/wiki/DbDiff]

### DbMigration
* Version: 0.9
* Desc: A tool to automatically setup and automate, as much as possible, the maintainence of a versioning scheme for Oracle databases. This is for organizations where the dba's cant be expected to have ant or other java tools on their computers. It just requires sqlplus
* Status: Production ready - the next steps are static analysis of written scripts to flag bad stuff 
* Tags: Java, Oracle
* more at the wiki page [https://github.com/mderosa/hdbc/wiki/DbMigration]

### Hippo
* Desc: A RESTful web application which provides a central location to create and to analyze web experiments. Right now I'm working on the server part of the application but eventually there will be a client jar as well which will make it easy to integrate the Hippo server into existing third party applications.
* Status: just getting started 

### Sumarian
* Desc: Creates a summary of public methods in project organized by package and then class. Each method is followed by a summary complexity measure in the form "{;;{};;}" which is a representation of the block structure, size, and complexity of the method.
* Status: Usable. I currently run it from the Clojure repl. 
* Tags: Clojure
