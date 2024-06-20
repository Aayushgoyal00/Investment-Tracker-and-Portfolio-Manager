package collection;

import java.sql.*;

public class MutualFund implements sql_connection{

    public static void printAllMutualFundIds() {

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT ID,Name FROM mutualfund";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Available Mutual Fund IDs:\n");
            while (rs.next()) {
                System.out.println(rs.getInt("ID")+"\t"+rs.getString("Name"));
            }
            System.out.println("\n");
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void getMutualFundInfo(int fundId) {

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM mutualfund WHERE ID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, fundId);
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {
                System.out.println("Fund ID: " + rs.getInt("ID"));
                System.out.println("Fund Name: " + rs.getString("NAME"));
                System.out.println("NAV (Net Asset Value): " + rs.getDouble("NAV"));
                System.out.println("Risk Rating : " + rs.getString("RiskRating"));
                System.out.println("MIN SIP Amount Required : " + rs.getDouble("SIP"));

            }
            System.out.println("\n");

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getStocksInMutualFund(int fundId) {


        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String sql = "SELECT DISTINCT * FROM mutualfundstocks WHERE MutualFundID = '" + fundId + "'";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Stocks in this Mutual Fund :- \n");
            while (rs.next()) {
                System.out.println("Stock ID: " + rs.getString("StockID")+"\tStock quantity: "
                        +rs.getInt("Quantity"));
            }
            System.out.println("\n");
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int MutualFundInPortfolio(String user_name) throws SQLException {
        int a=0;
        Connection myConn = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM portfolio inner join mutualfund\n" +
                "on portfolio.MutualFundID=mutualfund.ID\n" +
                "where Username=?;";
        PreparedStatement myStmt = myConn.prepareStatement(sql);
        myStmt.setString(1, user_name);
        ResultSet rs = myStmt.executeQuery();

        // Print all mutual Fund in user's portfolio

        while(rs.next()) {
            a++;
            int mutualFundID = rs.getInt("ID");
            String mutualFund = rs.getString("Name");
            int Quantity = rs.getInt("MutualfundQuantity");
            if (Quantity!=0) {
                System.out.println("FundID: "+mutualFundID +"\t\t"+ mutualFund+"\t\tQuantity: " + Quantity);
            }
        }
        System.out.println();
        rs.close();
        myStmt.close();
        return a;
    }
}