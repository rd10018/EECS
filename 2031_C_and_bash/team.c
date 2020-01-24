//note:the only problem I faced with this code is that during
// the execution of each if-else statement for the response,
// one invalid response gets sutomatically pressed on  the console.
//I am unable to figure it out. Please be grateful to mention some feedback on where i am making the mistake.






// EECS2031 team.c
//
// Program for maintaining a personal team.
//
//
// Uses a linked list to hold the team players.
//
// Author: <Rishab Dhamija>
// Student Info: <213223334>


#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <assert.h>

//**********************************************************************
// Linked List Definitions
//  Define your linked list node and pointer types
//  here for use throughout the file.
//
//
//

struct node
{
     char first_name[20];
     char last_name[20];
     char position[1];
    int value;
    struct node * next;

};




//**********************************************************************
// Linked List Function Declarations
//
// Functions that modify the linked list.
//   Declare your linked list functions here.
//
//   ADD STATEMENT(S) HERE



//**********************************************************************
// Support Function Declarations
//

void safegets (char s[], int arraySize);        // gets without buffer overflow
void familyNameDuplicate (char familyName[]);   // marker/tester friendly
void familyNameFound (char familyName[]);       //   functions to print
void familyNameNotFound (char familyName[]);    //     messages to user
void familyNameDeleted (char familyName[]);
void printTeamEmpty (void);
void printTeamTitle(void);
void printNoPlayersWithLowerValue(int value);

//**********************************************************************
// Program-wide Constants
//

const int MAX_LENGTH = 1023;
const char NULL_CHAR = '\0';
const char NEWLINE = '\n';
const char GOALKEEPER = 'G';
const char DEFENDER = 'D';
const char MIDFIELDER = 'M';
const char STRIKER = 'S';


//**********************************************************************
// Main Program
//

