package org.big.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.big.common.DBConnection;
import org.big.dto.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    public void insertProduct(Product product) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "INSERT INTO product (p_id, p_name, p_price, p_category, p_description, p_releaseDate, p_soldout, p_filename) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?,)";

        try {
            conn = DBConnection.getConnection();  // DB 연결
            pstmt = conn.prepareStatement(sql);

            // 쿼리 파라미터 설정
            pstmt.setString(1, product.getProductId());
            pstmt.setString(2, product.getName());
            pstmt.setInt(3, product.getPrice());
            pstmt.setString(4, product.getCategory());
            pstmt.setString(5, product.getDescription());
            pstmt.setString(6, product.getReleaseDate());
            pstmt.setLong(7, product.getSoldout());
            pstmt.setString(8, product.getFilename());

            // 실행
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();  // 예외 처리
        } finally {
            // 자원 해제
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Product getProductById(String productId) {
        String sql = "SELECT * FROM product WHERE p_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
            	Product product = new Product();
            	product.setProductId(rs.getString("p_id"));
            	product.setName(rs.getString("p_name"));
            	product.setPrice(rs.getInt("p_price"));
            	product.setCategory(rs.getString("p_category"));
            	product.setDescription(rs.getString("p_description"));
            	product.setReleaseDate(rs.getString("p_releaseDate"));
            	product.setSoldout(rs.getLong("p_soldout"));
            	product.setFilename(rs.getString("b_filename"));

                return product;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 책 정보 업데이트 메서드
    public void updateProduct(Product product) {
        String sql = "UPDATE product SET p_name=?, p_price=?, p_category=?, p_description=?, p_releaseDate=?, p_soldout=?, p_filename=? WHERE p_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
            pstmt.setString(3, product.getCategory());
            pstmt.setString(4, product.getDescription());
            pstmt.setString(5, product.getReleaseDate());
            pstmt.setLong(6, product.getSoldout());
            pstmt.setString(7, product.getFilename());
            pstmt.setString(8, product.getProductId());

            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
