package com.tpfh.fintech.common.utils;

/*import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
*/
/**
 * 由数据库表生成jpa实体工具
 */
public class GenEntityMysql {
	/*private static String SF_FILE_SEPARATOR = System.getProperty("file.separator");

	private static String filePath = "D:/application/eclipse/workspace/fund-admin/src/main";
	//private static String filePath = "/Users/hwwsyp";
	private static String modelPath = "D:/application/eclipse/workspace/fund-admin/src/main/resources/static/template/";

	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String DB = "invest";
	private static String URL = "jdbc:mysql://localhost:3306/"+ DB +"?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
	private static String NAME = "root";
	private static String PASS = "root";

	*//**
	 * 自动代码生成入口，配置相关表的名字
	 * 在数据库中定义的表名，后面需要用于定义实体类的名称，因此需要符合一定的规则
	 * @param args
	 *//*
	public static void main(String[] args) {		
		//GenEntityMysql.create("biz_tms_vs_aims_position_history", "持仓核对归历史");
	}

	public static R create(String tableName, String tableComment){
		AppGen gen = new AppGen();
		gen.setTableName(tableName);
		gen.setTableComment(tableComment);
		*//**
		 * 获取表字段以及注释
		 *//*
		List<AppGen> list = getAppGenListByTable(tableName);

		String name = gen.getTableName();
		String[] table =  StringUtils.split(name,"_");
		gen.setPrefix(table[0]);
		gen.setFunction(toLowerCaseFirstOne(secInitialCapital(name.substring(name.indexOf("_")))));
		gen.setEntityName(initialCapital(gen.getFunction()));
		list.stream().forEach(column-> {
			if(!column.equals("gmt_modified")){
				column.setEntityColumnName(secInitialCapital(column.getColumnName()));
			}
		});
		gen.setList(list);
		String javaFile = filePath +
				SF_FILE_SEPARATOR + "java" +
				SF_FILE_SEPARATOR+"com"+
				SF_FILE_SEPARATOR+ "tpfh"+
				SF_FILE_SEPARATOR+ "fintech"+
				SF_FILE_SEPARATOR+ "modules"+
				SF_FILE_SEPARATOR+ gen.getPrefix()+
				SF_FILE_SEPARATOR+ gen.getFunction()+ 
				SF_FILE_SEPARATOR;
		*//**
		 * 后端代码
		 *//*
		File entityFile = new File(javaFile+"entity"+
				SF_FILE_SEPARATOR+gen.getEntityName()+"Entity.java");

		File daoFile = new File(javaFile+"dao"+
				SF_FILE_SEPARATOR+gen.getEntityName()+"Dao.java");

		File serviceFile = new File(javaFile+"service"+
				SF_FILE_SEPARATOR+gen.getEntityName()+"Service.java");

		File serviceImplFile = new File(javaFile+"service"+
				SF_FILE_SEPARATOR+"impl"+SF_FILE_SEPARATOR+
				gen.getEntityName()+"ServiceImpl.java");

		File controllerFile = new File(javaFile+"controller"+
				SF_FILE_SEPARATOR + gen.getEntityName() + "Controller.java");

		*//**
		 * mybatis的mapper映射文件
		 *//*
		*//**
		 * mybatis orm配置文件
		 *//*
		String mybatisMapperPath = filePath+
				SF_FILE_SEPARATOR + "resources" +
				SF_FILE_SEPARATOR + "mapper"+
				SF_FILE_SEPARATOR + gen.getPrefix()+
				SF_FILE_SEPARATOR;
		File mapperFile = new File(mybatisMapperPath + gen.getEntityName() + "Dao.xml");


		*//**
		 * VUE前端代码
		 *//*
		String vuePath =  javaFile + "vue" +SF_FILE_SEPARATOR;
		File indexVueFile = new File(vuePath + SF_FILE_SEPARATOR + gen.getFunction() + SF_FILE_SEPARATOR + "index.vue");
		File addOrUpdateVueFile = new File(vuePath + SF_FILE_SEPARATOR + gen.getFunction() + SF_FILE_SEPARATOR + "add-or-update.vue");
		File uploadVueFile = new File(vuePath + SF_FILE_SEPARATOR + gen.getFunction() + SF_FILE_SEPARATOR + "upload.vue");
		File jsVueFile = new File(vuePath + gen.getFunction() + ".js");


		*//**
		 * 生成VUE页面及JS代码
		 *//*
		createCode("vue/index.ftl", gen, indexVueFile);
		createCode("vue/add-or-update.ftl", gen, addOrUpdateVueFile);
		createCode("vue/upload.ftl", gen, uploadVueFile);
		createCode("vue/js.ftl", gen, jsVueFile);

		*//**
		 * 生成后端代码 controller 实现
		 *//*
		createCode("java/controller.ftl", gen,controllerFile);

		*//**
		 * 生成后端代码 repository
		 *//*
		createCode("java/daoFile.ftl", gen, daoFile);
		*//**
		 * 生成后端代码 entity
		 *//*
		createCode("java/entity.ftl", gen,entityFile);
		*//**
		 * 生成后端代码 service
		 *//*
		createCode("java/service.ftl", gen,serviceFile);
		*//**
		 * 生成后端代码 service 实现
		 *//*
		createCode("java/serviceImpl.ftl", gen,serviceImplFile);


		*//**
		 * 生成mapper的xml文件
		 *//*
		createCode("xml/mapper.ftl", gen, mapperFile);


		File sqlFile = new File(vuePath + "menu.sql");
		*//**
		 * 生成模块菜单的sql语句
		 *//*
		createCode("sql/sql.ftl", gen, sqlFile);

		*//**
		 * 创建菜单并且为admin用户勾选上该菜单
		 *//*
		createNewMenuAndSelectForAdmin(gen.getPrefix(), gen.getFunction(), gen.getTableComment());
		return R.ok(filePath);
	}

	*//**
	 * 系统默认初次会创建一个"个性化"的一级目录，新加入的功能都默认加入此目录下创建菜单
	 * 并默认为admin超级用户勾选该菜单权限
	 *//*
	private static void createNewMenuAndSelectForAdmin(String prefix, String function, String tableComment){
		//新建模块菜单
		String sql = String.format("insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143,'%s','%s',1,'fa fa-cubes',0)", 
				tableComment, "modules/"+prefix+"/"+function+".html");
		executeSQL(sql);

		//查询模块菜单主键id
		Integer menuId = queryMenuId(tableComment);

		//新建其他按钮菜单
		String savePermission = prefix + ":" + function + ":save";
		String updatePermission = prefix + ":" + function + ":update";
		String deletePermission = prefix + ":" + function + ":delete";
		String infoListPermission = prefix + ":" + function + ":info," + prefix + ":" + function + ":list";

		sql = String.format("insert into sys_menu(parent_id,name,perms,type) values(%d, '新增', '%s' , 2)", menuId, savePermission);
		executeSQL(sql);

		sql = String.format("insert into sys_menu(parent_id,name,perms,type) values(%d, '更新', '%s' , 2)", menuId, updatePermission);
		executeSQL(sql);

		sql = String.format("insert into sys_menu(parent_id,name,perms,type) values(%d, '删除', '%s' , 2)", menuId, deletePermission);
		executeSQL(sql);

		sql = String.format("insert into sys_menu(parent_id,name,perms,type) values(%d, '查看', '%s' , 2)", menuId, infoListPermission);
		executeSQL(sql);
	}

	*//**
	 * 生成diamante
	 * @param model
	 * @param gen
	 * @param file
	 *//*
	public static void createCode(String model,AppGen gen,File file){
		try {
			Configuration conf = new Configuration(Configuration.getVersion());
			conf.setDirectoryForTemplateLoading(new File(modelPath));

			Template template = conf.getTemplate(model);

			Map<String, Object> result =  BeanUtil.beanToMap(gen);

			List<Map<String, Object>> list = new ArrayList<>();
			for(AppGen subGen : gen.getList()) {
				list.add(BeanUtil.beanToMap(subGen));
			}
			result.put("list", list);


			String text = FreeMarkerTemplateUtils.processTemplateIntoString(
					template, result);
			FileUtil.writeString(text,file,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	*//**
	 * 执行sql
	 * @param sql
	 *//*
	private static Integer queryMenuId(String tableComment) {
		Connection connection = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, NAME, PASS);
			String sql = "select menu_id from sys_menu where name = '"+ tableComment +"'";
			ResultSet set = connection.createStatement().executeQuery(sql);
			while(set.next()) {
				return set.getInt("menu_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	*//**
	 * 执行sql
	 * @param sql
	 *//*
	private static void executeSQL(String sql) {
		Connection connection = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, NAME, PASS);
			connection.createStatement().execute(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != connection) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	*//**
	 * 获取数据库信息
	 *//*
	private static List<AppGen> getAppGenListByTable(String tableName) {
		List<AppGen> list = new ArrayList<>();

		//创建连接
		Connection con = null;
		//查要生成实体类的表
		String sql = "SELECT COLUMN_NAME as columnName,COLUMN_COMMENT as columnComment, DATA_TYPE as dataType,"
				+"COLUMN_KEY as columnExtra,CHARACTER_MAXIMUM_LENGTH as columnLength "
				+"FROM information_schema.COLUMNS WHERE TABLE_SCHEMA='"+ DB +"' and TABLE_NAME = '" + tableName + "'";
		Statement state = null;
		try {
			try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			con = DriverManager.getConnection(URL, NAME, PASS);
			state = con.createStatement();
			ResultSet rs = state.executeQuery(sql);
			//            RSetMetaData metaData = rs.getMetaData();


			while (rs.next()) {
				AppGen temp = new AppGen();
				temp.setColumnName(rs.getString("columnName"));
				temp.setColumnComment(rs.getString("columnComment"));
				temp.setDataType(rs.getString("dataType"));
				temp.setColumnExtra(rs.getString("columnExtra"));
				temp.setColumnLength(rs.getString("columnLength"));

				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (null != con) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	*//**
	 * 由数据库表名生成实体类名
	 * @param tableName
	 * @return
	 *//*
	public static String allInitialCapital(String tableName) {
		if (org.springframework.util.StringUtils.isEmpty(tableName)) {
			return null;
		}
		tableName = allLowerCase(tableName);
		String[] tableNameArray = splitName(tableName);
		StringBuffer entryName = new StringBuffer();
		for (String part : tableNameArray) {
			entryName.append(initialCapital(part));
		}
		return entryName.toString();
	}
	*//**
	 * 由数据库列名生成实体类属性名
	 * @param columnName
	 * @return
	 *//*
	public static String secInitialCapital(String columnName) {
		if (org.springframework.util.StringUtils.isEmpty(columnName)) {
			return null;
		}
		columnName = allLowerCase(columnName);
		String[] columnNameArray = splitName(columnName);
		StringBuffer fieldName = new StringBuffer();
		for (int i = 0; i < columnNameArray.length; i++) {
			String part = columnNameArray[i];
			if (0 == i) {
				fieldName.append(part);
			} else {
				fieldName.append(initialCapital(part));
			}
		}
		return fieldName.toString();
	}
	*//**
	 * 所有字母转成小写
	 * @return
	 *//*
	public static String allLowerCase(String str) {
		if (org.springframework.util.StringUtils.isEmpty(str)) {
			return str;
		}
		return str.toLowerCase();
	}
	*//**
	 * 功能：将输入字符串的首字母改成大写
	 * @param str
	 * @return
	 *//*
	public static String initialCapital(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}
	*//**
	 * 分解名称
	 * @param str
	 * @return
	 *//*
	public static String[] splitName(String str) {
		if (org.springframework.util.StringUtils.isEmpty(str)) {
			return null;
		}
		return str.split("_");
	}
	*//**
	 * 首字母转小写
	 * @param s
	 * @return
	 *//*
	public static String toLowerCaseFirstOne(String s){
		if(Character.isLowerCase(s.charAt(0))){
			return s;
		} else{
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
		}
	}
}

class AppGen {

	*//**
	 * 表名
	 *//*
	private String tableName;
	*//**
	 * 实体类名
	 *//*
	private String entityName;
	*//**
	 * 实体类名 首字母小写
	 *//*
	private String lowerEntityName;
	*//**
	 * 表备注
	 *//*
	private String tableComment;
	*//**
	 * 表前缀
	 *//*
	private String prefix;
	*//**
	 * 功能描述
	 *//*
	private String function;

	*//**
	 * 列名
	 *//*
	private String columnName;
	*//**
	 * 实体列名
	 *//*
	private String entityColumnName;
	*//**
	 * 列描述
	 *//*
	private String columnComment;

	*//**
	 * 类型
	 *//*
	private String dataType;

	*//**
	 * 自增
	 *//*
	private Object columnExtra;
	*//**
	 * 长度
	 *//*
	private Object columnLength;

	private List<AppGen> list;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getLowerEntityName() {
		return lowerEntityName;
	}

	public void setLowerEntityName(String lowerEntityName) {
		this.lowerEntityName = lowerEntityName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getEntityColumnName() {
		return entityColumnName;
	}

	public void setEntityColumnName(String entityColumnName) {
		this.entityColumnName = entityColumnName;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Object getColumnExtra() {
		return columnExtra;
	}

	public void setColumnExtra(Object columnExtra) {
		this.columnExtra = columnExtra;
	}

	public Object getColumnLength() {
		return columnLength;
	}

	public void setColumnLength(Object columnLength) {
		this.columnLength = columnLength;
	}

	public List<AppGen> getList() {
		return list;
	}

	public void setList(List<AppGen> list) {
		this.list = list;
	}*/
}