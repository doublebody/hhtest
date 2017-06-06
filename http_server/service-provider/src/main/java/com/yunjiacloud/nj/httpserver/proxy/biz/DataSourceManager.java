package com.yunjiacloud.nj.httpserver.proxy.biz;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

public class DataSourceManager {

    private static DataSource _dataSource;

    public static void setDataSource(DataSource dataSource){
        _dataSource = dataSource;
    }

    public static DataSource createDataSource(Properties properties) throws Exception {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("conf.properties");
        prop.load(fis);

//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName(prop.getProperty(Const.SPRING_DATASOURCE_DRIVER_CLASS_NAME_KEY));
//        dataSource.setUrl(prop.getProperty(Const.SPRING_DATASOURCE_URL_KEY));
//        dataSource.setUsername(prop.getProperty(Const.SPRING_DATASOURCE_USERNAME_KEY));
//        dataSource.setPassword(Encrypt.DoDecrypt(prop.getProperty(Const.SPRING_DATASOURCE_PASSWORD_KEY)));
//
//        return dataSource;
        return null;

        // value = properties.getProperty(PROP_DEFAULTAUTOCOMMIT);
        // if (value != null) {
        // dataSource.setDefaultAutoCommit(Boolean.valueOf(value).booleanValue());
        // }
        //
        // value = properties.getProperty(PROP_DEFAULTREADONLY);
        // if (value != null) {
        // dataSource.setDefaultReadOnly(Boolean.valueOf(value).booleanValue());
        // }
        //
        // value = properties.getProperty(PROP_DRIVERCLASSNAME);
        // if (value != null) {
        // dataSource.setDriverClassName(value);
        // }
        //
        // value = properties.getProperty(PROP_MAXACTIVE);
        // if (value != null) {
        // dataSource.setMaxActive(Integer.parseInt(value));
        // }
        //
        // value = properties.getProperty(PROP_MAXIDLE);
        // if (value != null) {
        // dataSource.setMaxIdle(Integer.parseInt(value));
        // }
        //
        // value = properties.getProperty(PROP_MINIDLE);
        // if (value != null) {
        // dataSource.setMinIdle(Integer.parseInt(value));
        // }
        //
        // value = properties.getProperty(PROP_INITIALSIZE);
        // if (value != null) {
        // dataSource.setInitialSize(Integer.parseInt(value));
        // }
        //
        // value = properties.getProperty(PROP_MAXWAIT);
        // if (value != null) {
        // dataSource.setMaxWait(Long.parseLong(value));
        // }
        //
        // value = properties.getProperty(PROP_TESTONBORROW);
        // if (value != null) {
        // dataSource.setTestOnBorrow(Boolean.valueOf(value).booleanValue());
        // }
        //
        // value = properties.getProperty(PROP_TESTONRETURN);
        // if (value != null) {
        // dataSource.setTestOnReturn(Boolean.valueOf(value).booleanValue());
        // }
        //
        // value = properties.getProperty(PROP_TIMEBETWEENEVICTIONRUNSMILLIS);
        // if (value != null) {
        // dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(value));
        // }
        //
        // value = properties.getProperty(PROP_NUMTESTSPEREVICTIONRUN);
        // if (value != null) {
        // dataSource.setNumTestsPerEvictionRun(Integer.parseInt(value));
        // }
        //
        // value = properties.getProperty(PROP_MINEVICTABLEIDLETIMEMILLIS);
        // if (value != null) {
        // dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(value));
        // }
        //
        // value = properties.getProperty(PROP_TESTWHILEIDLE);
        // if (value != null) {
        // dataSource.setTestWhileIdle(Boolean.valueOf(value).booleanValue());
        // }
        //
        // value = properties.getProperty(PROP_PASSWORD);
        // if (value != null) {
        // dataSource.setPassword(value);
        // }
        //
        // value = properties.getProperty(PROP_URL);
        // if (value != null) {
        // dataSource.setUrl(value);
        // }
        //
        // value = properties.getProperty(PROP_USERNAME);
        // if (value != null) {
        // dataSource.setUsername(value);
        // }
        //
        // value = properties.getProperty(PROP_VALIDATIONQUERY);
        // if (value != null) {
        // dataSource.setValidationQuery(value);
        // }
        //
        // value = properties.getProperty(PROP_VALIDATIONQUERY_TIMEOUT);
        // if (value != null) {
        // dataSource.setValidationQueryTimeout(Integer.parseInt(value));
        // }
        //
        // value =
        // properties.getProperty(PROP_ACCESSTOUNDERLYINGCONNECTIONALLOWED);
        // if (value != null) {
        // dataSource.setAccessToUnderlyingConnectionAllowed(Boolean.valueOf(value).booleanValue());
        // }
        //
        // value = properties.getProperty(PROP_REMOVEABANDONED);
        // if (value != null) {
        // dataSource.setRemoveAbandoned(Boolean.valueOf(value).booleanValue());
        // }
        //
        // value = properties.getProperty(PROP_REMOVEABANDONEDTIMEOUT);
        // if (value != null) {
        // dataSource.setRemoveAbandonedTimeout(Integer.parseInt(value));
        // }
        //
        // value = properties.getProperty(PROP_LOGABANDONED);
        // if (value != null) {
        // dataSource.setLogAbandoned(Boolean.valueOf(value).booleanValue());
        // }
        //
        // value = properties.getProperty(PROP_POOLPREPAREDSTATEMENTS);
        // if (value != null) {
        // dataSource.setPoolPreparedStatements(Boolean.valueOf(value).booleanValue());
        // }
        //
        // value = properties.getProperty(PROP_MAXOPENPREPAREDSTATEMENTS);
        // if (value != null) {
        // dataSource.setMaxOpenPreparedStatements(Integer.parseInt(value));
        // }
        //
        // value = properties.getProperty(PROP_INITCONNECTIONSQLS);
        // if (value != null) {
        // StringTokenizer tokenizer = new StringTokenizer(value, ";");
        // dataSource.setConnectionInitSqls(Collections.list(tokenizer));
        // }

        // Return the configured DataSource instance
    }
}
