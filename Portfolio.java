package collection;

import java.sql.*;
import java.util.Scanner;


public class Portfolio implements sql_connection {

    Scanner scanner = new Scanner(System.in);


    Stock stock;
    MutualFund mutualFund;


    public Portfolio() {
        this.stock = new Stock();
        this.mutualFund = new MutualFund();
    }

    public void viewPortfolio(String user_name)  {

        try {
            stock.deleteNullEntriesInPortfolio(user_name);
            stock.StocksInPortfolio(user_name);
            mutualFund.MutualFundInPortfolio(user_name);

        }catch (SQLException e) {
            e.printStackTrace();}
    }


    public void addStock(String user_name) {
        try {
            Connection myConn = DriverManager.getConnection(url, username, password);
            stock.deleteNullEntriesInPortfolio(user_name);
            stock.printAllStockIds();

            // Ask user to select a stock and quantity

            System.out.println("Enter the Stock ID you want to add to your portfolio:");
            String selectedStockID1 = scanner.nextLine();
            System.out.println("Enter the quantity of the stock you want to add:");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            // Check if stock ID already exists in the portfolio for the user

            String checkSql = "SELECT * FROM portfolio WHERE Username = ? AND StockID = ?";
            PreparedStatement checkStmt = myConn.prepareStatement(checkSql);
            checkStmt.setString(1, user_name);
            checkStmt.setString(2, selectedStockID1);
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                // If stock ID exists, update the quantity

                String updateSql = "UPDATE portfolio SET StockQuantity = StockQuantity + ? WHERE Username = ? AND StockID = ?";
                PreparedStatement updateStmt = myConn.prepareStatement(updateSql);
                updateStmt.setInt(1, quantity);
                updateStmt.setString(2, user_name);
                updateStmt.setString(3, selectedStockID1);
                updateStmt.executeUpdate();
            } else {
                // If stock ID does not exist, insert a new entry
                String insertSql = "INSERT INTO portfolio (Username, StockID, StockQuantity) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = myConn.prepareStatement(insertSql);
                insertStmt.setString(1, user_name);
                insertStmt.setString(2, selectedStockID1);
                insertStmt.setInt(3, quantity);
                insertStmt.executeUpdate();
            }

            System.out.println("Stock added to portfolio successfully!\n");
            myConn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeStock(String user_name) {

        try {
            stock.deleteNullEntriesInPortfolio(user_name);
            Connection myConn = DriverManager.getConnection(url, username, password);
            stock.StocksInPortfolio(user_name);

            // Ask user to select a stock and quantity to remove

            System.out.println("Enter the Stock ID of the stock you want to remove from your portfolio:");
            String selectedStockID_toRemove = scanner.nextLine();
            System.out.println("Enter the quantity of the stock you want to remove:");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            // Check if user has enough of the selected stock in their portfolio

            String sql1 = "SELECT StockQuantity FROM portfolio WHERE Username = ? AND StockID = ?";
            PreparedStatement checkStmt = myConn.prepareStatement(sql1);
            checkStmt.setString(1,user_name);
            checkStmt.setString(2, selectedStockID_toRemove);
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                int availableQuantity = checkRs.getInt("StockQuantity");
                if (quantity > availableQuantity) {
                    System.out.println("Error: You do not have enough of the selected stock in your portfolio.");
                    return;
                }
            }

            // Remove selected stock and quantity from user's portfolio

            String sql2 = "UPDATE portfolio SET StockQuantity = StockQuantity - ? WHERE Username = ? AND StockID = ?";
            PreparedStatement pstmt = myConn.prepareStatement(sql2);
            pstmt.setInt(1, quantity);
            pstmt.setString(2, user_name);
            pstmt.setString(3, selectedStockID_toRemove);
            pstmt.executeUpdate();

            System.out.println("Stock removed from portfolio successfully!\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMutualFund(String user_name) {
        try {
            stock.deleteNullEntriesInPortfolio(user_name);
            Connection myConn = DriverManager.getConnection(url, username, password);
            mutualFund.printAllMutualFundIds();

            // Ask user to select a mutual fund and quantity
            System.out.println("Enter the Mutual Fund ID you want to add to your portfolio:");
            int selectedMutualFundID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the quantity you want to add:");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            // Check if mutual fund ID already exists in the portfolio for the user
            String checkSql = "SELECT * FROM portfolio WHERE Username = ? AND MutualFundID = ?";
            PreparedStatement checkStmt = myConn.prepareStatement(checkSql);
            checkStmt.setString(1, user_name);
            checkStmt.setInt(2, selectedMutualFundID);
            ResultSet checkRs = checkStmt.executeQuery();

            if (checkRs.next()) {
                // If mutual fund ID exists, update the quantity
                String updateSql = "UPDATE portfolio SET MutualFundQuantity = MutualFundQuantity + ? WHERE Username = ? AND MutualFundID = ?";
                PreparedStatement updateStmt = myConn.prepareStatement(updateSql);
                updateStmt.setInt(1, quantity);
                updateStmt.setString(2, user_name);
                updateStmt.setInt(3, selectedMutualFundID);
                updateStmt.executeUpdate();
            } else {
                // If mutual fund ID does not exist, insert a new entry
                String insertSql = "INSERT INTO portfolio (Username, MutualFundID, MutualFundQuantity) VALUES (?, ?, ?)";
                PreparedStatement insertStmt = myConn.prepareStatement(insertSql);
                insertStmt.setString(1, user_name);
                insertStmt.setInt(2, selectedMutualFundID);
                insertStmt.setInt(3, quantity);
                insertStmt.executeUpdate();
            }

            System.out.println("Mutual Fund added to portfolio successfully!\n");

            myConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //
    public void RemoveMutualFund(String user_name) {
        try {
            stock.deleteNullEntriesInPortfolio(user_name);
            Connection myConn = DriverManager.getConnection(url, username, password);
            mutualFund.MutualFundInPortfolio(user_name);
            // Ask user to select a mutual fund and quantity to remove
            System.out.println("Enter the Mutual Fund ID you want to remove from your portfolio:");
            int selectedMutualFundID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter the quantity you want to remove:");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            // Check if user has enough of the selected mutual fund in their portfolio
            String sql1 = "SELECT MutualFundQuantity FROM portfolio WHERE Username = ? AND MutualFundID = ?";
            PreparedStatement checkStmt = myConn.prepareStatement(sql1);
            checkStmt.setString(1,user_name);
            checkStmt.setInt(2, selectedMutualFundID);
            ResultSet checkRs = checkStmt.executeQuery();
            if (checkRs.next()) {
                int availableQuantity = checkRs.getInt("MutualFundQuantity");
                if (quantity > availableQuantity) {
                    System.out.println("Error: You do not have enough of the selected mutual fund in your portfolio.");
                    return;
                }
            }
            // Remove selected mutual fund and quantity from user's portfolio
            String sql2 = "UPDATE portfolio SET MutualFundQuantity = MutualFundQuantity - ? WHERE Username = ? AND MutualFundID = ?";
            PreparedStatement pstmt = myConn.prepareStatement(sql2);
            pstmt.setInt(1, quantity);
            pstmt.setString(2, user_name);
            pstmt.setInt(3, selectedMutualFundID);
            pstmt.executeUpdate();
            System.out.println("Mutual Fund removed from portfolio successfully!\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


