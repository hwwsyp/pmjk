-- 理财品种 — sys_menu（Oracle，parent_id 先 NULL）

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
VALUES (
    NULL,
    '理财品种',
    'modules/pmjk/cashflowalloc/financialvariety.html',
    NULL,
    1,
    'fa fa-cubes',
    1
);

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '查看', NULL, 'pmjk:financialvariety:list,pmjk:financialvariety:info', 2, NULL, 0
FROM sys_menu m WHERE m.name = '理财品种' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '新增', NULL, 'pmjk:financialvariety:save', 2, NULL, 1
FROM sys_menu m WHERE m.name = '理财品种' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '更新', NULL, 'pmjk:financialvariety:update', 2, NULL, 2
FROM sys_menu m WHERE m.name = '理财品种' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '删除', NULL, 'pmjk:financialvariety:delete', 2, NULL, 3
FROM sys_menu m WHERE m.name = '理财品种' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '同步', NULL, 'pmjk:financialvariety:sync', 2, NULL, 4
FROM sys_menu m WHERE m.name = '理财品种' AND m.type = 1 AND ROWNUM = 1;

COMMIT;
