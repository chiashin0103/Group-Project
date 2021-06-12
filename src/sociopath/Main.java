package sociopath;

import static sociopath.LibraryBook.checkIncreasingSubsequent;
import static sociopath.LibraryBook.findRemove;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Stack;
import javax.swing.JFrame;
import static sociopath.Friendship.sortList;

public class Main {

    public static void main(String[] args) {

        Graph<Integer> graph = new Graph<>();
        enemyisHere<Integer> enemy = new enemyisHere<>();
        frenemy<Integer> frenemy = new frenemy<>();
        String banner = "********************************************************************************************************";

        Scanner input = new Scanner(System.in);
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
            enemy.addEnemyVertex(i);
        }
        //initialize relationship(edges) according to Figure 1 with reputation respectively
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
        enemy.addEnemyEdge(v1, v3);
        enemy.addEnemyEdge(v2, v10);
//        graph.addEdge(v5, v7, 6);
//        graph.addEdge(v7, v5, 6);
//        graph.addEdge(v4, v2, 6);
//        graph.addEdge(v2, v4, 6);

        String end = "";

        
        System.out.println("------------------------------------------Welcome to Sociopath!-----------------------------------------");
        while (!"Yes".equalsIgnoreCase(end)) {
            boolean isok = false;
            int event = 1;

            while (!isok) {
                try {
                    System.out.println("Please choose from one of the following event.\n"
                            + "1) Teaching a stranger to solve lab questions\n"
                            + "2) Chit-chat\n"
                            + "3) Lunch time\n"
                            + "4) Arranging books\n"
                            + "5) Meet your crush\n"
                            + "6) Friendship\n"
                            + "7) Social dynamics\n"
                            + "8) Parallel farming");
                            
                    event = input.nextInt();
                    isok = true;
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println("Please enter integer value");
                }
            }

            switch (event) {

                case 1: {
                    System.out.println(banner);
                    //Event 1
                    System.out.print("Enter your id: ");
                    int we = input.nextInt();
                    System.out.print("Enter stranger's id who seek for your help: ");
                    int teaching = input.nextInt();
                    ArrayList<Integer> Oureneighbour = graph.getNeighbours(people[we - 1]);
                    

                    while (teaching == we || Oureneighbour.contains(teaching)) {
                        System.out.print("He is not a stranger! Please enter a valid input:");
                        teaching = input.nextInt();
                    }

                    System.out.println("You are " + we + ",");//tell we will represent which number
                    System.out.println("You are teaching " + teaching + " now");//tell in event 1 we will be teaching who
                    System.out.print("Are you good at programming? Answer YES or NO: ");//are you success to help her/him
                    String answer = input.next();

                    if (answer.equalsIgnoreCase("Yes")) {//if success to help him/her

                        {
                            graph.addEdge( people[teaching - 1],people[we - 1],10);// no edges so we connect them 
                            System.out.println("You and " + teaching + " are now friends with rep point " + graph.getRep(people[teaching - 1],people[we - 1]));

                        }
                    } else {// answer is no

                        graph.addEdge(people[teaching - 1],people[we - 1],2);//rep will set to 2
                        System.out.println("You and " + teaching + " are now friends with rep point " + graph.getRep(people[teaching - 1],people[we - 1]));

                    }
                    System.out.println(banner);
                    System.out.println("Enter any key to continue. Type YES if you want to end the program");
                    end = input.next();
                    break;
                    //END OF EVENT 1
                }
                case 2: {
                    System.out.println(banner);
                    //EVENT 2
                    String str = "";
                    System.out.print("Enter your id: ");
                    int we = input.nextInt();
                    ArrayList<Integer> OureneighbourEvent2 = graph.getNeighbours(people[we - 1]);
                    
                    System.out.print("Enter your new friend's id: ");
                    int friend = input.nextInt();

                    while (friend == we){
                        System.out.print("Please enter a valid input: ");
                        friend = input.nextInt();
                    }
                     
                    // put all neighbour into arry list for later use
                    ArrayList<Integer> tostoreneighbour = graph.getNeighbours(people[friend - 1]);
                   
                    System.out.print("Did your new friend have a chit-chat with his/her friends? Answer YES or NO: ");
                    String answer2 = input.next();

                    if (answer2.equalsIgnoreCase("Yes")) {
                        System.out.print("Will it be a good or bad message? Answer good or bad: ");
                        String answer3 = input.next();

                        if (answer3.equalsIgnoreCase("good")) {
                            for (int i = 0; i < tostoreneighbour.size(); i++) {
                                //System.out.println(tostoreneighbour.get(i)); for checking who are new friend
                                if(tostoreneighbour.get(i)==we)
                                continue;
//                                chit_chat<Integer> cc1 = new chit_chat<>(graph, people[we - 1], people[friend - 1], people[tostoreneighbour.get(i)-1]);
//                                str = "Good";
//                                cc1.strangerToFriend(str);
                                switch (tostoreneighbour.get(i)) {

                                    case 1:

                                        chit_chat<Integer> cc1 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v1);
                                        str = "Good";
                                        cc1.strangerToFriend(str);
                                        break;
                                    case 2:
                                        chit_chat<Integer> cc2 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v2);
                                        str = "Good";
                                        cc2.strangerToFriend(str);
                                        break;
                                    case 3:
                                        chit_chat<Integer> cc3 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v3);
                                        str = "Good";
                                        cc3.strangerToFriend(str);
                                        break;
                                    case 4:
                                        chit_chat<Integer> cc4 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v4);
                                        str = "Good";
                                        cc4.strangerToFriend(str);
                                        break;
                                    case 5:
                                        chit_chat<Integer> cc5 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v5);
                                        str = "Good";
                                        cc5.strangerToFriend(str);
                                        break;
                                    case 6:
                                        chit_chat<Integer> cc6 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v6);
                                        str = "Good";
                                        cc6.strangerToFriend(str);
                                        break;
                                    case 7:
                                        chit_chat<Integer> cc7 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v7);
                                        str = "Good";
                                        cc7.strangerToFriend(str);
                                        break;
                                    case 8:
                                        chit_chat<Integer> cc8 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v8);
                                        str = "Good";
                                        cc8.strangerToFriend(str);
                                        break;
                                    case 9:
                                        chit_chat<Integer> cc9 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v9);
                                        str = "Good";
                                        cc9.strangerToFriend(str);
                                        break;
                                    case 10:
                                        chit_chat<Integer> cc10 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v10);
                                        str = "Good";
                                        cc10.strangerToFriend(str);
                                        break;
                                }
                            }
                        } else {
                            for (int i = 0; i < tostoreneighbour.size(); i++) {
                                //System.out.println(tostoreneighbour.get(i));
                                
                                switch (tostoreneighbour.get(i)) {

                                    case 1:
                                        chit_chat<Integer> cc1 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v1);
                                        str = "Bad";
                                        cc1.strangerToFriend(str);
                                        break;
                                    case 2:
                                        chit_chat<Integer> cc2 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v2);
                                        str = "bad";
                                        cc2.strangerToFriend(str);
                                        break;
                                    case 3:
                                        chit_chat<Integer> cc3 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v3);
                                        str = "bad";
                                        cc3.strangerToFriend(str);
                                        break;
                                    case 4:
                                        chit_chat<Integer> cc4 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v4);
                                        str = "bad";
                                        cc4.strangerToFriend(str);
                                        break;
                                    case 5:
                                        chit_chat<Integer> cc5 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v5);
                                        str = "bad";
                                        cc5.strangerToFriend(str);
                                        break;
                                    case 6:
                                        chit_chat<Integer> cc6 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v6);
                                        str = "bad";
                                        cc6.strangerToFriend(str);
                                        break;
                                    case 7:
                                        chit_chat<Integer> cc7 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v7);
                                        str = "bad";
                                        cc7.strangerToFriend(str);
                                        break;
                                    case 8:
                                        chit_chat<Integer> cc8 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v8);
                                        str = "bad";
                                        cc8.strangerToFriend(str);
                                        break;
                                    case 9:
                                        chit_chat<Integer> cc9 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v9);
                                        str = "bad";
                                        cc9.strangerToFriend(str);
                                        break;
                                    case 10:
                                        chit_chat<Integer> cc10 = new chit_chat<>(graph, people[we - 1], people[friend - 1], v10);
                                        str = "bad";
                                        cc10.strangerToFriend(str);
                                        break;
                                }
                            }
                        }
                        for (int i = 0; i < tostoreneighbour.size(); i++) {
                                //System.out.println(tostoreneighbour.get(i)); for checking who are new friend
                                if(tostoreneighbour.get(i)==we)
                                    continue;
				if(OureneighbourEvent2.contains(tostoreneighbour.get(i)))
                                    continue;
                                switch (tostoreneighbour.get(i)) {

                                    case 1:

                                        System.out.println("You have rep point of " + graph.getRep(v1,people[we - 1] )+" relative to "+tostoreneighbour.get(i));
                                        break;
                                    case 2:
                                        System.out.println("You have rep point of " + graph.getRep(v2,people[we - 1] )+" relative to "+tostoreneighbour.get(i));                                        
                                        break;
                                    case 3:
                                        System.out.println("You have rep point of " + graph.getRep(v3,people[we - 1] )+" relative to "+tostoreneighbour.get(i));                                       
                                        break;
                                    case 4:
                                        System.out.println("You have rep point of " + graph.getRep(v4,people[we - 1] )+" relative to "+tostoreneighbour.get(i));                                       
                                        break;
                                    case 5:
                                        System.out.println("You have rep point of " + graph.getRep(v5,people[we - 1] )+" relative to "+tostoreneighbour.get(i));                                       
                                        break;
                                    case 6:
                                        System.out.println("You have rep point of " + graph.getRep(v6,people[we - 1] )+" relative to "+tostoreneighbour.get(i));                                       
                                        break;
                                    case 7:
                                        System.out.println("You have rep point of " + graph.getRep(v7,people[we - 1] )+"relative to "+tostoreneighbour.get(i));                                       
                                        break;
                                    case 8:
                                        System.out.println("You have rep point of " + graph.getRep(v8,people[we - 1] )+" relative to "+tostoreneighbour.get(i));                                       
                                        break;
                                    case 9:
                                        System.out.println("You have rep point of " + graph.getRep(v9,people[we - 1] )+" relative to "+tostoreneighbour.get(i));                                       
                                        break;
                                    case 10:
                                        System.out.println("You have rep point of " + graph.getRep(v10,people[we - 1] )+" relative to "+tostoreneighbour.get(i));                                       
                                        break;
                                }
                            }
                    }
                    
                    System.out.println("The new friendship are as follows: ");
                    graph.printEdges();
                    System.out.println(banner);
                    System.out.println("Enter any key to continue. Type YES if you want to end the program");
                    end = input.next();
                    break;
                }

                
                case 3: {
                    System.out.println(banner);
                    //Event 3
                    System.out.print("Enter your id: ");
                    int we = input.nextInt();
                    System.out.print("Enter your lunch time today (eg. 1105): ");
                    int lunchtime = input.nextInt();
                    while(lunchtime<1100||lunchtime>1400){
                        System.out.print("Please enter a valid LUNCH time: ");
                        lunchtime = input.nextInt();
                    }
                    System.out.print("Enter how long do you want to have lunch today (5 to 60 minutes): ");
                    int lunchperiod = input.nextInt();
                    while(lunchperiod<=5||lunchperiod>=60){
                        System.out.println("You lunch period is not healthy!!\nPlease make your lunch period longer than 5 minutes and shorter than 60 minutes!");
                        System.out.print("Enter how long do you want to have lunch today (5 to 60 minutes): ");
                        lunchperiod = input.nextInt();
                    }
                    System.out.print("Free-riders are annoying, I know, and I understand if you would rather not have any relationship with people with high diving rate."
                            + "\nWhat is the maximum diving rate you can accept for the person who have lunch with you? (0-100): ");
                    int divrate = input.nextInt();
                    graph.eatLunchwMaxRep(lunchtime,lunchperiod,people[we-1],divrate);
                    
                    System.out.println(banner);
                    System.out.println("Enter any key to continue. Type YES if you want to end the program");
                    end=input.next();
                    break;
                }
                
                case 4: {
                    System.out.println(banner);
                    //Event 4
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
                    while (checkIncreasingSubsequent(sort) == true);

                    //Print output
                    System.out.println("The number of round(s): " + count);
                    
                    System.out.println(banner);
                    System.out.println("Enter any key to continue. Type YES if you want to end the program");
                    end=input.next();
                    break;

                }

                
                case 5: {
                    System.out.println(banner);
                    //Event 5
                    System.out.print("Enter the stranger's id who start the rumour: ");
                    int rumors = input.nextInt();
                    ArrayList<Integer> Oureneighbour = graph.getNeighbours(people[rumors - 1]);
                    System.out.print("Enter your crush's id: ");
                    int crush = input.nextInt();

                    while (crush == rumors) {
                        System.out.print("Please enter a valid input: ");
                        crush = input.nextInt();
                    }
                    graph.canPreventRumour(people[rumors - 1], people[crush - 1]);
                    System.out.println("");
                    
                    System.out.println(banner);
                    System.out.println("Enter any key to continue. Type YES if you want to end the program");
                    end=input.next();
                    break;
                }
                
                case 6: {
                    System.out.println(banner);
                    //Event 6
                    Scanner sc = new Scanner(System.in);
                    JFrame board = new JFrame();
                    board.setSize(750, 500);
                    board.setTitle("Displaying graphical output: ");
                    visualizeOutput panel = new visualizeOutput();

                    System.out.println("Input: ");
                    int n = sc.nextInt();
                    Friendship g = new Friendship(n + 1);

                    int x = 50;
                    int y = 480;

                    for (int i = 0; i < n; i++) {

                        int l = sc.nextInt();
                        int r = sc.nextInt();
                        g.addEdge(l, r);
                        g.addEdge(r, l);

                        if (i % 2 == 0) {
                            x += 33;
                            y -= 123;
                        } else {
                            x += 199;
                            y -= 77;
                        }

                        panel.addVertex(String.valueOf(i + 1), x, y);
                        panel.addEdge(l - 1, r - 1);
                    }

                    List<List<List<Integer>>> list = new ArrayList<List<List<Integer>>>();
                    List<List<Integer>> inlist = new ArrayList<List<Integer>>();

                    for (int i = 1; i < n; i++) {
                        System.out.println("");
                        for (int j = i + 1; j <= n; j++) {
                            list.add(g.getAllPaths(i, j));
                            list.removeIf(p -> p.isEmpty()); // remove empty bracket []
                        }
                    }

                    for (int k = 0; k < list.size(); k++) {

                        for (int l = 0; l < list.get(k).size(); l++) {

                            inlist.add(list.get(k).get(l));

                        }

                    }

                    sortList(inlist);
                    System.out.println("You can form the following friendship(s): ");

                    for (int i = 0; i < inlist.size(); i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(inlist.get(i));
                    }

                    board.add(panel);
                    board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    board.setVisible(true);
                    
                    System.out.println(banner);
                    System.out.println("Enter any key to continue. Type YES if you want to end the program");
                    end=input.next();
                    break;
                }
                
                
                /*Extra feature */
                case 7: {
                    System.out.println(banner);
                    //Event 7 - Social dynamics
                    System.out.println("");
                    System.out.println("-----------Check for Frenemy------------");
                    areyouFrenemy(graph, enemy, frenemy, v1, v3, v2);
                    areyouFrenemy(graph, enemy, frenemy, v2, v10, v5);
                    System.out.println("Case 1- middle friend is friend with your hater=frenemy");
                    System.out.println("Vertex 1 and vertex 3 is enemy: " + enemy.hasEnemyEdge(v1, v3));
                    System.out.println("Vertex 2 and vertex 3 is enemy: " + enemy.hasEnemyEdge(v2, v3));
                    System.out.println("Vertex 2-->vertex 3 is frenemy: " + frenemy.hasFrenemyEdge(v2, v3));
                    System.out.println("Vertex 3-->vertex 2 is frenemy: " + frenemy.hasFrenemyEdge(v3, v2));
                    System.out.println("Case 2- middle friend have no relation with your hater");
                    System.out.println("Vertex 2 and vertex 10 is enemy: " + enemy.hasEnemyEdge(v2, v10));
                    System.out.println("Vertex 5-->vertex 10 is frenemy: " + frenemy.hasFrenemyEdge(v5, v10));
                    System.out.println("Vertex 5 and vertex 10 is enemy: " + enemy.hasEnemyEdge(v5, v10));

                    System.out.println(banner);
                    System.out.println("Enter any key to continue. Type YES if you want to end the program");
                    end=input.next();
                    break;
                }
                
                //extra feature 
                case 8: {
                    System.out.println(banner);
                    //Event 8 - Parallel farming
                    System.out.println("Your account is upgraded! Now you can have lunch with 3 people at the same time");
                    System.out.print("Enter your id: ");
                    int we = input.nextInt();
                    System.out.print("Enter your lunch time today (eg. 1105): ");
                    int lunchtime = input.nextInt();
                    while(lunchtime<1100||lunchtime>1400){
                        System.out.print("Please enter a valid LUNCH time: ");
                        lunchtime = input.nextInt();
                    }
                    System.out.print("Enter how long do you want to have lunch today (5 to 60 minutes): ");
                    int lunchperiod = input.nextInt();
                    while(lunchperiod<=5||lunchperiod>=60){
                        System.out.println("You lunch period is not healthy!!\nPlease make your lunch period longer than 5 minutes and shorter than 60 minutes!");
                        System.out.print("Enter how long do you want to have lunch today (5 to 60 minutes): ");
                        lunchperiod = input.nextInt();
                    }
                    System.out.print("Free-riders are annoying, I know, and I understand if you would rather not have any relationship with people with high diving rate."
                            + "\nWhat is the maximum diving rate you can accept for the person who have lunch with you? (0-100): ");
                    int divrate = input.nextInt();
                    graph.UpgradedEatLunchwMaxRep(lunchtime,lunchperiod,people[we-1],divrate);
                    
                    System.out.println(banner);
                    System.out.println("Enter any key to continue. Type YES if you want to end the program");
                    end=input.next();
                    break;
                }

            }

        }

    }

    public static void areyouFrenemy(Graph<Integer> graph, enemyisHere<Integer> enemy, frenemy<Integer> frenemy, VertexInfo<Integer> bestFriend, VertexInfo<Integer> hate, VertexInfo<Integer> middle) {
        if (enemy.hasEnemyEdge(bestFriend, hate) == true) {
            //when your friend is friend with your hater
            if (graph.hasEdge(middle, hate) == true || graph.hasEdge(hate, middle)==true) {
                frenemy.addFrenemyVertex(hate);
                frenemy.addFrenemyVertex(middle);
                frenemy.addFrenemyEdge(middle, hate);
            } else {
                //when your friend has no relation with your hater
                enemy.addEnemyEdge(middle, hate);
            }
        }
    }
    
   

}
