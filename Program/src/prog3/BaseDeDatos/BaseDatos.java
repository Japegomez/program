import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatos {
	public static void main(String[] args) {
		BD.init("Examen.bd");
		BD.crearTabla();
		BD.close();
		}
	
	public static class BD{

		private static Connection conexion;
		public static boolean init(String nombre) {
			try {
				Class.forName("org.sqlite.JDBC");  // Carga la clase de BD para sqlite
				conexion = DriverManager.getConnection("jdbc:sqlite:" + nombre );
				return true;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
		public static void close() {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		public static void crearTabla() {
			try {
				Statement stat = conexion.createStatement();
				stat.setQueryTimeout(30);
				String sql = "create table if not exists Examen (id integer primary key autoincrement,"
						+ "nombre varchar(50));";
				stat.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
