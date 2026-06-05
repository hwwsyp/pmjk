//生成菜单sql

//创建产品主控菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '产品主控', 'modules/pmjk/product.html', 1, 'fa fa-cubes', 0);

//创建产品主控的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:product:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:product:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:product:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:product:list,pmjk:product:info',2);