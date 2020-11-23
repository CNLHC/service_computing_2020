import numpy as np
import os
import pandas
from abc import ABC
from nncp import NNCR
from umean import UMean
from imean import IMean

DS_ROOT = "./wsdream-dataset"


class ds:
    def __init__(self, dsroot: str):
        with open(os.path.join(dsroot, 'dataset2', 'rtdata.txt'), "r") as fp:
            raw_rtdata = pandas.read_csv(fp, sep=" ", header=None).to_numpy()
            self.rtdata = np.zeros((142, 4500, 64))
            for (uidx, sidx, tidx, val) in raw_rtdata:
                self.rtdata[int(uidx)][int(sidx)][int(tidx)] = val

            self.pcount = np.count_nonzero(self.rtdata)
            self.rtdata_missing = self.rtdata.size - self.pcount


a = ds(DS_ROOT)
print("load done")

print("NNCR")
NNCR.predict(a.rtdata, pcount=a.pcount)
print("UMean")
UMean.predict(a.rtdata, pcount=a.pcount)
print("IMean")
IMean.predict(a.rtdata, pcount=a.pcount)
