import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

/*
 * Script to Generate .SQL file in order to populate The Armory
 * @author Alper Mencek
 */

public class ArmorySeedScript {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the total Amount of Users (note this will be the largest Table):");

        int x = in.nextInt();

        PrintWriter writer = new PrintWriter("GeneratedDML.sql", "UTF-8");
        Random rnd = new Random();
        int securityCode = 100+rnd.nextInt(899);
        int dollar = 0 +rnd.nextInt(1000);
        int cent =00 +rnd.nextInt(99);
        final String [] card = {"Debit","Credit"};
        int index=0;
        int index2=0;
        int expd = 1 ;
        int expM = 1 ;
        int cardNum = 00000000;
        final String [] Boolean = {"TRUE","FALSE"};
        //insert into User Table
        for (int i = 0 ; i <x; i++) {
            index = rnd.nextInt(card.length);
            securityCode = 100+rnd.nextInt(899);
            dollar = 0 +rnd.nextInt(1000);
            cent =10 +rnd.nextInt(99);
            expM = 1 +rnd.nextInt(12);
            expd = 1 +rnd.nextInt(29);
            cardNum = 10000000+rnd.nextInt(89999999);
            writer.println("INSERT INTO User VALUES('user"+i+"', 'password"+i+"',"+dollar+"."+cent+", '"+card[index]+"',"
                    + " '"+cardNum+"','"+securityCode+"', '2020-"+expM+"-"+expd+"');");
        }
        writer.println("\n");
        //Premium user
        for (int i = 0 ; i <x/5; i++) {
            index = rnd.nextInt(card.length);

            expM = 1 +rnd.nextInt(12);
            expd = 1 +rnd.nextInt(29);
            cardNum = 10000000+rnd.nextInt(89999999);
            writer.println("INSERT INTO Premium_user VALUES('user"+i+"',"
                    + ""+Boolean[index]+","+Boolean[index]+", '2020-"+expM+"-"+expd+"');");
        }
        writer.println("\n");
        //review User
        for (int i = x/5 ; i <((x/5)*2); i++) {
            index = rnd.nextInt(card.length);

            expM = 1 +rnd.nextInt(12);
            expd = 1 +rnd.nextInt(29);
            cardNum = 10000000+rnd.nextInt(89999999);
            writer.println("INSERT INTO Review_user VALUES('user"+i+"',"+Boolean[index]+");");
        }
        writer.println("\n");

        //Banned user

        final String [] Banned = {"Cheating","Illegal Gambling","System Hacking"};
        for (int i = x/5 ; i <((x/5)*2); i++) {
            index = rnd.nextInt(card.length);
            index2 = rnd.nextInt(Banned.length);

            expM = 1 +rnd.nextInt(12);
            expd = 1 +rnd.nextInt(29);
            cardNum = 10000000+rnd.nextInt(99999999);
            writer.println("INSERT INTO Banned_user VALUES('user"+i+"',"+Boolean[index]+",'"+Banned[index2]+"');");
        }
        writer.println("\n");

        //Sys Admin
        for (int i =((x/5)*2) ; i <((x/5)*3); i++) {
            writer.println("INSERT INTO System_administrator VALUES('user"+i+"');");
        }
        writer.println("\n");

        //publisher
        int [] publisher = new int[x/5];
        String [] State = {"Arizona","California","New York", "Florida","Texas","Oregon","Hawaii","Washington"};
        int sindex = 0;
        int publisherid = 0;
        for (int i = 0 ; i <x/5; i++) {
            publisherid = 10000 +rnd.nextInt(89999);
            sindex = rnd.nextInt(State.length);
            writer.println("INSERT INTO Publisher VALUES('"+publisherid+"',"
                    + " 'publisher"+i+"', ' City "+i+", "+State[sindex]+" ');");
            publisher[i] +=publisherid; 
        }
        writer.println("\n");

        //game
        int pindex =0;
        int [] game = new int[x/3];
        int gameid = 0;
        int price =0;
        for(int i = 0 ; i <x/3; i++) {
            gameid =  10000 +rnd.nextInt(89999);
            expM = 1 +rnd.nextInt(12);
            expd = 1 +rnd.nextInt(29);
            price = 2+rnd.nextInt(120);
            pindex = rnd.nextInt(publisher.length);
            writer.println("INSERT INTO Game VALUES('"+gameid+"', 'Game Title"+i+"',"
                    + " '2019-02-22', '2019-"+expM+"-"+expd+"',"+ price+".99, '"+publisher[pindex]+"');");
            game[i] += gameid;
        }
        writer.println("\n");

