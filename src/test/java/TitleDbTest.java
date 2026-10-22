import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TitleDbTest {

    @Test
    public void activeTitlesCountMatches() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sdet.db");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("Select count(*) from titles where status='Active'")) {

            Assert.assertTrue(rs.next(), "FAIL: query returned no rows");
            Assert.assertEquals(rs.getInt(1), 2, "FAIL: Active title count mismatch");

        } catch (Exception e) {
            throw new RuntimeException("Database query failed", e);

        }

    }

    @DataProvider(name = "isbnSearches")
    public Object[][] isbnSearches() {
        return new Object[][] {
                { "978-1-4028-9462-6", "KolorBear Adventures" },
                { "978-0-306-40615-2", "Luna The Unicorn" }
        };
    }

    @Test(dataProvider = "isbnSearches")
    public void titleFoundByIsbn(String isbn, String expectedTitle) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:sdet.db");
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT title_name FROM titles WHERE isbn = ?")) {

            pstmt.setString(1, isbn); // '1' = first '?'; value bound as DATA, not SQL

            try (ResultSet rs = pstmt.executeQuery()) {
                Assert.assertTrue(rs.next(), "FAIL: no title found for ISBN " + isbn);
                Assert.assertEquals(rs.getString("title_name"), expectedTitle,
                        "FAIL: wrong title for ISBN " + isbn);
                Assert.assertFalse(rs.next(),
                        "Duplicate records found for ISBN " + isbn);

            }
        } catch (Exception e) {
            throw new RuntimeException("Database query failed", e);
        }

    }
}
