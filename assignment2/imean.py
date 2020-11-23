from method import Method

import numpy as np


class IMean(Method):

    @classmethod
    def predict(cls, data, shape=(142, 4500, 64), pcount=1):
        out = data.copy()
        I, J, K = shape
        for j in range(J):
            data[:, j, :] = np.tile(np.array([np.mean(data[:, j, :])]), (I, K))
        MAE = cls.MAE(data, out, pcount)
        RMSE = cls.RMSE(data, out, pcount)
        print(MAE, RMSE)
        return (MAE, RMSE)
