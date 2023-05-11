Jacob Jonas, 18439731
README for DSA Assignment
------------------------------------------------------------------------------------------------------------------

There are 3 way to run the program:

Runnning the program without any command line arguments provides the usage information for starting the program
Usage information shows the user the correct command line arguments to use and their meaning.

They can run the program with the command line arguments:

 -s keyFile strFile pathFile (numPaths) 

 "-s" indicates that the user wants to run the program in silent mode.
 "keyFile" is the file from which a keyboard will be created.
 "strFile" is the file from which strings will be read and paths generated for.
 "pathFile" is the file where the list of paths will be saved. 
 "numPaths" is an optional argument that specifies how many paths between each character pairing will be generated.


They can run the program with the command line argument: -i

This will begin the program in an interactive testing environment.

------------------------------------------------------------------------------------------------------------------

KeyMeUp.java

Contains the main methods for the program

Depends on FileIO.java, Operations.java, Interactive.java and Display.java if run in interactive mode

However, only depends on FileIO.java if run in silent mode

------------------------------------------------------------------------------------------------------------------

UnitTestKeyMeUp.java

Contains methods which test methods used in KeyMeUp.java

------------------------------------------------------------------------------------------------------------------

FileIO.java

Contains all fileio related methods for the program

------------------------------------------------------------------------------------------------------------------

UnitTestFileIO.java

Contains methods which test methods used in FileIO.java

------------------------------------------------------------------------------------------------------------------

Operations.java

Contains the methods necessary for modifying the keyboard used in KeyMeUp

Modifications are only applied if the users choose to do so using the "Save changes" option

------------------------------------------------------------------------------------------------------------------

Interactive.java

Contains methods for the main program that require user interaction

Depends on FileIO.java, Operations.java and Display.java

------------------------------------------------------------------------------------------------------------------

UnitTestInteractive.java

Contains methods for testing the methods used in Interactive,java

------------------------------------------------------------------------------------------------------------------

Display.java

Contains methods used for display information on the objects used in the program

------------------------------------------------------------------------------------------------------------------

DSAGraph.java

DSAGraph class and its related methods

DSAGraphVertex and DSAGraphEdge are implemented as private inner classes

Depends on BinarySearchTree.java and DSALinkedList.java

------------------------------------------------------------------------------------------------------------------

UnitTestDSAGraph.java

Tests the implementation and functionality of the DSAGraph class

------------------------------------------------------------------------------------------------------------------

BinarySearchTree.java

BinarySearchTree class and its related methods

TreeNode is implemented as a private inner class.

Depends on DSALinkedList.java

------------------------------------------------------------------------------------------------------------------

UnitTestBinarySearchTree.java

Tests the implementation and functionality of the BinarySearchTree class

------------------------------------------------------------------------------------------------------------------

DSALinkedList.java

DSALinkedList class and its related methods

DSAListNode and DSALinkedListIterator as implemented as private inner classes

------------------------------------------------------------------------------------------------------------------

UnitTestDSALinkedList.java

Tests the implementation and functionality of the DSALinkedList class

------------------------------------------------------------------------------------------------------------------

UnitTestIterator.java

Test the implementation of the Iterator used for DSALinkedList

------------------------------------------------------------------------------------------------------------------

DSALLStack.java

DSALLStack class and its related methods

Depends on DSALinkedList.java

------------------------------------------------------------------------------------------------------------------

UnitTestDSAStack.java

Test the implementation of DSALLStack

------------------------------------------------------------------------------------------------------------------

DSALLQueue.java

DSALLQueue class and its related methods

Depends on DSALinkedList.java

------------------------------------------------------------------------------------------------------------------

UnitTestDSALLQueue.java

Test the implementation of DSALLQueue

------------------------------------------------------------------------------------------------------------------

strings.txt

Text file used to read in strings for silent mode

------------------------------------------------------------------------------------------------------------------

netflix.al

Representation of the Netflix digital keyboard

------------------------------------------------------------------------------------------------------------------

stan.al

Representation of the Stan digital keyboard

------------------------------------------------------------------------------------------------------------------

iview.al

Representation of the iView digital keyboard

------------------------------------------------------------------------------------------------------------------

switch.al

Representation of the switch digital keyboard

------------------------------------------------------------------------------------------------------------------

unknown.al

Representation of the unknown digital keyboard

------------------------------------------------------------------------------------------------------------------

empty.txt and empty.al

Empty files used for test purposes

------------------------------------------------------------------------------------------------------------------

testinput.txt

Used for test purposes

------------------------------------------------------------------------------------------------------------------

strings.txt

Contains random strings used for scenarios 1 and 2 in the report

------------------------------------------------------------------------------------------------------------------

special.txt

Contains the modified versions of the strings in strings.txt having numbers and symbols now

------------------------------------------------------------------------------------------------------------------

anime.txt 

Contains anime titles used for scenario 3 in the report

------------------------------------------------------------------------------------------------------------------