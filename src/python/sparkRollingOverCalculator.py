# This Pyspark code snippet to calculate the rolling average from the source data 
# and store back to the DB

################################# 20 Days ##########################################

#create window by casting timestamp to long (number of seconds)
days20 = (Window()
     .partitionBy(col("stock_name"))
     .orderBy(F.col("date").cast('long'))
     .rangeBetween(-days(20), 0))
     
df20 = df.withColumn('rolling_average_high', F.avg("high").over(days20))
        .withColumn('rolling_average_low', F.avg("low").over(days20))
        .withColumn('rolling_average_volume', F.avg("volume").over(days20))
        
#Later write back this data to 20days table DB/via Data catalog

################################# 50 Days ##########################################
     
days50 = (Window()
     .partitionBy(col("stock_name"))
     .orderBy(F.col("date").cast('long'))
     .rangeBetween(-days(50), 0))
    
df50 = df.withColumn('rolling_average_high', F.avg("high").over(days50))
        .withColumn('rolling_average_low', F.avg("low").over(days50))
        .withColumn('rolling_average_volume', F.avg("volume").over(days50))
        
#Later write back this data to  50days table DB or via Data catalog

     
     
################################# 200 Days ##########################################

     
days200 = (Window()
     .partitionBy(col("stock_name"))
     .orderBy(F.col("date").cast('long'))
     .rangeBetween(-days(200), 0))


df200 = df.withColumn('rolling_average_high', F.avg("high").over(days200))
        .withColumn('rolling_average_low', F.avg("low").over(days200))
        .withColumn('rolling_average_volume', F.avg("volume").over(days200))
        
#Later write back this data to 200days table DB or via Data catalog

################################# END  ############################################








