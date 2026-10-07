-- 现金流分配 / 组合库存 — sys_menu 初始化（Oracle）
-- 说明：
-- 1. parent_id 按你的要求先留空（NULL），执行后请手工 UPDATE 挂到实际上级目录（一级目录常用 0）。
-- 2. 菜单 URL 格式与代码生成器一致，前端通过 name=portfoliostock 路由跳转。
-- 3. 按钮权限需分配给角色（sys_role_menu），admin 角色可在「角色管理」中勾选。

-- ========== 目录：现金流分配 ==========
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
VALUES (NULL, '现金流分配', NULL, NULL, 0, 'fa fa-folder-o', 0);

-- ========== 菜单：组合库存 ==========
-- 挂到「现金流分配」下时，可将 parent_id 改为：
--   (SELECT menu_id FROM sys_menu WHERE name = '现金流分配' AND type = 0 AND ROWNUM = 1)
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
VALUES (
    NULL,
    '组合库存',
    'modules/pmjk/cashflowalloc/portfoliostock.html',
    NULL,
    1,
    'fa fa-cubes',
    0
);

-- ========== 按钮权限（parent_id 指向「组合库存」菜单；若上一步 parent_id 仍为 NULL，请先修正菜单父子关系再执行）==========
INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '查看', NULL, 'pmjk:portfoliostock:list,pmjk:portfoliostock:info', 2, NULL, 0
FROM sys_menu m
WHERE m.name = '组合库存' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '新增', NULL, 'pmjk:portfoliostock:save', 2, NULL, 1
FROM sys_menu m
WHERE m.name = '组合库存' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '更新', NULL, 'pmjk:portfoliostock:update', 2, NULL, 2
FROM sys_menu m
WHERE m.name = '组合库存' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '删除', NULL, 'pmjk:portfoliostock:delete', 2, NULL, 3
FROM sys_menu m
WHERE m.name = '组合库存' AND m.type = 1 AND ROWNUM = 1;

INSERT INTO sys_menu (parent_id, name, url, perms, type, icon, order_num)
SELECT m.menu_id, '同步', NULL, 'pmjk:portfoliostock:sync', 2, NULL, 4
FROM sys_menu m
WHERE m.name = '组合库存' AND m.type = 1 AND ROWNUM = 1;

-- ========== （可选）修正 parent_id 示例，请把 :YOUR_PARENT_MENU_ID 换成实际上级 menu_id ==========
/*
UPDATE sys_menu SET parent_id = :YOUR_PARENT_MENU_ID
WHERE name = '现金流分配' AND type = 0;

UPDATE sys_menu SET parent_id = (
    SELECT menu_id FROM sys_menu WHERE name = '现金流分配' AND type = 0 AND ROWNUM = 1
)
WHERE name = '组合库存' AND type = 1;
*/

COMMIT;
