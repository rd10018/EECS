#!/bin/bash
#NAME: RISHAB DHAMIJA
#STUDENT NO: 213223334
#EECS 2031 SECTION M
#ASSIGNMENT 1 QUESTION 3

#the result will hold the value of the computation

result=0

#this function will print instructions in case user enters the wrong operator
wrong_operator()
{
echo "Usage-./calculator.sh value1 operator value2"
echo "where,"
echo "value1:numeric value"
echo "value2:numeric value"
echo "operator on of +,-,/,x"
}

#case-construct so as to computer based on user- input

case $2 in 
 +) let result=`expr $1 + $3`
    echo $result;;
 -) let result=`expr $1 - $3`
    echo $result;;
 x) let result=`expr $1 \* $3`  #using escape sequence since * is a wildcard
    echo $result;;
 /) if [ $3 -eq 0 ]  #computing the division by zero error
    then
    	 echo "Division-by-zero-Error!"
    else 
    let result=`expr $1 / $3`
    echo $result
    fi;;
 *) wrong_operator;;   #case when user enters operator not in calculator
esac


