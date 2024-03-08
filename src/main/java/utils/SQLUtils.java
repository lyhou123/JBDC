package utils;

public class SQLUtils {
    public  static class PersonSQL{
        public static final String GELALLPERSON = """
            select * from my_database;
            """;
        public static final String INSERTNEWPERSON = """
                INSERT INTO my_database (fullname,gender,email,address) VALUES(?,?,?,?);
                """;
        public  static final String DELETEPERSONBYID = """
                delete from my_database where id = ?
                """;

        public  static final String UPDATEPERSON = """
                update my_database set  fullname=?,gender=?,email=?,address=?
                where id = ?
                """;
    }
    public  static class SystemUserSQL{
        public static final String LOGIN= """
                SELECT * FROM "user" WHERE username = ? AND password = ?
                """;
        public static final String SIGNUP= """
                 INSERT INTO "user" (username,password) VALUES(?,?);
                """;
        public static final String CHECK_USERNAME= """
                 SELECT username FROM "user" WHERE username = ?;
                """;

    }
}
