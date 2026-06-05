//生成菜单sql

//创建风险五级分类菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '风险五级分类', 'modules/pmjk/productrisk.html', 1, 'fa fa-cubes', 0);

//创建风险五级分类的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:productrisk:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:productrisk:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:productrisk:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:productrisk:list,pmjk:productrisk:info',2);