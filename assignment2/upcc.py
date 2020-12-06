from abcmethod import Method
import numpy as np


class UPCC(Method):
    @classmethod
    def predict(cls, data, shape=(142, 4500, 64), pcount=1, ds=None):
        out = data.copy()
        time_inv_data = cls.nonzero_mean(data, axis=2)
        w = np.cov(time_inv_data)
        umean = cls.nonzero_mean(time_inv_data, axis=1)

        t_w = np.tile(np.reshape(w, (142, 142, 1)), (1, 1, 4500))
        t_data = np.tile(np.reshape(time_inv_data.copy(),
                                    (142, 1, 4500)), (1, 142, 1))
        t_umean = np.tile(np.reshape(umean, (142, 1, 1)), (1, 142, 4500))
        t_umean_a3 = np.tile(np.reshape(umean, (142, 1)), (1, 4500))

        t01 = np.sum((t_data - t_umean) * t_w, axis=0)
        t02 = np.sum(t_w, axis=0)
        t02[t01 == 0] = 1
        t1 = t01 / t02

        res = np.tile(np.reshape(t_umean_a3+(t1), (142, 4500, 1)), (1, 1, 64))
        out[ds.mask] = 0
        res[~ds.mask] = 0
        out = out+res

        MAE = cls.MAE(data, out, pcount)
        RMSE = cls.RMSE(data, out, pcount)
        print(MAE, RMSE)
        return (MAE, RMSE)
