from abc import ABC, abstractclassmethod
import numpy as np


class Method(ABC):
    @classmethod
    def MAE(cls, data, pdata, pcount):
        MAE = np.sum(np.abs(data-pdata))/pcount
        return MAE

    @classmethod
    def RMSE(cls, data, pdata, pcount):
        RMSE = np.sqrt(np.sum(np.power(np.abs(data-pdata), 2))/pcount)
        return RMSE

    @abstractclassmethod
    def predict(cls, data, shape=(142, 4500, 64), pcount=1):
        pass
