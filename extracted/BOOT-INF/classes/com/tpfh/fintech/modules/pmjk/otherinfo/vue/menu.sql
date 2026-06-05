//生成菜单sql

//创建其它信息菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '其它信息', 'modules/pmjk/otherinfo.html', 1, 'fa fa-cubes', 0);

//创建其它信息的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:otherinfo:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:otherinfo:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:otherinfo:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:otherinfo:list,pmjk:otherinfo:info',2);