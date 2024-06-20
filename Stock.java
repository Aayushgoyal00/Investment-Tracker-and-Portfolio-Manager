package collection;
import java.sql.*;

public class Stock implements sql_connection {


    public void printAllStockIds() {


        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT StockID,StockName FROM stocks";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Available Stocks :-\n");
            while (rs.next()) {
                System.out.println("StockID :- " + rs.getString("StockID") + "\t\t StockName:- " + rs.getString("StockName"));
            }
            System.out.println();

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getStockInfo(String stockId) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM stocks WHERE StockID = '" + stockId + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Stock ID: " + rs.getString("StockID"));
                System.out.println("Stock Name: " + rs.getString("StockName"));
                System.out.println("Stock Price: " + rs.getString("StockPrice"));
                System.out.println("Market Cap: " + rs.getString("MarketCap"));
                System.out.println("PE Ratio: " + rs.getString("PE_Ratio"));
                System.out.println("Dividend Yield: " + rs.getString("DividendYield"));
                System.out.println("Sector: " + rs.getString("Sector"));
            }
            System.out.println("\n");

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllSector() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT Distinct Sector FROM stocks";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Available Sector:\n");
            while (rs.next()) {
                System.out.println(rs.getString("Sector"));
            }
            System.out.println("\n");
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printStockIdsBySector(String sector) {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT StockID,StockName FROM stocks WHERE Sector = '" + sector + "'";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Available Stock IDs in " + sector + " :-\n");
            while (rs.next()) {
                System.out.println("StockID:- " + rs.getString("StockID") + "\t\t" + "StockName:- " + rs.getString("StockName"));
            }
            System.out.println("\n");

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if stock ID and mutual fund ID are null, and stock quantity and mutual fund quantity are zero

    public void deleteNullEntriesInPortfolio(String user_name) throws SQLException {
        Connection myConn = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM portfolio WHERE ((Stockquantity IS NULL or stockquantity=0) AND ( MutualFundQuantity IS NULL or MutualFundQuantity=0)) and username=?";
        PreparedStatement deleteStmt = myConn.prepareStatement(sql);
        deleteStmt.setString(1, user_name);
        deleteStmt.executeUpdate();
    }

    // Display all stocks in user's portfolio

    public void StocksInPortfolio(String user_name) throws SQLException {
        Connection myConn = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM portfolio inner join stocks\n" +
                "on portfolio.StockID=stocks.StockID\n" +
                "where Username=?;";
        PreparedStatement myStmt = myConn.prepareStatement(sql);
        myStmt.setString(1, user_name);
        ResultSet rs = myStmt.executeQuery();

        // Print all stocks in user's portfolio

        while(rs.next()) {
            String stockID = rs.getString("StockID");
            int stockQuantity = rs.getInt("StockQuantity");
            int stockPrice = rs.getInt("StockPrice");
            if (stockID!=null && stockQuantity!=0) {
                System.out.println("Stock ID: " + stockID + "\t\tStockQuantity: " + stockQuantity + "\t\tStockPrice: $" + stockPrice);
            }
        }
        System.out.println();
    }
}
