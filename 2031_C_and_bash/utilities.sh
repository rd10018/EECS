#NAME : RISHAB DHAMIJA
#STUDENT NO : 213223334
#EECS 2031 SECTION M
#ASSIGNMENT 1 QUESTION 4

#!/bin/bash


exit_status=0  #used to exit the while loop

#displays the menu
menu_display()
{
echo "----------------------------------------"
echo "	               MAIN-MENU              "
echo "----------------------------------------"
echo "1.Ongoing Processor Activity (Type in ctrl-C to enter another choice)"
echo "2.Users currently logged in"
echo "3.Number of users currently logged in "
echo "4.Users with the bash shell"
echo "5.Exit"
echo "----------------------------------------"
echo -n  "Please enter option[1-5]:" 

}


next_entry_prompt()
{
echo -n  "Press[enter] key to continue..."
}

#computes based on the user's choice
user_choice()
{
while [ ! $exit_status -eq 1 ]; do
	read number

	case $number in

		1) top
	          next_entry_prompt;;
                2) who
		  next_entry_prompt;;
                3) who | wc -l
		  next_entry_prompt;;
                4)grep bash /etc/passwd | grep -o '^[A-Z1-9a-z0]*' | uniq
		  next_entry_prompt;;
		5)exit_status=1;;
		*)echo "wrong selection. Please look at menu again and make your choice"               
		 menu_display;;	
	
		esac
 
          
done
}

#invoking functions

clear
menu_display
user_choice

