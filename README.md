# 🛒 MinecartSpeed

[![License](https://img.shields.io/badge/License-CC0--1.0-green.svg)](LICENSE)
[![Minecraft](https://img.shields.io/badge/Minecraft-26.2-blue.svg)](https://minecraft.net)
[![Fabric](https://img.shields.io/badge/Mod%20Loader-Fabric-yellow.svg)](https://fabricmc.net)
[![Java](https://img.shields.io/badge/Java-25-red.svg)](https://adoptium.net)


<p align="">
  <img src="./src/main/resources/assets/minecartspeed/icon1.png" alt="新工具" width="300">
</p>

一个 **Fabric** 模组，允许玩家通过**鼠标滚轮**或**命令**动态调整矿车的最大速度。
---
---

## ✨ 功能

- 🖱️ **鼠标滚轮调节** — 坐在矿车上时，滚动鼠标滚轮即可实时调整矿车最大速度（步长 0.1）
- ⌨️ **命令控制** — 通过 `/minecartspeed` 命令精确获取、设置或调整矿车速度
- 💾 **持久化存储** — 矿车的速度设置会随世界保存，重新进入游戏依然生效
- 🌐 **客户端-服务端同步** — 滚轮操作通过 Fabric 网络包同步至服务端
- 🎯 **速度范围** — `0.1` ~ `10.0`（默认值 `0.4`）

---

## 📋 命令

| 命令 | 说明 |
|---|---|
| `/minecartspeed get` | 查看自己所骑矿车的最大速度 |
| `/minecartspeed get <玩家>` | 查看指定玩家所骑矿车的最大速度 |
| `/minecartspeed set <速度>` | 设置自己所骑矿车的最大速度 |
| `/minecartspeed set <速度> <玩家>` | 设置指定玩家所骑矿车的最大速度 |
| `/minecartspeed add <增量>` | 在现有速度基础上增加/减少（支持负数） |
| `/minecartspeed add <增量> <玩家>` | 对指定玩家的矿车速度进行增减 |

> 💡 速度范围为 `0.1` ~ `10.0`，超出范围会自动限制到边界值。

---

## 🎮 使用方法

1. 坐上任意矿车
2. **向上滚动**鼠标滚轮 → 增加最大速度
3. **向下滚动**鼠标滚轮 → 减少最大速度
4. 屏幕上方会实时显示当前矿车最大速度

或者使用命令进行精确控制：

```mcfunction
# 查看当前速度
/minecartspeed get

# 设置为 2.5
/minecartspeed set 2.5

# 增加 0.5
/minecartspeed add 0.5
```

---

## 🔧 安装

| 依赖 | 版本要求                                  |
|---|---------------------------------------|
| Minecraft | `~26.2-` `注：此仓库内的文件版本是26.2-snapshot8` |
| Fabric Loader | `>= 0.19.2`                           |
| Fabric API | `*`                                   |
| Java | `>= 25`                               |

1. 安装 [Fabric Loader](https://fabricmc.net/use/)
2. 下载 [Fabric API](https://modrinth.com/mod/fabric-api)
3. 将本模组的 `.jar` 文件和 Fabric API 放入 `.minecraft/mods/` 目录
4. 启动游戏 🚀

---

## 🏗️ 构建

```bash
./gradlew build
```

构建产物位于 `build/libs/` 目录。

---

## 👥 作者

- **ukhankhulun**
- **hongshaoluobotou**

---

## 📄 许可证

本项目采用 [CC0-1.0](LICENSE) 许可证 — 可自由使用、修改和分发。

---

## 🔗 链接

- [GitHub 仓库](https://github.com/ukhankhulun/minecartspeed-mod)
- [Fabric 文档](https://docs.fabricmc.net)
