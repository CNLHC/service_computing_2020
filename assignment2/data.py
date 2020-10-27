import numpy as np
import os
import pandas
from abc import ABC

DS_ROOT = "./wsdream-dataset"



class NNCR():
    @classmethod
    def predict(cls,data,shape=(142,4500,64)):
        cls.rank = 10
        U=np.zeros((shape[0],cls.rank))
        S=np.zeros((shape[1],cls.rank))
        T=np.zeros((shape[2],cls.rank))
        

class ds:
    def __init__(self, dsroot: str):
        with open(os.path.join(dsroot, 'dataset2-small', 'rtdata.txt'), "r") as fp:
            raw_rtdata = pandas.read_csv(fp, sep=" ", header=None).to_numpy()
            self.rtdata = np.zeros((142, 4500, 64))
            for (uidx, sidx, tidx, val) in raw_rtdata:
                self.rtdata[int(uidx)][int(sidx)][int(tidx)] = val
            self.rtdata_missing = np.array(np.where(self.rtdata == 0))


a = ds(DS_ROOT)
rank = np.linalg.matrix_rank(a.rtdata)
print(rank, len(rank))
