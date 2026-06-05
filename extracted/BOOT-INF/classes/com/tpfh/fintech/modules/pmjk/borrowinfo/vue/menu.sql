//生成菜单sql

//创建增信抵押物菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '增信抵押物', 'modules/pmjk/borrowinfo.html', 1, 'fa fa-cubes', 0);

//创建增信抵押物的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:borrowinfo:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:borrowinfo:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:borrowinfo:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:borrowinfo:list,pmjk:borrowinfo:info',2);