int main (void)
{
    const char bannerString[]
        = "Personal Team Maintenance Program.\n\n";
    const char commandList[]
        = "Commands are I (insert), D (delete), S (search by name),\n"
          "  V (search by value), P (print), Q (quit).\n";

    // Declare linked list head.
    //   ADD STATEMENT(S) HERE TO DECLARE LINKED LIST HEAD.
    struct node * first = NULL;

    // announce start of program
    printf("%s",bannerString);
    printf("%s",commandList);

    char response;
    char input[MAX_LENGTH+1];
    int loop_value = 1;
     do
    {
        printf("\nCommand?: ");
        safegets(input,MAX_LENGTH+1);
        // Response is first char entered by user.
        // Convert to uppercase to simplify later comparisons.
        response = toupper(input[0]);



         if (response == 'D')
        {
            // Delete a player from the list.

            printf("\nEnter family name for entry to delete: ");
             char family_name[30];

            scanf("%s", &family_name);
            struct node * delete_node, *previous;
            int found = 0;

            for(delete_node = first,previous = NULL; delete_node != NULL && strcmp(delete_node->last_name,family_name)!=0;previous=delete_node ,delete_node= delete_node->next)
              ;
              if(delete_node == NULL)
              {
                  printf("the searched family_name to be deleted doesn't exist in the team");
              }
             else if(previous == NULL)
              {
                  printf("\nDeleting player with family name <%s> from the area.\n",family_name);
                  first = first->next;
              }
              else
              {
                  printf("\nDeleting player with family name <%s> from the area.\n",family_name);
             previous->next = delete_node->next;
              }
             free(delete_node);





            //   ADD STATEMENT(S) HERE

        }
        else if (response == 'I' )
        {
            // Insert a player entry into the linked list.
            // Maintain the list in correct order (G, D, M, S).
            //   ADD STATEMENT(S) HERE
            struct node * new_member = (struct node *)malloc(sizeof(struct node));

            // USE THE FOLLOWING PRINTF STATEMENTS WHEN PROMPTING FOR DATA:
             printf("  \tfamily name: ");
             scanf("%s", &new_member->last_name);
             printf("  \tfirst name: ");
             scanf("%s", &new_member->first_name);
             printf("  \tposition: ");
             scanf("%s", &new_member->position);
             printf("  \tvalue: ");
             scanf("%d", &new_member->value);

             struct node * search_node;
             search_node = first;
             int found1 =0;


             while(search_node!=NULL)
             {

               if(strcmp(search_node->last_name, new_member->last_name) == 0)
               {
                 found1 =1;
                 printf("this node has family name already present in the team");
               }
               search_node = search_node->next;
             }
          if(found1 == 0)
          {



                  if(new_member->position[0] == 'G')
                   {

                           new_member->next = first;
                           first = new_member;

                   }





             if(new_member->position[0] == 'D')
                {
                    struct node * p;
                    struct node * prev;
                     int found = 0;
                    p = first;
                    if(p == NULL)
                    {
                        new_member->next = first;
                           first = new_member;
                    }
                    else
                    {

                     while(found != 1)
                    {
                        if( p->position[0] =='G' && p->next !=NULL)
                        {

                          prev = p->next;
                          if(prev->position[0] == 'D'||prev->position[0] == 'M'||prev->position[0] == 'S')
                          {

                            p->next = new_member;
                             new_member->next = prev;
                            found =1 ;
                          }
                          else
                          {
                              p = p->next;
                          }
                        }
                          if( p->position[0] =='G' && p->next ==NULL)

                            {

                              p->next = new_member;
                              new_member->next = NULL;
                              found = 1;

                            }
                         if(p->position[0] == 'D'||p->position[0] == 'M'||p->position[0] == 'S')
                          {

                            p->next = new_member;
                             new_member->next = prev;
                            found =1 ;
                          }

                    }
                    }

                }



                 if(new_member->position[0] == 'M')
                {
                    struct node * p;
                    struct node * prev;
                     int found = 0;
                    p = first;
                     if(p == NULL)
                    {
                        new_member->next = first;
                           first = new_member;
                    }
                    else
                    {


                     while(found != 1)
                    {
                        if( p->position[0] =='G' && p->next ==NULL)

                            {

                              p->next = new_member;
                              new_member->next = NULL;
                              found = 1;

                            }
                        if( p->position[0] =='G' && p->next !=NULL)
                        {

                          prev = p->next;
                          if(prev->position[0] == 'G'|| prev->position[0] == 'D' ) //
                          {
                              p = p->next;
                          }
                          if(prev->position[0] == 'M' ||prev->position[0] == 'S')
                          {

                            p->next = new_member;
                             new_member->next = prev;
                            found =1 ;
                          }

                        }
                        if( p->position[0] =='D' && p->next !=NULL)
                        {

                          prev = p->next;
                          if( prev->position[0] == 'D' ) //
                          {
                              p = p->next;
                          }
                          if(prev->position[0] == 'M' || prev->position[0] == 'S')
                          {

                            p->next = new_member;
                             new_member->next = prev;
                            found =1 ;
                          }
                        }
                          if( p->position[0] =='D' && p->next ==NULL)

                            {

                              p->next = new_member;
                              new_member->next = NULL;
                              found = 1;

                            }
                        if( p->position[0] =='M' ||p->position[0] =='S')

                            {

                              p->next = new_member;
                              new_member->next = NULL;
                              found = 1;

                            }


                    }
                    }
                }






                 if(new_member->position[0] == 'S')
                {
                   struct node * p;
                    struct node * prev;
                     int found = 0;
                    p = first;
                     if(p == NULL)
                    {
                        new_member->next = first;
                           first = new_member;
                    }
                    else
                    {


                     while(found != 1)
                    {
                        if( p->position[0] =='G' || p->position[0] =='D' ||p->position[0] =='M)'&& p->next ==NULL)

                            {

                              p->next = new_member;
                              new_member->next = NULL;
                              found = 1;

                            }
                       else if( p->position[0] =='G' && p->next !=NULL)
                        {

                          prev = p->next;
                          if(prev->position[0] == 'G'|| prev->position[0] == 'D'|| prev->position[0] == 'M' ) //
                          {
                              p = p->next;
                          }
                          else if(prev->position[0] == 'S')
                          {

                            p->next = new_member;
                             new_member->next = prev;
                            found =1 ;
                          }

                        }
                        else if( p->position[0] =='D' && p->next !=NULL)
                        {

                          prev = p->next;
                          if( prev->position[0] == 'D' ||prev->position[0] == 'M' ) //
                          {
                              p = p->next;
                          }
                          else if( prev->position[0] == 'S')
                          {

                            p->next = new_member;
                             new_member->next = prev;
                            found =1 ;
                          }
                        }
                         else if( p->position[0] =='M' && p->next !=NULL)

                            {
                                 prev = p->next;
                          if( prev->position[0] == 'M' ) //
                          {
                              p = p->next;
                          }
                          else if( prev->position[0] == 'S')
                          {

                            p->next = new_member;
                             new_member->next = prev;
                            found =1 ;
                          }

                            }

                       else if( p->position[0] =='S')

                            {

                              p->next = new_member;
                              new_member->next = NULL;
                              found = 1;

                            }


                    }



                    }

                 }

             }
        }
        else if (response == 'S')
        {
            // Search for a player by family name.

            char family_name[30];
            printf("\nEnter family name to search for: ");
            scanf("%s", &family_name);
            struct node * search_node11;
            search_node11 = first;
            int found = 0;

            while(found !=1 && search_node11 != NULL)
            {
                if(strcmp(search_node11->last_name, family_name)==0)
                {
                    printf("\nthe player with the family name <%s> was found in the team\n", family_name);
                     printf("%s\n", search_node11->last_name);
                     printf("%s\n", search_node11->first_name);
                     printf("%s\n", search_node11->position);
                     printf("%d\n", search_node11->value);


                found = 1;
                }
                else
                {
                search_node11 = search_node11-> next;
                }

            }

            if(found == 0)
            {
                printf("the player with the family name <%s> is not in the team", family_name);
            }

            //   ADD STATEMENT(S) HERE

        }

            else if (response == 'V')
            {

            // Search for players that are worth less than or equal a value.
             int v = 0;
            printf("\nEnter value: ");
            scanf("%d",&v);
            struct node * search_node11;
            search_node11 = first;
            int found  = 0;
            while(search_node11 !=NULL)
            {
                if(search_node11->value <= v)
                {
                     printf("%s\n", search_node11->last_name);
                     printf("%s\n", search_node11->first_name);
                     printf("%s\n", search_node11->position);
                     printf("%d\n", search_node11->value);
                     printf("\n");
                found = 1;

                }
                search_node11 = search_node11-> next;

            }
                if (found == 0)
                    printf("no player9s0 in the team is worth less than or equl to <%d>\n",value);
            }


        else if (response == 'P')
        {
            // Print the team.

            struct node * p;
             printf("\nMy Team: \n\n");
           for(p=first;p!= NULL;p = p->next)
          {


             printf("%s\n", p->last_name);
             printf("%s\n", p->first_name);
            printf("%s\n", p->position);
             printf("%d\n", p->value);
             printf("\n");
           }
        }
        else if (response == 'Q')
        {
        }
        else
        {


            // do this if no command matched ...
            printf("\nInvalid command.\n%s\n",commandList);
            loop_value = 0;
        }

          }while (response != 'Q');






    // Delete the whole linked list that hold the team.
    //   ADD STATEMENT(S) HERE
           struct node * p, *prev1;
           p = first;
            while(p!=NULL)
            {
                prev1 = p->next;
                free(p);
                p= prev1;
            }
              first = NULL;

    // Print the linked list to confirm deletion.
    //   ADD STATEMENT(S) HERE
          printf("printing the list after quit command\n");
        struct node * p1;
             printf("\nMy Team: \n");
           for(p1=first;p1!= NULL;p1 = p1->next)
          {


             printf("%s\n", p1->last_name);
             printf("%s\n", p1->first_name);
            printf("%s\n", p1->position);
             printf("%d\n", p1->value);
             printf("\n");
           }

    return 0;
}



