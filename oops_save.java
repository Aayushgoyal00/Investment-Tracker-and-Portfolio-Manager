import java.util.*;
import java.util.Scanner;
import collection.*;


public class oops_save {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = null;
        String username = null;
        boolean isLoggedIn = true;

        System.out.println("\n************* Welcome to Investment Tracker *************\n");


        boolean isLoginSuccessful = false;
        boolean main_entry = false;
            while (!main_entry) {
        try {
            System.out.println("Are you an existing user or a new user?");
            System.out.println("Type:- 1 For existing user\nType:- 2 For new user ");
                String entry = scanner.nextLine();
                UserManager usermanager = new UserManager();
                if (Objects.equals(entry, "1")) {
                    main_entry = true;
                    System.out.println("Enter username: ");
                    username = scanner.nextLine();

                    System.out.println("Enter password: ");
                    String password = scanner.nextLine();

                    name = usermanager.loginUser(username, password);
                    if (name == null) {
                        throw new IllegalArgumentException("Username or password is incorrect.");
                    } else {
                        isLoginSuccessful = true;
                    }
                } else if (Objects.equals(entry, "2")) {
                    main_entry = true;
                    System.out.println("Type your name:-");
                    name = scanner.nextLine();
                    System.out.println("Type a new username:-");
                    username = scanner.nextLine();
                    System.out.println("Type your password:-");
                    String password = scanner.nextLine();
                    String correctUsername = usermanager.correct_user(username);
                    boolean new_user = usermanager.registerUser(correctUsername, password, name);
                    if (new_user) {
                        isLoginSuccessful = true;
                        username = correctUsername;
                        usermanager.create_newUserInSQL(correctUsername, password, name);
                    } else {
                        throw new InputMismatchException("File is not present.");
                    }
                } else {
                    isLoggedIn = false;
                    System.out.println("Invalid option selected.");
//                    throw new IllegalArgumentException("Invalid option selected.");
                }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException en) {
            System.out.println("An error occurred: " + en.getMessage());
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

        if (isLoginSuccessful) {
            System.out.println("************  Welcome " + name + "  ************\n");}

        while (isLoggedIn) {
            if (isLoginSuccessful) {
                System.out.println("""
                        Type the index number to select options.
                        1) Portfolio
                        2) View Mutual Fund Information
                        3) View Stock Information
                        4) Log Out""");

                int num = scanner.nextInt();

                try {
                    switch (num) {
                        case 1:
                            Portfolio portfolio = new Portfolio();
                            boolean temp=true;
                            while (temp) {
                                System.out.println("Please select an option:");
                                System.out.println("1. View portfolio");
                                System.out.println("2. Add stock");
                                System.out.println("3. Remove stock");
                                System.out.println("4. Add Mutual Fund");
                                System.out.println("5. Remove Mutual Fund");
                                System.out.println("6. Exit");

                                int option = scanner.nextInt();
                                scanner.nextLine();  // Consume newline left-over

                                switch (option) {
                                    case 1:
                                        portfolio.viewPortfolio(username);
                                        break;
                                    case 2:
                                        portfolio.addStock(username);
                                        break;
                                    case 3:
                                        portfolio.removeStock(username);
                                        break;
                                    case 4:
                                        portfolio.addMutualFund(username);
                                        break;
                                    case 5:
                                        portfolio.RemoveMutualFund(username);
                                        break;
                                    case 6:
                                        System.out.println("Exiting... \nReturning to Main Menu...\n");
                                        temp=false;
                                        break;
                                    default:
                                        System.out.println("Invalid option. Please try again.");
                                }
                            }
                            break;
                        case 2:
                            MutualFund mutualFund = new MutualFund();
                            MutualFund.printAllMutualFundIds();
                            System.out.println("Enter the mutual fund ID :");
                            int fundId = scanner.nextInt();
                            MutualFund.getMutualFundInfo(fundId);

                            System.out.println("Do you want to see the stock inside that Mutual fund ?\n" +
                                    "1) Yes \t2) No &\t Return to Main Menu....");
                            int num2 = scanner.nextInt();
                            switch (num2) {
                                case 1:
                                    mutualFund.getStocksInMutualFund(fundId);
                                    break;
                                case 2:
                                    break;
                                default:
                                    throw new ArithmeticException("Input doesn't match with the above indexes. ");

                            }
                            break;

                        case 3:
                            Stock stock = new Stock();
                            System.out.println("Do you want Information of stock (sector wise) ?\n" +
                                    "1) For Sector wise \t2) For Individual stock ");
                            int num3 = scanner.nextInt();
                            if (num3 == 1) {
                                stock.printAllSector();
                                System.out.println("Enter the sector from which You want to get stock information");
                                String sector = scanner.next();
                                stock.printStockIdsBySector(sector);
                                System.out.println("Enter the stock ID:");
                                String stockId = scanner.next();
                                stock.getStockInfo(stockId);
                            } else if (num3 == 2) {
                                stock.printAllStockIds();
                                System.out.println("Enter the stock ID:");
                                String stockId = scanner.next();
                                stock.getStockInfo(stockId);

                            } else {
                                throw new ArithmeticException("Input doesn't match with the above indexes. ");
                            }
                            break;
                        case 4:
                            // Log out
                            isLoggedIn = false;
                            System.out.println("Logged out successfully.");
                            break;

                        default:
                            System.out.println("Invalid option. Please try again.");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Username or password is incorrect.");
                isLoggedIn = false;
            }
        }
        scanner.close();
    }
}
