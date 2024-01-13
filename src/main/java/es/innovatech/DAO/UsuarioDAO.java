    /*
    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
    */
    package es.innovatech.DAO;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;

    import es.innovatech.beans.Usuario;

    /**
     * Clase que implementa la interfaz IUsuariosDAO para gestionar los usuarios en la base de datos.
     * 
     * @author pedro
     */
    public class UsuarioDAO implements IUsuariosDAO {

        
        /**
         * {@inheritDoc}
         */
        @Override
        public void add(Usuario usuario) {
            Connection connection = null;
            String query = "INSERT INTO usuarios (nombre, apellidos, email, nif, password, telefono, direccion, codigoPostal, localidad, provincia, avatar) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                connection = ConnectionFactory.getConnection();
                connection.setAutoCommit(false);
                PreparedStatement ptmt = connection.prepareStatement(query);
                ptmt.setString(1, usuario.getNombre());
                ptmt.setString(2, usuario.getApellidos());
                ptmt.setString(3, usuario.getEmail());
                ptmt.setString(4, usuario.getNIF());
                ptmt.setString(5, usuario.getPassword());
                ptmt.setInt(6, usuario.getTelefono());
                ptmt.setString(7, usuario.getDireccion());
                ptmt.setInt(8, usuario.getCodigoPostal());
                ptmt.setString(9, usuario.getLocalidad());
                ptmt.setString(10, usuario.getProvincia());
                ptmt.setString(11, usuario.getAvatar());
                ptmt.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                if(connection != null){
                    try{
                        e.printStackTrace();
                        connection.rollback();
                    }catch(SQLException rollbackException){
                        rollbackException.printStackTrace();
                    }
                }
            }finally{
                this.closeConnection();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void update(Usuario usuario) {
            Connection connection = null;   
            String query = "UPDATE usuarios SET nombre = ?, apellidos = ?, nif = ?, telefono = ?, direccion = ?, codigoPostal = ?, localidad = ?, provincia = ?, avatar = ?, password = ?, verificado = ? WHERE email = ?";
            try {
                connection = ConnectionFactory.getConnection();
                connection.setAutoCommit(false);
                PreparedStatement ptmt = connection.prepareStatement(query);
                ptmt.setString(1, usuario.getNombre());
                ptmt.setString(2, usuario.getApellidos());
                ptmt.setString(3, usuario.getNIF());
                ptmt.setInt(4, usuario.getTelefono());
                ptmt.setString(5, usuario.getDireccion());
                ptmt.setInt(6, usuario.getCodigoPostal());
                ptmt.setString(7, usuario.getLocalidad());
                ptmt.setString(8, usuario.getProvincia());
                ptmt.setString(9, usuario.getAvatar());
                ptmt.setString(10, usuario.getPassword());
                ptmt.setBoolean(11, usuario.isVerificado());
                ptmt.setString(12, usuario.getEmail());
                ptmt.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                if(connection != null){
                    try{
                        e.printStackTrace();
                        
                        connection.rollback();
                    }catch(SQLException rollbackException){
                        rollbackException.printStackTrace();
                    }
                }
            }finally{
                this.closeConnection();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Usuario getUsuario(String password, String email) {
            Connection connection = null;
            Usuario usuario = new Usuario();
            String query = "SELECT * FROM usuarios WHERE password = ? AND email = ?";
            try {
                connection = ConnectionFactory.getConnection();
                PreparedStatement ptmt = connection.prepareStatement(query);
                ptmt.setString(1, password);
                ptmt.setString(2, email);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    usuario.setId((short) rs.getInt("idusuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setNIF(rs.getString("nif"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setTelefono(rs.getInt("telefono"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setCodigoPostal(rs.getInt("codigoPostal"));
                    usuario.setLocalidad(rs.getString("localidad"));
                    usuario.setProvincia(rs.getString("provincia"));
                    usuario.setVerificado(rs.getBoolean("verificado"));
                    usuario.setAvatar(rs.getString("avatar"));
                    if(rs.getDate("ultimoAcceso") != null){
                        usuario.setUltimoAcceso(rs.getDate("ultimoAcceso"));
                    }   
                    return usuario;
                }
                this.closeConnection(); 
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeConnection();
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void delete(String email) {
            Connection connection = null;
            String query = "DELETE FROM usuarios WHERE email = ?";

            try {
                connection = ConnectionFactory.getConnection();
                connection.setAutoCommit(false);
                PreparedStatement ptmt = connection.prepareStatement(query);
                ptmt.setString(1, email);
                ptmt.executeUpdate();
                connection.commit();
                this.closeConnection(); 
            } catch (SQLException e) {
                e.printStackTrace();
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException rollbackException) {
                        rollbackException.printStackTrace();
                    }
                }
            } finally {
                this.closeConnection();
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Usuario getUsuarioPorId(short id){
            Connection connection = null;
            Usuario usuario = new Usuario();
            try {
            connection = ConnectionFactory.getConnection();
                String query = "SELECT * FROM usuarios WHERE idusuario = ?";
                PreparedStatement ptmt = connection.prepareStatement(query);
                ptmt.setInt(1, id);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    usuario.setId((short) rs.getInt("idusuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setNIF(rs.getString("nif"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setTelefono(rs.getInt("telefono"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setCodigoPostal(rs.getInt("codigoPostal"));
                    usuario.setLocalidad(rs.getString("localidad"));
                    usuario.setProvincia(rs.getString("provincia"));
                    usuario.setVerificado(rs.getBoolean("verificado"));
                    usuario.setAvatar(rs.getString("avatar"));
                    if(rs.getDate("ultimoAcceso") != null){
                        usuario.setUltimoAcceso(rs.getDate("ultimoAcceso"));
                    }
                    return usuario;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeConnection();
            }
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Usuario getUsuariobyEmail(String email){
            Connection connection = null;
            Usuario usuario = null;
            String query = "SELECT * FROM usuarios WHERE email = ?";
            try {
                connection = ConnectionFactory.getConnection();
                PreparedStatement ptmt = connection.prepareStatement(query);
                ptmt.setString(1, email);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId((short) rs.getInt("idusuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setNIF(rs.getString("nif"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setTelefono(rs.getInt("telefono"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setCodigoPostal(rs.getInt("codigoPostal"));
                    usuario.setLocalidad(rs.getString("localidad"));
                    usuario.setProvincia(rs.getString("provincia"));
                    usuario.setVerificado(rs.getBoolean("verificado"));
                    usuario.setAvatar(rs.getString("avatar"));
                    if(rs.getDate("ultimoAcceso") != null){
                        usuario.setUltimoAcceso(rs.getDate("ultimoAcceso"));
                    }
                    return usuario;
                }
                this.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeConnection();
            }
            return usuario;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public short getLastIdUsuario(){
            Connection connection = null;
            String query = "SELECT LAST_INSERT_ID()";
            short idUsuario = 0;
            try {
                connection = ConnectionFactory.getConnection();
                PreparedStatement ptmt = connection.prepareStatement(query);
                ResultSet rs = ptmt.executeQuery();
                while (rs.next()) {
                    idUsuario = (short) rs.getInt("LAST_INSERT_ID()");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeConnection();
            }
            return idUsuario;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void closeConnection() {
            ConnectionFactory.closeConnection();
        }

    }
