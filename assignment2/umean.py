from abcmethod import Method

import numpy as np


class UMean(Method):

    @classmethod
    def predict(cls, data, shape=(142, 4500, 64), pcount=1, ds=None):
        out = data.copy()
        time_inv_data = cls.nonzero_mean(data, axis=2)
        umean = cls.nonzero_mean(time_inv_data, axis=1)
        t1 = np.tile(umean.reshape(142, 1, 1), (1, 4500, 64))

        t1[~ds.mask] = 0
        out[ds.mask] = 0
        out = out+t1

        MAE = cls.MAE(data, out, pcount)
        RMSE = cls.RMSE(data, out, pcount)
        print(MAE, RMSE)
        return (MAE, RMSE)
