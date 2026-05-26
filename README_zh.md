# 🚄 MinecartSpeed

<p align="center">
  <img src="./pictures/image.png" alt="MinecartSpeed Logo" width="1627">
</p>

<p align="center">
  一个现代化 <b>Fabric</b> 模组，允许玩家通过鼠标滚轮或命令动态调整矿车最大速度。
</p>

<p align="center">
  <a href="./LICENSE">
    <img src="https://img.shields.io/badge/License-CC0--1.0-green.svg" alt="License">
  </a>
  <a href="https://minecraft.net">
    <img src="https://img.shields.io/badge/Minecraft-26.2--snapshot8-blue.svg" alt="Minecraft">
  </a>
  <a href="https://fabricmc.net">
    <img src="https://img.shields.io/badge/Loader-Fabric-orange.svg" alt="Fabric">
  </a>
  <a href="https://adoptium.net">
    <img src="https://img.shields.io/badge/Java-25-red.svg" alt="Java">
  </a>
</p>

<p align="center">
   简体中文 | <a href="README.md">English</a>
</p>

---

## ✨ 功能特性

- 🖱️ **鼠标滚轮调节**  
  坐在矿车上时，滚动鼠标滚轮即可动态调整矿车最大速度。

- ⌨️ **命令支持**  
  通过 `/minecartspeed` 命令精确控制矿车速度。

- 💾 **持久化存储**  
  矿车速度会随世界一起保存，重新进入游戏后依然生效。

- 🌐 **客户端-服务端同步**  
  鼠标滚轮操作会通过 Fabric 网络同步至服务端。

- 🎯 **自定义速度范围**  
  速度可在 `0.1` ~ `10.0` 之间调整（默认值：`0.4`）。

---

## 📋 命令列表

| 命令 | 说明 |
|---|---|
| `/minecartspeed get` | 获取自己当前矿车的最大速度 |
| `/minecartspeed get <玩家>` | 获取指定玩家矿车的最大速度 |
| `/minecartspeed set <速度>` | 设置自己矿车的最大速度 |
| `/minecartspeed set <速度> <玩家>` | 设置指定玩家矿车的最大速度 |
| `/minecartspeed add <数值>` | 增加或减少当前速度 |
| `/minecartspeed add <数值> <玩家>` | 修改指定玩家矿车的速度 |

> 💡 所有速度数值都会自动限制在 `0.1` ~ `10.0` 范围内。

---

## 🎮 使用方法

1. 坐上任意矿车
2. 向上滚动鼠标滚轮 → 增加速度
3. 向下滚动鼠标滚轮 → 减少速度
4. 当前速度会实时显示在屏幕上方

### 命令示例

```mcfunction
# 获取当前速度
/minecartspeed get

# 设置速度为 2.5
/minecartspeed set 2.5

# 增加 0.5
/minecartspeed add 0.5
```

---

## 📦 安装方法

| 依赖 | 要求版本                                         |
|---|----------------------------------------------|
| Minecraft | `26.2-snapshot-8`（其他版本请在Releases里查找） |
| Fabric Loader | `>= 0.19.2`                                  |
| Fabric API | 最新版本                                         |
| Java | `>= 25`                                      |

### 安装步骤

1. 安装 [Fabric Loader](https://fabricmc.net/use/)
2. 下载 [Fabric API](https://modrinth.com/mod/fabric-api)
3. 将本模组 `.jar` 文件与 Fabric API 放入 `.minecraft/mods/` 文件夹
4. 启动游戏 🚀

---

## ⚠ 快照版本兼容性

本模组当前基于 Minecraft 快照版本开发。  
由于 Minecraft Snapshot 更新频繁，API 与内部实现可能随时发生变化。

---

## 🏗️ 构建项目

```bash
./gradlew build
```

构建完成后的文件位于：

```text
build/libs/
```

---

## 🌐 安装要求

| 环境 | 是否需要 |
|---|---|
| 客户端 | ✅ 必须 |
| 服务端 | ✅ 推荐 |

> 完整功能需要客户端与服务端同时安装。  
> 仅安装服务端时，命令功能仍可使用，但鼠标滚轮调速与界面显示将不可用。

---
## 🗺 开发计划

- [x] 动态矿车速度控制
- [x] 鼠标滚轮调节
- [x] 命令支持
- [x] 持久化存储
- [ ] 配置界面（GUI）
- [ ] 单世界配置
- [ ] 专用服务端同步配置
- [ ] NeoForge或者其他 支持

---

## 👥 作者

- **ukhankhulun**
- **hongshaoluobotou**

---

## 📄 许可证

本项目基于 [CC0-1.0](LICENSE) 许可证开源。  
你可以自由使用、修改以及分发本项目。

---

## 🔗 相关链接

- GitHub 仓库  
  https://github.com/ukhankhulun/minecartspeed

- Fabric 官方文档  
  https://docs.fabricmc.net

- Modrinth  
  https://modrinth.com/mod/minecartspeed-mod

---