package MP7.src;

import java.util.Scanner;
import java.io.PrintWriter;

public class BlockChainDriver {
    public static void main(String[] args){
        if(args.length > 1){
            //if the user puts more args than just the dollar amount, give an error
            return;
        } else {
            int amount = Integer.valueOf(args[0]);
            Scanner scanner = new Scanner(System.in);
            PrintWriter pen = new PrintWriter(System.out, true);

            //creates a blockChain with the given amount as the starting balance
            BlockChain blockChain = new BlockChain(amount);

            //read a user-inputted expression
            String userInput = scanner.nextLine();

            while(! userInput.equals("quit")){
                //keep reading lines
                switch (userInput) {
                    case "mine": blockChain.mine(amount);
                                    break;
                    case "append": blockChain.append(); //needs a blk
                                    break;
                    case "remove": blockChain.removeLast();
                                    break;
                    case "check": blockChain.isValidBlockChain();
                                    break;
                    case "report": blockChain.printBalance();
                                    break;
                    case "help": pen.println(menu());
                    case "quit": break;

                }
            }

        }

        //scan.close();
    }//main

    public static String menu(){
        return String.join("Valid commands:",
        "mine: discovers the nonce for a given transaction '\n'",
        "append: appends a new block onto the end of the chain \n",
        "remove: removes the last block from the end of the chain \n",
        "check: checks that the block chain is valid \n",
        "report: reports the balances of Alexis and Blake \n",
        "help: prints this list of commands '\n'",
        "quit: quits the program");
    }
}//blockDriver
