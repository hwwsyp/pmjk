//生成菜单sql

//创建内外部评级菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '内外部评级', 'modules/pmjk/rating.html', 1, 'fa fa-cubes', 0);

//创建内外部评级的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:rating:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:rating:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:rating:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:rating:list,pmjk:rating:info',2);