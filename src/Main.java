
import com.sun.jdi.connect.spi.Connection;
import db.Conexion;

public class Main {
    public static void main(String[] args) {
        Conexion conexion = Conexion.getInstance();
        Connection conn = (Connection) conexion.getConnection();
        if (conn != null) {
            System.out.println("Conexión a la base de datos exitosa.");
        }
    }

    private static class SQLException {

        public SQLException() {
        }
    }
}
