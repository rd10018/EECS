-- Add below your SQL statements. 
-- You can create intermediate views (as needed). Remember to drop these views after you have populated the result tables.
-- You can use the "\i a2.sql" command in psql to execute the SQL commands in this file.

-- Query 1 statements
drop view if exists v1 cascade;
drop view if exists v2 cascade ;

create view v1 as 
select c1.cid as c1id, c1.cname as c1name, c2.cid as c2id, c2.cname as c2name, c2.height
from country c1, country c2, neighbour
where c1.cid = country and c2.cid = neighbor ; 

create view v2 as
select c1name, max(height)  as max_height
from v1
group by c1name ;

INSERT into query1(
select v1.c1id , v1.c1name , v1.c2id , v1.c2name 
from v1, v2
where v1.c1name  = v2.c1name  and v2.max_height = v1.height
order by c1name ASC 
);


-- Query 2 statements
   drop view if exists v1 cascade;
    drop view if exists v2 cascade;
    
  create view v1 as
  (select cid from country) EXCEPT (select cid from oceanAccess);
  
  create view v2 as
  select country.cid , country.cname
  from v1 , country
  where v1.cid = country.cid 
  order by cname ASC;
  
  insert into query2 (select * from v2 );

    
    
-- Query 3 statements
    drop view if exists v1 cascade;
    drop view if exists v2 cascade;
    drop view if exists v3 cascade;
    drop view if exists v4 cascade;
    
  create view v1 as
  (select cid from country) EXCEPT (select cid from oceanAccess);
  
  -- view v2 is relation for landlocked country
  create view v2 as
  select country.cid , country.cname
  from v1 , country
  where v1.cid = country.cid 
  order by cname ASC;
  
  -- view v3 is relation for neighbour count of alllandlocked countries
  create view v3 as
  select v2.cid , v2.cname , count( neighbour.neighbor) as neighbor_count
  from v2 , neighbour
  where v2.cid = neighbour.country
  group by v2.cid, v2.cname
  having count(neighbour.neighbor) = 1;
  
  create view v4 as 
  select  v3.cid as c1id , v3.cname as c1name , neighbour.neighbor as c2id , country.cname as c2name
  from v3, neighbour, country
  where v3.cid = neighbour.country and neighbour.neighbor = country.cid 
  order by c1name ASC;
  
  insert into query3(select * from v4);





-- Query 4 statements
drop view if exists v1 cascade;
drop view if exists v2 cascade;
drop view if exists v3 cascade;
drop view if exists v4 cascade;
drop view if exists v5 cascade;
drop view if exists v6 cascade;

-- view v1  consists of countries with direct ocean access
create view v1 as 
select country.cname as cname , ocean.oname as oname
from country, oceanAccess, ocean
where country.cid = oceanAccess.cid and oceanAccess.oid = ocean.oid;

--select * from v1 order by cname;

-- view v2 consists of countries without direct access, but with neighbours
create view v2 as
(select country from neighbour) EXCEPT (select cid from oceanAccess); 

--select * from v2;

create view v3 as
select v2.country, neighbour.neighbor, oid
from v2, neighbour, oceanAccess
where v2.country = neighbour.country and neighbour.neighbor = oceanAccess.cid
order by v2.country;


create view v4 as
select distinct(country.cname ) as cname  , ocean.oname as oname 
from v3, country, ocean
where v3.country = country.cid and v3.oid = ocean.oid;

--select * from v3;
--select * from v4;
create view v5 as
(select * from v1) UNION (select * from v4);

create view v6 as
select * from v5
order by cname ASC, oname DESC;

insert into query4(select * from v6);



-- Query 5 statements
drop view if exists v1 cascade;

create view v1 as
select country.cid, country.cname , AVG(hdi_score) as avghdi
from hdi , country
where hdi.cid = country.cid and (hdi.year >= 2009 and hdi.year <= 2011)
group by country.cid, country.cname
order by avghdi DESC
limit 10;

insert into query5(select  * from v1);


-- Query 6 statements
drop view if exists v1 cascade;
drop view if exists v2 cascade;
drop view if exists v3 cascade;
drop view if exists v4 cascade;
drop view if exists v5 cascade;

create view v1 as
select h1.cid as cid1, h1.year as year1, h1.hdi_score as hdi_score1, h2.cid as cid2, h2.year as year2, h2.hdi_score as hdi_score2
from hdi h1, hdi h2
where h1.cid = h2.cid and h1.hdi_score < h2.hdi_score and h1.year = 2009 and (h2.year >=2009 and h2.year<=2013);


create view v2 as
select cid2 as cid, year2 as year
from v1;

create view v3 as
select cid, year 
from hdi
where year >= 2010 and year <= 2013;

create view v4 as
select * from v3
except
select * from v2;

create view v5 as
select cid, cname
from country
where cid IN
	(select cid from v3
	except
	select cid from v4);
 
 insert into query6(select * from v5 order by v5.cname ASC);


-- Query 7 statements
drop view if exists v1 cascade;
drop view if exists v2 cascade;

create view v1 as
select religion.cid, religion.rid, religion.rname, religion.rpercentage as rpercentage, 
	population * rpercentage as followers
from religion, country
where country.cid = religion.cid
order by cid;


--select * from v1;

create view v2 as
select  rid, rname, ROUND(sum(followers)) as followers
from v1
group by rid, rname;

insert into query7(select * from v2 order by followers DESC);


-- Query 8 statements
drop view if exists v1 cascade;
drop view if exists v2 cascade;
drop view if exists v3 cascade;
drop view if exists v4 cascade;
drop view if exists v5 cascade;

create view v1 as
select cid, max(lpercentage) as max_percentage
from language
group by cid
order by cid;


-- view v2 is of countries
create view v2 as
select v1.cid , country.cname, language.lname as most_popular
from  v1, language, country
where v1.cid = language.cid and v1.max_percentage = language.lpercentage and country.cid = v1.cid;


create view v3 as
select neighbour.country as c1id, v2.cname as c1name ,v2.most_popular as c1lang,  neighbour.neighbor  
from neighbour, v2 
where neighbour.country = v2.cid ;


create view v4 as
select v3.c1name, v3.c1lang, v2.cname as c2name , v2.most_popular as c2lang
from v3, v2
where v3.neighbor = v2.cid;


create view v5 as
select v4.c1name , v4.c2name , v4.c1lang as lname 
from v4
where v4.c1lang = v4.c2lang
order by lname ASC , c1name DESC;

insert into query8(select * from v5);


-- Query 9 statements
drop view if exists v1 cascade;
drop view if exists v2 cascade;
drop view if exists v3 cascade;

create view v1 as
select oceanAccess.cid, max(ocean.depth) as max_depth
from oceanAccess, ocean
where oceanAccess.oid = ocean.oid
group by oceanAccess.cid
order by cid;

create view v2 as
select cid, height
from country
order by cid;

create view v3 as
select country.cname , v2.height - v1.max_depth as totalspan
from v1,v2, country
where v2.cid = v1.cid and v2.cid = country.cid;

insert into query9(select cname , totalspan  from v3 where totalspan = (select max(totalspan) from v3));


-- Query 10 statements
drop view if exists v1 cascade;
drop view if exists v2 cascade;


create view v1 as
select country, sum(length) as borders
from neighbour 
group by country
order by country ASC;


-- select * from v1;

create view v2 as 
select country.cname , borders as borderslength
from v1, country
where country.cid = v1.country
	and borders IN (select max(borders) from v1);
 
 insert into query10(select * from v2);

