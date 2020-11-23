from method import Method

import numpy as np


class UMean(Method):

    @classmethod
    def predict(cls, data, shape=(142, 4500, 64), pcount=1):
        out = data.copy()
        I, J, K = shape
        for u in range(out.shape[0]):
            data[u, :, :] = np.tile(np.array([np.mean(data[u, :, :])]), (J, K))
        MAE = cls.MAE(data, out, pcount)
        RMSE = cls.RMSE(data, out, pcount)
        print(MAE, RMSE)
        return (MAE, RMSE)
