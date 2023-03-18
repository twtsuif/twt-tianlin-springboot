create table tb_apply
(
    id                 int unsigned auto_increment comment '主键'
        primary key,
    uid                int unsigned                            null comment '用户id',
    name               char(10)                                null comment '姓名',
    gender             tinyint                                 null comment '性别',
    nation             varchar(255)                            null comment '民族',
    native_place       varchar(255)                            null comment '籍贯',
    idcard             char(18)                                null comment '身份证号',
    birth_date         char(10)                                null comment '出生日期',
    phone              char(11)                                null comment '手机号',
    email              varchar(255)                            null comment '邮箱',
    qq                 char(20)                                null comment 'QQ',
    from_place         varchar(255)                            null comment '生源地',
    high_school        varchar(255)                            null comment '生源高中',
    household          varchar(255)                            null comment '户口所在地',
    score              char(10)                                null comment '高考分数',
    identity           varchar(255)                            null comment '身份',
    identity_detail    varchar(255)                            null comment '身份详情',
    party_will         tinyint                                 null comment '是否有入党意愿（1为是 2为否）',
    major              varchar(255)                            null comment '专业',
    clothes_size       varchar(255)                            null comment '衣服尺寸',
    high_school_exp    varchar(255)                            null comment '高中经历',
    high_school_honour varchar(255)                            null comment '高中荣誉',
    introduction       varchar(255)                            null comment '自我介绍',
    hobby              varchar(255)                            null comment '兴趣爱好',
    photo_path         varchar(255)                            null comment '图片地址',
    file_path          varchar(255)                            null comment '档案地址',
    admit              tinyint   default 0                     null comment '是否通过申请',
    year               int                                     null comment '申请年份',
    created_at         timestamp default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP,
    updated_at         timestamp default '0000-00-00 00:00:00' not null
)
    engine = InnoDB
    charset = utf8;

create table tb_confirm
(
    id               int unsigned auto_increment comment '主键'
        primary key,
    uid              int                                     not null comment '用户id',
    name             varchar(255)                            not null comment '用户名',
    idcard           char(18)                                not null comment '身份证号码',
    is_join          tinyint(1)                              not null comment '是否确认能够参加培训',
    buy              tinyint(1)                              null comment '是否需要购买卧具',
    way_to_jin       varchar(255)                            null comment '到津方式',
    station          varchar(255)                            null comment '到津站点',
    year             int                                     null comment '确认年份',
    bed_need         varchar(255)                            null comment '卧具详情',
    created_at       timestamp default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP,
    updated_at       timestamp default '0000-00-00 00:00:00' not null,
    is_need_pick_up  tinyint                                 null comment '是否需要接站',
    time_to_jin      varchar(255)                            null comment '到津时间',
    train_number     varchar(255)                            null comment '车次',
    accompany_number int                                     null comment '陪伴人数'
)
    engine = InnoDB
    charset = utf8;

create table tb_notice
(
    id         int unsigned auto_increment comment '主键'
        primary key,
    title      varchar(255)                            not null comment '标题',
    content    text                                    null comment '内容',
    file_path  varchar(255)                            null comment '附件路径',
    is_deleted tinyint                                 null comment '软删除',
    created_at timestamp default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP,
    updated_at timestamp default '0000-00-00 00:00:00' not null
)
    engine = InnoDB
    charset = utf8;

create table tb_role
(
    id   int auto_increment comment '主键'
        primary key,
    name varchar(10) null comment '角色名'
)
    engine = InnoDB;

create table tb_status
(
    id      tinyint not null comment '主键'
        primary key,
    apply   tinyint null comment '申请系统是否打开',
    confirm tinyint null comment '确认系统是否打开'
)
    engine = InnoDB
    charset = latin1;

create table tb_user
(
    id         int unsigned auto_increment comment '主键'
        primary key,
    name       varchar(255) collate utf8_unicode_ci not null comment '姓名',
    email      varchar(255) collate utf8_unicode_ci not null comment '邮箱',
    password   varchar(255) collate utf8_unicode_ci not null comment '密码',
    created_at timestamp                            null,
    updated_at timestamp                            null,
    constraint users_email_unique
        unique (email)
)
    engine = InnoDB
    charset = utf8;

create table tb_user_role
(
    id     int auto_increment comment '主键'
        primary key,
    userid int null comment '用户id',
    roleid int null comment '角色id'
)
    engine = InnoDB;
