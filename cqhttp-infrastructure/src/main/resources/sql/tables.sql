/*
 Navicat Premium Data Transfer

 Source Server         : qq_bot
 Source Server Type    : SQLite
 Source Server Version : 3035005 (3.35.5)
 Source Schema         : main

 Target Server Type    : SQLite
 Target Server Version : 3035005 (3.35.5)
 File Encoding         : 65001

 Date: 28/06/2023 20:24:13
*/

PRAGMA foreign_keys = false;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS "config";
CREATE TABLE "config"
(
    "id"     text       NOT NULL,
    "name"   text       NOT NULL,
    "value1" TEXT       NOT NULL,
    "value2" TEXT,
    "value3" TEXT,
    "desc"   TEXT,
    "type"   TEXT       NOT NULL,
    "status" integer(1) NOT NULL,
    PRIMARY KEY ("id")
);

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS "group";
CREATE TABLE "group"
(
    "id"          text NOT NULL,
    "name"        TEXT NOT NULL,
    "create_id"   text NOT NULL,
    "create_time" DATE NOT NULL,
    PRIMARY KEY ("id")
);

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS "logs";
CREATE TABLE "logs"
(
    "id"           text NOT NULL,
    "event_type"   TEXT NOT NULL,
    "event_source" TEXT NOT NULL,
    "description"  TEXT NOT NULL,
    "create_time"  DATE NOT NULL,
    PRIMARY KEY ("id")
);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "user";
CREATE TABLE "user"
(
    "qq"        varchar(255) NOT NULL,
    "name"      varchar(255),
    "create_qq" varchar,
    "type"      varchar      NOT NULL,
    PRIMARY KEY ("qq")
);

-- ----------------------------
-- Table structure for words
-- ----------------------------
DROP TABLE IF EXISTS "words";
CREATE TABLE "words"
(
    "word" TEXT NOT NULL
);

PRAGMA foreign_keys = true;
