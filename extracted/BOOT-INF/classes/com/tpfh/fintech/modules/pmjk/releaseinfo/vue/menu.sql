//生成菜单sql

//创建发行信息菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '发行信息', 'modules/pmjk/releaseinfo.html', 1, 'fa fa-cubes', 0);

//创建发行信息的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:releaseinfo:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:releaseinfo:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:releaseinfo:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:releaseinfo:list,pmjk:releaseinfo:info',2);