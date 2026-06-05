//生成菜单sql

//创建现金流菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '现金流', 'modules/pmjk/cashflowdata.html', 1, 'fa fa-cubes', 0);

//创建现金流的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:cashflowdata:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:cashflowdata:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:cashflowdata:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:cashflowdata:list,pmjk:cashflowdata:info',2);