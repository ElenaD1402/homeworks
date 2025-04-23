package org.elena.test2.utils;

import java.io.File;

public class ConnectionStringUtil {
    public static String getConnectionString() {
        File file = new File("src/UsersDatabase.db");
        if (file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append("jdbc");
            sb.append(":");
            sb.append("sqlite");
            sb.append(":");
            sb.append(file.getAbsolutePath());
            return sb.toString();
        } else {
            System.out.println("Database is not found");
            return "";
        }
    }
}
