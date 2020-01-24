/*
Name : Rishab Dhamija
Student no: 213223334
EECS 2031 Section M
Assignment : 2
Question: 2

*/
#include <stdio.h>
#include <stdlib.h>

//function declaration
char encrypt_input(char in, int shift);


int main()
{
    // 2 arrays , one to hold user input as well as other to hold user output
    char input[80],output[80],c;
    //counter behaves as an index for array elements
    int counter = 0,shift = 0;
    //taking user input in the input array
    printf("Enter message to be encrypted:");
    while((c=getchar())!='\n')
        {
            input[counter] = c;
            counter++;
        }
       input[counter] = '\n'; // acts as a sentinel to mark the end of the array
        counter = 0;
   //this shift condition will only work if user enters the correct shift amount within range
     printf("Enter shift amount (1-25):");
     scanf("%d", &shift);


     if( shift<1 || shift>25 )
        {
        printf("Wrong shift Value\n");
        }
      else
      {
          //encrypting one element at a time from input array and saving it to output array
        while(input[counter] != '\n')
            {
                c = input[counter];
                output[counter] = encrypt_input(c, shift);
                counter++;
            }
        output[counter] = '\n';
        counter = 0;
        printf("Encrypted message: ");
       //printing the encrypted message on the screen
        while(output[counter] != '\n')
        {
            printf("%c",output[counter]);
            counter++;
        }
            printf("\n");
      }
        return 0;
    }


char encrypt_input(char in, int shift){
     //difference : it calculates the space present between the input array element as well as the case-bound alphabet a
     //mutual difference: it adds shift amount to the difference and mods it by 26 so as to get the final amount
     //                   by which we shift the alphabet from a
     int difference,mutual_difference;
     if(isalpha(in))
        {
         if(isupper(in))
            {
                difference = in - 'A';
                mutual_difference = (difference + shift) % 26;
                // returning this will provide sum of 65(ASCII of 'A' and the mutual_difference)
                return mutual_difference + 'A';
            }
          else
          {
               difference = in - 'a';
                mutual_difference = (difference + shift) % 26;
                // returning this will provide sum of 97(ASCII of 'a' and the mutual_difference)
                return mutual_difference + 'a';
          }
        }
      else
        //we return this if the input element is not an alphabet, which haa to remain idle
          return in;
}


