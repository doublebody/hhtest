package com.yunjiacloud.nj.httpserver.proxy.biz;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Statement;


public class TraceLogRecorder {

    public static void saveLog(DataSource dataSource, String name) {
        Statement stmt = null;
        ResultSet rs = null;
        try {

//            stmt = createConnection(dataSource).createStatement();
//            rs = stmt.executeQuery(sql);

            // populate user object
            
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
//            closeQuitely(rs);
//            closeQuitely(stmt);
        }
    }
}
