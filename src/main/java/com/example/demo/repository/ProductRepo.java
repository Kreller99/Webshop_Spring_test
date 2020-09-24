package com.example.demo.repository;

import com.example.demo.model.Product;
import com.example.demo.util.DatabaseConnectionManager;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ProductRepo implements ICrudRepo{
    private Connection conn;

    private List<Product> products = new ArrayList<>();

    public ProductRepo(){
        this.conn = DatabaseConnectionManager.getDatabaseConnection();
    }

    public List<Product> readAll(){
        try{
           PreparedStatement ps = conn.prepareStatement("SELECT * FROM product");
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               Product tempProduct = new Product();
               tempProduct.setId(rs.getInt(1));
               tempProduct.setName(rs.getString(2));
               tempProduct.setPrice(rs.getDouble(3));
               tempProduct.setDescription(rs.getString(4));
               products.add(tempProduct);
           }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public boolean create(Product product){
        String sql = "INSERT INTO product(name, price, description) VALUES(?,?,?)";

        try{
            PreparedStatement productCreation = conn.prepareStatement(sql);

            productCreation.setString(1, product.getName());
            productCreation.setDouble(2, product.getPrice());
            productCreation.setString(3, product.getDescription());
            int insertedRows = productCreation.executeUpdate();
            if(insertedRows > 0){
                System.out.println("Et produkt er blevet oprettet!");
            }

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return true;
    }

    @Override
    public Product read(int id){
        Product productReturn = new Product();
        try{
            PreparedStatement getProduct = conn.prepareStatement("SELECT * FROM product WHERE id=" + id);
            ResultSet rs = getProduct.executeQuery();
            while(rs.next()){
                productReturn.setId(rs.getInt(1));
                productReturn.setName(rs.getString(2));
                productReturn.setPrice(rs.getDouble(3));
                productReturn.setDescription(rs.getString(4));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return productReturn;
    }

    @Override
    public boolean update(Product product){
        String sql = "UPDATE product SET name=?, price=?, description=? WHERE idProduct=" + product.getId();

        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Et produkt er blevet opdateret!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id){
        String sql = "DELETE FROM product WHERE id=?";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            int deletedRows = preparedStatement.executeUpdate();
            if(deletedRows > 0){
                System.out.println("Et produkt er blevet slettet fra varelisten");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
