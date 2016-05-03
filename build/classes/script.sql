-- ==========================登陆控制=====================================
drop table if exists users;
create table users(
	id bigint primary key auto_increment comment '主键id',
	login_name varchar(64) comment '登陆名称',
	password varchar(64) comment '密码',
	nick_name varchar(64) comment '昵称',
	user_type integer comment '用户类型，1.管理员，2.用户，3.商家',
	login_switch integer comment '登陆开关，1.能够登陆，2.不能登陆，3.商家注册申请',
	admin_flag tinyint(1) default 0 comment '超级管理标识', -- 默认为不是,只有一个需要手动改
	create_time datetime comment '注册时间',
	head_path varchar(255) comment '头像存放地址'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户表';

-- ============================商家=======================================
drop table if exists merchants;
create table merchants(
	id bigint primary key auto_increment comment '商户主键',
	user_id bigint comment '登陆id',
	logo_addr varchar(255) comment '商家标识存放位置',
	mobile varchar(64) comment '商户的电话',
	name varchar(64) comment '商铺名称',
	merchant_addr varchar(255) comment '商店地址',
	merchant_type varchar(64) comment '商户类型',
	slogen varchar(255) comment '广告语',
	month_count integer comment '月销量',
	delivery_start float comment '起送价格',
	delivery_price float comment '配送费用',
	box_price float comment '餐盒费用',
	delivery_time integer comment '配送时间,单位：分钟',
	work_time varchar(64) comment '营业时间',
	cod_flag integer comment '是否支持货到付款，1.支持，2.不支持',
	open_flag integer comment '上架标识 ，1.上架，2.下架'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商户表';


drop table if exists merchant_types;
create table merchant_types(
	id bigint primary key auto_increment comment '商户类型id',
	merchant_type varchar(64) comment '商户类型名称'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商户类型表';

drop table if exists merchant_strategy;
create table merchant_strategy(
	id bigint primary key auto_increment comment '策略id',
	merchant_id bigint comment '商户的id',
	strategy_type integer comment '策略类型。1.新用户减免，2.满额度减免，3.折扣',
	premise_price float comment '前提额度',
	balance_price float comment '减免额度',
	discount Integer comment '折扣'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商户策略表';

drop table if exists goods;
create table goods(
	id bigint primary key auto_increment comment '商品id',
	name varchar(64) comment '商品名称',
	price float comment '商品价格',
	goods_desc varchar(255) comment '商品描述',
	goods_pic varchar(255) comment '商品图片',
	category_id bigint comment '所属分类的id',
	month_count integer comment '月销量',
	buzz integer comment '点赞量'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商品表';

drop table if exists goods_category;
create table goods_category(
	id bigint primary key auto_increment comment '商品分类id',
	category_name varchar(64) comment '分类名称',
	merchant_id bigint comment '商户id',
	sort integer comment '排序字段'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商品分类表';

--==============================用户==================================

drop table if exists address;
create table address(
	id bigint primary key auto_increment comment '地址id',
	user_id bigint comment '用户id',
	real_name varchar(11) comment '真实姓名',
	mobile varchar(11) comment '手机',
	sex varchar(11) comment '性别，1男，2女',
	address_attr varchar(255) comment '地址详情',
	house_number varchar(64) comment '门牌号码',
	status integer comment '地址状态，1.默认状态；2.非默认'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '地址表';

drop table if exists collection;
create table collection (
	id bigint primary key auto_increment comment '收藏信息id',
	user_id bigint comment '用户id',
	merchant_id bigint comment '所收藏的商铺'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '收藏表';

drop table if exists merchant_comment;
create table merchant_comment(
	id bigint primary key auto_increment comment '商家评论id',
	merchant_id bigint comment '被评论的商家',
	create_by bigint comment '评论人',
	create_name varchar(64) comment '评论人名称',
	comment_content varchar(255) comment '评论内容',
	create_time datetime comment '评论时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '商户评论表'; 
--==================================订单================================
drop table if exists w_order;
create table w_order(
	id bigint primary key auto_increment comment '订单主键',
	order_number varchar(20) comment '订单号', 
	user_id bigint comment '用户id',
	merchant_id bigint comment '商铺id',
	create_time datetime not null comment '下单时间',
	merchant_name varchar(64) comment '商铺名称',
	user_addr varchar(255) comment '用户地址',
	status integer comment '订单状态  0.未支付。10.待接单。20.已接单，准备中。30.已备好。40.在路上。50.完成订单',
	pay_type integer comment '支付方式，1.在线支付，2.货到付款',
	eat_type integer comment '食用方式，1.外卖，2.堂吃',
	delivery_time integer comment '预计配送时间',
	finish_time datetime comment '完成时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '订单信息表';

drop table if exists order_goods_info;
create table order_goods_info(
	id bigint primary key auto_increment comment '订单商品详情id',
	order_id bigint comment '订单的唯一标识',
	goods_id bigint comment '商品id',
	goods_name varchar(64) comment '商品名称',
	goods_count integer comment '商品数量',
	goods_price float comment '商品单价'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '订单商品详情';

drop table if exists order_price_info;
create table order_price_info(
	id bigint primary key auto_increment comment '订单价格id',
	order_id bigint comment '订单的唯一标识',
	amount float comment '订单总价',
	balance float comment '优惠价格',
	box_price float comment '餐盒价格',
	delivery_price float comment '配送费',
	pay_price float comment '最后支付金额'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '订单价格详情';

drop table if exists order_process;
create table order_process(
	id bigint primary key auto_increment comment '订单流程id',
	order_id bigint comment '订单唯一标识',
	process_time datetime comment '流程时间',
	order_status integer comment '订单状态',
	process_status varchar(64) comment '订单状态信息',
	process_desc varchar(255) comment '流程描述信息'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '订单流程表';

--=========================================管理者相关============================================
drop table if exists complaint;
create table complaint(
	id bigint primary key auto_increment comment '投诉信息id',
	user_id bigint comment '用户id',
	user_name varchar(64) comment '用户昵称',
	merchant_id bigint comment '商户id',
	merchant_name varchar(64) comment '商户名称',
	order_id bigint comment '订单id',
	content varchar(500) comment '投诉内容',
	create_time datetime comment '投诉时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '投诉信息';

drop table if exists punish;
create table punish(
	id bigint primary key auto_increment comment '处罚信息',
	merchant_id bigint comment '被处罚商户id',
	merchant_name varchar(64) comment '商户名称',
	user_id bigint comment '操作人',
	user_name varchar(64) comment '操作人',
	punish_reason varchar(250) comment '处罚原因',
	punish_days integer comment '处罚天数。-1为永久封闭',
	create_time datetime comment '处罚日期'
)ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '处罚信息';
