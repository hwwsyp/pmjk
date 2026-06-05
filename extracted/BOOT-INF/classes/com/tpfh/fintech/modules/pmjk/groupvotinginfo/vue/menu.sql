//生成菜单sql

//创建集团投审会菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '集团投审会', 'modules/pmjk/groupvotinginfo.html', 1, 'fa fa-cubes', 0);

//创建集团投审会的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:groupvotinginfo:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:groupvotinginfo:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:groupvotinginfo:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:groupvotinginfo:list,pmjk:groupvotinginfo:info',2);