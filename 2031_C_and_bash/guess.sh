#NAME : RISHAB DHAMIJA
#EECS 2031 SECTION M
#STUDENT NO: 213223334
#ASSIGNMENT#1 QUESTION#2
#!/bin/bash


#initially, both variables are initializes to NULL
random=
number=                    
found=0
# a function to print welcome message for user as well as instructions for entering  number within range
welcome_message()
{
echo "Welcome to the number game."
echo "Guess a number between 1 and 64(inclusive)."
}


#this function will read the number entered by the user
prompt_user()
{
read number;
}


#this fucmtion generates the random number between 1 and 64
random_generator()
{
random=`expr $RANDOM % 64`      
let random=random+1    #this addition is made to avoid 0 as a number
}



#this function computes output based on user's guesses towards the number
game_generator()
{
counter=0
while [ $counter -lt 6 ]; do  #run this loop 6  times
	prompt_user
	
	if [ $number -gt $random ]   # number guessed is bigger
        then
		echo "Too big."
		let counter=counter+1
       #this additional if is to avoid printing try again in the sixth case
		if [ ! $counter -eq 6 ]; then
			echo "Try again."
		fi
	fi
 	
	if [ $number -lt $random ]; then
		echo "Too small."
		let counter=counter+1
      #this additional if is to avoid printing try again in the sixth case
		if [ ! $counter -eq 6 ]; then
			echo "Try again."
		fi
	fi

	if [ $number -eq $random ]; then
		echo "You won!"
		found=1
		break	
	fi

done

if [ ! $found -eq 1 ]; then  #six turns have passed and user hasn't guessed it
	echo "You Lost!"
fi 	
}  




clear

#the sequence of function invocations start here
welcome_message
random_generator
game_generator
