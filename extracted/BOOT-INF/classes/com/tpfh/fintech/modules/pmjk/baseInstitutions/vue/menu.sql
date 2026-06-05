//生成菜单sql

//创建干系人菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '干系人', 'modules/pmjk/baseInstitutions.html', 1, 'fa fa-cubes', 0);

//创建干系人的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:baseInstitutions:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:baseInstitutions:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:baseInstitutions:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:baseInstitutions:list,pmjk:baseInstitutions:info',2);