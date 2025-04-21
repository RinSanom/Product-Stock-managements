import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[][] stock = new String[0][];
        String[] insertionHistory = new String[100]; // max 100 insertions
        int historyCount = 0;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        Scanner cs = new Scanner(System.in);

        int opt;
        do {
            System.out.println("""
                    ============================================================================
                    ||                  Welcome to Product Stock managements                  ||
                    ============================================================================
                    ||  Options:                                                              ||    
                    ||          [ 1. ] Set Up Stock with Catalogue                            ||
                    ||          [ 2. ] View Product in Stock                                  ||
                    ||          [ 3. ] Insert Product to Stock Catalogue                      ||
                    ||          [ 4. ] Update Product in Stock Catalogue by Product name      ||
                    ||          [ 5. ] Delete Product in Stock Catalogue by Name              ||
                    ||          [ 6. ] View Insertion History in Stock Catalogue              ||
                    ||          [ 7. ] Exit the program                                       ||
                    ||                                                                        ||
                    ||========================================================================||
                    """);
            System.out.print(" Pleace select the options:   ");


            while (true){
                if (cs.hasNextInt()){
                    opt = cs.nextInt();
                    break;
                } else {
                    System.err.println(" Invalid input! Please enter number only.");
                    cs.next();
                }

            }

            switch(opt) {
                case 1-> {
                    System.out.println("""
                            ==========================================
                            ||      Welcome to Stock set up         ||
                            ==========================================
                            """);
                    System.out.print("[ + ] Insert value of stock:  ");
                    int stockValue = new Scanner(System.in).nextInt();
                    stock = new String[stockValue][];

                    for( int i = 0; i< stockValue; i++ ){
                        System.out.print("Insert value for catalogues on stock ["+ (i + 1) + "] : ");
                        int catalogueValue = new Scanner(System.in).nextInt();
                        stock[i] = new String[catalogueValue];
                        for( int j= 0; j<catalogueValue; j++){
                            stock[i][j] = "EMPTY";
                        }
                    }
                    System.out.println("\n-------------- SET UP STOCK SUCCEEDED -------------");
                    for(int i = 0; i<stock.length; i++){
                        System.out.print(" stock:[" +( i+1 )+"] => | ");
                        for(int j = 0; j<stock[i].length; j++){
                            System.out.print("[" + (j + 1) + "- " + stock[i][j] + " ] ");
                        }
                        System.out.println();
                    }
                    System.out.println("-------------------------------------------------\n");

                }
                case 2-> {
                    System.out.println("""
                            ==========================================
                            ||            View your Stock           ||
                            ==========================================
                            """);
                    System.out.println();
                    if( stock.length == 0 ){
                        System.err.println("[!] No stock found. Please set up stock first!");
                    }else {
                        System.out.println("\n-------------- YOUR HAVE ["+ stock.length +"] STOCK -------------");
                        for(int i = 0; i<stock.length; i++){
                            System.out.print(" stock:[" +( i+1 )+"] => ");
                            for(int j = 0; j<stock[i].length; j++){
                                System.out.print("[" + (j + 1) + "- " + stock[i][j] + " ] ");
                            }
                            System.out.println();
                        }
                        System.out.println("-----------------------------------------------\n");
                    }
                }
                case 3 -> {
                    System.out.println("-------------- Insert Product ---------------");
                    if(stock.length == 0 ){
                        System.err.println("[!] No stock found. Please set up stock first!");
                        return;
                    }
                    System.out.print(" What number of stock that u want to insert ?  " );
                    for (int i =0; i<stock.length; i++){
                        System.out.print((i+1) +" | ");
                    }

                    System.out.println();

                    System.out.print(" Enter Stock Number That You want to insert : ");
                    int stockNumber = new Scanner(System.in).nextInt() - 1 ;

                    if (stockNumber < 0 || stockNumber>=stock.length){
                        System.err.println("[!] Invalid stock number.");
                        break;
                    }

                    boolean isFullStock = true;
                    for ( String item : stock[stockNumber]){
                        if (item.equalsIgnoreCase("EMPTY")) {
                            isFullStock = false;
                        }
                    }
                    if (isFullStock){
                        System.err.println("[!] Stock [" + (stockNumber + 1) + "] is full!");
                    }
                    System.out.println();

                    System.out.print(" Stock ["+ (stockNumber+1) + "] Have Available ["+ stock[stockNumber].length +"] Catalogue is : ");
                    for (int i =0; i<stock[stockNumber].length; i++ ) {
                      if(stock[stockNumber][i].equals("EMPTY")){
                          System.out.print ( "[" +(i+1) + "-" + stock[stockNumber][i] + "]  ");
                      }
                    }

                    System.out.println();

                    System.out.print("Chose Catalogue To Insert Product:");
                    int catalogueNumber = new Scanner(System.in).nextInt() - 1;



                    if (catalogueNumber < 0 || catalogueNumber >= stock[stockNumber].length) {
                        System.err.println("[!] Invalid catalogue index.");
                        break;
                    }
                    if(!stock[stockNumber][catalogueNumber].equalsIgnoreCase("EMPTY")){
                        System.err.println("Catalogue"+catalogueNumber +"already insert!!!!!");
                    }else {
                        System.out.print("Enter product name to insert: ");
                        String product = new Scanner(System.in).nextLine();
                        stock[stockNumber][catalogueNumber] = product;


                        insertionHistory[historyCount++] = "[ " + formattedDateTime + " ] Inserted '" + product + "' into Stock [" +(stockNumber+1)+ "] Catalogue ["+(catalogueNumber+1)+"]";


                        System.out.println("Product inserted successfully into Stock [" +(stockNumber+1) + "] Catalogue  ["+(catalogueNumber+1)+"]");
                    }
                    System.out.println("\n\n");
                }

                case 4 ->{
                    if(stock.length == 0 ){
                        System.err.println("[!] No stock found. Please set up stock first!");
                        return;
                    }

                    System.out.println("""
                            ==========================================
                            ||    Do You Want To Update Product ?  ||
                            ==========================================
                            """);

                    System.out.print("Please Insert Name Of product That You To Update : ");
                    String name = new Scanner(System.in).nextLine();

                    boolean isUpdata = false;
                    for(int i =0; i<stock.length; i++){
                        for(int j=0; j<stock[i].length; j++){
                          if (stock[i][j].equalsIgnoreCase(name)){
                              System.out.print("[*] [ "+ name + " ] Found in Stock [" + (i + 1) + "] Catalogue [" + (j + 1) + "]\n");
                              System.out.print("[+] Enter new product name: ");
                              String newName = new Scanner(System.in).nextLine();
                              stock[i][j] = newName;
                              System.out.println("[+] Product updated successfully!");

                              insertionHistory[historyCount++] = "[ " + formattedDateTime + " ] Updated '" + newName + "' into Stock [" +(i+1)+ "] Catalogue ["+(j+1)+"]";


                              isUpdata = true;
                          }
                        }
                    }

                    if (!isUpdata) {
                        System.err.println("  Product \"" + name + "\" not found in stock.");
                    }
                }

                case 5 -> {
                    if(stock.length == 0 ){
                        System.err.println("[!] No stock found. Please set up stock first!");
                        return;
                    }

                    System.out.println("""
                            ==========================================
                            ||    Do You Want To Deleted Product ?  ||
                            ==========================================
                            """);

                    System.out.print("Pleace Insert Name Of product That You To Delete: ");
                    String name = new Scanner(System.in).nextLine();


                    boolean isFound = false;
                    for(int i=0; i<stock.length; i++){
                        for(int j=0; j<stock[i].length; j++){
                            if(stock[i][j].equalsIgnoreCase(name)){
                                stock[i][j] = "EMPTY";
                                System.out.println("[ (: ] Product \"" + name + "\" deleted from Stock [" + (i + 1) + "] Catalogue [" + (j + 1) + "]");

                                insertionHistory[historyCount++] = "[ " + formattedDateTime + " ] Deleted '" + name + "' into Stock [" +(i+1)+ "] Catalogue ["+(j+1)+"]";


                                isFound = true;
                            }
                        }
                    }
                    if(!isFound){
                        System.err.println("⚠ Product \"" + name + "\" not found in any stock.");
                    }
                    System.out.println();
                 }

                case 6 -> {
                    System.out.println("-------------- Insertion History ---------------");
                    if (historyCount == 0) {
                        System.out.println("No history recorded yet.");
                    } else {
                        for (int i = 0; i < historyCount; i++) {
                            System.out.println((i + 1) + ". " + insertionHistory[i]);
                        }
                    }
                    System.out.println("-----------------------------------------------\n");
                }
                case 7 ->{
                    System.exit(0);
                }
            }
        } while (opt != 7);
    }
}
