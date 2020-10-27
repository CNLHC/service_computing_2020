import numpy as np
import os
import pandas

class ds:  
    def __init__(self,dsroot:str):
        with open(os.path.join(dsroot,'dataset1','rtMatrix.txt'),"r") as fp:
            self.rtMat = pandas.read_csv(fp,header=None,sep="\t").to_numpy()[:,:-1]
        with open(os.path.join(dsroot,'dataset1','tpMatrix.txt'),"r") as fp:
            self.tpMat = pandas.read_csv(fp,header=None,sep="\t").to_numpy()[:,:-1]
        with open(os.path.join(dsroot,'dataset1','userlist.txt'),"r") as fp:
            self.userList= pandas.read_csv(fp,sep="\t").iloc[1:]
        with open(os.path.join(dsroot,'dataset1','wslist.txt'),"rb") as fp:
            self.wsList= pandas.read_csv(fp,sep="\t",encoding="latin1").iloc[1:]
        with open(os.path.join(dsroot,'dataset2','rtdata.txt'),"r") as fp:
            self.rtdata = pandas.read_csv(fp,sep=" ",header=None).to_numpy()
        with open(os.path.join(dsroot,'dataset2','tpdata.txt'),"r") as fp:
            self.tpdata = pandas.read_csv(fp,sep=" ",header=None).to_numpy()


            

dsroot = "./wsdream-dataset"
a =ds(dsroot)


    
