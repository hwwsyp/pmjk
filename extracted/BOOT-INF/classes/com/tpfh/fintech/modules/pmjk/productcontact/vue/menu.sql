//生成菜单sql

//创建产品联系人菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '产品联系人', 'modules/pmjk/productcontact.html', 1, 'fa fa-cubes', 0);

//创建产品联系人的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:productcontact:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:productcontact:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:productcontact:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:productcontact:list,pmjk:productcontact:info',2);