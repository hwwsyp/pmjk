-- 现金流分配计算 — sys_menu（parent_id 请先挂到「现金流分配」目录）

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
VALUES (NULL, '现金流', 'modules/pmjk/cashflowalloc/cashflowdivide.html', NULL, 1, 'fa fa-exchange', 2);

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '查看', NULL, 'pmjk:cashflowdivide:list', 2, NULL, 0 FROM sys_menu m WHERE m.name = '现金流' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '计算', NULL, 'pmjk:cashflowdivide:calculate', 2, NULL, 1 FROM sys_menu m WHERE m.name = '现金流' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '保存', NULL, 'pmjk:cashflowdivide:save', 2, NULL, 2 FROM sys_menu m WHERE m.name = '现金流' AND m.type = 1 AND ROWNUM = 1;

COMMIT;
