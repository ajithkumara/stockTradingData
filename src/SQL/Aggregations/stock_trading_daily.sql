

SELECT  date as week , AVG(high) high_avg ,AVG(low) low_avg,AVG(volume) vol_avg
FROM  stock_trading_daily 
GROUP BY strftime('%W', date)
ORDER BY date


SELECT  SUBSTRING(date,0,8) month , AVG(high) ,AVG(low),AVG(volume)
FROM  stock_trading_daily 
GROUP BY strftime('%m', date)
ORDER BY date


SELECT  SUBSTRING(date,0,5) year , AVG(high) ,AVG(low),AVG(volume)
FROM  stock_trading_daily 
GROUP BY strftime('%Y', date)
ORDER BY date


SELECT  
  case 
        when 0 + strftime('%m', date) between  1 and  3 then 'Q4'
        when 0 + strftime('%m', date) between  4 and  6 then 'Q1'
        when 0 + strftime('%m', date) between  7 and  9 then 'Q2'
        when 0 + strftime('%m', date) between 10 and 12 then 'Q3'
    end as quarter,
 
 AVG(high) high_avg ,AVG(low) low_avg,AVG(volume) vol_avg
FROM  stock_trading_daily 
GROUP BY case 
        when 0 + strftime('%m', date) between  1 and  3 then 'Q4'
        when 0 + strftime('%m', date) between  4 and  6 then 'Q1'
        when 0 + strftime('%m', date) between  7 and  9 then 'Q2'
        when 0 + strftime('%m', date) between 10 and 12 then 'Q3'
    end 
ORDER BY date






