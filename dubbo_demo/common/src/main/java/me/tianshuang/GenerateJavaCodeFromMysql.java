package me.tianshuang;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.google.common.base.CaseFormat;
import com.mysql.cj.core.util.StringUtils;
import com.squareup.javapoet.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.SystemUtils;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


/**
 * 
 * 需要安装jar包到本地maven。找肖明明要jar包
 * mvn install:install-file -Dfile=D:/src/javapoet/target/javapoet-1.11.1.jar -DgroupId=com.squareup  -DartifactId=javapoet -Dversion=1.11.1 -Dpackaging=jar
 * 
 * 不安装的话也可以运行，但生成的代码格式有点问题。
 */
public class GenerateJavaCodeFromMysql {

    public static final String FOUR_SPACE = "    ";
    public static final String SHOW_FULL_COLUMNS_FROM_TABLE = "SHOW FULL COLUMNS FROM %s";
    public static final String SELECT_ALL_FROM_TABLE_LIMIT_ONE = "SELECT * FROM %s LIMIT 1";
    public static final String FIELD = "field";
    public static final String COMMENT = "Comment";


    public static void main(String[] args) throws IOException {


        try  {

            Config config = new Config();
            config.setUrl("jdbc:mysql://192.168.10.204:3306/xmm_test?useUnicode=true&amp;characterEncoding=UTF-8");
            config.setUsername("root");
            config.setPassword("Root123456!");
            config.setDatabaseName("xmm_test");//库名，TableList为空时一定要写，不然会把其他库的表也读出来
//            config.setTableList(Arrays.asList(new String[]{"t_user"}));//要生成bean的表名，传空表示生成全部表的bean
            config.setTableList(new ArrayList<>());//要生成bean的表名，传空表示生成全部表的bean
            config.setPackageName("bean");
            config.setUseComment(true);//添加Java字段说明
            config.setUseLocalDate(false);//不使用Java8 的日期API，日期字段格式为java.util.Date
            config.setUseLocalDateTime(false);//不使用Java8 的日期API，日期字段格式为java.util.Date
            config.setUseLombok(true);//使用Lombok注解

            optimizeUrl(config);

            try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword())) {

                fillTableListIfEmpty(connection, config.getDatabaseName(),config.getTableList());

                for (String table : config.getTableList()) {
                    Map<String, String> fieldCommentMap = new HashMap<>();
                    fillFieldAndComment(connection, table, fieldCommentMap);

                    try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(SELECT_ALL_FROM_TABLE_LIMIT_ONE, table))) {
                        ResultSet resultSet = preparedStatement.executeQuery();
                        ResultSetMetaData metadata = resultSet.getMetaData();
                        TypeSpec.Builder builder = TypeSpec.classBuilder(lowerUnderscoreToUpperCamel(table)).addModifiers(Modifier.PUBLIC);

                        List<Field> fieldList = new ArrayList<>();

                        for (int i = 1; i <= metadata.getColumnCount(); i++) {
                            String columnClass = metadata.getColumnClassName(i);
                            Class clazz;
                            switch (columnClass) {
                                case "java.lang.Integer":
                                    clazz = Integer.class;
                                    break;
                                case "java.lang.String":
                                    clazz = String.class;
                                    break;
                                case "java.lang.Long":
                                    clazz = Long.class;
                                    break;
                                case "java.lang.Boolean":
                                    clazz = Boolean.class;
                                    break;
                                case "java.lang.Float":
                                    clazz = Float.class;
                                    break;
                                case "java.lang.Double":
                                    clazz = Double.class;
                                    break;
                                case "java.math.BigDecimal":
                                    clazz = BigDecimal.class;
                                    break;
                                case "java.math.BigInteger":
                                    clazz = BigInteger.class;
                                    break;
                                case "java.sql.Date":
                                    if (config.isUseLocalDate() && (SystemUtils.IS_JAVA_1_8 || SystemUtils.IS_JAVA_1_9)) {
                                        clazz = LocalDate.class;
                                    } else {
                                        clazz = java.util.Date.class;
                                    }
                                    break;
                                case "java.sql.Time":
                                case "java.sql.Timestamp":
                                    if (config.isUseLocalDateTime() && (SystemUtils.IS_JAVA_1_8 || SystemUtils.IS_JAVA_1_9)) {
                                        clazz = LocalDateTime.class;
                                    } else {
                                        clazz = java.util.Date.class;
                                    }
                                    break;
                                default:
                                    clazz = byte[].class;
                                    break;
                            }
                            String fieldName = lowerUnderscoreToLowerCamel(metadata.getColumnName(i));
                            FieldSpec.Builder fieldSpecBuilder = FieldSpec.builder(clazz, fieldName)
                                    .addModifiers(Modifier.PRIVATE);
                            fieldList.add(new Field(clazz, fieldName));
                            String fieldComment = fieldCommentMap.get(metadata.getColumnName(i));
                            if (config.isUseComment() && !StringUtils.isNullOrEmpty(fieldComment)) {
                                fieldSpecBuilder.addJavadoc(fieldComment + System.getProperty("line.separator"));
                            }
                            //添加属性注解
                            generateFiledAnnotation(fieldSpecBuilder,metadata.getColumnName(i));
                            
                            builder.addField(fieldSpecBuilder.build());
                        }

                        if (config.isUseLombok()) {
                            builder.addAnnotation(Data.class);
                        } else {
                            generateGetterAndSetter(builder, fieldList);
                        }

                        JavaFile javaFile = JavaFile.builder(config.getPackageName(), builder.build()).skipJavaLangImports(true).indent(FOUR_SPACE).build();
//                        javaFile.writeTo(Paths.get("."));
                        System.out.println(javaFile.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }

    private static void fillFieldAndComment(Connection connection, String table, Map<String, String> fieldCommentMap) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(SHOW_FULL_COLUMNS_FROM_TABLE, table))) {
            ResultSet fieldCommentResultSet = preparedStatement.executeQuery();
            while (fieldCommentResultSet.next()) {
                fieldCommentMap.put(fieldCommentResultSet.getNString(FIELD), fieldCommentResultSet.getNString(COMMENT));
            }
        }
    }

    private static void optimizeUrl(Config config) {
        if (config.getUrl().contains("?")) {
            config.setUrl(config.getUrl() + "&serverTimezone=UTC");
        } else {
            config.setUrl(config.getUrl() + "?serverTimezone=UTC");
        }
    }

    private static void fillTableListIfEmpty(Connection connection,String dbName, List<String> tableList) throws SQLException {
        if (tableList.isEmpty()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(dbName, null, "%", null);
            while (resultSet.next()) {
                tableList.add(resultSet.getString(3));
            }
        }
    }

    private static void generateGetterAndSetter(TypeSpec.Builder builder, List<Field> fieldList) {
        for (Field field : fieldList) {
            MethodSpec.Builder getMethodSpecBuilder;
            if (field.getClazz() == Boolean.class) {
                getMethodSpecBuilder = MethodSpec.methodBuilder("is" + lowerCamelToUpperCamel(field.getName()));
            } else {
                getMethodSpecBuilder = MethodSpec.methodBuilder("get" + lowerCamelToUpperCamel(field.getName()));
            }
            MethodSpec getMethodSpec = getMethodSpecBuilder.addModifiers(Modifier.PUBLIC).returns(field.getClazz()).addStatement("return " + field.getName()).build();
            builder.addMethod(getMethodSpec);

            MethodSpec setMethodSpec = MethodSpec.methodBuilder("set" + lowerCamelToUpperCamel(field.getName())).addModifiers(Modifier.PUBLIC).returns(TypeName.VOID).addParameter(field.getClazz(), field.getName()).addStatement("this." + field.getName() + " = " + field.getName()).build();
            builder.addMethod(setMethodSpec);
        }
    }

    private static String lowerUnderscoreToLowerCamel(String string) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, string);
    }

    private static String lowerUnderscoreToUpperCamel(String string) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, string);
    }

    private static String lowerCamelToUpperCamel(String string) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, string);
    }
    
    private static void generateFiledAnnotation(FieldSpec.Builder fieldSpecBuilder, String columnName){
        AnnotationSpec.Builder fab=null;
        if("id".equalsIgnoreCase(columnName)){//主键
            fab=AnnotationSpec.builder(TableId.class);
        }else{//非主键
            fab=AnnotationSpec.builder(TableField.class);
            String filedName = lowerUnderscoreToLowerCamel(columnName);
            if(!columnName.equals(filedName))
                fab.addMember("value","$S",columnName);
        }
        fieldSpecBuilder.addAnnotation(fab.build());
    }


    @Data
    static class Field {

        private Class clazz;
        private String name;

        Field(Class clazz, String name) {
            this.clazz = clazz;
            this.name = name;
        }

    }

    @Getter
    @Setter
    static class Config {

        private String url;

        private String username;

        private String password;

        private String databaseName;

        private List<String> tableList;

        private String packageName;

        private boolean useComment;

        private boolean useLocalDate;

        private boolean useLocalDateTime;

        private boolean useLombok;

    }


}

