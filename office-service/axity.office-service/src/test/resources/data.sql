insert  into TBL_Office(cd_id,nb_city,nb_phone,nb_addressLine1,nb_addressLine2,nb_state,nb_postalCode,cd_country) values 
(1,'San Francisco','+1 650 219 4782','100 Market Street','Suite 300','CA','94080',1);

insert  into TBL_Office(cd_id,nb_city,nb_phone,nb_addressLine1,nb_addressLine2,nb_state,nb_postalCode,cd_country) values 
(2,'Boston','+1 215 837 0825','1550 Court Place','Suite 102','MA','02107',1);

insert  into TBL_Office(cd_id,nb_city,nb_phone,nb_addressLine1,nb_addressLine2,nb_state,nb_postalCode,cd_country) values 
(3,'NYC','+1 212 555 3000','523 East 53rd Street','apt. 5A','NY','10022',1);

insert  into TBL_Office(cd_id,nb_city,nb_phone,nb_addressLine1,nb_addressLine2,nb_state,nb_postalCode,cd_country) values 
(4,'Paris','+33 14 723 4404','43 Rue Jouffroy D''abbans',NULL,NULL,'75017',2);

insert  into TBL_Office(cd_id,nb_city,nb_phone,nb_addressLine1,nb_addressLine2,nb_state,nb_postalCode,cd_country) values 
(5,'Tokyo','+81 33 224 5000','4-1 Kioicho',NULL,'Chiyoda-Ku','102-8578',4);

insert  into TBL_Office(cd_id,nb_city,nb_phone,nb_addressLine1,nb_addressLine2,nb_state,nb_postalCode,cd_country) values 
(6,'Sydney','+61 2 9264 2451','5-11 Wentworth Avenue','Floor #2',NULL,'NSW 2010',5);

insert  into TBL_Office(cd_id,nb_city,nb_phone,nb_addressLine1,nb_addressLine2,nb_state,nb_postalCode,cd_country) values 
(7,'London','+44 20 7877 2041','25 Old Broad Street','Level 7',NULL,'EC2N 1HN',3);

insert into TBL_Territory(cd_id,nb_name) values
(1, 'NA');

insert into TBL_Territory(cd_id,nb_name) values
(2, 'EMEA');

insert into TBL_Territory(cd_id,nb_name) values
(3, 'Japan');

insert into TBL_Territory(cd_id,nb_name) values
(4, 'APAC');

insert into TBL_Country(cd_id,cd_territory, nb_name) values
(1, 1, 'USA');

insert into TBL_Country(cd_id,cd_territory, nb_name) values
(2, 2, 'France');

insert into TBL_Country(cd_id,cd_territory, nb_name) values
(3, 2, 'UK');

insert into TBL_Country(cd_id,cd_territory, nb_name) values
(4, 3, 'Japan');

insert into TBL_Country(cd_id,cd_territory, nb_name) values
(5, 4, 'Australia');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(1,'guy.stark','guy.stark@company.net','Guy','Stark');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(2,'denise.alford','denise.alford@company.net','Denise','Alford');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(3,'ezekiel.farmer','ezekiel.farmer@company.net','Ezekiel','Farmer');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(4,'hall.william','hall.william@company.net','Hall','William');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(5,'gillian.bowers','gillian.bowers@company.net','Gillian','Bowers');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(6,'indira.woods','indira.woods@company.net','Indira','Woods');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(7,'quinlan.hess','quinlan.hess@company.net','Quinlan','Hess');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(8,'jordan.beach','jordan.beach@company.net','Jordan','Beach');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(9,'carlos.knapp','carlos.knapp@company.net','Carlos','Knapp');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(10,'evelyn.key','evelyn.key@company.net','Evelyn','Key');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(11,'sophia.peterson','sophia.peterson@company.net','Sophia','Peterson');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(12,'jonah.stephens','jonah.stephens@company.net','Jonah','Stephens');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(13,'olivia.pierce','olivia.pierce@company.net','Olivia','Pierce');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(14,'carter.koch','carter.koch@company.net','Carter','Koch');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(15,'deirdre.spencer','deirdre.spencer@company.net','Deirdre','Spencer');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(16,'carl.wolf','carl.wolf@company.net','Carl','Wolf');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(17,'allistair.poole','allistair.poole@company.net','Allistair','Poole');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(18,'rhea.mckinney','rhea.mckinney@company.net','Rhea','Mckinney');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname) values
(19,'mercedes.hart','mercedes.hart@company.net','Mercedes','Hart');

insert into TBL_User(cd_id,cd_username,cd_email,nb_name,nb_lastname)values
(20,'hilda.holder','hilda.holder@company.net','Hilda','Holder');

insert into TBL_Role(cd_id,nb_name) values (1, 'Ventas');
insert into TBL_Role(cd_id,nb_name) values (2, 'Almacén');
insert into TBL_Role(cd_id,nb_name) values (3, 'Distribuidor');
insert into TBL_Role(cd_id,nb_name) values (4, 'Asistente');

insert into TRL_UserRole(cd_user,cd_role) values (1,1);
insert into TRL_UserRole(cd_user,cd_role) values (2,1);
insert into TRL_UserRole(cd_user,cd_role) values (3,1);
insert into TRL_UserRole(cd_user,cd_role) values (4,1);
insert into TRL_UserRole(cd_user,cd_role) values (5,1);
insert into TRL_UserRole(cd_user,cd_role) values (6,2);
insert into TRL_UserRole(cd_user,cd_role) values (7,2);
insert into TRL_UserRole(cd_user,cd_role) values (8,2);
insert into TRL_UserRole(cd_user,cd_role) values (9,2);
insert into TRL_UserRole(cd_user,cd_role) values (10,3);
insert into TRL_UserRole(cd_user,cd_role) values (10,4);
insert into TRL_UserRole(cd_user,cd_role) values (11,3);
insert into TRL_UserRole(cd_user,cd_role) values (11,4);
insert into TRL_UserRole(cd_user,cd_role) values (12,2);
insert into TRL_UserRole(cd_user,cd_role) values (12,4);
insert into TRL_UserRole(cd_user,cd_role) values (13,1);
insert into TRL_UserRole(cd_user,cd_role) values (14,1);
insert into TRL_UserRole(cd_user,cd_role) values (15,1);
insert into TRL_UserRole(cd_user,cd_role) values (16,1);
insert into TRL_UserRole(cd_user,cd_role) values (17,1);
insert into TRL_UserRole(cd_user,cd_role) values (18,1);
insert into TRL_UserRole(cd_user,cd_role) values (19,1);
insert into TRL_UserRole(cd_user,cd_role) values (20,1);
insert into TRL_UserRole(cd_user,cd_role) values (20,2);
insert into TRL_UserRole(cd_user,cd_role) values (20,3);
insert into TRL_UserRole(cd_user,cd_role) values (20,4);