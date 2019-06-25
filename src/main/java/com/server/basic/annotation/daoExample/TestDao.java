package com.server.basic.annotation.daoExample;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestDao {

    public static void main(String[] args) {

        Filter f1 = new Filter();
        f1.setId(10);

        Filter f2 = new Filter();
        f2.setUserName("Lucy");

        Filter f3 = new Filter();
        f3.setEmail("lucy@163.com,Tang@163.com,Han@163.com");

        String sql1= query(f1);
        String sql2= query(f2);
        String sql3= query(f3);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);

    }

    private static String query(Filter filter) {

        StringBuilder sb = new StringBuilder();
        //获取class
        Class c = filter.getClass();

        //获取Table注释类的表明
        Boolean isExists = c.isAnnotationPresent(Table.class);
        if(!isExists){
            return null;
        }
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();
        sb.append("select * from ").append(tableName).append(" where 1=1");

        //遍历所有字段
        Field[]  fields = c.getDeclaredFields();
        for(Field field : fields){
            //处理每个字段对应的sql，获取字段名、字段值
            Boolean isColumn = field.isAnnotationPresent(Column.class);
            if(!isColumn){
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String column_name = column.value();
            Object fieldValue = null;

            //获取字段的值
            String fieldName = field.getName();
            //拼接 get 方法名
            String getMethodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            try {
                // 获取字段对应的get方法
                Method getMethod = c.getMethod(getMethodName );
                //调用get方法获取到字段值
                fieldValue = getMethod.invoke(filter,null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //拼装SQL
            if(fieldValue==null || (
                    fieldValue instanceof Integer && (Integer)fieldValue == 0)) {
                continue;
            }

            sb.append(" and ").append(column_name);
            if(fieldValue instanceof Integer){
                sb.append("=").append(fieldValue);
            }
            if(fieldValue instanceof String){
                if(((String) fieldValue).contains(",")){
                    String[] values = ((String) fieldValue).split(",");
                    sb.append(" in (");
                    for(String value : values){
                        sb.append("'").append(value).append("'").append(",");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    sb.append(")");
                }else{
                    sb.append("=").append("'").append(fieldValue).append("'");
                }
            }
        }
        return sb.toString();
    }
}
