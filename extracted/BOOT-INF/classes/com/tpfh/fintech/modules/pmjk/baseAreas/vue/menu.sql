//生成菜单sql

//创建国家地区菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '国家地区', 'modules/pmjk/baseAreas.html', 1, 'fa fa-cubes', 0);

//创建国家地区的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:baseAreas:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:baseAreas:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:baseAreas:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:baseAreas:list,pmjk:baseAreas:info',2);