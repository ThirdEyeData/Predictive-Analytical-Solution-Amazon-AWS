__author__ = 'abhinandan'

import csv
import subprocess
import threading
import tinys3
import psycopg2

def scheduler():
    # do something here ...
    # call f() again in 60 seconds
    threading.Timer(300, scheduler).start()
    subprocess.check_call(['Rscript', 'C://Users//Administrator//Scheduler//WeatherLR.R'], shell=False)
    #CSV Modification
    csvmofification()
    #Connect to S3
    s3fileuplaod()
    #RedShift Connection
    redshiftUpload()


#S3 File Upload
def s3fileuplaod():
    s3 = tinys3.Connection("AKIAJZJDCKYE224FBKUA","U+RBTBz3M6UBt1muMxfN2hlX/bkIUs9DAZQiypiq",tls=True,endpoint='s3-us-west-2.amazonaws.com')
    f = open('C://Users//Administrator//Output//Prediction-new.csv','rb')
    s3.upload('Prediction-new.csv',f,'basebucket-55862-s3disk-0')
    print("Uploaded")

#CSV modification
def csvmofification():
   with open("C://Users//Administrator//Output//prediction.csv", "rb") as infile, open("C://Users//Administrator//Output//Prediction-new.csv", "wb") as outfile:
     reader = csv.reader(infile)
     next(reader, None)  # skip the headers
     writer = csv.writer(outfile)
     begin = 1
     end = 14
     for row in reader:
         writer.writerow(row[begin:end])

#RedShift Connection
def redshiftUpload():
   conn_string = "dbname='mydb' port='5439' user='master' password='BigData257' host='vredshift.cziozxqpyojq.us-west-2.redshift.amazonaws.com'";
   conn = psycopg2.connect(conn_string);

   truncatesql = conn.cursor();
   truncatesql.execute("truncate weather_prediction");
   conn.commit();
   truncatesql.close();

   loaddata = conn.cursor();
   loaddata.execute("copy weather_prediction from 's3://basebucket-55862-s3disk-0/Prediction-new.csv' credentials 'aws_access_key_id=AKIAJZJDCKYE224FBKUA;aws_secret_access_key=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' delimiter ',' region 'us-west-2'");
   conn.commit();
   loaddata.close();
   conn.close();
   print("done")


# start calling f now and every 5 mins thereafter
scheduler()
