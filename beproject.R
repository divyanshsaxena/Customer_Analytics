#Connecting MongoDB with R
library(rmongodb)
mongo <- mongo.create()
mongo
mongo.is.connected(mongo)
mongo.get.databases(mongo)
db <- "adapty"
mongo.get.database.collections(mongo, db)
srvspl2 <- "adapty.sr"
cityone <- mongo.find.all(mongo, srvspl2)
print(cityone)


SearchResultvsProductListing1 <- read.csv("C:/Users/Divyansh/Desktop/Work/Adapty/Data/SearchResultvsProductListing1.csv", stringsAsFactors=FALSE)
View(SearchResultvsProductListing1)
srvspl <- SearchResultvsProductListing1
srvspl$Date <- as.Date(srvspl$Date)
srvspl$day <- unclass(as.POSIXlt(srvspl$Date))[[4]]
srvspl$month <- unclass(as.POSIXlt(srvspl$Date))[[5]]
srvspl$year <- unclass(as.POSIXlt(srvspl$Date))[[6]] + 1900

srvspl$month <- srvspl$month+1
i <- 1
count <- 1
while(i < 732)
{
    while(i %% 7 != 0)
    { 
    srvspl$week[i] <- count
    i = i + 1
    }
    count <- count + 1
    i <- i + 1  
}

i <- 1
while( i < 732)
{
while(i %% 7 == 0)
{
  srvspl$week[i] <- srvspl$week[i-1]
  i <- i + 1
}
i <- i + 1
}

library(plotly)
srvspl$Search_Result <- srvspl$SRtoCart
srvspl$Product_Listing <- srvspl$PLtoCart
srvspl$Day <- srvspl$day
srvspl$Month <- srvspl$month
srvspl$Year <- srvspl$year
srvspl$Week <- srvspl$week
srvspl <- srvspl[,-(2:6)]
srvspl <- srvspl[,-(2)]

#Plotting Date vs Search Result
plot_ly(srvspl,y = Search_Result,x = Date)

#Plotting Date vs Product Listing
plot_ly(srvspl,y = Product_Listing,x = Date)

#Plotting Search Result vs Product Listing Weekly
library(reshape2)
q <- srvspl[,c(7,2,3)]
q <- q[1:50,]
df.long <- melt(q,id = "Week")
ggplot(df.long,aes(Week,value,fill=variable))+
  geom_bar(stat="identity",position="dodge")

#Plotting Search Result vs Product Listing Monthly
q <- srvspl[,c(5,2,3)]
q <- q[1:200,]
df.long <- melt(q,id = "Month")
ggplot(df.long,aes(Month,value,fill=variable))+
  geom_bar(stat="identity",position="dodge")

#Plotting Search Result vs Product Listing Monthly
q <- srvspl[,c(6,2,3)]
df.long <- melt(q,id = "Year")
ggplot(df.long,aes(Year,value,fill=variable))+
  geom_bar(stat="identity",position="dodge")

#Prediction of sales for next upcoming days

library(forecast)
library(timeSeries)
t <- ts(srvspl[,2], frequency = 13)
f <- forecast(t)

m <- accuracy(f)
k <- m[2]
k <- c(k, m[5])

f <- as.data.frame(f)
plot(f[,2])
lines(f[,2])









