//生成菜单sql

//创建行业分类菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '行业分类', 'modules/pmjk/baseIndustrypartition.html', 1, 'fa fa-cubes', 0);

//创建行业分类的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:baseIndustrypartition:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:baseIndustrypartition:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:baseIndustrypartition:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:baseIndustrypartition:list,pmjk:baseIndustrypartition:info',2);