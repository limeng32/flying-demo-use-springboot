insert into account_(id, name, password, activated, opLock) values(1,'ada','ada1ada','1','1');
insert into account_(id, name, password, activated, opLock) values(2,'bob','bob2bob','1','3');
insert into account_(id, name, password, activated, opLock) values(3,'carl','carl3carl','1','5');
insert into account_(id, name, password, activated, opLock) values(4,'dude','dude4dude','1','7');
insert into account_(id, name, password, activated, opLock) values(5,'eli','eli5eli','1','9');

insert into CART
values ('1', '0', null,'1');

insert into CART
values ('2', '0', null,'2');

insert into COMMODITY
values ('1', '牙刷A', '1200');

insert into COMMODITY
values ('2', '牙刷B', '1850');

insert into COMMODITY
values ('3', '牙刷C', '2100');

insert into COMMODITY
values ('4', '佳洁士牙膏', '1499');

insert into COMMODITY
values ('5', '六必治牙膏', '1999');

insert into COMMODITY
values ('6', '云南白药牙膏', '2499');

insert into COMMODITY
values ('7', '潘婷洗发露', '3500');

insert into COMMODITY
values ('8', '多芬洗发露', '3900');

insert into COMMODITY
values ('9', '海飞丝洗发露', '5100');

insert into COMMODITY
values ('10', '浴液-1500ML', '2800');

insert into COMMODITY
values ('11', '浴液-2000ML', '3200');

insert into COMMODITY
values ('12', '浴液-4000ML', '4900');

insert into CART_COMMODITY
values ('1', '1', '1', '3');

insert into CART_COMMODITY
values ('2', '1', '5', '4');

insert into CART_COMMODITY
values ('3', '1', '8', '1');

insert into CART_COMMODITY
values ('4', '1', '12', '1');

insert into CART_COMMODITY
values ('5', '2', '2', '2');

insert into CART_COMMODITY
values ('6', '2', '4', '1');

insert into CART_COMMODITY
values ('7', '2', '9', '2');

insert into CART_COMMODITY
values ('8', '2', '11', '1');

insert into ROLE
values ('1', '普通会员', 'normal');

insert into ROLE
values ('2', '银牌会员', 'silver');

insert into ROLE
values ('3', '金牌会员', 'gold');