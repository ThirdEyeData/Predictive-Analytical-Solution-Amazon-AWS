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
    subprocess.check_call(['Rscript', 'C://Users//Administrator//Documents//WeatherLR.R'], shell=False)
    #CSV Modification
    csvmofification()
    #Connect to S3
    s3fileuplaod()
    #RedShift Connection
    redshiftUpload()


#S3 File Upload
def s3fileuplaod():
    s3 = tinys3.Connection("AKIAJFETOLAADYA37PTQ","9YJ5vW0xxp/GzVtoVDrB604L7qYpNUR2MQjMexhQ",tls=True,endpoint='s3-us-west-2.amazonaws.com')
    f = open('C://Users//Administrator//Documents//Prediction-new.csv','rb')
    s3.upload('Prediction-new.csv',f,'amazon-aws-immersion-project')
    print("Uploaded")

#CSV modification
def csvmofification():
   with open("C://Users//Administrator//Documents//prediction.csv", "rb") as infile, open("C://Users//Administrator//Documents//Prediction-new.csv", "wb") as outfile:
     reader = csv.reader(infile)
     next(reader, None)  # skip the headers
     writer = csv.writer(outfile)
     begin = 1
     end = 14
     for row in reader:
         writer.writerow(row[begin:end])

#RedShift Connection
def redshiftUpload():
   conn_string = "dbname='immersion' port='5439' user='immersion' password='Immersion!2016' host='immersion-project.cziozxqpyojq.us-west-2.redshift.amazonaws.com'";
   conn = psycopg2.connect(conn_string);

   truncatesql = conn.cursor();
   truncatesql.execute("truncate weather_prediction");
   conn.commit();
   truncatesql.close();

   loaddata = conn.cursor();
   loaddata.execute("copy weather_prediction from 's3://amazon-aws-immersion-project/Prediction-new.csv' credentials 'aws_access_key_id=AKIAJFETOLAADYA37PTQ;aws_secret_access_key=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' delimiter ',' region 'us-west-2'");
   conn.commit();
   loaddata.close();
   conn.close();
   print("done")


# start calling f now and every 5 mins thereafter
scheduler()
