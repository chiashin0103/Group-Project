package sociopath;

import static event5dsassigment.LibraryBook.checkIncreasingSubsequent;
import static event5dsassigment.LibraryBook.findRemove;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Stack;

public class Main {
    
    public static void main(String[] args) {
    
        Graph<Integer> graph = new Graph<>();
        Scanner input=new Scanner(System.in);
        //Random insert vertex information (id->10 people, diving rate, lunch start hour, lunch period)
        VertexInfo<Integer> v1 = new VertexInfo(1, 50, 1120, 15);
        VertexInfo<Integer> v2 = new VertexInfo(2, 60, 1130, 20);
        VertexInfo<Integer> v3 = new VertexInfo(3, 70, 1200, 30);
        VertexInfo<Integer> v4 = new VertexInfo(4, 30, 1230, 40);
        VertexInfo<Integer> v5 = new VertexInfo(5, 80, 1300, 50);
        VertexInfo<Integer> v6 = new VertexInfo(6, 20, 1320, 20);
        VertexInfo<Integer> v7 = new VertexInfo(7, 15, 1245, 30);
        VertexInfo<Integer> v8 = new VertexInfo(8, 80, 1145, 25);
        VertexInfo<Integer> v9 = new VertexInfo(9, 55, 1233, 28);
        VertexInfo<Integer> v10 = new VertexInfo(10, 35, 1125, 35);

        VertexInfo[] people = {v1, v2, v3, v4, v5, v6, v7, v8, v9, v10};

        for (VertexInfo i : people) {
            graph.addVertex(i);
        }

        //initialize relationship(edges) according to Figure 1 with reputation respectively
        System.out.println("Adding edges...");
        graph.addEdge(v1, v7, 4);
        graph.addEdge(v1, v2, 5);
        graph.addEdge(v2, v6, 9);
        graph.addEdge(v2, v3, 5);
        graph.addEdge(v2, v5, 6);
        graph.addEdge(v2, v1, 8);
        graph.addEdge(v3, v2, 4);
        graph.addEdge(v4, v8, 7);
        graph.addEdge(v4, v10, 7);
        graph.addEdge(v5, v2, 2);
        graph.addEdge(v6, v2, 7);
        graph.addEdge(v7, v1, 3);
        graph.addEdge(v8, v4, 10);
        graph.addEdge(v9, v10, 5);
        graph.addEdge(v10, v4, 7);
        graph.addEdge(v10, v9, 6);

      
        String end="";
        
        while(!"Yes".equalsIgnoreCase(end)){
            boolean isok = false;
            int event=1;
            
        while(!isok){       
            try {
                System.out.println("Enter the Event you want 1,2,3,4,5,6");
                event = input.nextInt();
                isok = true;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Please enter integer value");
            }
        }
        
        switch (event){
            
        case 1:{
        //Event 1
        System.out.println("Enter Your vertex Number For Event 1");
        int we=input.nextInt();
        System.out.println("Enter who u are teaching");
        ArrayList<Integer> Oureneighbour=graph.getNeighbours(people[we-1]);
        int teaching=input.nextInt();
        
        while(teaching==we||Oureneighbour.contains(teaching)){
        System.out.println("Please enter a valid input");
        teaching=input.nextInt();
         }
        
        System.out.println("You are "+we );//tell we will represent which number
        System.out.println("You are teaching "+teaching+" now" );//tell in event 1 we will be teaching who
        System.out.println("Are you help him/her in the lab question? Answer YES or NO");//are you seccess to help her/him
        String answer=input.next();
         
        if(answer.equalsIgnoreCase("Yes")){//if success to help him/her
                 
                 {
                     graph.addEdge(people[we-1],people[teaching-1],10);// no edges so we connect them 
                     System.out.println("You and "+ teaching + " are friend now with rep point "+graph.getRep(people[we-1],people[teaching-1]));
                     
                 }
         }
             else{// answer is no
                 
                 graph.addEdge(people[we-1],people[teaching-1],2);//rep will set to 2
                 System.out.println("You and "+ teaching + " are friend now with rep point "+graph.getRep(people[we-1],people[teaching-1]));
                
             }
                 System.out.println("Type YES if you want to end the program");
                 end=input.next();
                break;
             //END OF EVENT 1
        }
         case 2:{
             //EVENT 2
               String str="";
               System.out.println("Enter Your vertex Number For Event 2");
               int we=input.nextInt();
               System.out.println("Enter who u are your new friend");
               int friend=input.nextInt();

               while(friend==we){
               System.out.println("Please enter a valid input");
               friend=input.nextInt();
               }
               
               ArrayList<Integer> tostoreneighbour=graph.getNeighbours(people[friend-1]);
                // put all neighbour into arry list for later use
               System.out.println("Do you want to tell your friend about him/her?Answer YES or NO)");
             String answer2=input.next();
             
             if(answer2.equalsIgnoreCase("Yes")){
                 System.out.println("Will it be a good or bad message?Answer good or bad");
                 String answer3=input.next();
                 
             if(answer3.equalsIgnoreCase("good")){   
             for(int i = 0; i < tostoreneighbour.size();i++) {
                 //System.out.println(tostoreneighbour.get(i)); for checking who are new friend
                                   
                    switch (tostoreneighbour.get(i)) {
                      
                     case 1:
                         
                     chit_chat<Integer> cc1 = new chit_chat<>(graph,people[we-1],people[friend-1],v1);
                     str = "Good";
                     cc1.strangerToFriend(str);
                      break;
                     case 2:
                     chit_chat<Integer> cc2 = new chit_chat<>(graph,people[we-1],people[friend-1],v2);
                     str = "Good";
                     cc2.strangerToFriend(str);
                       break;
                     case 3:
                       chit_chat<Integer> cc3 = new chit_chat<>(graph,people[we-1],people[friend-1],v3);
                       str = "Good";
                     cc3.strangerToFriend(str);
                       break;
                     case 4:
                       chit_chat<Integer> cc4 = new chit_chat<>(graph,people[we-1],people[friend-1],v4);
                       str = "Good";
                     cc4.strangerToFriend(str);
                       break;
                    case 5:
                       chit_chat<Integer> cc5 = new chit_chat<>(graph,people[we-1],people[friend-1],v5);
                       str = "Good";
                     cc5.strangerToFriend(str);
                     break;
                     case 6:
                       chit_chat<Integer> cc6 = new chit_chat<>(graph,people[we-1],people[friend-1],v6);
                       str = "Good";
                     cc6.strangerToFriend(str);
                      break;
                   case 7:
                       chit_chat<Integer> cc7 = new chit_chat<>(graph,people[we-1],people[friend-1],v7);
                       str = "Good";
                     cc7.strangerToFriend(str);
                       break;
                     case 8:
                       chit_chat<Integer> cc8 = new chit_chat<>(graph,people[we-1],people[friend-1],v8);
                       str = "Good";
                     cc8.strangerToFriend(str);
                       break;
                     case 9:
                       chit_chat<Integer> cc9 = new chit_chat<>(graph,people[we-1],people[friend-1],v9);
                       str = "Good";
                     cc9.strangerToFriend(str);
                       break;
                     case 10:
                       chit_chat<Integer> cc10 = new chit_chat<>(graph,people[we-1],people[friend-1],v10);
                       str = "Good";
                     cc10.strangerToFriend(str);
                       break;
                   }
                         }
             }
             else{
                 for(int i = 0; i < tostoreneighbour.size();i++) {
                 System.out.println(tostoreneighbour.get(i));
                                   
                    switch (tostoreneighbour.get(i)) {
                      
                     case 1:
                         
                     chit_chat<Integer> cc1 = new chit_chat<>(graph,people[we-1],people[friend-1],v1);
                     str = "Bad";
                     cc1.strangerToFriend(str);
                      break;
                     case 2:
                     chit_chat<Integer> cc2 = new chit_chat<>(graph,people[we-1],people[friend-1],v2);
                     str = "bad";
                     cc2.strangerToFriend(str);
                       break;
                     case 3:
                       chit_chat<Integer> cc3 = new chit_chat<>(graph,people[we-1],people[friend-1],v3);
                       str = "bad";
                     cc3.strangerToFriend(str);
                       break;
                     case 4:
                       chit_chat<Integer> cc4 = new chit_chat<>(graph,people[we-1],people[friend-1],v4);
                       str = "bad";
                     cc4.strangerToFriend(str);
                       break;
                    case 5:
                       chit_chat<Integer> cc5 = new chit_chat<>(graph,people[we-1],people[friend-1],v5);
                       str = "bad";
                     cc5.strangerToFriend(str);
                     break;
                     case 6:
                       chit_chat<Integer> cc6 = new chit_chat<>(graph,people[we-1],people[friend-1],v6);
                       str = "bad";
                     cc6.strangerToFriend(str);
                      break;
                   case 7:
                       chit_chat<Integer> cc7 = new chit_chat<>(graph,people[we-1],people[friend-1],v7);
                       str = "bad";
                     cc7.strangerToFriend(str);
                       break;
                     case 8:
                       chit_chat<Integer> cc8 = new chit_chat<>(graph,people[we-1],people[friend-1],v8);
                       str = "bad";
                     cc8.strangerToFriend(str);
                       break;
                     case 9:
                       chit_chat<Integer> cc9 = new chit_chat<>(graph,people[we-1],people[friend-1],v9);
                       str = "bad";
                     cc9.strangerToFriend(str);
                       break;
                     case 10:
                       chit_chat<Integer> cc10 = new chit_chat<>(graph,people[we-1],people[friend-1],v10);
                       str = "bad";
                     cc10.strangerToFriend(str);
                       break;
                   }
                         }
             }
             }
             System.out.println("Type YES if you want to end the program");
              end=input.next();
             break;
        }
        
        //Event 3
            case 3: {
        System.out.println("Enter Your vertex Number");
        int we=input.nextInt();
        System.out.println("Maximum reputation that can be obtain is " + graph.getMaxRep(v9));
        System.out.println("Type YES if you want to end the program");
        end=input.next();
        break;
    }
        //Event 4
            case 4: {
        Scanner s = new Scanner(System.in);
        Stack<Integer> stack = new Stack<Integer>();
        
        System.out.print("Enter the number of book: ");
        int size = s.nextInt();
        int newSize;
        int count = 0;
        int[] sort = new int[size];

        //User input
        System.out.print("Enter the heights of books: ");
        String input1 = s.next();
        input1 += s.nextLine();

        //store it in array
        String[] height = input1.split(" ");

        //convert to int array
        for (int i = 0; i < size; i++) {
            sort[i] = Integer.parseInt(height[i]);
        }

        do {
            findRemove(sort);
            stack.addAll(findRemove(sort));
            newSize = stack.size();
            sort = new int[newSize];
            for (int i = newSize - 1; i >= 0; i--) {
                sort[i] = stack.pop();
            }

            //counting for output
            count++;

        } //check whether it has increasing subsequent
        while ( checkIncreasingSubsequent(sort) == true);

        //Print output
        System.out.println("The number of round(s): " + count);
                System.out.println("Type YES if you want to end the program");
                 end=input.next();
                break;
        
    }

        //Event 5
            case 5: {
        System.out.println("Enter Where the rumors Start");
        int rumors=input.nextInt();
        ArrayList<Integer> Oureneighbour=graph.getNeighbours(people[rumors-1]);
        System.out.println("Enter who u are your crush");
               int crush=input.nextInt();

               while (crush==rumors){
               System.out.println("Please enter a valid input");
               crush=input.nextInt();
               }
              graph.canPreventRumour(people[rumors-1], people[crush-1]);
       System.out.println("");
       System.out.println("Type YES if you want to end the program");
       end=input.next();
        break;
    }
        //Event 6
     case 6: {
         //get number of vertices
        System.out.println("Input: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //initialize a graph
        formingFriendBond bff = new formingFriendBond(n + 1);
        int s = 0, d = 0;
        //get input 
        for (int i = 0; i < n; i++) {
            bff.addEdge(sc.nextInt(), sc.nextInt());
        }
        //print output
        System.out.println("");
        System.out.print("You can form the following friendship(s) : ");
        System.out.println("");
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                bff.printAllPaths(i, j);
            }
        }
        System.out.println("Type YES if you want to end the program");
        end=input.next();
        break;
    }
        
    }
    
    }
}
}