package Objetos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUser {
    private static final String URL = "jdbc:mysql://localhost:3306/gourmet_racar?useSSL=false&serverTimezone=UTC";
    private static final String USER = "app_user";  // Usuario con privilegios limitados
    private static final String PASSWORD = "user_pass";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC");
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Métodos de consulta para usuarios normales
    public static List<String[]> searchRestaurants(Integer idBarrio, Integer idTipo, Integer idRango, Integer minRating) {
        List<String[]> restaurants = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT r.id_restaurante, r.nombre, b.nombre as barrio, t.nombre as tipo, " +
                        "p.rango as precio, AVG(v.valoracion) as rating " +
                        "FROM restaurantes r " +
                        "JOIN barrios b ON r.id_barrio = b.id_barrio " +
                        "JOIN tipos_comida t ON r.id_tipo = t.id_tipo " +
                        "JOIN precios p ON r.id_rango_precio = p.id_rango " +
                        "LEFT JOIN valoraciones v ON r.id_restaurante = v.id_restaurante " +
                        "WHERE 1=1");

        if (idBarrio != null) {
            sql.append(" AND r.id_barrio = ").append(idBarrio);
        }
        if (idTipo != null) {
            sql.append(" AND r.id_tipo = ").append(idTipo);
        }
        if (idRango != null) {
            sql.append(" AND r.id_rango_precio = ").append(idRango);
        }

        sql.append(" GROUP BY r.id_restaurante");

        if (minRating != null) {
            sql.append(" HAVING AVG(v.valoracion) >= ").append(minRating);
        }

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql.toString())) {

            while (rs.next()) {
                String[] restaurantData = new String[6];
                restaurantData[0] = rs.getString("id_restaurante");
                restaurantData[1] = rs.getString("nombre");
                restaurantData[2] = rs.getString("barrio");
                restaurantData[3] = rs.getString("tipo");
                restaurantData[4] = rs.getString("precio");
                restaurantData[5] = rs.getString("rating");

                restaurants.add(restaurantData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    public static boolean addRating(int idUsuario, int idRestaurante, int valoracion, String comentario) {
        String sql = "INSERT INTO valoraciones (id_usuario, id_restaurante, valoracion, comentario) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idRestaurante);
            stmt.setInt(3, valoracion);
            stmt.setString(4, comentario);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String[]> getRestaurantDetails(int idRestaurante) {
        List<String[]> details = new ArrayList<>();
        String sql = "SELECT r.nombre, b.nombre as barrio, t.nombre as tipo, " +
                "p.rango as precio, r.direccion, r.telefono, r.descripcion, " +
                "AVG(v.valoracion) as rating " +
                "FROM restaurantes r " +
                "JOIN barrios b ON r.id_barrio = b.id_barrio " +
                "JOIN tipos_comida t ON r.id_tipo = t.id_tipo " +
                "JOIN precios p ON r.id_rango_precio = p.id_rango " +
                "LEFT JOIN valoraciones v ON r.id_restaurante = v.id_restaurante " +
                "WHERE r.id_restaurante = ? " +
                "GROUP BY r.id_restaurante";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRestaurante);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String[] restaurantDetails = new String[8];
                    restaurantDetails[0] = rs.getString("nombre");
                    restaurantDetails[1] = rs.getString("barrio");
                    restaurantDetails[2] = rs.getString("tipo");
                    restaurantDetails[3] = rs.getString("precio");
                    restaurantDetails[4] = rs.getString("direccion");
                    restaurantDetails[5] = rs.getString("telefono");
                    restaurantDetails[6] = rs.getString("descripcion");
                    restaurantDetails[7] = rs.getString("rating");

                    details.add(restaurantDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
    }

    public static List<String[]> getRestaurantRatings(int idRestaurante) {
        List<String[]> ratings = new ArrayList<>();
        String sql = "SELECT u.nombre as usuario, v.valoracion, v.comentario, v.fecha " +
                "FROM valoraciones v " +
                "JOIN usuarios u ON v.id_usuario = u.id_usuario " +
                "WHERE v.id_restaurante = ? " +
                "ORDER BY v.fecha DESC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRestaurante);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String[] ratingData = new String[4];
                    ratingData[0] = rs.getString("usuario");
                    ratingData[1] = rs.getString("valoracion");
                    ratingData[2] = rs.getString("comentario");
                    ratingData[3] = rs.getString("fecha");

                    ratings.add(ratingData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratings;
    }
}