        //owns
        int gindex = 0;
        for(int i=0; i<x; i++) {
            gindex = rnd.nextInt(game.length);
            writer.println("INSERT INTO Owns VALUES('user"+i+"', '"+game[gindex]+"');");
        }
        writer.println("\n");


        //Developer
        int [] developerid = new int[x/5];
        int did = 0;
        int didinx =0;
        for (int i = 0 ; i <x/5; i++) {
            did =  100000 +rnd.nextInt(999999);

            writer.println("INSERT INTO Developer VALUES('"+did+"', 'Developer"+i+"', NULL);");
            developerid[i] += did;
        }
        writer.println("\n");

        //developer branch
        int[] bidarr = new int[x/3]; 
        int bidarrIndex = 0;
        int branchid = 0;
        for(int i = 0; i < x/5; i++) {
            didinx = rnd.nextInt(developerid.length);
            sindex = rnd.nextInt(State.length);
            branchid =  10000 +rnd.nextInt(99999);
            writer.println("INSERT INTO Developer_branch VALUES('"+developerid[i]+"', '"+branchid+"', "
                    + "'City"+i+ ", "+State[sindex]+"');");
            bidarr[bidarrIndex] += branchid;
            bidarrIndex++;
        }
        for(int i = 0; i <(x/3)-(x/5); i++) {
            didinx = rnd.nextInt(developerid.length);
            sindex = rnd.nextInt(State.length);
            branchid =  10000 +rnd.nextInt(99999);
            writer.println("INSERT INTO Developer_branch VALUES('"+developerid[didinx]+"', '"+branchid+"', "
                    + "'City"+i+ ", "+State[sindex]+"');");
            bidarr[bidarrIndex] += branchid;
            bidarrIndex++;
        }
        writer.println("\n");
        //

        //update and set
        for(int i = 0 ; i<x/5; i++){
            writer.println("UPDATE Developer SET mainBranch = '"+bidarr[i]+"' WHERE developerID = '"+developerid[i]+"';");
        }
        writer.println("\n");


        //develops
        for(int i =0; i < x/5; i++) {
            writer.println("INSERT INTO Develops VALUES('"+developerid[i]+"', '"+game[i]+"');");
        }
        writer.println("\n");

        //Genre
        String [] Genres = {"Role-playing","Fighting","Shooter","Action","Adventure"};

        writer.println("INSERT INTO Genre VALUES('Role-playing', 'The player controls the actions of a unique character.');");
        writer.println("INSERT INTO Genre VALUES('Fighting', 'Players control a character and engage in close combat with opponents.');");
        writer.println("INSERT INTO Genre VALUES('Shooter', 'The player controls a character and uses weapons, typically guns, to fight enemies.');");
        writer.println("INSERT INTO Genre VALUES('Action', 'The player must interact in physical challenges such as hand-eye coordination and reaction-time.');");
        writer.println("INSERT INTO Genre VALUES('Adventure', 'The player takes the role of a protagonist in a story that features exploration and puzzle-solving.');");

        writer.println("\n");

        //is_a
        int genreindx =0;
        for(int i = 0; i < x/3; i++) {

            genreindx = rnd.nextInt(Genres.length);
            writer.println("INSERT INTO Is_a VALUES('"+game[i]+"', '"+Genres[genreindx]+"');");
        }
        writer.println("\n");


        //review
        int rev = 0;
        int rev2= 0;
        int y = 0;

        for(int i = x/5 ; i <((x/5)*2); i++) {
            expM = 1 +rnd.nextInt(12);
            expd = 1 +rnd.nextInt(29);
            rev = 3+rnd.nextInt(6);
            rev2 = rnd.nextInt(9);
            writer.println("INSERT INTO Review VALUES('Review"+y+"', "+rev+"."+rev2+", '"+game[y]+"', 'user"+i+"', '2019-"+expM+"-"+expd+"');");
            y++;
        }

        writer.println("\n");

        //uses
        String [] console = {"Playstation 4", "Xbox One", "Nintendo Switch", "PC"}; 
        int cindex =0;
        for(int i = 0; i< x ; i++) {
            cindex =rnd.nextInt(console.length);
            writer.println("INSERT INTO Uses VALUES('user"+i+"', '"+console[cindex]+"');");
        }
        writer.println("\n");

        //available on
        for(int i = 0 ; i <x/3; i++) {
            cindex =rnd.nextInt(console.length);
            writer.println("INSERT INTO Available_on VALUES('"+game[i]+"', '"+console[cindex]+"');");
        }

        //
        writer.close();
    }
}
