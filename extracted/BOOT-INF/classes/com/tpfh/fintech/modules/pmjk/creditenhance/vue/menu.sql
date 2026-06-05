//生成菜单sql

//创建增信措施菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '增信措施', 'modules/pmjk/creditenhance.html', 1, 'fa fa-cubes', 0);

//创建增信措施的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:creditenhance:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:creditenhance:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:creditenhance:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:creditenhance:list,pmjk:creditenhance:info',2);