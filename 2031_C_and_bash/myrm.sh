#NAME: RISHAB DHAMIJA
#STUDENT NO: 213223334
#EECS 2031 SECTION M
#ASSIGNMENT 1 QUESTION 1
#!/bin/bash


#this is the location of the removed files from the directory
R1=/tmp/rd110018/eecs2031m/a1/recycle-bin



#this function checks whether the desired file exists in the directory or not 
file_delete()
{	
	if [ -f $p ]; then
       		 echo "deleting $p"
		 mv $p "$R1" 
        else
        	 echo "file does not exist "
         fi	
      
}

#this function prints message when parameters have not been provided by the user

no_parameter_check()
{
	echo "Error: no target specified"
	echo "Usage:./myrm<files>"
}




#implementation of script begins here
 mkdir -p $R1
#this test checks whether user entered any parameters or not
if [ -z "$1" ]; then
	no_parameter_check
else
#take a file name from list and delete it
	for p in $@; do 		
	    file_delete
	done
fi  

