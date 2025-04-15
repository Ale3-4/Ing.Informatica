package Objetos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdmin {
    private static String URL = "jdbc:mysql://localhost:3306/gourmet_radar?useSSL=false&serverTimezone=UTC";
    private static String USER = "admin_user";  // Usuario con privilegios ADMIN
    private static String PASSWORD = "admin_pass";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver JDBC");
            e.printStackTrace();
        }
    }

    public DatabaseAdmin(String s, String gourmet, String password) {
        URL=s;
        USER=gourmet;
        PASSWORD=password;
    }


    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Métodos para gestión de restaurantes
    public boolean addRestaurant(String nombre, int idBarrio, int idTipo, int idRango,
                                 String direccion, String telefono, String descripcion) {
        String sql = "INSERT INTO restaurantes (nombre, id_barrio, id_tipo, id_rango_precio, direccion, telefono, descripcion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setInt(2, idBarrio);
            stmt.setInt(3, idTipo);
            stmt.setInt(4, idRango);
            stmt.setString(5, direccion);
            stmt.setString(6, telefono);
            stmt.setString(7, descripcion);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateRestaurant(int idRestaurante, String nombre, int idBarrio, int idTipo,
                                           int idRango, String direccion, String telefono) {
        String sql = "UPDATE restaurantes SET nombre = ?, id_barrio = ?, id_tipo = ?, " +
                "id_rango_precio = ?, direccion = ?, telefono = ? WHERE id_restaurante = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setInt(2, idBarrio);
            stmt.setInt(3, idTipo);
            stmt.setInt(4, idRango);
            stmt.setString(5, direccion);
            stmt.setString(6, telefono);
            stmt.setInt(7, idRestaurante);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteRestaurant(int idRestaurante) {
        String sql = "DELETE FROM restaurantes WHERE id_restaurante = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRestaurante);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Métodos para gestión de usuarios
    public static boolean promoteToAdmin(int idUsuario) {
        String sql = "UPDATE usuarios SET tipo = 'admin' WHERE id_usuario = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los restaurantes (con más detalles)
    public static List<String[]> getAllRestaurants() {
        List<String[]> restaurants = new ArrayList<>();
        String sql = "SELECT r.*, b.nombre as barrio, t.nombre as tipo " +
                "FROM restaurantes r " +
                "JOIN barrios b ON r.id_barrio = b.id_barrio " +
                "JOIN tipos_comida t ON r.id_tipo = t.id_tipo";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String[] restaurantData = new String[8];
                restaurantData[0] = rs.getString("id_restaurante");
                restaurantData[1] = rs.getString("nombre");
                restaurantData[2] = rs.getString("barrio");
                restaurantData[3] = rs.getString("tipo");
                restaurantData[4] = rs.getString("direccion");
                restaurantData[5] = rs.getString("telefono");
                restaurantData[6] = rs.getString("descripcion");
                restaurantData[7] = rs.getString("id_rango_precio");

                restaurants.add(restaurantData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurants;
    }
}
