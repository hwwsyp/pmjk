//生成菜单sql

//创建投资相关方菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '投资相关方', 'modules/pmjk/stakeholder.html', 1, 'fa fa-cubes', 0);

//创建投资相关方的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:stakeholder:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:stakeholder:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:stakeholder:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:stakeholder:list,pmjk:stakeholder:info',2);