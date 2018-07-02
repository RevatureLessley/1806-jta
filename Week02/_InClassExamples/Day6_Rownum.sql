--ROWNUM
SELECT * FROM npc WHERE currency in
(select MIN(currency) FROM(
SELECT * FROM npc order by currency desc) 
WHERE rownum = 7);