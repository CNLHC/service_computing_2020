
import numpy as np
from abcmethod import Method


class IMean(Method):

    @classmethod
    def predict(cls, data, shape=(142, 4500, 64), pcount=1, ds=None):
        out = data.copy()
        out = data.copy()
        time_inv_data = cls.nonzero_mean(data, axis=2)
        imean = cls.nonzero_mean(time_inv_data, axis=0)
        t1 = np.tile(imean.reshape(1, 4500, 1), (142, 1, 64))
        t1[~ds.mask] = 0
        out[ds.mask] = 0
        out = out+t1

        MAE = cls.MAE(data, out, pcount)
        RMSE = cls.RMSE(data, out, pcount)
        print(MAE, RMSE)
        return (MAE, RMSE)
