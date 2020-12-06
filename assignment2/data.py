import numpy as np
import os
import pandas
from abc import ABC
from umean import UMean
from imean import IMean
from ipcc import IPCC
from upcc import UPCC
from nncp import NNCP

from pathlib import Path

DS_ROOT = Path("./wsdream-dataset")


class ds:
    def __init__(self, dsroot: str):
        with open(dsroot / 'dataset2' / 'rtdata.txt', "r") as fp:
            raw_rtdata = pandas.read_csv(fp, sep=" ", header=None).to_numpy()
            self.rtdata = np.zeros((142, 4500, 64))
            for (uidx, sidx, tidx, val) in raw_rtdata:
                self.rtdata[int(uidx)][int(sidx)][int(tidx)] = val

            self.pcount = np.count_nonzero(self.rtdata)
            self.rtdata_missing = self.rtdata.size - self.pcount
            self.mask = self.rtdata == 0

            self.mask = np.random.choice(
                [True, False], size=(142, 4500, 64), p=[0.2, 0.8]) | self.mask


a = ds(DS_ROOT)


print("NNCP")
NNCP.predict(a.rtdata, pcount=a.pcount)
print("UMean")
UMean.predict(a.rtdata, pcount=a.pcount, ds=a)
print("IMean")
IMean.predict(a.rtdata, pcount=a.pcount, ds=a)
print("UPCC")
UPCC.predict(a.rtdata, pcount=a.pcount, ds=a)
print("IPCC")
IPCC.predict(a.rtdata, pcount=a.pcount, ds=a)
