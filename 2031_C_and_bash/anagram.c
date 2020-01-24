/*
Name : Rishab Dhamija
Student no: 213223334
EECS 2031 Section M
Assignment : 2
Question: 3

*/


#include <stdio.h>
#include  <stdlib.h>
#include <ctype.h>

# define ARRAY_SIZE 50

//fucntion for performing check on wrods for anagram
int check_words_for_anagram(char [], char []);


int main()
{
    //word1 and word2 are two arrays that constitute the two words entered by the user
    //the only purpose of these arrays is that the sequences of nulbers in them could be compared later
    //for debugging purposes
   char word1[ARRAY_SIZE], word2[ARRAY_SIZE];
   //status indicates whether the words are anagrams or not
   int status = 0;
   //counter is used as an index for word1 and word2 elements
   int counter = 0;
   // c is used to store user input in form of a character
    char c;

    //getting user input and storing it into word1 array
   printf("Enter first word\n");
   while((c = getchar()) != '\n')
   {
       word1[counter] = c;
       counter++;
   }
   // as a sentinel for word1 terminator
      word1[counter] = '\n';
     //making counter 0 so as to use it again for second word
      counter = 0;

   printf("Enter second string\n");

   //getting user input and storing it into word2 array
   while((c=getchar())!='\n')
   {
       word2[counter] = c;
       counter++;
   }
    //as a sentinel for the word2 terminator
     word2[counter] = '\n';

    //invoking funciton check_words_for_anagram
     status = check_words_for_anagram(word1, word2);

   if (status == 1)
      printf("The words are anagrams.\n");
   else
      printf("The words are not anagrams.\n");

   return 0;
}
//this function checks for whether the words match in permutation level
int check_words_for_anagram(char a[], char b[])
{
    //w1 and w2 arrays will store the numeric sequences for the words, initially all of their values are 0
   int w1[26] = {0};
   int w2[26] = {0}, i = 0;
   //index_upadte is used to make changes in the specific element of the array corresponding to the element in a[]
   int index_update = 0;
   char lower1;

   // Calculating frequency of characters in first string

   while (a[i] != '\n')
   {
       // we will only count the occurrences of alphabetical characters
       //rest have to be ignored y the compiler
      if(isalpha(a[i]))
      {
          //converting upper case to lower case since both of them have to treated as the same by the user
          if(isupper(a[i]))
          {
              lower1 = tolower(a[i]);
          }
          else
          {
              lower1 = a[i];
          }
      //index_update will carry the value of the specific index that has to be updated in the w1 array
      index_update = lower1 - 'a';
      w1[index_update]+=1;
      }

   i++;
   }

//same set of comments as above
  int k =0;

   while (b[k] != '\n')
   {
      if(isalpha(b[k]))
      {
          if(isupper(b[k]))
          {
              lower1 = tolower(b[k]);
          }
          else
          {
              lower1 = b[k];
          }

      index_update = lower1 - 'a';
       w2[index_update]+=1;
      }
      k++;
   }

   // Comparing frequency of characters

   for (int c = 0; c < 26; c++)
   {
       //if any numeric value is different, it means the words have different characters in them, which is wrong
      if (w1[c] != w2[c])
         return 0;
   }

   return 1;
}

