import csv
import numpy

data =[]
sum = 0
with open("2019 Winter Data Science Intern Challenge Data Set - Sheet1.csv") as csvFile:
    csvReader = csv.reader(csvFile,delimiter=',')
    next(csvReader)
    for row in csvReader:
        data.append((float(row[3])/float(row[4])))
npArray1 = numpy.array(data)
#We use numpy to calculate the first and third quartile
q3= numpy.percentile(npArray1,75)
q1= numpy.percentile(npArray1,25)
#We then prin the Inter Quartile Range (IQR)
iqr = q3-q1
print("The first quartile is "+ str(q1))
print("The third quartile is " + str(q3))
print("The inter quartile range is " +str(iqr))
print(numpy.sum(npArray1))
i = 0
while i < len(data):
    #We filter any data that is either 1.5*IQR Higher than q3, or lower than q1 - 1.5*IQR  
    if(float(data[i]) < float(q1) - (float(iqr)*1.5) or float(data[i]) > float(q3) + (float(iqr)*1.5)):
        #Uncomment line --- to include store 42
        #if(data[i] != 352.0):
            print(str(data[i]) +" "+ str(i))
            del data[i]
            i=i-1
    i=i+1
npArray2 = numpy.array(data)
print(numpy.sum(npArray2) / len(npArray2))