//**********************************************************************
// Support Function Definitions

// Function to get a line of input without overflowing target char array.
void safegets (char s[], int arraySize)
{
    int i = 0, maxIndex = arraySize-1;
    char c;
    while (i < maxIndex && (c = getchar()) != NEWLINE)
    {
        s[i] = c;
        i = i + 1;
    }
    s[i] = NULL_CHAR;
}

// Function to call when user is trying to insert a family name
// that is already in the book.
void familyNameDuplicate (char familyName[])
{
    printf("\nAn entry for <%s> is already in the team!\n"
           "New entry not entered.\n",familyName);
}

// Function to call when a player with this family name was found in the team.
void familyNameFound (char familyName[])
{
    printf("\nThe player with family name <%s> was found in the team.\n",
             familyName);
}

// Function to call when a player with this family name was not found in the team.
void familyNameNotFound (char familyName[])
{
    printf("\nThe player with family name <%s> is not in the team.\n",
             familyName);

}

// Function to call when a family name that is to be deleted
// was found in the team.
void familyNameDeleted (char familyName[])
{
    printf("\nDeleting player with family name <%s> from the team.\n",
             familyName);
}

// Function to call when printing an empty team.
void printTeamEmpty (void)
{
    printf("\nThe team is empty.\n");
}

// Function to call to print title when whole team being printed.
void printTeamTitle (void)
{
    printf("\nMy Team: \n");
}

// Function to call when no player in the team has lower or equal value to the given value
void printNoPlayersWithLowerValue(int value)
{
	printf("\nNo player(s) in the team is worth less than or equal to <%d>.\n", value);
}

//**********************************************************************
// Add your functions below this line.


