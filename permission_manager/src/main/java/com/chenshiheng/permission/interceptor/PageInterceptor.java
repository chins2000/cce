package com.chenshiheng.permission.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.chenshiheng.permission.dto.Page;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args=Connection.class)})
public class PageInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		//获取配置文件中sql语句的id
		String id = mappedStatement.getId();
		if(id.matches(".+ByPage$")){
			BoundSql boundSql = statementHandler.getBoundSql();
			String sql = boundSql.getSql();
			String countsql = "select count(*) from("+sql+")a";
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			Connection connection = (Connection)invocation.getArgs()[0];
			PreparedStatement countpre = connection.prepareStatement(countsql);
			parameterHandler.setParameters(countpre);
			ResultSet resultSet = countpre.executeQuery();
			
			Map<String, Object> parameter = (Map<String, Object>)boundSql.getParameterObject();
			Page page = (Page)parameter.get("page");
			if(resultSet.next()){
				page.setTotalNumberOther(resultSet.getInt(1));
			}
			String pageSql = sql+" limit "+page.getDbIndex()+","+page.getDbNumber();
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
