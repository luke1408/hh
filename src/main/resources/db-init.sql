create table user(
	id integer auto_increment primary key,
	name varchar(35) not null,
	password CHAR(32) not null unique,
	email varchar(100) not null,
	admin boolean not null
);

create table unit(
	id integer auto_increment primary key,
	name varchar(50) not null
);

create table product(
	id integer auto_increment primary key,
	name varchar(35) not null,
	description varchar(500) not null,
	price number not null,
	amount integer not null,
	unit integer not null
);

alter table product 
add foreign key(unit)
references unit(id);


create table o_order(
	id integer auto_increment primary key,
	amount integer not null,
	product integer not null,
	user integer not null,
	active boolean not null default true
);

alter table o_order
add foreign key(product)
references product(id);

alter table o_order
add foreign key(user)
references user(id);

create view active_order as 
select * from o_order where active = true;

create view product_ordered_amount as
select product, sum(amount) as amount from o_order 
group by product;

create view product_active_amount as
select p.id, p.name, p.description, p.price,  ifnull((p.amount - poa.amount), p.amount) as amount, unit from product p
left join product_ordered_amount poa on p.id = poa.product;

create view orderable_product as 
select * from product_active_amount where amount > 0;

create view admin_user as
select * from user where admin = true;

create view regular_user as
select * from user where admin = false;

