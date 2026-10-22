import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbSetup {

    public static void main(String[] args) throws Exception {
        // try-with-resources: the 'try (...)' line auto-closes the Connection
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sdet.db");
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS titles (
                    id INTEGER PRIMARY KEY,
                    title_name TEXT,
                    isbn TEXT,
                    status TEXT
                )
                """);

            stmt.execute("DELETE FROM titles"); // clean slate for repeatable runs
            stmt.execute("INSERT INTO titles (title_name, isbn, status) VALUES ('KolorBear Adventures', '978-1-4028-9462-6', 'Active')");
            stmt.execute("INSERT INTO titles (title_name, isbn, status) VALUES ('Luna The Unicorn', '978-0-306-40615-2', 'Draft')");
            stmt.execute("INSERT INTO titles (title_name, isbn, status) VALUES ('Travel Puzzles', '978-1-86000-0001', 'Active')");

            System.out.println("Database ready: sdet.db");
        }
    }
}
