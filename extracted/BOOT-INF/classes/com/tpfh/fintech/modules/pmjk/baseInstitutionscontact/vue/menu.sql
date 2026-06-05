//生成菜单sql

//创建机构联系人菜单
insert into sys_menu(parent_id,name,url,type,icon,order_num) values(143, '机构联系人', 'modules/pmjk/baseInstitutionscontact.html', 1, 'fa fa-cubes', 0);

//创建机构联系人的增删改查按钮
insert into sys_menu(parent_id,name,perms,type) values(, '新增', 'pmjk:baseInstitutionscontact:save',2);
insert into sys_menu(parent_id,name,perms,type) values(, '更新', 'pmjk:baseInstitutionscontact:update',2);
insert into sys_menu(parent_id,name,perms,type) values(, '删除', 'pmjk:baseInstitutionscontact:delete',2);
insert into sys_menu(parent_id,name,perms,type) values(, '查看', 'pmjk:baseInstitutionscontact:list,pmjk:baseInstitutionscontact:info',2);