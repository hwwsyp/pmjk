//生成菜单sql

//创建组织结构对象菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '组织结构对象', 'modules/pmjk/baseOrgobject.html', 1, 'fa fa-cubes', 0);

//创建组织结构对象的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:baseOrgobject:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:baseOrgobject:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:baseOrgobject:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:baseOrgobject:list,pmjk:baseOrgobject:info',2);