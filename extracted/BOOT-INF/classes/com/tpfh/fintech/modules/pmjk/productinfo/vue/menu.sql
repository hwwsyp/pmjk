//生成菜单sql

//创建产品概要菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '产品概要', 'modules/pmjk/productinfo.html', 1, 'fa fa-cubes', 0);

//创建产品概要的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:productinfo:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:productinfo:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:productinfo:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:productinfo:list,pmjk:productinfo:info',2);