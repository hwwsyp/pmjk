//生成菜单sql

//创建${tableComment}菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '${tableComment}', 'modules/${prefix}/${function}.html', 1, 'fa fa-cubes', 0);

//创建${tableComment}的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', '${prefix}:${function}:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', '${prefix}:${function}:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', '${prefix}:${function}:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', '${prefix}:${function}:list,${prefix}:${function}:info',2);