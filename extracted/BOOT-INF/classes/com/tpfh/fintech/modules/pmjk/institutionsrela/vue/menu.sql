//生成菜单sql

//创建增信担保人菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '增信担保人', 'modules/pmjk/institutionsrela.html', 1, 'fa fa-cubes', 0);

//创建增信担保人的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:institutionsrela:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:institutionsrela:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:institutionsrela:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:institutionsrela:list,pmjk:institutionsrela:info',2);