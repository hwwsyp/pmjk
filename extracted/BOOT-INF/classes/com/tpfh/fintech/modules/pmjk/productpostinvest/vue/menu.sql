//生成菜单sql

//创建投后事项菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '投后事项', 'modules/pmjk/productpostinvest.html', 1, 'fa fa-cubes', 0);

//创建投后事项的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:productpostinvest:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:productpostinvest:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:productpostinvest:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:productpostinvest:list,pmjk:productpostinvest:info',2);