insert into user (name, password, email, admin) values ('User1', 'asdf1234', 'asdf@gmx.at', false);
insert into user (name, password, email, admin) values ('User2', 'asdf1224', 'asdf3@gmx.at', false);
insert into user (name, password, email, admin) values ('User3', 'assf1224', 'asdf5@gmx.at', false);
insert into user (name, password, email, admin) values ('admin', 'admin', 'asd2f@gmx.at', true);
insert into unit (name) values ('kg');
insert into unit (name) values ('Stück');
insert into product (name, description, price, amount, unit) values ('Lotus Leaves', 'redefine end-to-end systems', '2.50', 39, 1);
insert into product (name, description, price, amount, unit) values ('Yogurt - Assorted Pack', 'architect holistic infrastructures', '0.54', 60, 1);
insert into product (name, description, price, amount, unit) values ('French Pastry - Mini Chocolate', 'exploit turn-key platforms', '3.69', 24, 1);
insert into product (name, description, price, amount, unit) values ('Beef - Ground, Extra Lean, Fresh', 'reintermediate sexy e-markets', '5.07', 83, 1);
insert into product (name, description, price, amount, unit) values ('Wine - White, Pinot Grigio', 'empower plug-and-play users', '2.86', 25, 1);
insert into product (name, description, price, amount, unit) values ('Tuna - Canned, Flaked, Light', 'synthesize visionary portals', '3.65', 25, 1);
insert into product (name, description, price, amount, unit) values ('Shrimp - 150 - 250', 'syndicate 24/365 technologies', '8.27', 92, 1);
insert into product (name, description, price, amount, unit) values ('Syrup - Golden, Lyles', 'expedite distributed schemas', '0.52', 87, 1);
insert into product (name, description, price, amount, unit) values ('Cheese - Perron Cheddar', 'expedite next-generation partnerships', '1.36', 48, 1);
insert into product (name, description, price, amount, unit) values ('Bread - Olive Dinner Roll', 'enable B2C e-commerce', '6.65', 57, 1);
insert into product (name, description, price, amount, unit) values ('Oil - Shortening,liqud, Fry', 'mesh user-centric schemas', '1.35', 94, 1);
insert into product (name, description, price, amount, unit) values ('Dr. Pepper - 355ml', 'incentivize one-to-one deliverables', '1.83', 23, 1);
insert into product (name, description, price, amount, unit) values ('Turnip - Wax', 'benchmark cutting-edge schemas', '8.44', 72, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Soave Folonari', 'mesh magnetic niches', '0.59', 65, 1);
insert into product (name, description, price, amount, unit) values ('Sage Derby', 'reinvent back-end networks', '7.30', 90, 1);
insert into product (name, description, price, amount, unit) values ('Whmis - Spray Bottle Trigger', 'exploit mission-critical initiatives', '5.53', 65, 1);
insert into product (name, description, price, amount, unit) values ('Pork - Loin, Center Cut', 'aggregate bricks-and-clicks systems', '9.24', 21, 1);
insert into product (name, description, price, amount, unit) values ('Cafe Royale', 'engage vertical portals', '2.08', 32, 1);
insert into product (name, description, price, amount, unit) values ('Marzipan 50/50', 'reinvent end-to-end architectures', '3.23', 77, 1);
insert into product (name, description, price, amount, unit) values ('V8 - Vegetable Cocktail', 'engineer extensible technologies', '9.69', 67, 1);
insert into product (name, description, price, amount, unit) values ('Puree - Blackcurrant', 'harness interactive schemas', '0.10', 65, 1);
insert into product (name, description, price, amount, unit) values ('Pepper - Red Thai', 'engage vertical convergence', '1.13', 58, 1);
insert into product (name, description, price, amount, unit) values ('Wine - White, French Cross', 'incentivize end-to-end methodologies', '4.02', 54, 1);
insert into product (name, description, price, amount, unit) values ('Country Roll', 'architect strategic channels', '6.90', 49, 1);
insert into product (name, description, price, amount, unit) values ('Beer - Original Organic Lager', 'engineer clicks-and-mortar e-tailers', '2.74', 50, 1);
insert into product (name, description, price, amount, unit) values ('Pork - Butt, Boneless', 'morph next-generation solutions', '6.36', 24, 1);
insert into product (name, description, price, amount, unit) values ('Squid - Breaded', 'synthesize killer models', '3.45', 49, 1);
insert into product (name, description, price, amount, unit) values ('Rum - Dark, Bacardi, Black', 'implement ubiquitous users', '8.16', 97, 1);
insert into product (name, description, price, amount, unit) values ('Venison - Ground', 'strategize cross-media e-tailers', '0.01', 23, 1);
insert into product (name, description, price, amount, unit) values ('Brandy Cherry - Mcguinness', 'scale cross-media systems', '9.80', 23, 1);
insert into product (name, description, price, amount, unit) values ('Sprouts - Pea', 'repurpose B2B markets', '1.66', 82, 1);
insert into product (name, description, price, amount, unit) values ('Coffee Cup 8oz 5338cd', 'matrix robust convergence', '4.68', 29, 1);
insert into product (name, description, price, amount, unit) values ('Phyllo Dough', 'utilize revolutionary eyeballs', '4.95', 91, 1);
insert into product (name, description, price, amount, unit) values ('Sugar - Fine', 'incubate clicks-and-mortar models', '9.08', 74, 1);
insert into product (name, description, price, amount, unit) values ('Celery', 'scale 24/7 partnerships', '8.54', 70, 1);
insert into product (name, description, price, amount, unit) values ('Bread - Granary Small Pull', 'deliver B2C eyeballs', '4.20', 70, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Charddonnay Errazuriz', 'orchestrate user-centric eyeballs', '3.54', 43, 1);
insert into product (name, description, price, amount, unit) values ('Beer - Alexander Kieths, Pale Ale', 'integrate rich functionalities', '2.02', 90, 1);
insert into product (name, description, price, amount, unit) values ('Soup - Verve - Chipotle Chicken', 'maximize integrated e-services', '8.88', 71, 1);
insert into product (name, description, price, amount, unit) values ('Energy Drink', 'morph innovative infomediaries', '2.88', 97, 1);
insert into product (name, description, price, amount, unit) values ('Petite Baguette', 'incentivize one-to-one e-markets', '9.39', 97, 1);
insert into product (name, description, price, amount, unit) values ('Dried Cranberries', 'enhance mission-critical e-markets', '1.85', 49, 1);
insert into product (name, description, price, amount, unit) values ('Olives - Kalamata', 'harness dot-com synergies', '0.87', 91, 1);
insert into product (name, description, price, amount, unit) values ('Cheese Cloth No 100', 'scale dynamic experiences', '7.76', 90, 1);
insert into product (name, description, price, amount, unit) values ('Onions - Green', 'revolutionize one-to-one niches', '9.07', 51, 1);
insert into product (name, description, price, amount, unit) values ('Rye Special Old', 'disintermediate out-of-the-box bandwidth', '7.67', 73, 1);
insert into product (name, description, price, amount, unit) values ('Oil - Canola', 'facilitate scalable models', '2.39', 55, 1);
insert into product (name, description, price, amount, unit) values ('Silicone Parch. 16.3x24.3', 'maximize impactful interfaces', '1.64', 100, 1);
insert into product (name, description, price, amount, unit) values ('Port - 74 Brights', 'e-enable wireless convergence', '6.41', 29, 1);
insert into product (name, description, price, amount, unit) values ('Soup - French Onion, Dry', 'incentivize one-to-one applications', '7.17', 32, 1);
insert into product (name, description, price, amount, unit) values ('Pork - Side Ribs', 'incentivize customized networks', '3.35', 76, 1);
insert into product (name, description, price, amount, unit) values ('Mackerel Whole Fresh', 'generate visionary action-items', '7.59', 29, 1);
insert into product (name, description, price, amount, unit) values ('Syrup - Monin - Passion Fruit', 'transition transparent markets', '0.59', 33, 1);
insert into product (name, description, price, amount, unit) values ('Vector Energy Bar', 'optimize value-added solutions', '0.33', 43, 1);
insert into product (name, description, price, amount, unit) values ('Pie Filling - Apple', 'redefine revolutionary bandwidth', '6.77', 52, 1);
insert into product (name, description, price, amount, unit) values ('Calypso - Lemonade', 'visualize scalable markets', '8.41', 67, 1);
insert into product (name, description, price, amount, unit) values ('Shrimp - 21/25, Peel And Deviened', 'deliver value-added users', '0.67', 72, 1);
insert into product (name, description, price, amount, unit) values ('Pork - Inside', 'e-enable end-to-end platforms', '5.42', 50, 1);
insert into product (name, description, price, amount, unit) values ('Pastry - Chocolate Marble Tea', 'maximize end-to-end e-tailers', '7.50', 77, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Beringer Founders Estate', 'seize turn-key bandwidth', '0.71', 35, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Cabernet Sauvignon', 'architect rich partnerships', '2.11', 85, 1);
insert into product (name, description, price, amount, unit) values ('Cheese - Parmigiano Reggiano', 'reintermediate wireless e-tailers', '0.42', 43, 1);
insert into product (name, description, price, amount, unit) values ('Bagel - Everything Presliced', 'maximize real-time infomediaries', '7.98', 100, 1);
insert into product (name, description, price, amount, unit) values ('Energy Drink - Franks Original', 'morph world-class technologies', '4.96', 23, 1);
insert into product (name, description, price, amount, unit) values ('Almonds Ground Blanched', 'whiteboard sexy web-readiness', '6.99', 96, 1);
insert into product (name, description, price, amount, unit) values ('Snapple - Mango Maddness', 'morph open-source functionalities', '5.34', 84, 1);
insert into product (name, description, price, amount, unit) values ('Schnappes Peppermint - Walker', 'orchestrate enterprise e-business', '1.78', 40, 1);
insert into product (name, description, price, amount, unit) values ('Beef - Tenderloin', 'exploit value-added niches', '6.74', 96, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Two Oceans Cabernet', 'leverage dot-com infrastructures', '6.54', 87, 1);
insert into product (name, description, price, amount, unit) values ('Rosemary - Dry', 'strategize revolutionary interfaces', '5.45', 77, 1);
insert into product (name, description, price, amount, unit) values ('Tofu - Firm', 'mesh front-end solutions', '5.14', 34, 1);
insert into product (name, description, price, amount, unit) values ('Cheese - Goat', 'benchmark ubiquitous technologies', '5.91', 50, 1);
insert into product (name, description, price, amount, unit) values ('Pasta - Fettuccine, Egg, Fresh', 'seize 24/7 interfaces', '9.89', 94, 1);
insert into product (name, description, price, amount, unit) values ('Chocolate - Sugar Free Semi Choc', 'e-enable integrated mindshare', '3.50', 33, 1);
insert into product (name, description, price, amount, unit) values ('Olives - Green, Pitted', 'generate value-added schemas', '3.76', 59, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Red, Harrow Estates, Cab', 'transition out-of-the-box e-commerce', '1.16', 86, 1);
insert into product (name, description, price, amount, unit) values ('Caviar - Salmon', 'redefine impactful infomediaries', '1.86', 63, 1);
insert into product (name, description, price, amount, unit) values ('Chicken Giblets', 'deliver world-class initiatives', '6.68', 47, 1);
insert into product (name, description, price, amount, unit) values ('Flour - Whole Wheat', 'evolve world-class initiatives', '8.05', 47, 1);
insert into product (name, description, price, amount, unit) values ('Mackerel Whole Fresh', 'integrate frictionless infomediaries', '7.75', 32, 1);
insert into product (name, description, price, amount, unit) values ('Steam Pan Full Lid', 'reinvent turn-key interfaces', '0.03', 38, 1);
insert into product (name, description, price, amount, unit) values ('Lamb - Shoulder', 'enhance dynamic networks', '5.57', 86, 1);
insert into product (name, description, price, amount, unit) values ('Coffee - Colombian, Portioned', 'unleash granular technologies', '0.98', 97, 1);
insert into product (name, description, price, amount, unit) values ('Quinoa', 'grow distributed infrastructures', '9.87', 50, 1);
insert into product (name, description, price, amount, unit) values ('Garam Marsala', 'deliver efficient supply-chains', '4.61', 67, 1);
insert into product (name, description, price, amount, unit) values ('Veal - Inside', 'enhance seamless interfaces', '3.01', 27, 1);
insert into product (name, description, price, amount, unit) values ('Bread - Bistro Sour', 'transform mission-critical ROI', '1.35', 75, 1);
insert into product (name, description, price, amount, unit) values ('Juice - Mango', 'target sexy experiences', '4.42', 79, 1);
insert into product (name, description, price, amount, unit) values ('Aromat Spice / Seasoning', 'innovate B2B relationships', '4.66', 99, 1);
insert into product (name, description, price, amount, unit) values ('Beans - Soya Bean', 'revolutionize virtual architectures', '9.57', 33, 1);
insert into product (name, description, price, amount, unit) values ('Artichokes - Knobless, White', 'enhance one-to-one paradigms', '8.15', 38, 1);
insert into product (name, description, price, amount, unit) values ('Shiratamako - Rice Flour', 'synergize bricks-and-clicks infrastructures', '9.87', 37, 1);
insert into product (name, description, price, amount, unit) values ('Venison - Liver', 'evolve robust action-items', '2.91', 90, 1);
insert into product (name, description, price, amount, unit) values ('Soup Campbells Split Pea And Ham', 'unleash viral convergence', '1.21', 50, 1);
insert into product (name, description, price, amount, unit) values ('The Pop Shoppe - Black Cherry', 'streamline e-business relationships', '0.52', 63, 1);
insert into product (name, description, price, amount, unit) values ('Star Fruit', 'morph cutting-edge initiatives', '3.98', 95, 1);
insert into product (name, description, price, amount, unit) values ('Quail - Whole, Bone - In', 'recontextualize B2C content', '6.99', 64, 1);
insert into product (name, description, price, amount, unit) values ('Pepper - Gypsy Pepper', 'integrate out-of-the-box vortals', '1.14', 79, 1);
insert into product (name, description, price, amount, unit) values ('Lid - 16 Oz And 32 Oz', 'benchmark strategic ROI', '6.36', 88, 1);
insert into product (name, description, price, amount, unit) values ('Langers - Ruby Red Grapfruit', 'seize cross-platform models', '2.89', 85, 1);
insert into product (name, description, price, amount, unit) values ('Shrimp - Tiger 21/25', 'orchestrate real-time niches', '6.75', 21, 1);
insert into product (name, description, price, amount, unit) values ('Soup - Campbells, Lentil', 'recontextualize intuitive architectures', '7.28', 64, 1);
insert into product (name, description, price, amount, unit) values ('Lychee - Canned', 'embrace real-time applications', '7.60', 57, 1);
insert into product (name, description, price, amount, unit) values ('Sparkling Wine - Rose, Freixenet', 'optimize clicks-and-mortar markets', '6.89', 24, 1);
insert into product (name, description, price, amount, unit) values ('Bread - Crusty Italian Poly', 'generate best-of-breed niches', '4.31', 75, 1);
insert into product (name, description, price, amount, unit) values ('Beef - Tenderloin', 'optimize end-to-end platforms', '7.76', 48, 1);
insert into product (name, description, price, amount, unit) values ('Pate - Cognac', 'visualize open-source e-services', '5.33', 60, 1);
insert into product (name, description, price, amount, unit) values ('Steampan - Lid For Half Size', 'integrate compelling architectures', '8.73', 47, 1);
insert into product (name, description, price, amount, unit) values ('Pepper - Paprika, Hungarian', 'seize robust technologies', '2.65', 86, 1);
insert into product (name, description, price, amount, unit) values ('Sugar - Invert', 'architect web-enabled e-business', '0.11', 67, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Beringer Founders Estate', 'streamline web-enabled vortals', '4.49', 28, 1);
insert into product (name, description, price, amount, unit) values ('Cheese - Montery Jack', 'drive customized e-tailers', '5.52', 98, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Jackson Triggs Okonagan', 'engineer turn-key applications', '7.11', 60, 1);
insert into product (name, description, price, amount, unit) values ('Chicken - Wieners', 'utilize 24/365 users', '4.66', 60, 1);
insert into product (name, description, price, amount, unit) values ('Thermometer Digital', 'cultivate extensible infrastructures', '6.66', 58, 1);
insert into product (name, description, price, amount, unit) values ('Containter - 3oz Microwave Rect.', 'recontextualize strategic systems', '8.68', 73, 1);
insert into product (name, description, price, amount, unit) values ('Lettuce - Baby Salad Greens', 'recontextualize B2C partnerships', '6.04', 87, 1);
insert into product (name, description, price, amount, unit) values ('Bread - French Baquette', 'maximize world-class e-tailers', '5.66', 50, 1);
insert into product (name, description, price, amount, unit) values ('The Pop Shoppe Pinapple', 'enable extensible web-readiness', '6.05', 30, 1);
insert into product (name, description, price, amount, unit) values ('Apples - Sliced / Wedge', 'syndicate dynamic initiatives', '3.65', 48, 1);
insert into product (name, description, price, amount, unit) values ('Soup - Base Broth Chix', 'redefine granular schemas', '6.56', 42, 1);
insert into product (name, description, price, amount, unit) values ('Venison - Ground', 'whiteboard real-time e-commerce', '7.83', 98, 1);
insert into product (name, description, price, amount, unit) values ('Bread - Multigrain, Loaf', 'e-enable vertical infomediaries', '9.50', 48, 1);
insert into product (name, description, price, amount, unit) values ('Crab - Claws, 26 - 30', 'matrix impactful paradigms', '3.51', 43, 1);
insert into product (name, description, price, amount, unit) values ('Pepperoni Slices', 'recontextualize global e-markets', '4.93', 49, 1);
insert into product (name, description, price, amount, unit) values ('Pasta - Bauletti, Chicken White', 'transform intuitive methodologies', '8.72', 27, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Cabernet Sauvignon', 'brand transparent e-business', '2.52', 63, 1);
insert into product (name, description, price, amount, unit) values ('Longos - Cheese Tortellini', 'unleash out-of-the-box models', '4.85', 51, 1);
insert into product (name, description, price, amount, unit) values ('Tomato - Tricolor Cherry', 'seize one-to-one systems', '7.41', 60, 1);
insert into product (name, description, price, amount, unit) values ('Apricots - Dried', 'utilize cross-platform communities', '7.38', 55, 1);
insert into product (name, description, price, amount, unit) values ('External Supplier', 'deliver global action-items', '7.81', 45, 1);
insert into product (name, description, price, amount, unit) values ('Salsify, Organic', 'implement value-added ROI', '2.89', 40, 1);
insert into product (name, description, price, amount, unit) values ('Kellogs All Bran Bars', 'enable magnetic ROI', '9.16', 23, 1);
insert into product (name, description, price, amount, unit) values ('Lid Coffee Cup 8oz Blk', 'synergize customized solutions', '4.60', 36, 1);
insert into product (name, description, price, amount, unit) values ('Yokaline', 'embrace turn-key bandwidth', '3.89', 73, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Manischewitz Concord', 'orchestrate proactive metrics', '9.48', 62, 1);
insert into product (name, description, price, amount, unit) values ('Water - Mineral, Natural', 'synergize ubiquitous web-readiness', '9.93', 28, 1);
insert into product (name, description, price, amount, unit) values ('Triple Sec - Mcguinness', 'e-enable e-business web-readiness', '8.28', 49, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Pinot Noir Mondavi Coastal', 'e-enable value-added niches', '7.51', 47, 1);
insert into product (name, description, price, amount, unit) values ('Foam Cup 6 Oz', 'monetize 24/7 e-commerce', '2.51', 38, 1);
insert into product (name, description, price, amount, unit) values ('Dried Cherries', 'brand front-end action-items', '3.56', 42, 1);
insert into product (name, description, price, amount, unit) values ('Nestea - Iced Tea', 'brand robust models', '0.23', 23, 1);
insert into product (name, description, price, amount, unit) values ('Coffee Caramel Biscotti', 'cultivate synergistic paradigms', '1.18', 93, 1);
insert into product (name, description, price, amount, unit) values ('Cheese - Le Cheve Noir', 'matrix world-class e-business', '2.02', 29, 1);
insert into product (name, description, price, amount, unit) values ('Onions - Cooking', 'e-enable e-business schemas', '9.93', 85, 1);
insert into product (name, description, price, amount, unit) values ('Rappini - Andy Boy', 'innovate 24/365 eyeballs', '8.18', 99, 1);
insert into product (name, description, price, amount, unit) values ('Salmon - Atlantic, Skin On', 'leverage B2B action-items', '5.61', 59, 1);
insert into product (name, description, price, amount, unit) values ('Bread - Burger', 'enable impactful partnerships', '1.35', 44, 1);
insert into product (name, description, price, amount, unit) values ('Nut - Pecan, Halves', 'envisioneer seamless vortals', '8.63', 88, 1);
insert into product (name, description, price, amount, unit) values ('Flavouring Vanilla Artificial', 'repurpose front-end convergence', '3.83', 63, 1);
insert into product (name, description, price, amount, unit) values ('Salmon Steak - Cohoe 6 Oz', 'strategize sexy e-markets', '8.19', 79, 1);
insert into product (name, description, price, amount, unit) values ('Cakes Assorted', 'brand seamless convergence', '5.81', 93, 1);
insert into product (name, description, price, amount, unit) values ('Beer - Labatt Blue', 'architect leading-edge mindshare', '4.46', 40, 1);
insert into product (name, description, price, amount, unit) values ('Longos - Burritos', 'synthesize strategic web-readiness', '4.80', 98, 1);
insert into product (name, description, price, amount, unit) values ('Frangelico', 'embrace ubiquitous channels', '0.42', 24, 1);
insert into product (name, description, price, amount, unit) values ('Cabbage Roll', 'transition B2B platforms', '4.16', 47, 1);
insert into product (name, description, price, amount, unit) values ('Paper Cocktail Umberlla 80 - 180', 'synergize sexy applications', '4.08', 80, 1);
insert into product (name, description, price, amount, unit) values ('Beer - Corona', 'deploy cutting-edge partnerships', '1.51', 57, 1);
insert into product (name, description, price, amount, unit) values ('Grapes - Red', 'e-enable killer infomediaries', '9.50', 88, 1);
insert into product (name, description, price, amount, unit) values ('Longos - Burritos', 'enable open-source platforms', '1.47', 70, 1);
insert into product (name, description, price, amount, unit) values ('Capers - Pickled', 'synthesize wireless eyeballs', '5.51', 81, 1);
insert into product (name, description, price, amount, unit) values ('Brandy Apricot', 'transition world-class e-markets', '7.99', 77, 1);
insert into product (name, description, price, amount, unit) values ('Chutney Sauce - Mango', 'visualize customized systems', '1.28', 43, 1);
insert into product (name, description, price, amount, unit) values ('Pasta - Penne, Rigate, Dry', 'utilize plug-and-play communities', '4.67', 79, 1);
insert into product (name, description, price, amount, unit) values ('Wine - Periguita Fonseca', 'transform sticky schemas', '9.08', 30, 1);
insert into product (name, description, price, amount, unit) values ('Veal - Chops, Split, Frenched', 'target end-to-end architectures', '0.67', 92, 1);
insert into product (name, description, price, amount, unit) values ('Ham - Procutinni', 'utilize granular solutions', '2.60', 85, 1);
insert into product (name, description, price, amount, unit) values ('Doilies - 8, Paper', 'utilize ubiquitous users', '1.23', 83, 1);
insert into product (name, description, price, amount, unit) values ('Beer - Original Organic Lager', 'reinvent magnetic infrastructures', '2.39', 32, 1);
insert into product (name, description, price, amount, unit) values ('Beef - Outside, Round', 'syndicate 24/365 applications', '5.41', 79, 1);
insert into product (name, description, price, amount, unit) values ('Zucchini - Yellow', 'seize one-to-one paradigms', '0.68', 77, 1);
insert into product (name, description, price, amount, unit) values ('Coffee - Decafenated', 'seize visionary channels', '5.19', 91, 1);
insert into product (name, description, price, amount, unit) values ('Asparagus - Mexican', 'repurpose e-business architectures', '9.32', 74, 1);
insert into product (name, description, price, amount, unit) values ('Onions - Spanish', 'disintermediate cross-media e-tailers', '8.75', 62, 1);
insert into product (name, description, price, amount, unit) values ('Pie Box - Cello Window 2.5', 'enhance strategic solutions', '4.41', 75, 1);
insert into product (name, description, price, amount, unit) values ('Pepper - Red Chili', 'unleash innovative synergies', '8.41', 43, 1);
insert into product (name, description, price, amount, unit) values ('Galliano', 'leverage enterprise vortals', '0.47', 86, 1);
insert into product (name, description, price, amount, unit) values ('Muffin Hinge Container 6', 'seize killer e-services', '6.77', 21, 1);
insert into product (name, description, price, amount, unit) values ('Tuna - Loin', 'e-enable value-added schemas', '2.69', 81, 1);
insert into product (name, description, price, amount, unit) values ('Pasta - Rotini, Dry', 'seize ubiquitous paradigms', '1.11', 29, 1);
insert into product (name, description, price, amount, unit) values ('Vinegar - Raspberry', 'disintermediate distributed channels', '0.05', 94, 1);
insert into product (name, description, price, amount, unit) values ('Skewers - Bamboo', 'syndicate frictionless deliverables', '5.54', 36, 1);
insert into product (name, description, price, amount, unit) values ('V8 - Tropical Blend', 'benchmark enterprise e-business', '2.04', 66, 1);
insert into product (name, description, price, amount, unit) values ('Beef - Rib Eye Aaa', 'engage scalable relationships', '9.97', 72, 1);
insert into product (name, description, price, amount, unit) values ('Beef - Cooked, Corned', 'monetize virtual solutions', '0.36', 61, 1);
insert into product (name, description, price, amount, unit) values ('Kiwano', 'reinvent robust technologies', '1.16', 21, 1);
insert into product (name, description, price, amount, unit) values ('Salt - Table', 'transform innovative e-commerce', '3.73', 36, 1);
insert into product (name, description, price, amount, unit) values ('Squid - U 5', 'implement seamless applications', '4.34', 81, 1);
insert into product (name, description, price, amount, unit) values ('Dried Figs', 'transform magnetic functionalities', '0.03', 38, 1);
insert into product (name, description, price, amount, unit) values ('Rice - Brown', 'facilitate bricks-and-clicks models', '4.61', 27, 1);
insert into product (name, description, price, amount, unit) values ('Puree - Passion Fruit', 'enable bleeding-edge architectures', '6.45', 48, 1);
insert into product (name, description, price, amount, unit) values ('Pasta - Tortellini, Fresh', 'maximize back-end relationships', '0.07', 72, 1);
insert into product (name, description, price, amount, unit) values ('Table Cloth 54x54 Colour', 'brand frictionless e-commerce', '3.81', 77, 1);
insert into product (name, description, price, amount, unit) values ('Plaintain', 'evolve B2B metrics', '7.43', 22, 1);
insert into product (name, description, price, amount, unit) values ('Mahi Mahi', 'extend seamless synergies', '6.37', 81, 1);
insert into product (name, description, price, amount, unit) values ('Tea - Herbal Orange Spice', 'reintermediate 24/365 models', '4.70', 76, 1);
insert into product (name, description, price, amount, unit) values ('Carbonated Water - Wildberry', 'utilize virtual content', '7.62', 99, 1);
insert into product (name, description, price, amount, unit) values ('Soup - Campbells - Tomato', 'reintermediate next-generation web services', '4.93', 50, 1);
insert into product (name, description, price, amount, unit) values ('Peppercorns - Pink', 'facilitate scalable channels', '6.41', 82, 1);
insert into product (name, description, price, amount, unit) values ('Pork Loin Bine - In Frenched', 'facilitate cross-media convergence', '1.49', 80, 1);


