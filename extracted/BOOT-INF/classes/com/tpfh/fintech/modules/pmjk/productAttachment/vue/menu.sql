//生成菜单sql

//创建产品附件菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '产品附件', 'modules/pmjk/productAttachment.html', 1, 'fa fa-cubes', 0);

//创建产品附件的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:productAttachment:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:productAttachment:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:productAttachment:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:productAttachment:list,pmjk:productAttachment:info',2);